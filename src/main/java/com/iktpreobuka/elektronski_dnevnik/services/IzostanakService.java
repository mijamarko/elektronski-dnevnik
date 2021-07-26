package com.iktpreobuka.elektronski_dnevnik.services;

import java.sql.Date;

import com.iktpreobuka.elektronski_dnevnik.dto.IzostanciDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;

public interface IzostanakService {
	
	public ServiceResponse dodajNoveIzostanke(Integer ucenikId, IzostanciDTO izostanci);
	
	public ServiceResponse dobaviSveIzostankeUVremenskomPeriodu(Integer ucenikId, Date pocetniDatum, Date zavrsniDatum);
	
	public ServiceResponse dobaviTipIzostankaUVremenskomPeriodu(Integer ucenikId, Date pocetniDatum, Date zavrsniDatum, EIzostanak tipIzostanka);
	
	public ServiceResponse izmeniIzostankeUVremenskomPeriodu(Integer ucenikId, Date pocetniDatum, Date zavrsniDatum, EIzostanak tipIzostanakaKojiSeMenja, EIzostanak tipIzostanakaUKojiSeMenja);

}
