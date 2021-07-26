package com.iktpreobuka.elektronski_dnevnik.repositories;

import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;

public interface NastavnikRepository extends KorisnikRepository {
	
	public NastavnikEntity findByEmail(String email);

}
