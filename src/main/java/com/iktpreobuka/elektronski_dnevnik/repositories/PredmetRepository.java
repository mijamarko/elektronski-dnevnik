package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;

public interface PredmetRepository extends CrudRepository<PredmetEntity, Integer> {
	
	public PredmetEntity findByName(String name);

}
