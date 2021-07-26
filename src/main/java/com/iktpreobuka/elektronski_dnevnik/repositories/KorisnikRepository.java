package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;


public interface KorisnikRepository extends CrudRepository<KorisnikEntity, Integer> {
	
	public KorisnikEntity findByEmail(String email);
	
	@Query("from KorisnikEntity k, RoleEntity r where k.role = r.id and r.name =?1")
	public Iterable<KorisnikEntity> findAllByRoleName(String name);
	
	

}
