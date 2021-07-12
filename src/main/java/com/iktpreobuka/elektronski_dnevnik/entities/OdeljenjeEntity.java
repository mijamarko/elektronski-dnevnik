package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "odeljenja")
public class OdeljenjeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "odeljenje_id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonManagedReference(value = "odeljenja")
	@JoinColumn(name = "odeljenje_id", nullable = false)
	private RazredEntity razred;
	
	@Min(value = 1, message = "Odeljenje ne moze biti nulto.")
	private Integer brojOdeljenja;
	
	private String oznakaOdeljenja; //TODO razred.getname + brojOdeljenja.toString
	
	@OneToMany(mappedBy = "odeljenje", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonManagedReference(value = "ucenici")
	private List<UcenikEntity> ucenici = new ArrayList<UcenikEntity>();
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonBackReference(value = "razredni")
	private ProsvetiteljEntity razredniStaresina;
	
	@Version
	private Integer version;

}
