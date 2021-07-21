package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.NastavnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;

public interface NastavnikService {

	public NastavnikServiceResponse getAllPredmetiOvogNastavnika(Integer id);

	public NastavnikServiceResponse addNewPredmet(Integer user_id, PredmetEntity predmet);

	public NastavnikServiceResponse addNewPredmet(Integer user_id, Integer predmet_id);

	public NastavnikServiceResponse addNewPredmet(Integer user_id, String predmet_name);

	public NastavnikServiceResponse addNewOdeljenje(Integer user_id, OdeljenjeEntity odeljenje);

	public NastavnikServiceResponse addNewOdeljenje(Integer user_id, Integer odeljenje_id);
	
	public NastavnikServiceResponse izmeniOdeljenjeKomJeRazredniStaresina(Integer user_id, OdeljenjeEntity odeljenje);
	
	public NastavnikServiceResponse izmeniOdeljenjeKomJeRazredniStaresina(Integer user_id, Integer odeljenje_id);
	
	public NastavnikServiceResponse deletePredmetFromNastavnik(Integer user_id, PredmetEntity predmet);

	public NastavnikServiceResponse deletePredmetFromNastavnik(Integer user_id, Integer predmet_id);
	
	public NastavnikServiceResponse deleteOdeljenjeFromNastavnik(Integer user_id, OdeljenjeEntity odeljenje);
	
	public NastavnikServiceResponse deleteOdeljenjeFromNastavnik(Integer user_id, Integer odeljenje_id);	
}
