package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "izostanci")
public class IzostanakEntity {
	@Id
	private Integer id;
	private EIzostanak tipIzostanka;
	private Date datumIzostanka;
	
	private UcenikEntity ucenikKojiJeIzostao;

}
