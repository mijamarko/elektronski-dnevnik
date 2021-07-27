package com.iktpreobuka.elektronski_dnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;

public interface OcenaRepository extends CrudRepository<OcenaEntity, Integer> {
	
	public List<OcenaEntity> findByUcenik(UcenikEntity ucenik);

}
