package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.OdeljenjeServiceResponse;

public interface OdeljenjeService {
	
	public OdeljenjeServiceResponse odeljenjePostoji(KorisnikDTO req);

}
