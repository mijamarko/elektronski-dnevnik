package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;

public interface PredmetService {
	
	public ServiceResponse dobaviSvePredmete();
	
	public ServiceResponse dobaviPredmetPoId(Integer predmetId);
	
	public ServiceResponse napraviNoviPredmet(PredmetEntity predmet);
	
	public ServiceResponse dodajNovogNastavnikaKojiPredajePredmet(Integer predmetId, Integer nastavnikId);
	
	public ServiceResponse dodajNovoOdeljenjeKojeSlusaPredmet(Integer predmetId, Integer odeljenjeId);
	
	public ServiceResponse obrisiPredmet(Integer predmetId);
	
	public ServiceResponse obrisiNastavnikaKojiPredajePredmet(Integer predmetId, Integer nastavnikId);
	
	public ServiceResponse obrisiOdeljenjeKojeSlusaPredmet(Integer predmetId, Integer odeljenjeId);

}
