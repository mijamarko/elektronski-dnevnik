package com.iktpreobuka.elektronski_dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
	
	public RoleEntity findByName(String name);

}
