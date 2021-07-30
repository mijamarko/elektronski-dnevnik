package com.iktpreobuka.elektronski_dnevnik.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EPolugodiste;
import com.iktpreobuka.elektronski_dnevnik.enums.ETip_Ocene;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.OcenaRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;

@Service
public class OcenaServiceImpl implements OcenaService {
	
	@Autowired
	private OcenaRepository ocenaRepository;
	
	@Autowired
	private NastavnikRepository nastavnikRepository;
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ServiceResponse dodajNovuOcenu(UcenikEntity ucenik, Integer predmetId, Integer nastavnikId,
			Double ocena, ETip_Ocene tipOcene, EPolugodiste polugodiste) {
		logger.info("Ulazi u metodu dodajNovuOcenu za ucenika sa id %d, za predmet %d od strane nastavnika %d", ucenik.getId(), predmetId, nastavnikId);
		OcenaEntity novaOcena = new OcenaEntity();
		novaOcena.setDatumDodele(new Date(System.currentTimeMillis()));
		novaOcena.setOcena(ocena);
		novaOcena.setPolugodiste(polugodiste);
		novaOcena.setTipOcene(tipOcene);
		novaOcena.setPolugodiste(polugodiste);
		novaOcena.setNastavnikKojiJeDaoOcenu((NastavnikEntity) nastavnikRepository.findById(nastavnikId).get());
		novaOcena.setPredmetIzKogJeOcena(predmetRepository.findById(predmetId).get());
		ocenaRepository.save(novaOcena);
		try {
			emailService.sendMessage(novaOcena);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Ocena %d uspesno dodata uceniko sa id %d, za predmet %d od strane nastavnika %d", novaOcena.getOcena() ,ucenik.getId(), predmetId, nastavnikId);
		return new ServiceResponse("Ocena uspesno dodata", HttpStatus.OK, novaOcena);
	}

	@Override
	public ServiceResponse izmeniOcenuIzPredmeta(UcenikEntity ucenik, Integer ocenaId, Double novaOcena) {
		logger.info("Ulazi u metodu izmeniOcenuIzPredmeta za ucenika sa id %d, za ocenu %d", ucenik.getId(), ocenaId);
		Optional<OcenaEntity> opocena = ocenaRepository.findById(ocenaId);
		if(opocena.isPresent()) {
			OcenaEntity ocena = opocena.get();
			if(ocena.getUcenik().equals(ucenik)) {
				if(novaOcena > ocena.getOcena()) {
					ocena.setOcena(novaOcena);
					ocena.setDatumIzmene(new Date(System.currentTimeMillis()));
					ocenaRepository.save(ocena);
					logger.info("Ocena sa id %d uspesno izmenjena na %d", ocenaId, novaOcena);
					return new ServiceResponse("Ocena uspesno izmenjena", HttpStatus.OK, ocena);
				}
				logger.warn("Ocena id %d je veca od nove ocene %d i nije izmenjena", ocenaId, novaOcena);
				return new ServiceResponse("O-4", "Izmenjena ocena mora biti veca od trenutne", HttpStatus.BAD_REQUEST);
			}
			logger.warn("Ocena %d ne pripada uceniku id %d", ocenaId, ucenik.getId());
			return new ServiceResponse("O-3", "Ocena ne pripada zadatom uceniku", HttpStatus.BAD_REQUEST);
		}
		logger.warn("Ocena sa id %d ne postoji", ocenaId);
		return new ServiceResponse("O-2", "Ocena ne postoji", HttpStatus.BAD_REQUEST);	
	}

	@Override
	public ServiceResponse izracunajProsecnuOcenuIzPredmetaUPolugodistu(UcenikEntity ucenik, Integer predmetId,
			EPolugodiste polugodiste, Integer nastavnikId) {
		logger.info("Ulazi u metodu izracunajProsecnuOcenuIzPredmetaUPolugodistu za ucenika sa id %d, za predmet %d i polugodiste %s", ucenik.getId(), predmetId, polugodiste.toString());
		ArrayList<OcenaEntity> ocene = new ArrayList<>();
		double prosek = 0.0;
		ocenaRepository.findByUcenik(ucenik).forEach(o -> {
			if(o.getPredmetIzKogJeOcena().getId() == predmetId && o.getPolugodiste() == polugodiste) {
				ocene.add(o);
			}
		});
		if(ocene.size() > 0) {
			for(int i = 0; i < ocene.size(); i++) {
				prosek += ocene.get(i).getOcena();
			}
			prosek /= ocene.size();
			logger.info("Ocena %d uspesno dodata uceniku sa id %d, za predmet %d od strane nastavnika %d", prosek ,ucenik.getId(), predmetId, nastavnikId);
			return dodajNovuOcenu(ucenik, predmetId, nastavnikId, prosek, ETip_Ocene.ZAKLJUCNA_OCENA_POLUGODISTE, polugodiste);
			
		}
		logger.warn("Ucenik %d nema ocene iz predmeta %d u polugodistu %s", ucenik.getId(), predmetId, polugodiste.toString());
		return new ServiceResponse("O-5", "Ucenik nema ocene iz zadatog predmeta u zadatom polugodistu", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izracunajProsecnuOcenuIzPredmetaUGodini(UcenikEntity ucenik, Integer predmetId, Integer nastavnikId) {
		logger.info("Ulazi u metodu izracunajProsecnuOcenuIzPredmetaUGodini za ucenika sa id %d, za predmet %d", ucenik.getId(), predmetId);
		ArrayList<OcenaEntity> ocene = new ArrayList<>();
		double prosek = 0.0;
		ocenaRepository.findByUcenik(ucenik).forEach(o -> {
			if(o.getPredmetIzKogJeOcena().getId() == predmetId && o.getTipOcene() == ETip_Ocene.ZAKLJUCNA_OCENA_POLUGODISTE) {
				ocene.add(o);
			}
		});
		if(ocene.size() == 2) {
			prosek = (ocene.get(0).getOcena() + ocene.get(1).getOcena())/2;
			logger.info("Ocena %d uspesno dodata uceniku sa id %d, za predmet %d od strane nastavnika %d", prosek ,ucenik.getId(), predmetId, nastavnikId);
			return dodajNovuOcenu(ucenik, predmetId, nastavnikId, prosek, ETip_Ocene.ZAKLJUCNA_OCENA_GODINA, EPolugodiste.DRUGO);
		}
		logger.warn("Ucenik %d nema zakljucenu ocenu iz predmeta %d u jednom polugodistu", ucenik.getId(), predmetId);
		return new ServiceResponse("O-5", "Ucenik nema zakljucenu ocenu u jednom polugodistu", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izracunajUspehUcenikaUPolugodistu(UcenikEntity ucenik, EPolugodiste polugodiste) {
		logger.info("Ulazi u metodu izracunajUspehUcenikaUPolugodistu za ucenika sa id %d, za polugodiste %s", ucenik.getId(), polugodiste.toString());
		ArrayList<OcenaEntity> ocene = new ArrayList<>();
		double prosek = 0.0;
		ocenaRepository.findByUcenik(ucenik).forEach(o -> {
			if(o.getPolugodiste() == polugodiste) {
				ocene.add(o);
			}
		});
		if(ocene.size() > 0) {
			for(int i = 0; i < ocene.size(); i++) {
				prosek += ocene.get(i).getOcena();
			}
			prosek /= ocene.size();
			logger.info("Uspesno izracunata prosecna ocena uceniku %d u polugodistu %s", ucenik.getId(), polugodiste.toString());
			return new ServiceResponse("Prosecna ocena ucenika u zadatom polugodistu", HttpStatus.OK, prosek);
		}
		logger.warn("Ucenik %d nema oceneu polugodistu %s", ucenik.getId(), polugodiste.toString());
		return new ServiceResponse("O-6", "Ucenik nema ocene u zadatom polugodistu", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izracunajUspehUcenikaUGodini(UcenikEntity ucenik) {
		logger.info("Ulazi u metodu izracunajUspehUcenikaUGodini za ucenika sa id %d", ucenik.getId());
		ArrayList<OcenaEntity> ocene = new ArrayList<>();
		double prosek = 0.0;
		ocenaRepository.findByUcenik(ucenik).forEach(o ->ocene.add(o));
		if(ocene.size() > 0) {
			for(int i = 0; i < ocene.size(); i++) {
				prosek += ocene.get(i).getOcena();
			}
			prosek /= ocene.size();
			logger.info("Uspesno izracunata prosecna ocena uceniku %d", ucenik.getId());
			return new ServiceResponse("Prosecna ocena ucenika u godini", HttpStatus.OK, prosek);
		}
		logger.warn("Ucenik %d nema ocena", ucenik.getId());
		return new ServiceResponse("O-6", "Ucenik nema ocene godini", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izracunajIzlaznostNaOdredjeniZadatak(OdeljenjeEntity odeljenje, Date datumZadatka,
			ETip_Ocene tipZadatka) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse izracunajProsekOdeljenjaNaOdredjenomZadatku(OdeljenjeEntity odeljenje, Date datumZadatka,
			ETip_Ocene tipZadatka) {
		// TODO Auto-generated method stub
		return null;
	}
}
