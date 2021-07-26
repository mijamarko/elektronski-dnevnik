package com.iktpreobuka.elektronski_dnevnik.services;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.OcenaRepository;

@Service
public class OcenaServiceImpl implements OcenaService {
	
	@Autowired
	private OcenaRepository ocenaRepository;

	@Override
	public ServiceResponse dodajNovuOcenu(UcenikEntity ucenik, OcenaEntity ocena) {
		ocena.setDatumDodele(new Date(System.currentTimeMillis()));
		ocena.setUcenik(ucenik);
		ocenaRepository.save(ocena);
		return new ServiceResponse("Ocena uspesno dodata", HttpStatus.OK, ocena);
	}

	@Override
	public ServiceResponse izmeniOcenuIzPredmeta(UcenikEntity ucenik, Integer ocenaId, Integer novaOcena) {
		Optional<OcenaEntity> opocena = ocenaRepository.findById(ocenaId);
		if(opocena.isPresent()) {
			OcenaEntity ocena = opocena.get();
			if(ocena.getUcenik().equals(ucenik)) {
				if(novaOcena > 1) {
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
	
	
	
	

}
