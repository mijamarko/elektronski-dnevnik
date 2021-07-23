package com.iktpreobuka.elektronski_dnevnik.controllers.user_specific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.controllers.ServiceResponseHandler;
import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.NastavnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.services.user_specific.NastavnikServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/users/nastavnici")
public class NastavnikController {
	
	/**
	 * Upravljanje obelezjima specificnim za nastavnike
	 * GET - dobavi sve predmete ovog nastavnika
	 * POST - dodaj novi predmet koji predaje
	 * POST - dodaj novo odeljenje kom je razredni staresina
	 * PUT - promeni odeljenje kom je razredni staresina
	 * DELETE - ukloni predmet koji vise ne predaje
	 * DELETE - ukloni odeljenje kom vise nije razredni staresina
	 */
	
	@Autowired
	private NastavnikServiceImpl nastavnikService;
	
	@Autowired
	private ServiceResponseHandler serviceResponseHandler;
	
	@GetMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> dobaviSvePredmeteOvogNastavnika(@PathVariable Integer id) {
		NastavnikServiceResponse response = nastavnikService.dobaviSvePredmeteOvogNastavnika(id);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PostMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> dodajNoviPredmetNastavniku(@PathVariable Integer id, @RequestBody KorisnikDTO req) {
		NastavnikServiceResponse response = nastavnikService.dodajNoviPredmetNastavniku(id, req);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PostMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> dodajNovoOdeljenjeNastavniku(@PathVariable Integer id, @RequestBody KorisnikDTO req) {
		NastavnikServiceResponse response = nastavnikService.dodajNovoOdeljenjeNastavniku(id, req);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PutMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> izmeniOdeljenjeKomJeRazredniStaresina(@PathVariable Integer id, @RequestBody KorisnikDTO req) {
		NastavnikServiceResponse response = nastavnikService.izmeniOdeljenjeKomJeRazredniStaresina(id, req);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@DeleteMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> obrisiPredmetNastavniku(@PathVariable Integer id, @RequestBody KorisnikDTO req) {
		NastavnikServiceResponse response = nastavnikService.obrisiPredmetNastavniku(id, req);
		return serviceResponseHandler.handleResponse(response);
	}

	@DeleteMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> obrisiOdeljenjeNastavniku(@PathVariable Integer id, @RequestBody KorisnikDTO req) {
		NastavnikServiceResponse response = nastavnikService.obrisiOdeljenjeNastavniku(id, req);
		return serviceResponseHandler.handleResponse(response);
	}
	
	

}
