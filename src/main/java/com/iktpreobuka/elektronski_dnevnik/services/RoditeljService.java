package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.RoditeljEntity;

public interface RoditeljService {
	
	public ServiceResponse dobaviSveRoditelje();
	
	public ServiceResponse dobaviRoditeljaPoId(Integer id);
	
	public ServiceResponse dobaviDecuRoditelja(Integer roditeljId);
	
	public ServiceResponse promeniEmail(Integer nastavnikId, EmailDTO noviPodaci);
	
	public ServiceResponse promeniSifru(Integer nastavnikId, SifraDTO noviPodaci);
	
	public ServiceResponse napraviNovogRoditelja(RoditeljEntity roditelj);
	
	public ServiceResponse dodajDeteRoditelju(Integer roditeljId, Integer ucenikId);
	
	public ServiceResponse obrisiRoditelja(Integer id);

}
