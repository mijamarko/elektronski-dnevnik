package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	@Range(min = 0, max = 5)
	private Integer ocena;
	@ManyToOne
	private KorisnikEntity ocenjivac;
	@ManyToOne
	private PredmetEntity predmet;
	@ManyToOne
	private UcenikEntity ucenik;
	@Version
	private Integer version;
	@Enumerated(EnumType.STRING)
	private ETip_Ocene tipOcene;
	private EPolugodiste polugodiste;
	
	public OcenaEntity() {
		super();
		// TODO Auto-generated constructor stub
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

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public KorisnikEntity getOcenjivac() {
		return ocenjivac;
	}

	public void setOcenjivac(KorisnikEntity ocenjivac) {
		this.ocenjivac = ocenjivac;
	}

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
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

	@Override
	public String toString() {
		return "OcenaEntity [id=" + id + ", datumDodele=" + datumDodele + ", ocena=" + ocena + ", ocenjivac="
				+ ocenjivac + ", predmet=" + predmet + ", ucenik=" + ucenik + ", version=" + version + ", tipOcene="
				+ tipOcene + ", polugodiste=" + polugodiste + "]";
	}
	
	
	//TODO povezati sa odgovarajucim entitetima
	
	

}
