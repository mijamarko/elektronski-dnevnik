package com.iktpreobuka.elektronski_dnevnik.dto;

import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;

public class IzostanakIzmenaDTO {
	
	private String pocetniDatum;
	private String zavrsniDatum;
	private EIzostanak tipIzostankaZaPromenu;
	private EIzostanak tipIzostankaUKojiSeMenja;
	
	public IzostanakIzmenaDTO() {
		super();
	}

	public String getPocetniDatum() {
		return pocetniDatum;
	}

	public void setPocetniDatum(String pocetniDatum) {
		this.pocetniDatum = pocetniDatum;
	}

	public String getZavrsniDatum() {
		return zavrsniDatum;
	}

	public void setZavrsniDatum(String zavrsniDatum) {
		this.zavrsniDatum = zavrsniDatum;
	}

	public EIzostanak getTipIzostankaZaPromenu() {
		return tipIzostankaZaPromenu;
	}

	public void setTipIzostankaZaPromenu(EIzostanak tipIzostankaZaPromenu) {
		this.tipIzostankaZaPromenu = tipIzostankaZaPromenu;
	}

	public EIzostanak getTipIzostankaUKojiSeMenja() {
		return tipIzostankaUKojiSeMenja;
	}

	public void setTipIzostankaUKojiSeMenja(EIzostanak tipIzostankaUKojiSeMenja) {
		this.tipIzostankaUKojiSeMenja = tipIzostankaUKojiSeMenja;
	}
	
	

}
