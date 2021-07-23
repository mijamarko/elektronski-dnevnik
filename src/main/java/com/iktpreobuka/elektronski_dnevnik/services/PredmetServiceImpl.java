package com.iktpreobuka.elektronski_dnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;

@Service
public class PredmetServiceImpl implements PredmetService {
	
	@Autowired
	private PredmetRepository predmetRepository;

	@Override
	public PredmetEntity predmetPostoji(KorisnikDTO req) {
		if(!req.getPredmetId().equals(null)) {
			if(predmetRepository.existsById(req.getPredmetId())) {
				return predmetRepository.findById(req.getPredmetId()).get();
			}
		} else if(!req.getPredmetName().equals(null)) {
			if(!predmetRepository.findByNazivPredmeta(req.getPredmetName()).equals(null)) {
				return predmetRepository.findByNazivPredmeta(req.getPredmetName());
			}
		}
		return null;
	}

}
