package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "ucenici")
public class UcenikEntity extends KorisnikEntity {
	
	@OneToOne
	private OdeljenjeEntity odeljenjeKojePohadja;
	
	@OneToMany
	private List<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
	
	@ManyToMany
	private List<RoditeljEntity> roditelji = new ArrayList<RoditeljEntity>();

	public UcenikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OdeljenjeEntity getOdeljenjeKojePohadja() {
		return odeljenjeKojePohadja;
	}

	public void setOdeljenjeKojePohadja(OdeljenjeEntity odeljenjeKojePohadja) {
		this.odeljenjeKojePohadja = odeljenjeKojePohadja;
	}

	public List<IzostanakEntity> getIzostanci() {
		return izostanci;
	}

	public void setIzostanci(List<IzostanakEntity> izostanci) {
		this.izostanci = izostanci;
	}

	public List<RoditeljEntity> getRoditelji() {
		return roditelji;
	}

	public void setRoditelji(List<RoditeljEntity> roditelji) {
		this.roditelji = roditelji;
	}

	@Override
	public String toString() {
		return "UcenikEntity [odeljenjeKojePohadja=" + odeljenjeKojePohadja + ", izostanci=" + izostanci
				+ ", roditelji=" + roditelji + "]";
	}
	
	

}
