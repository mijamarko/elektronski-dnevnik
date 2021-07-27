package com.iktpreobuka.elektronski_dnevnik.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.IzostanakEntity;

public interface IzostanakRepository extends CrudRepository<IzostanakEntity, Integer> {
	
	public Iterable<IzostanakEntity> findByDatumIzostankaBetween(Date startDate, Date endDate);
	
	@Query("from IzostanakEntity i where i.datum_izostanka between ?1 and ?2 and where i.user_id = ?3")
	public Iterable<IzostanakEntity> findByDatumIzostanakaBetweenZaUcenika(Date startDate, Date endDate, Integer ucenikId);

}
