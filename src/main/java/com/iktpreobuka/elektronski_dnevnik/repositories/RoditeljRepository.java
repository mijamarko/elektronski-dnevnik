package com.iktpreobuka.elektronski_dnevnik.repositories;

import com.iktpreobuka.elektronski_dnevnik.entities.RoditeljEntity;

public interface RoditeljRepository extends KorisnikRepository {
	
	public RoditeljEntity findByEmail(String name);

}
