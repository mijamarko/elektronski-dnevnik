package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RazredEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RazredRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;

@Service
public class OdeljenjeServiceImpl implements OdeljenjeService {

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private RazredRepository razredRepository;
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private NastavnikRepository nastavnikRepository;

	@Autowired
	private NastavnikServiceImpl nastavnikService;
	
	@Autowired
	private RazredServiceImpl razredService;

	@Override
	public ServiceResponse dobaviSvaOdeljenja() {
		ArrayList<OdeljenjeEntity> odeljenja = new ArrayList<OdeljenjeEntity>();
		odeljenjeRepository.findAll().forEach(o -> odeljenja.add(o));
		if (odeljenja.size() > 0) {
			return new ServiceResponse("Trazena odeljenja", HttpStatus.OK, odeljenja);
		}
		return new ServiceResponse("O-1", "Nema odeljenja", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviOdeljenjePoId(Integer odeljenjeId) {
		Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
		if (odeljenje.isPresent()) {
			return new ServiceResponse("Trazeno odeljenje", HttpStatus.OK, odeljenje.get());
		}
		return new ServiceResponse("O-2", "Trazeno odeljenje ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSveUcenikeJednogOdeljenja(Integer odeljenjeId) {
		Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
		if (odeljenje.isPresent()) {
			ArrayList<UcenikEntity> ucenici = new ArrayList<UcenikEntity>();
			odeljenje.get().getUcenici().forEach(u -> ucenici.add(u));
			if (ucenici.size() > 0) {
				return new ServiceResponse("Ucenici iz trazenog odeljenja", HttpStatus.OK, ucenici);
			}
			return new ServiceResponse("O-3", "Zadato odeljenje nema ucenika", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("O-2", "Trazeno odeljenje ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviRazrednogStaresinuOdeljenja(Integer odeljenjeId) {
		Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
		if (odeljenje.isPresent()) {
			if (odeljenje.get().getRazredniStaresina() != null) {
				NastavnikEntity razredni = odeljenje.get().getRazredniStaresina();
				return new ServiceResponse("Razredni staresina trazenog odeljenja", HttpStatus.OK, razredni);
			}
			return new ServiceResponse("O-4", "Odeljenje nema razrednog staresinu", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("O-2", "Trazeno odeljenje ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSvePredmeteKojeOdeljenjeSlusa(Integer odeljenjeId) {
		Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
		if (odeljenje.isPresent()) {
			ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
			odeljenje.get().getPredmetiKojeOdeljenjeSlusa().forEach(p -> predmeti.add(p));
			if (predmeti.size() > 0) {
				return new ServiceResponse("Predmeti koje trazeno odeljenje slusa", HttpStatus.OK, predmeti);
			}
			return new ServiceResponse("O-5", "Trazeno odeljenje trenutno ne slusa nijedan predmet",
					HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("O-2", "Trazeno odeljenje ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviRazredKomOdeljenjePripada(Integer odeljenjeId) {
		Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
		if (odeljenje.isPresent()) {
			RazredEntity razred = odeljenje.get().getRazred();
			if (razred != null) {
				return new ServiceResponse("Razred trazenog odeljenja", HttpStatus.OK, razred);
			}
			return new ServiceResponse("O-6", "Trazeno odeljenje trenutno ne pripada razredu", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("O-2", "Trazeno odeljenje ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izmeniRazredKomOdeljenjePripada(Integer odeljenjeId, Integer razredId) {
		ServiceResponse response = dobaviRazredKomOdeljenjePripada(odeljenjeId);
		if (response.getValue() instanceof RazredEntity) {
			RazredEntity trenutniRazred = (RazredEntity) response.getValue();
			OdeljenjeEntity odeljenje = odeljenjeRepository.findById(odeljenjeId).get();
			razredService.obrisiOdeljenjeKojePripadaRazredu(trenutniRazred.getId(), odeljenjeId);
			ServiceResponse dodajResponse = razredService.dodajNovoOdeljenjeRazredu(razredId, odeljenjeId);
			odeljenje.setRazred((RazredEntity) dodajResponse.getValue());
			return new ServiceResponse("Razred uspesno izmenjen za trazeno odeljenje", HttpStatus.OK);
		}
		return response;
	}

	@Override
	public ServiceResponse izmeniRazrednogStaresinuOdeljenja(Integer odeljenjeId, Integer nastavnikId) {
		ServiceResponse response = dobaviRazrednogStaresinuOdeljenja(odeljenjeId);
		if (response.getValue() instanceof NastavnikEntity) {
			if (nastavnikService.dobaviNastavnikaPoId(nastavnikId).getValue() instanceof NastavnikEntity) {
				Optional<OdeljenjeEntity> opodeljenje = odeljenjeRepository.findById(odeljenjeId);
				if (opodeljenje.isPresent()) {
					OdeljenjeEntity odeljenje = opodeljenje.get();
					odeljenje.setRazredniStaresina(
							(NastavnikEntity) nastavnikService.dobaviNastavnikaPoId(nastavnikId).getValue());
					odeljenjeRepository.save(odeljenje);
					nastavnikService.dodajOdeljenjeNastavniku(nastavnikId, odeljenjeId);
					return new ServiceResponse("Razredni uspesno izmenjen zadatom odeljenju", HttpStatus.OK, odeljenje);
				}
				return new ServiceResponse("O-2", "Trazeno odeljenje ne postoji", HttpStatus.BAD_REQUEST);
			}
			return new ServiceResponse("N-2", "Trazeni korisnik nije nastavnik", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@Override
	public ServiceResponse napraviNovoOdeljenje(OdeljenjeEntity odeljenje, Integer razredId) {
		odeljenjeRepository.save(odeljenje);
		ServiceResponse response = dobaviSvaOdeljenja();
		ArrayList<OdeljenjeEntity> sacuvanoOdeljenje = new ArrayList<OdeljenjeEntity>();
		if (response.getValue() instanceof ArrayList<?>) {
			ArrayList<OdeljenjeEntity> odeljenja = (ArrayList<OdeljenjeEntity>) response.getValue();
			odeljenja.forEach(o -> {
				if (o.getRazred() == null) {
					sacuvanoOdeljenje.add(o);
				}
			});
			if (dodajRazredKomOdeljenjePripada(odeljenja.get(0).getId(), razredId).getHttpStatus() == HttpStatus.OK) {
				return new ServiceResponse("Odeljenje uspesno kreirano", HttpStatus.OK);
			}
			odeljenjeRepository.delete(odeljenje);
		}
		return new ServiceResponse("O-5",
				"Odeljenje neuspesno kreirano. Ili vec postoji u zadatom razredu ili razred ne postoji",
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajRazredKomOdeljenjePripada(Integer odeljenjeId, Integer razredId) {
		Optional<RazredEntity> oprazred = razredRepository.findById(razredId);
		if (oprazred.isPresent()) {
			RazredEntity razred = oprazred.get();
			ArrayList<OdeljenjeEntity> odeljenja = (ArrayList<OdeljenjeEntity>) razred.getOdeljenja();
			razred.getOdeljenja().forEach(o -> {
				if (o.getId() == odeljenjeId) {
					odeljenja.add(o);
				}
			});
			if (odeljenja.size() == 0) {
				razred.getOdeljenja().add(odeljenjeRepository.findById(odeljenjeId).get());
				odeljenjeRepository.save(odeljenjeRepository.findById(odeljenjeId).get());
				razredService.dodajNovoOdeljenjeRazredu(razredId, odeljenjeId);
				return new ServiceResponse("Razred uspesno dodat odeljenju", HttpStatus.OK);
			}
			return new ServiceResponse("O-6", "Odeljenje vec pripada razredu", HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	@Override
	public ServiceResponse dodajUcenikaUOdeljenje(Integer odeljenjeId, Integer ucenikId) {
		ServiceResponse response = dobaviOdeljenjePoId(odeljenjeId);
		if(response.getHttpStatus() == HttpStatus.OK) {
			OdeljenjeEntity odeljenje = (OdeljenjeEntity) response.getValue();
			ArrayList<UcenikEntity> ucenici = new ArrayList<UcenikEntity>();
			odeljenje.getUcenici().forEach(u -> {
				if(u.getId() == ucenikId) {
					ucenici.add(u);
				}
			});
			if(ucenici.size() == 0) {
				odeljenje.getUcenici().add((UcenikEntity) ucenikRepository.findById(ucenikId).get());
				odeljenjeRepository.save(odeljenje);
				return new ServiceResponse("Ucenik uspesno dodat u odeljenje", HttpStatus.OK);
			}
			return new ServiceResponse("O-7", "Ucenik vec pripada odeljenju", HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	@Override
	public ServiceResponse dodajPredmetKojiOdeljenjeSlusa(Integer odeljenjeId, Integer predmetId) {
		ServiceResponse response = dobaviOdeljenjePoId(odeljenjeId);
		if(response.getHttpStatus() == HttpStatus.OK) {
			OdeljenjeEntity odeljenje = (OdeljenjeEntity) response.getValue();
			ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
			odeljenje.getPredmetiKojeOdeljenjeSlusa().forEach(p -> {
				if(p.getId() == predmetId) {
					predmeti.add(p);
				}
			});
			if(predmeti.size() == 0) {
				odeljenje.getPredmetiKojeOdeljenjeSlusa().add(predmetRepository.findById(predmetId).get());
				odeljenjeRepository.save(odeljenje);
				return new ServiceResponse("Predmet uspesno dodat odeljenju", HttpStatus.OK);
			}
			return new ServiceResponse("O-8", "Odeljenje vec slusa predmet", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@Override
	public ServiceResponse dodajRazrednogStaresinuOdeljenju(Integer odeljenjeId, Integer nastavnikId) {
		ServiceResponse response = dobaviOdeljenjePoId(odeljenjeId);
		if(response.getHttpStatus() == HttpStatus.OK) {
			OdeljenjeEntity odeljenje = (OdeljenjeEntity) response.getValue();
			if(odeljenje.getRazredniStaresina() == null) {
				odeljenje.setRazredniStaresina((NastavnikEntity) nastavnikRepository.findById(nastavnikId).get());
				odeljenjeRepository.save(odeljenje);
				return new ServiceResponse("Razredni staresina uspesno dodat odeljenju", HttpStatus.OK, odeljenje);
			}
			return new ServiceResponse("O-9", "Odeljenje vec ima razrednog staresinu", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@Override
	public ServiceResponse obrisiPredmetKojiOdeljenjeSlusa(Integer odeljenjeId, Integer predmetId) {
		ServiceResponse response = dobaviOdeljenjePoId(odeljenjeId);
		if(response.getHttpStatus() == HttpStatus.OK) {
			OdeljenjeEntity odeljenje = (OdeljenjeEntity) response.getValue();
			odeljenje.getPredmetiKojeOdeljenjeSlusa().forEach(p -> {
				if(p.getId() == predmetId) {
					odeljenje.getPredmetiKojeOdeljenjeSlusa().remove(p);
				}
			});
			odeljenjeRepository.save(odeljenje);
			return new ServiceResponse("Predmet uspesno obrisan odeljenju", HttpStatus.OK);
		}
		return response;
	}

	@Override
	public ServiceResponse obrisiUcenikaIzOdeljenja(Integer odeljenjeId, Integer ucenikId) {
		ServiceResponse response = dobaviOdeljenjePoId(odeljenjeId);
		if(response.getHttpStatus() == HttpStatus.OK) {
			OdeljenjeEntity odeljenje = (OdeljenjeEntity) response.getValue();
			odeljenje.getUcenici().removeIf(u -> u.getId() == ucenikId);
			return new ServiceResponse("Ucenik uspesno obrisan iz odeljenja", HttpStatus.OK);
		}
		return response;
	}

}
