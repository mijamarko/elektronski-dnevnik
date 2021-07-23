package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "ucenici")
@AttributeOverride(name = "user_id", column = @Column(name = "ucenik_id"))
public class UcenikEntity extends KorisnikEntity {
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "odeljenjeKojePohadja")
	@JoinColumn(name = "odeljenjeKojePohadja")
	private OdeljenjeEntity odeljenjeKojePohadja;
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "ucenikKojiJeIzostao")
	@JsonBackReference(value = "izostanci")
	private List<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
	
	@ManyToMany(mappedBy = "deca")
	private List<RoditeljEntity> roditelji = new ArrayList<RoditeljEntity>();
	
	@OneToMany(mappedBy = "ucenik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonBackReference(value = "ocene")
	private List<OcenaEntity> ocene = new ArrayList<OcenaEntity>();

	public UcenikEntity() {
		super();
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

	public List<OcenaEntity> getOcene() {
		return ocene;
	}

	public void setOcene(List<OcenaEntity> ocene) {
		this.ocene = ocene;
	}
	
	

}
