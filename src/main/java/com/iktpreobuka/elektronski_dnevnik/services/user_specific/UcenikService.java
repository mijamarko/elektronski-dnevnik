package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.UcenikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.IzostanakEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;

public interface UcenikService {
	
	public UcenikEntity korisnikJeUcenik(Integer id);
	
	public UcenikServiceResponse dobaviSveOceneUcenika(Integer ucenikId);
	
	public UcenikServiceResponse dobaviSveOceneUcenikaIzPredmeta(Integer ucenikId, KorisnikDTO req);
	
	public UcenikServiceResponse dobaviSveIzostankeUcenika(Integer ucenikId);
	
	public UcenikServiceResponse dodajOdeljenjeKojePohadja(Integer ucenikId, KorisnikDTO req);
	
	public UcenikServiceResponse dodajRoditelja(Integer ucenikId, Integer roditeljId);
	
	public UcenikServiceResponse dodajIzostanakUceniku(Integer ucenikId, IzostanakEntity izostanak);
	
	public UcenikServiceResponse dodajOcenuUceniku(Integer ucenikId, OcenaEntity ocena);
	
	public UcenikServiceResponse promeniOdeljenjeKojePohadja(Integer ucenikId, KorisnikDTO req);
	
	public UcenikServiceResponse promeniIzostanakUceniku(Integer ucenikId, KorisnikDTO req);

}
