package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roditelji")
@DiscriminatorValue("roditelj")
public class RoditeljEntity extends KorisnikEntity {
		
	@ManyToMany
	@JoinTable(name = "roditelji_deca",
	joinColumns = @JoinColumn(name = "roditelj_id"),
	inverseJoinColumns = @JoinColumn(name = "ucenik_id"))
	private List<UcenikEntity> deca = new ArrayList<UcenikEntity>();

	public RoditeljEntity() {
		super();
	}
	
	public List<UcenikEntity> getDeca() {
		return deca;
	}

	public void setDeca(List<UcenikEntity> deca) {
		this.deca = deca;
	}	

}
