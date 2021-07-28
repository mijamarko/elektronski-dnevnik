package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RazredEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;

@Service
public class OdeljenjeServiceImpl implements OdeljenjeService{
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private NastavnikServiceImpl nastavnikService;

	@Override
	public ServiceResponse dobaviSvaOdeljenja() {
		ArrayList<OdeljenjeEntity> odeljenja = new ArrayList<OdeljenjeEntity>();
		odeljenjeRepository.findAll().forEach(o -> odeljenja.add(o));
		if(odeljenja.size() > 0) {
			return new ServiceResponse("Trazena odeljenja", HttpStatus.OK, odeljenja);
		}
		return new ServiceResponse("O-1", "Nema odeljenja", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviOdeljenjePoId(Integer odeljenjeId) {
		Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
		if(odeljenje.isPresent()) {
			return new ServiceResponse("Trazeno odeljenje", HttpStatus.OK, odeljenje.get());
		}
		return new ServiceResponse("O-2", "Trazeno odeljenje ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSveUcenikeJednogOdeljenja(Integer odeljenjeId) {
		Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
		if(odeljenje.isPresent()) {
			ArrayList<UcenikEntity> ucenici = new ArrayList<UcenikEntity>();
			odeljenje.get().getUcenici().forEach(u -> ucenici.add(u));
			if(ucenici.size() > 0) {
				return new ServiceResponse("Ucenici iz trazenog odeljenja", HttpStatus.OK, ucenici);
			}
			return new ServiceResponse("O-3", "Zadato odeljenje nema ucenika", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("O-2", "Trazeno odeljenje ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviRazrednogStaresinuOdeljenja(Integer odeljenjeId) {
		Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
		if(odeljenje.isPresent()) {
			if(odeljenje.get().getRazredniStaresina() != null) {
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
		if(odeljenje.isPresent()) {
			ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
			odeljenje.get().getPredmetiKojeOdeljenjeSlusa().forEach(p -> predmeti.add(p));
			if(predmeti.size() > 0) {
				return new ServiceResponse("Predmeti koje trazeno odeljenje slusa", HttpStatus.OK, predmeti);
			}
			return new ServiceResponse("O-5", "Trazeno odeljenje trenutno ne slusa nijedan predmet", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("O-2", "Trazeno odeljenje ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviRazredKomOdeljenjePripada(Integer odeljenjeId) {
		Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
		if(odeljenje.isPresent()) {
			RazredEntity razred = odeljenje.get().getRazred();
			if(razred != null) {
				return new ServiceResponse("Razred trazenog odeljenja", HttpStatus.OK, razred);
			}
			return new ServiceResponse("O-6", "Trazeno odeljenje trenutno ne pripada razredu", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("O-2", "Trazeno odeljenje ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izmeniRazredKomOdeljenjePripada(Integer odeljenjeId, Integer razredId) {
		ServiceResponse response = dobaviRazredKomOdeljenjePripada(odeljenjeId);
		if(response.getValue() instanceof RazredEntity) {
			OdeljenjeEntity odeljenje = odeljenjeRepository.findById(odeljenjeId).get();
			odeljenje.setRazred((RazredEntity) response.getValue());
			odeljenjeRepository.save(odeljenje);
			//razredService.izmeniOdeljenjeKojePripadaRazredu(Integer razredId, Integer odeljenjeId);
			return new ServiceResponse("Razred uspesno izmenjen za trazeno odeljenje", HttpStatus.OK);
		}
		return response;
	}

	@Override
	public ServiceResponse izmeniRazrednogStaresinuOdeljenja(Integer odeljenjeId, Integer nastavnikId) {
		ServiceResponse response = dobaviRazrednogStaresinuOdeljenja(odeljenjeId);
		if(response.getValue() instanceof NastavnikEntity) {
			if(nastavnikService.dobaviNastavnikaPoId(nastavnikId).getValue() instanceof NastavnikEntity) {
				Optional<OdeljenjeEntity> opodeljenje = odeljenjeRepository.findById(odeljenjeId);
				if(opodeljenje.isPresent()) {
					OdeljenjeEntity odeljenje = opodeljenje.get();
					odeljenje.setRazredniStaresina((NastavnikEntity) nastavnikService.dobaviNastavnikaPoId(nastavnikId).getValue());
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
		
		return null;
	}

	@Override
	public ServiceResponse dodajRazredKomOdeljenjePripada(Integer odeljenjeId, Integer razredId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dodajUcenikaUOdeljenje(Integer odeljenjeId, Integer ucenikId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dodajPredmetKojiOdeljenjeSlusa(Integer odeljenjeId, Integer predmetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dodajRazrednogStaresinuOdeljenju(Integer odeljenjeId, Integer nastavnikId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse obrisiPredmetKojiOdeljenjeSlusa(Integer odeljenjeId, Integer predmetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse obrisiUcenikaIzOdeljenja(Integer odeljenjeId, Integer ucenikId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
