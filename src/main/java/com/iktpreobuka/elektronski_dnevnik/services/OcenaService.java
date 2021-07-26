package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;

public interface OcenaService {
	
	public ServiceResponse izmeniOcenuIzPredmeta(UcenikEntity ucenik, Integer ocenaId, Integer novaOcena);
	
	public ServiceResponse dodajNovuOcenu(UcenikEntity ucenik, OcenaEntity ocena);
	
	

}
