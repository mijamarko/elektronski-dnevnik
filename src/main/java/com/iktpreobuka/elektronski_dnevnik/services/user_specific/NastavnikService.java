package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.NastavnikServiceResponse;

public interface NastavnikService {

	public NastavnikServiceResponse dobaviSvePredmeteOvogNastavnika(Integer userId);

	public NastavnikServiceResponse dodajNoviPredmetNastavniku(Integer userId, KorisnikDTO req);

	public NastavnikServiceResponse dodajNovoOdeljenjeNastavniku(Integer userId, KorisnikDTO req);
	
	public NastavnikServiceResponse izmeniOdeljenjeKomJeRazredniStaresina(Integer userId, KorisnikDTO req);
	
	public NastavnikServiceResponse obrisiPredmetNastavniku(Integer userId, KorisnikDTO req);
	
	public NastavnikServiceResponse obrisiOdeljenjeNastavniku(Integer userId, KorisnikDTO req);

}
