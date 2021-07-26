package com.iktpreobuka.elektronski_dnevnik.repositories;

import java.sql.Date;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.IzostanakEntity;

public interface IzostanakRepository extends CrudRepository<IzostanakEntity, Integer> {
	
	public Iterable<IzostanakEntity> findByDatumIzostankaBetween(Date startDate, Date endDate);

}
