package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "razredi")
public class RazredEntity {
	@Id
	private Integer id;
	
	@OneToMany(mappedBy = "razred", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonBackReference(value = "odeljenja")
	private List<OdeljenjeEntity> odeljenja = new ArrayList<OdeljenjeEntity>();

	public RazredEntity() {
		super();
		// TODO Auto-generated constructor stub
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
	
	

}
