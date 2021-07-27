package com.iktpreobuka.elektronski_dnevnik.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.relationships.NastavnikPredajePredmet;
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
	private UcenikServiceImpl ucenikService;

	@Override
	public ServiceResponse dodajNovuOcenu(UcenikEntity ucenik, Integer predmetId, Integer nastavnikId,
			Double ocena, ETip_Ocene tipOcene, EPolugodiste polugodiste) {
		OcenaEntity novaOcena = new OcenaEntity();
		novaOcena.setDatumDodele(new Date(System.currentTimeMillis()));
		novaOcena.setOcena(ocena);
		novaOcena.setPolugodiste(polugodiste);
		novaOcena.setTipOcene(tipOcene);
		novaOcena.setPolugodiste(polugodiste);
		novaOcena.setNastavnikPredmet(new NastavnikPredajePredmet((NastavnikEntity) nastavnikRepository.findById(nastavnikId).get(), predmetRepository.findById(predmetId).get()));
		ocenaRepository.save(novaOcena);
		return new ServiceResponse("Ocena uspesno dodata", HttpStatus.OK, novaOcena);
	}

	@Override
	public ServiceResponse izmeniOcenuIzPredmeta(UcenikEntity ucenik, Integer ocenaId, Double novaOcena) {
		Optional<OcenaEntity> opocena = ocenaRepository.findById(ocenaId);
		if(opocena.isPresent()) {
			OcenaEntity ocena = opocena.get();
			if(ocena.getUcenik().equals(ucenik)) {
				if(novaOcena > ocena.getOcena()) {
					ocena.setOcena(novaOcena);
					ocena.setDatumIzmene(new Date(System.currentTimeMillis()));
					ocenaRepository.save(ocena);
					return new ServiceResponse("Ocena uspesno izmenjena", HttpStatus.OK, ocena);
				}
				return new ServiceResponse("O-4", "Izmenjena ocena mora biti veca od trenutne", HttpStatus.BAD_REQUEST);
			}
			return new ServiceResponse("O-3", "Ocena ne pripada zadatom uceniku", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("O-2", "Ocena ne postoji", HttpStatus.BAD_REQUEST);	
	}

	@Override
	public ServiceResponse izracunajProsecnuOcenuIzPredmetaUPolugodistu(UcenikEntity ucenik, Integer predmetId,
			EPolugodiste polugodiste, Integer nastavnikId) {
		ArrayList<OcenaEntity> ocene = new ArrayList<>();
		double prosek = 0.0;
		ocenaRepository.findByUcenik(ucenik).forEach(o -> {
			if(o.getNastavnikPredmet().getPredmet().getId() == predmetId && o.getPolugodiste() == polugodiste) {
				ocene.add(o);
			}
		});
		if(ocene.size() > 0) {
			for(int i = 0; i < ocene.size(); i++) {
				prosek += ocene.get(i).getOcena();
			}
			prosek /= ocene.size();
			return dodajNovuOcenu(ucenik, predmetId, nastavnikId, prosek, ETip_Ocene.ZAKLJUCNA_OCENA_POLUGODISTE, polugodiste);
			
		}
		return new ServiceResponse("O-5", "Ucenik nema ocene iz zadatog predmeta u zadatom polugodistu", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izracunajProsecnuOcenuIzPredmetaUGodini(UcenikEntity ucenik, Integer predmetId, Integer nastavnikId) {
		ArrayList<OcenaEntity> ocene = new ArrayList<>();
		double prosek = 0.0;
		ocenaRepository.findByUcenik(ucenik).forEach(o -> {
			if(o.getNastavnikPredmet().getPredmet().getId() == predmetId && o.getTipOcene() == ETip_Ocene.ZAKLJUCNA_OCENA_POLUGODISTE) {
				ocene.add(o);
			}
		});
		if(ocene.size() == 2) {
			prosek = (ocene.get(0).getOcena() + ocene.get(1).getOcena())/2;
			return dodajNovuOcenu(ucenik, predmetId, nastavnikId, prosek, ETip_Ocene.ZAKLJUCNA_OCENA_GODINA, EPolugodiste.DRUGO);
		}
		return new ServiceResponse("O-5", "Ucenik nema zakljucenu ocenu u jednom polugodistu", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izracunajUspehUcenikaUPolugodistu(UcenikEntity ucenik, EPolugodiste polugodiste) {
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
			return new ServiceResponse("Prosecna ocena ucenika u zadatom polugodistu", HttpStatus.OK, prosek);
		}
		return new ServiceResponse("O-6", "Ucenik nema ocene u zadatom polugodistu", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izracunajUspehUcenikaUGodini(UcenikEntity ucenik) {
		ArrayList<OcenaEntity> ocene = new ArrayList<>();
		double prosek = 0.0;
		ocenaRepository.findByUcenik(ucenik).forEach(o ->ocene.add(o));
		if(ocene.size() > 0) {
			for(int i = 0; i < ocene.size(); i++) {
				prosek += ocene.get(i).getOcena();
			}
			prosek /= ocene.size();
			return new ServiceResponse("Prosecna ocena ucenika u godini", HttpStatus.OK, prosek);
		}
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
