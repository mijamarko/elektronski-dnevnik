package com.iktpreobuka.elektronski_dnevnik.repositories;

import com.iktpreobuka.elektronski_dnevnik.entities.AdminEntity;

public interface AdminRepository extends KorisnikRepository {
	
	public AdminEntity findByEmail(String email);

}
