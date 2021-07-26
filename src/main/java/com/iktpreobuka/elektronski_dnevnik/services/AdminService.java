package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.AdminEntity;

public interface AdminService {
	
	public ServiceResponse dobaviSveAdmine();
	
	public ServiceResponse dobaviAdminaPoId(Integer id);
	
	public ServiceResponse promeniEmail(Integer nastavnikId, EmailDTO noviPodaci);
	
	public ServiceResponse promeniSifru(Integer nastavnikId, SifraDTO noviPodaci);
	
	public ServiceResponse napraviNovogAdmina(AdminEntity admin);
	
	public ServiceResponse obrisiAdmina(Integer id);

}
