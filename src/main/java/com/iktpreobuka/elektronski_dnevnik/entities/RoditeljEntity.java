package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "roditelji")
public class RoditeljEntity extends KorisnikEntity {
	
	@Version
	private Integer version;
	
	@ManyToMany
	private List<UcenikEntity> deca = new ArrayList<UcenikEntity>();

	public RoditeljEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<UcenikEntity> getDeca() {
		return deca;
	}

	public void setDeca(List<UcenikEntity> deca) {
		this.deca = deca;
	}

	@Override
	public String toString() {
		return "RoditeljEntity [version=" + version + ", deca=" + deca + "]";
	}
	
	

}
