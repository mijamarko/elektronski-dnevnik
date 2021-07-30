package com.iktpreobuka.elektronski_dnevnik.services;

import java.sql.Date;

import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakIzmenaDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanciDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;

public interface IzostanakService {
	
	public ServiceResponse dodajNoveIzostanke(UcenikEntity ucenik, IzostanciDTO izostanci);
	
	public ServiceResponse dobaviSveIzostankeUVremenskomPerioduZaUcenika(UcenikEntity ucenik, IzostanakIzmenaDTO trazeniIzostanci);

	public ServiceResponse dobaviSveIzostankeUVremenskomPerioduZaOdeljenje(OdeljenjeEntity odeljenje, Date pocetniDatum, Date zavrsniDatum);

	public ServiceResponse dobaviTipIzostankaUVremenskomPerioduZaUcenika(UcenikEntity ucenik, IzostanakIzmenaDTO trazeniIzostanci);

	public ServiceResponse dobaviTipIzostankaUVremenskomPerioduZaOdeljenje(OdeljenjeEntity odeljenje, IzostanakIzmenaDTO trazeniIzostanci);

	public ServiceResponse izmeniIzostankeUVremenskomPerioduZaUcenika(UcenikEntity ucenik, IzostanakIzmenaDTO noviPodaci);

}
