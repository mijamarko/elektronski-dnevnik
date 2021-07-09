package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.elektronski_dnevnik.enums.EOcena;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "ocene")
public class OcenaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private Date datumDodele;
	
	private EOcena ocena;
	@ManyToOne
	private KorisnikEntity ocenjivac;
	@ManyToOne
	private PredmetEntity predmet;
	@ManyToOne
	private UcenikEntity ucenik;
	@Version
	private Integer version;
	
	//TODO povezati sa odgovarajucim entitetima

}
