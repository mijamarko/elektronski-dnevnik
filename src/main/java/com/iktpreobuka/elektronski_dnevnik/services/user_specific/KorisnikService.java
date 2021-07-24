package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;

public interface KorisnikService {
	
	public KorisnikServiceResponse dobaviSveKorisnike();
	
	public KorisnikServiceResponse dobaviKorisnikaPoId(Integer id);
	
	public KorisnikEntity korisnikPostoji(Integer id);
	
	public KorisnikEntity emailPostoji(String email);
	
	public KorisnikServiceResponse napraviNovogKorisnika(KorisnikEntity korisnik);
	
	public KorisnikServiceResponse promeniEmail(Integer id, KorisnikDTO noviPodaci);
	
	public KorisnikServiceResponse promeniSifru(Integer id, KorisnikDTO noviPodaci);
	
	public KorisnikServiceResponse obrisiKorisnika(Integer id);

}
