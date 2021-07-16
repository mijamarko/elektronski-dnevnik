package com.iktpreobuka.elektronski_dnevnik.entities.composite_keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PredajeKey implements Serializable{
	
	@Column(name = "nastavnik_id")
	private Integer nastavnikId;
	
	@Column(name = "predmet_id")
	private Integer predmetId;

	public PredajeKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getNastavnikId() {
		return nastavnikId;
	}

	public void setNastavnikId(Integer nastavnikId) {
		this.nastavnikId = nastavnikId;
	}

	public Integer getPredmetId() {
		return predmetId;
	}

	public void setPredmetId(Integer predmetId) {
		this.predmetId = predmetId;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	

}
