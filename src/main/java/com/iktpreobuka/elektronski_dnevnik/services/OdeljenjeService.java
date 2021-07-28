package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;

public interface OdeljenjeService {
	
	public ServiceResponse dobaviSvaOdeljenja();
	
	public ServiceResponse dobaviOdeljenjePoId(Integer odeljenjeId);
	
	public ServiceResponse dobaviSveUcenikeJednogOdeljenja(Integer odeljenjeId);
	
	public ServiceResponse dobaviRazrednogStaresinuOdeljenja(Integer odeljenjeId);
	
	public ServiceResponse dobaviSvePredmeteKojeOdeljenjeSlusa(Integer odeljenjeId);
	
	public ServiceResponse dobaviRazredKomOdeljenjePripada(Integer odeljenjeId);
	
	public ServiceResponse izmeniRazredKomOdeljenjePripada(Integer odeljenjeId, Integer razredId);
	
	public ServiceResponse izmeniRazrednogStaresinuOdeljenja(Integer odeljenjeId, Integer nastavnikId);
	
	public ServiceResponse napraviNovoOdeljenje(OdeljenjeEntity odeljenje, Integer razredId);
	
	public ServiceResponse dodajRazredKomOdeljenjePripada(Integer odeljenjeId, Integer razredId);
	
	public ServiceResponse dodajUcenikaUOdeljenje(Integer odeljenjeId, Integer ucenikId);
	
	public ServiceResponse dodajPredmetKojiOdeljenjeSlusa(Integer odeljenjeId, Integer predmetId);
	
	public ServiceResponse dodajRazrednogStaresinuOdeljenju(Integer odeljenjeId, Integer nastavnikId);
	
	public ServiceResponse obrisiPredmetKojiOdeljenjeSlusa(Integer odeljenjeId, Integer predmetId);
	
	public ServiceResponse obrisiUcenikaIzOdeljenja(Integer odeljenjeId, Integer ucenikId);

}
