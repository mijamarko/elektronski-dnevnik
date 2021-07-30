package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakIzmenaDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanciDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.IzostanakEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoditeljEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;
import com.iktpreobuka.elektronski_dnevnik.enums.EPolugodiste;
import com.iktpreobuka.elektronski_dnevnik.enums.ETip_Ocene;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;

@Service
public class UcenikServiceImpl implements UcenikService {

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	@Autowired
	private OcenaServiceImpl ocenaService;

	@Autowired
	private IzostanakServiceImpl izostanakService;
	
	@Autowired
	private OdeljenjeServiceImpl odeljenjeService;

	@Override
	public ServiceResponse dobaviSveUcenike() {
		ArrayList<UcenikEntity> ucenici = new ArrayList<UcenikEntity>();
		ucenikRepository.findAll().forEach(n -> {
			ucenici.add((UcenikEntity) n);
		});
		if (ucenici.size() > 0) {
			return new ServiceResponse("Pronadjeni ucenicic", HttpStatus.OK, ucenici);
		}
		return new ServiceResponse("U-1", "Nema ucenika", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviUcenikaPoId(Integer ucenikId) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			return new ServiceResponse("Trazeni ucenik", HttpStatus.OK, ucenik);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviOdeljenjeKojeUcenikPohadja(Integer ucenikId) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			OdeljenjeEntity odeljenje = ucenik.getOdeljenjeKojePohadja();
			if (odeljenje != null) {
				return new ServiceResponse("Odeljenje trazenog ucenika", HttpStatus.OK, odeljenje);
			}
			return new ServiceResponse("U-4", "Ucenik ne pripada nijednom odeljenju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSveIzostankeUcenika(Integer ucenikId) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
			ucenik.getIzostanci().forEach(i -> izostanci.add(i));
			if (izostanci.size() > 0) {
				return new ServiceResponse("Izostanci za trazenog ucenika", HttpStatus.OK, izostanci);
			}
			return new ServiceResponse("U-5", "Ucenik trenutno nema izostanaka", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSveIzostankeUcenikaPoTipu(Integer ucenikId, EIzostanak tipIzostanka) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
			ucenik.getIzostanci().forEach(i -> {
				if (i.getTipIzostanka() == tipIzostanka) {
					izostanci.add(i);
				}
			});
			if (izostanci.size() > 0) {
				return new ServiceResponse("Izostanci za trazenog ucenika", HttpStatus.OK, izostanci);
			}
			return new ServiceResponse("U-6", "Ucenik trenutno nema izostanaka zadatog tipa", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSveIzostankeUVremenskomPeriodu(Integer ucenikId, IzostanakIzmenaDTO trazeniIzostanci) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			return izostanakService.dobaviSveIzostankeUVremenskomPerioduZaUcenika(ucenik,trazeniIzostanci);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviTipIzostankaUVremenskomPerioduZaUcenika(Integer ucenikId, IzostanakIzmenaDTO trazeniIzostanci) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			return izostanakService.dobaviTipIzostankaUVremenskomPerioduZaUcenika(ucenik, trazeniIzostanci);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviRoditeljeUcenika(Integer ucenikId) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			ArrayList<RoditeljEntity> roditelji = new ArrayList<RoditeljEntity>();
			ucenik.getRoditelji().forEach(r -> roditelji.add(r));
			if (roditelji.size() > 0) {
				return new ServiceResponse("Roditelj(i) trazenog ucenika", HttpStatus.OK, roditelji);
			}
			return new ServiceResponse("U-9", "Ucenik nema dodate roditelje", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSveOceneUcenika(Integer ucenikId) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			ArrayList<OcenaEntity> ocene = new ArrayList<OcenaEntity>();
			ucenik.getOcene().forEach(o -> ocene.add(o));
			if (ocene.size() > 0) {
				return new ServiceResponse("Ocene trazenog ucenika", HttpStatus.OK, ocene);
			}
			return new ServiceResponse("U-10", "Ucenik nema upisanih ocena", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSveOceneIzJednogPredmeta(Integer ucenikId, Integer predmetId) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			ArrayList<OcenaEntity> ocene = new ArrayList<OcenaEntity>();
			ucenik.getOcene().forEach(o -> {
				if (o.getPredmetIzKogJeOcena().getId() == predmetId) {
					ocene.add(o);
				}
			});
			if (ocene.size() > 0) {
				return new ServiceResponse("Ocene trazenog ucenika za trazeni predmet", HttpStatus.OK, ocene);
			}
			return new ServiceResponse("U-11", "Ucenik nema upisanih ocena iz zadatog predmeta",
					HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izmeniOdeljenjeKojePohadja(Integer ucenikId, Integer odeljenjeId) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			OdeljenjeEntity trenutnoOdeljenje = ucenik.getOdeljenjeKojePohadja();
			if (trenutnoOdeljenje != null) {
				Optional<OdeljenjeEntity> novoOdeljenje = odeljenjeRepository.findById(odeljenjeId);
				if (novoOdeljenje.isPresent()) {
					ServiceResponse obrisiResponse = odeljenjeService.obrisiUcenikaIzOdeljenja(odeljenjeId, ucenikId);
					if(obrisiResponse.getHttpStatus() == HttpStatus.BAD_REQUEST) {
						return obrisiResponse;
					}
					ServiceResponse dodajResponse = odeljenjeService.dodajUcenikaUOdeljenje(novoOdeljenje.get().getId(), ucenikId);
					if(dodajResponse.getHttpStatus() == HttpStatus.BAD_REQUEST) {
						return dodajResponse;
					}
					ucenik.setOdeljenjeKojePohadja(novoOdeljenje.get());
					ucenikRepository.save(ucenik);
					return new ServiceResponse("Odeljenje uspesno promenjeno", HttpStatus.OK, ucenik);
				}
				return new ServiceResponse("O-2", "Odeljenje ne postoji", HttpStatus.BAD_REQUEST);
			}
			return new ServiceResponse("U-4", "Ucenik ne pripada nijednom odeljenju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izmeniIzostankeUVremenskomPeriodu(Integer ucenikId, IzostanakIzmenaDTO noviPodaci) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			return izostanakService.izmeniIzostankeUVremenskomPerioduZaUcenika(ucenik, noviPodaci);
			
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izmeniOcenuIzPredmeta(Integer ucenikId, Integer ocenaId, Double novaOcena) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			ServiceResponse response = ocenaService.izmeniOcenuIzPredmeta(ucenik, ocenaId, novaOcena);
			ucenik.getOcene().add((OcenaEntity) response.getValue());
			ucenikRepository.save(ucenik);
			return response;
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajProsecnuOcenuIzPredmetaUPolugodistu(Integer ucenikId, Integer predmetId,
			EPolugodiste polugodiste, Integer nastavnikId) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			ServiceResponse response = ocenaService.izracunajProsecnuOcenuIzPredmetaUPolugodistu(ucenik, predmetId,
					polugodiste, nastavnikId);
			ucenik.getOcene().add((OcenaEntity) response.getValue());
			ucenikRepository.save(ucenik);
			return response;
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajProsecnuOcenuIzPredmetaUGodini(Integer ucenikId, Integer predmetId,
			Integer nastavnikId) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			ServiceResponse response = ocenaService.izracunajProsecnuOcenuIzPredmetaUGodini(ucenik, predmetId,
					nastavnikId);
			ucenik.getOcene().add((OcenaEntity) response.getValue());
			ucenikRepository.save(ucenik);
			return response;
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izracunajUspehUcenikaUPolugodistu(Integer ucenikId, EPolugodiste polugodiste) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			return ocenaService.izracunajUspehUcenikaUPolugodistu(ucenik, polugodiste);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izracunajUspehUcenikaUGodini(Integer ucenikId) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			return ocenaService.izracunajUspehUcenikaUGodini(ucenik);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izmeniEmail(Integer ucenikId, EmailDTO noviPodaci) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			// TODO security
			if (ucenik.getEmail().equals(noviPodaci.getEmail())) {
				ucenik.setEmail(noviPodaci.getNoviEmail());
				ucenikRepository.save(ucenik);
				return new ServiceResponse("Email uspesno promenjen", HttpStatus.OK);
			}
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izmeniSifru(Integer ucenikId, SifraDTO noviPodaci) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			// TODO security
			if (ucenik.getEmail().equals(noviPodaci.getEmail())) {
				ucenik.setSifra(noviPodaci.getNovaSifra());
				ucenikRepository.save(ucenik);
				return new ServiceResponse("Sifra uspesno promenjena", HttpStatus.OK);
			}
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse napraviNovogUcenika(UcenikEntity ucenik) {
		if (ucenikRepository.findByEmail(ucenik.getEmail()) == null) {
			RoleEntity role = roleRepository.findByName("ROLE_UCENIK");
			ucenik.setRole(role);
			ucenikRepository.save(ucenik);
			role.getUsers().add(ucenik);
			roleRepository.save(role);
			return new ServiceResponse("Ucenik uspesno kreiran", HttpStatus.OK, ucenik);
		}
		return new ServiceResponse("U-3", "Ucenik vec postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajNovuOcenuIzOdredjenogPredmeta(Integer ucenikId, Integer predmetId, Integer nastavnikId,
			Double ocena, ETip_Ocene tipOcene, EPolugodiste polugodiste) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			ServiceResponse response = ocenaService.dodajNovuOcenu(ucenik, predmetId, nastavnikId, ocena, tipOcene,
					polugodiste);
			if (response.getHttpStatus() == HttpStatus.OK) {
				ucenik.getOcene().add((OcenaEntity) response.getValue());
				ucenikRepository.save(ucenik);
				return response;
			}
			return new ServiceResponse("O-4", "Doslo je do greske", response.getHttpStatus());
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajNoveIzostanke(Integer ucenikId, IzostanciDTO izostanci) {
		Optional<KorisnikEntity> korisnik = ucenikRepository.findById(ucenikId);
		if (korisnik.isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) korisnik.get();
			ServiceResponse response = izostanakService.dodajNoveIzostanke(ucenik, izostanci);
			ArrayList<IzostanakEntity> upisaniIzostanci = (ArrayList<IzostanakEntity>) response.getValue();
			upisaniIzostanci.forEach(i -> {
				ucenik.getIzostanci().add(i);
			});
			ucenikRepository.save(ucenik);
			return response;
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiUcenika(Integer ucenikId) {
		if (ucenikRepository.findById(ucenikId).isPresent()) {
			UcenikEntity ucenik = (UcenikEntity) ucenikRepository.findById(ucenikId).get();
			ucenikRepository.delete(ucenik);
			return new ServiceResponse("Ucenik  uspesno obrisan", HttpStatus.OK);
		}
		return new ServiceResponse("U-2", "Ucenik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

}
