package com.iktpreobuka.elektronski_dnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.OdeljenjeServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;

@Service
public class OdeljenjeServiceImpl implements OdeljenjeService {
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	@Override
	public OdeljenjeServiceResponse odeljenjePostoji(KorisnikDTO req) {
		if(req.getOdeljenjeId().equals(null)) {
			return new OdeljenjeServiceResponse(HttpStatus.BAD_REQUEST, "G-1", "Lose formiran zahtev");
		}
		if(odeljenjeRepository.existsById(req.getOdeljenjeId())) {
			return new OdeljenjeServiceResponse(HttpStatus.OK, "Odeljenje postoji", odeljenjeRepository.findById(req.getOdeljenjeId()).get());
		}
		return new OdeljenjeServiceResponse(HttpStatus.BAD_REQUEST, "O-1", "Odeljenje ne postoji");
	}
	
	

}
