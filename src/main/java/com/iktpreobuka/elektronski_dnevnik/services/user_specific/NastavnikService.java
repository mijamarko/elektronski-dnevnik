package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.NastavnikServiceResponse;

public interface NastavnikService {
	
	public NastavnikServiceResponse getAllPredmetiOvogNastavnika(Integer id);

}
