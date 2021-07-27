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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.elektronski_dnevnik.entities.relationships.NastavnikPredajePredmet;
import com.iktpreobuka.elektronski_dnevnik.enums.EPolugodiste;
import com.iktpreobuka.elektronski_dnevnik.enums.ETip_Ocene;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "ocene")
public class OcenaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	@PastOrPresent
	private Date datumDodele;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	@PastOrPresent
	private Date datumIzmene;
	
	@Range(min = 0, max = 5)
	private Double ocena;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "nastavnik"),
		@JoinColumn(name = "predmet")
	})
	@JsonBackReference(value = "ocena")
	private NastavnikPredajePredmet nastavnikPredmet;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ucenik")
	@JsonBackReference(value = "ocene")
	private UcenikEntity ucenik;
	
	@Version
	private Integer version;
	
	@Enumerated(EnumType.STRING)
	private ETip_Ocene tipOcene;
	
	@Enumerated(EnumType.STRING)
	private EPolugodiste polugodiste;
	
	public OcenaEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatumDodele() {
		return datumDodele;
	}

	public void setDatumDodele(Date datumDodele) {
		this.datumDodele = datumDodele;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}

	public UcenikEntity getUcenik() {
		return ucenik;
	}

	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ETip_Ocene getTipOcene() {
		return tipOcene;
	}

	public void setTipOcene(ETip_Ocene tipOcene) {
		this.tipOcene = tipOcene;
	}

	public EPolugodiste getPolugodiste() {
		return polugodiste;
	}

	public void setPolugodiste(EPolugodiste polugodiste) {
		this.polugodiste = polugodiste;
	}

	public NastavnikPredajePredmet getNastavnikPredmet() {
		return nastavnikPredmet;
	}

	public void setNastavnikPredmet(NastavnikPredajePredmet nastavnikPredmet) {
		this.nastavnikPredmet = nastavnikPredmet;
	}

	public Date getDatumIzmene() {
		return datumIzmene;
	}

	public void setDatumIzmene(Date datumIzmene) {
		this.datumIzmene = datumIzmene;
	}
	
	
	
	
}
