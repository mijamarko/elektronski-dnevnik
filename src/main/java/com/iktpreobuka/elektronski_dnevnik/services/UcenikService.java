package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakIzmenaDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanciDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;
import com.iktpreobuka.elektronski_dnevnik.enums.EPolugodiste;
import com.iktpreobuka.elektronski_dnevnik.enums.ETip_Ocene;

public interface UcenikService {

	public ServiceResponse dobaviSveUcenike();

	public ServiceResponse dobaviUcenikaPoId(Integer ucenikId);

	public ServiceResponse dobaviOdeljenjeKojeUcenikPohadja(Integer ucenikId);

	public ServiceResponse dobaviSveIzostankeUcenika(Integer ucenikId);

	public ServiceResponse dobaviSveIzostankeUcenikaPoTipu(Integer ucenikId, EIzostanak tipIzostanka);

	public ServiceResponse dobaviSveIzostankeUVremenskomPeriodu(Integer ucenikId, IzostanakIzmenaDTO trazeniIzostanci);

	public ServiceResponse dobaviTipIzostankaUVremenskomPerioduZaUcenika(Integer ucenikId, IzostanakIzmenaDTO noviPodaci);

	public ServiceResponse dobaviRoditeljeUcenika(Integer ucenikId);

	public ServiceResponse dobaviSveOceneUcenika(Integer ucenikId);

	public ServiceResponse dobaviSveOceneIzJednogPredmeta(Integer ucenikId, Integer predmetId);

	public ServiceResponse izmeniOdeljenjeKojePohadja(Integer ucenikId, Integer odeljenjeId);

	public ServiceResponse izmeniIzostankeUVremenskomPeriodu(Integer ucenikId, IzostanakIzmenaDTO noviPodaci);

	public ServiceResponse izmeniOcenuIzPredmeta(Integer ucenikId, Integer ocenaId, Double novaOcena);

	public ServiceResponse izmeniEmail(Integer ucenikId, EmailDTO noviPodaci);

	public ServiceResponse izmeniSifru(Integer ucenikId, SifraDTO noviPodaci);

	public ServiceResponse napraviNovogUcenika(UcenikEntity ucenik);

	public ServiceResponse dodajNovuOcenuIzOdredjenogPredmeta(Integer ucenikId, Integer predmetId, Integer nastavnikId,
			Double ocena, ETip_Ocene tipOcene, EPolugodiste polugodiste);

	public ServiceResponse dodajProsecnuOcenuIzPredmetaUPolugodistu(Integer ucenikId, Integer predmetId,
			EPolugodiste polugodiste, Integer nastavnikId);

	public ServiceResponse dodajProsecnuOcenuIzPredmetaUGodini(Integer ucenikId, Integer predmetId, Integer nastavnikId);

	public ServiceResponse izracunajUspehUcenikaUPolugodistu(Integer ucenikId, EPolugodiste polugodiste);

	public ServiceResponse izracunajUspehUcenikaUGodini(Integer ucenikId);

	public ServiceResponse dodajNoveIzostanke(Integer ucenikId, IzostanciDTO izostanci);

	public ServiceResponse obrisiUcenika(Integer ucenikId);

}
