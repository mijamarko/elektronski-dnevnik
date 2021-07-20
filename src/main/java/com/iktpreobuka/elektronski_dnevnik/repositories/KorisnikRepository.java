package com.iktpreobuka.elektronski_dnevnik.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;

public interface KorisnikRepository extends CrudRepository<KorisnikEntity, Integer> {
	
	public KorisnikEntity findByEmail(String email);
	
	@Query("select roles from KorisnikEntity k where k.id=?1")
	public ArrayList<RoleEntity> findAllByUserId(Integer userId);
	
	

}
