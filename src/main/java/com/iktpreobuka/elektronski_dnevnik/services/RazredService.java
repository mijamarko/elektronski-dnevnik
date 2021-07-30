package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.RazredEntity;

public interface RazredService {
	
	public ServiceResponse dobaviSveRazrede();
	
	public ServiceResponse dobaviRazredPoId(Integer razredId);
	
	public ServiceResponse dobaviSvaOdeljenjaRazreda(Integer razredId);
	
	public ServiceResponse napraviNoviRazred(RazredEntity razred);
	
	public ServiceResponse dodajNovoOdeljenjeRazredu(Integer razredId, Integer odeljenjeId);
	
	public ServiceResponse obrisiRazred(Integer razredId);
	
	public ServiceResponse obrisiOdeljenjeKojePripadaRazredu(Integer razredId, Integer odeljenjeId);
}
