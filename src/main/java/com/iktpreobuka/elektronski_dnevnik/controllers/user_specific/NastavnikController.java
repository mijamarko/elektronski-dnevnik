package com.iktpreobuka.elektronski_dnevnik.controllers.user_specific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.controllers.ServiceResponseHandler;
import com.iktpreobuka.elektronski_dnevnik.services.user_specific.NastavnikServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/users/nastavnici")
public class NastavnikController {
	
	/**
	 * Upravljanje obelezjima specificnim za nastavnike
	 * TODO GET - dobavi sve predmete ovog nastavnika
	 * TODO POST - dodaj novi predmet koji predaje
	 * TODO POST - dodaj novo odeljenje kom je razredni staresina
	 * TODO PUT - promeni odeljenje kom je razredni staresina
	 * TODO PUT - ukloni predmet koji vise ne predaje
	 * TODO PUT - ukloni odeljenje kom vise nije razredni staresina
	 */
	
	@Autowired
	private NastavnikServiceImpl nastavnikService;
	
	@Autowired
	private ServiceResponseHandler serviceResponseHandler;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getAllPredmetiOvogNastavnika(@PathVariable Integer id) {
		
	}


}
