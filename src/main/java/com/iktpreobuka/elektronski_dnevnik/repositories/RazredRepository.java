package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.RazredEntity;

public interface RazredRepository extends CrudRepository<RazredEntity, Integer> {
	
	public RazredEntity findByBrojRazreda(String brojRazreda);

}
