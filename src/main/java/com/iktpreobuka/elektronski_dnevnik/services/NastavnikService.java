package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;

public interface NastavnikService {
	
	public ServiceResponse dobaviSveNastavnike();
	
	public ServiceResponse dobaviNastavnikaPoId(Integer id);
	
	public ServiceResponse dobaviSvePredmeteOvogNastavnika(Integer id);
	
	public ServiceResponse promeniEmail(Integer nastavnikId, EmailDTO noviPodaci);
	
	public ServiceResponse promeniSifru(Integer nastavnikId, SifraDTO noviPodaci);
	
	public ServiceResponse napraviNovogNastavnika(NastavnikEntity nastavnik);
	
	public ServiceResponse dodajNoviPredmetKojiPredaje(Integer nastavnikId, Integer predmetId);
	
	public ServiceResponse dodajOdeljenjeNastavniku(Integer nastavnikId, Integer odeljenjeId);
	
	public ServiceResponse obrisiNastavnika(Integer id);
	
	public ServiceResponse obrisiOdeljenjeNastaviku(Integer nastavnikId, Integer odeljenjeId);
	
	public ServiceResponse obrisiPredmetNastavniku(Integer nastavnikId, Integer predmetId);

}
