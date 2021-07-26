package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;

@NoRepositoryBean
public interface KorisnikRepository extends CrudRepository<KorisnikEntity, Integer> {
	
	public KorisnikEntity findByEmail(String email);
	
	

}
