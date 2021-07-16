package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;

public interface LoginService {
	
	public String getJWT(KorisnikEntity korisnik, String secretKey, Integer tokenDuration);

}
