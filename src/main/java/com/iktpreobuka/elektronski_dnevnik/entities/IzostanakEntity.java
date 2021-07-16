package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "izostanci")
public class IzostanakEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private EIzostanak tipIzostanka;
	
	@JsonFormat(shape = Shape.STRING,
			pattern = "dd-MM-yyyy")
	private Date datumIzostanka;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ucenikKojiJeIzostao")
	@JsonManagedReference(value = "izostanci")
	private UcenikEntity ucenikKojiJeIzostao;

	public IzostanakEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EIzostanak getTipIzostanka() {
		return tipIzostanka;
	}

	public void setTipIzostanka(EIzostanak tipIzostanka) {
		this.tipIzostanka = tipIzostanka;
	}

	public Date getDatumIzostanka() {
		return datumIzostanka;
	}

	public void setDatumIzostanka(Date datumIzostanka) {
		this.datumIzostanka = datumIzostanka;
	}

	public UcenikEntity getUcenikKojiJeIzostao() {
		return ucenikKojiJeIzostao;
	}

	public void setUcenikKojiJeIzostao(UcenikEntity ucenikKojiJeIzostao) {
		this.ucenikKojiJeIzostao = ucenikKojiJeIzostao;
	}
	
	

}
