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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.security.Views;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "razredi")
public class RazredEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "razred_id")
	@JsonView(Views.Admin.class)
	private Integer id;
	
	@OneToMany(mappedBy = "razred", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonManagedReference(value = "odeljenja")
	@JsonView(Views.Admin.class)
	private List<OdeljenjeEntity> odeljenja = new ArrayList<OdeljenjeEntity>();
	
	@NotBlank
	@Size(min = 1, max = 3, message = "Molimo unesite broj razreda rimskim ciframa.")
	@JsonView(Views.Ucenik.class)
	private String brojRazreda;

	public RazredEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<OdeljenjeEntity> getOdeljenja() {
		return odeljenja;
	}

	public void setOdeljenja(List<OdeljenjeEntity> odeljenja) {
		this.odeljenja = odeljenja;
	}

	public String getBrojRazreda() {
		return brojRazreda;
	}

	public void setBrojRazreda(String brojRazreda) {
		this.brojRazreda = brojRazreda;
	}
	
	

}
