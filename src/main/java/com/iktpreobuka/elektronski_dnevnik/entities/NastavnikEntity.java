package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "nastavnici")
@PrimaryKeyJoinColumn(name = "user_id")
public class NastavnikEntity extends ProsvetiteljEntity {
	
	@ManyToMany
	private List<PredmetEntity> predmetiKojePredaje;
	
	@OneToMany
	private List<OcenaEntity> upisaneOcene;
	
	//TODO povezati sa ocenaentity
}
