package com.iktpreobuka.elektronski_dnevnik.entities.relationships;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.composite_keys.PredajeKey;

@Entity
public class NastavnikPredajePredmet {
	
	@EmbeddedId
	private PredajeKey id;
	
	@ManyToOne
	@MapsId("nastavnikId")
	@JoinColumn(name = "nastavnik_id")
	private NastavnikEntity nastavnik;
	
	@ManyToOne
	@MapsId("predmetId")
	@JoinColumn(name = "predmet_id")
	private PredmetEntity predmet;
	
	@OneToMany(mappedBy = "nastavnikPredmet", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonBackReference(value = "ocena")
	private List<OcenaEntity> ocena = new ArrayList<OcenaEntity>();

}
