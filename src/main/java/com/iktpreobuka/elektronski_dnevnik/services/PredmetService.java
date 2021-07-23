package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;

public interface PredmetService {
	
	public PredmetEntity predmetPostoji(KorisnikDTO req);

}
