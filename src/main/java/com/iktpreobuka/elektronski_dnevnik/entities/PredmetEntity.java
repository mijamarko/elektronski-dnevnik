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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "predmeti")
public class PredmetEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column(name = "predmet_id")
	@JsonView(Views.Admin.class)
	private Integer id;
	
	@Column(name = "naziv_predmeta")
	@NotBlank(message = "Naziv predmeta ne sme biti prazan.")
	@Size(min = 5, message = "Naziv predmeta mora biti duzi od {min} karaktera.")
	@JsonView(Views.Ucenik.class)
	private String nazivPredmeta;

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(
			name = "predmet_nastavnik",
			joinColumns = @JoinColumn(name = "predmet_id"),
			inverseJoinColumns = @JoinColumn(name = "nastavnik_id")
			)
	@JsonView(Views.Ucenik.class)
	private List<NastavnikEntity> predavaci = new ArrayList<NastavnikEntity>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(
			name = "predmet_odeljenje",
			joinColumns = @JoinColumn(name = "predmet_id"),
			inverseJoinColumns = @JoinColumn(name = "odeljenje_id")
			)
	@JsonView(Views.Nastavnik.class)
	private List<OdeljenjeEntity> odeljenjaKojaSlusajuPredmet = new ArrayList<OdeljenjeEntity>();
	
	@OneToMany(mappedBy = "predmetIzKogJeOcena")
	@JsonManagedReference(value = "predmet_ocena")
	@JsonView(Views.Nastavnik.class)
	private List<OcenaEntity> oceneIzOvogPredmeta = new ArrayList<OcenaEntity>();

	public PredmetEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public List<NastavnikEntity> getPredavaci() {
		return predavaci;
	}

	public void setPredavaci(List<NastavnikEntity> predavaci) {
		this.predavaci = predavaci;
	}

	public List<OdeljenjeEntity> getOdeljenjaKojaSlusajuPredmet() {
		return odeljenjaKojaSlusajuPredmet;
	}

	public void setOdeljenjaKojaSlusajuPredmet(List<OdeljenjeEntity> odeljenjaKojaSlusajuPredmet) {
		this.odeljenjaKojaSlusajuPredmet = odeljenjaKojaSlusajuPredmet;
	}

	public List<OcenaEntity> getOceneIzOvogPredmeta() {
		return oceneIzOvogPredmeta;
	}

	public void setOceneIzOvogPredmeta(List<OcenaEntity> oceneIzOvogPredmeta) {
		this.oceneIzOvogPredmeta = oceneIzOvogPredmeta;
	}
	
	
	

}
