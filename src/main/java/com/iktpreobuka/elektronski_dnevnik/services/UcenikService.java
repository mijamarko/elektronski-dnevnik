package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakIzmenaDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;

public interface UcenikService {
	
	public ServiceResponse dobaviSveUcenike();
	
	public ServiceResponse dobaviOdeljenjeKojeUcenikPohadja(Integer ucenikId);
	
	public ServiceResponse dobaviSveIzostankeUcenika(Integer ucenikId);
	
	public ServiceResponse dobaviSveIzostankeUcenikaPoTipu(Integer ucenikId, EIzostanak tipIzostanka);
	
	public ServiceResponse dobaviSveIzostankeUVremenskomPeriodu(Integer ucenikId, IzostanakIzmenaDTO izostanci);
	
	public ServiceResponse dobaviRoditeljeUcenika(Integer ucenikId);
	
	public ServiceResponse dobaviSveOceneUcenika(Integer ucenikId);
	
	public ServiceResponse dobaviSveOceneIzJednogPredmeta(Integer ucenikId, Integer predmetId);
	
	public ServiceResponse izmeniOdeljenjeKojePohadja(Integer ucenikId, Integer odeljenjeId);
	
	public ServiceResponse izmeniIzostankeUVremenskomPeriodu(Integer ucenikId, IzostanakIzmenaDTO noviPodaci);
	
	public ServiceResponse izmeniOcenuIzPredmeta(Integer ucenikId, Integer ocenaId, Integer novaOcena);
	
	public ServiceResponse izmeniEmail(Integer ucenikId, EmailDTO noviPodaci);
	
	public ServiceResponse izmeniSifru(Integer ucenikId, SifraDTO noviPodaci);
	
	public ServiceResponse napraviNovogUcenika(UcenikEntity ucenik);
	
	public ServiceResponse dodajNovuOcenuIzOdredjenogPredmeta(OcenaEntity ocena);
	
	public ServiceResponse dodajNoveIzostanke(IzostanakDTO izostanci);
	
	public ServiceResponse obrisiUcenika(Integer ucenikId);
	
}
