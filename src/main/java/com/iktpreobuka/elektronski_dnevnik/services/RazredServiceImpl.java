package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RazredEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RazredRepository;

@Service
public class RazredServiceImpl implements RazredService {
	
	@Autowired
	private RazredRepository razredRepository;
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	@Override
	public ServiceResponse dobaviSveRazrede() {
		ArrayList<RazredEntity> razredi = new ArrayList<RazredEntity>();
		razredRepository.findAll().forEach(r -> razredi.add(r));
		if(razredi.size() > 0) {
			return new ServiceResponse("Svi razredi", HttpStatus.OK, razredi);
		}
		return new ServiceResponse("R-1", "Nema razreda", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviRazredPoId(Integer razredId) {
		Optional<RazredEntity> razred = razredRepository.findById(razredId);
		if(razred.isPresent()) {
			return new ServiceResponse("Trazeni razred", HttpStatus.OK, razred.get());
		}
		return new ServiceResponse("R-2", "Trazeni razred ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSvaOdeljenjaRazreda(Integer razredId) {
		ServiceResponse response = dobaviRazredPoId(razredId);
		if(response.getHttpStatus() == HttpStatus.OK) {
			ArrayList<OdeljenjeEntity> odeljenja = new ArrayList<OdeljenjeEntity>();
			RazredEntity razred = (RazredEntity) response.getValue();
			razred.getOdeljenja().forEach(o -> odeljenja.add(o));
			if(odeljenja.size() > 0) {
				return new ServiceResponse("Odeljenja trazenog razreda", HttpStatus.OK, odeljenja);
			}
			return new ServiceResponse("R-3", "Trazeni razred nema odeljenja", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@Override
	public ServiceResponse napraviNoviRazred(RazredEntity razred) {
		if(razredRepository.findByBrojRazreda(razred.getBrojRazreda()) == null) {
			razredRepository.save(razred);
			return new ServiceResponse("Razred uspesno kreiran", HttpStatus.OK, razred);
		}
		return new ServiceResponse("R-4", "Razred sa zadatim brojem vec postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajNovoOdeljenjeRazredu(Integer razredId, Integer odeljenjeId) {
		ServiceResponse response = dobaviRazredPoId(razredId);
		if(response.getHttpStatus() == HttpStatus.OK) {
			RazredEntity razred = (RazredEntity) response.getValue();
			OdeljenjeEntity novoOdeljenje = odeljenjeRepository.findById(odeljenjeId).get();
			if(!razred.getOdeljenja().contains(novoOdeljenje)) {
				razred.getOdeljenja().add(novoOdeljenje);
				razredRepository.save(razred);
			}
			return new ServiceResponse("R-5", "Odeljenje vec pripada zadatom razredu", HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@Override
	public ServiceResponse obrisiRazred(Integer razredId) {
		ServiceResponse response = dobaviRazredPoId(razredId);
		if(response.getHttpStatus() == HttpStatus.OK) {
			razredRepository.delete((RazredEntity) response.getValue());
			return new ServiceResponse("Razred uspesno obrisan", HttpStatus.OK);
		}
		return response;
	}

	@Override
	public ServiceResponse obrisiOdeljenjeKojePripadaRazredu(Integer razredId, Integer odeljenjeId) {
		ServiceResponse response = dobaviRazredPoId(razredId);
		if(response.getHttpStatus() == HttpStatus.OK) {
			RazredEntity razred = (RazredEntity) response.getValue();
			OdeljenjeEntity odeljenje = odeljenjeRepository.findById(odeljenjeId).get();
			if(razred.getOdeljenja().contains(odeljenje)) {
				razred.getOdeljenja().remove(odeljenje);
				razredRepository.save(razred);
				return new ServiceResponse("Odeljenje uspesno obrisano", HttpStatus.OK);
			}
			return new ServiceResponse("R-6", "Odeljenje ne pripada zadatom razredu", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	

}
