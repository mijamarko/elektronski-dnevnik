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
import com.iktpreobuka.elektronski_dnevnik.dto.responses.NastavnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
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
	 * TODO DELETE - ukloni odeljenje kom vise nije razredni staresina
	 */
	
	@Autowired
	private NastavnikServiceImpl nastavnikService;
	
	@Autowired
	private ServiceResponseHandler serviceResponseHandler;
	
	@GetMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> getAllPredmetiOvogNastavnika(@PathVariable Integer id) {
		NastavnikServiceResponse response = nastavnikService.getAllPredmetiOvogNastavnika(id);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PostMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> addNewPredmet(@RequestBody PredmetEntity predmet, @PathVariable Integer id) {
		NastavnikServiceResponse response = nastavnikService.addNewPredmet(id, predmet);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PostMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> addNewPredmet(@RequestBody Integer predmet_id, @PathVariable Integer id) {
		NastavnikServiceResponse response = nastavnikService.addNewPredmet(id, predmet_id);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PostMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> addNewPredmet(@RequestBody String predmet_name, @PathVariable Integer id) {
		NastavnikServiceResponse response = nastavnikService.addNewPredmet(id, predmet_name);
		return serviceResponseHandler.handleResponse(response);
	}

	@PostMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> addNewOdeljenje(@RequestBody OdeljenjeEntity odeljenje, @PathVariable Integer id) {
		NastavnikServiceResponse response = nastavnikService.addNewOdeljenje(id, odeljenje);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PostMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> addNewOdeljenje(@RequestBody Integer odeljenje_id, @PathVariable Integer id) {
		NastavnikServiceResponse response = nastavnikService.addNewOdeljenje(id, odeljenje_id);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PutMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> izmeniOdeljenjeKomJeRazredniStaresina(@PathVariable Integer id, @RequestBody OdeljenjeEntity odeljenje) {
		NastavnikServiceResponse response = nastavnikService.izmeniOdeljenjeKomJeRazredniStaresina(id, odeljenje);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PutMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> izmeniOdeljenjeKomJeRazredniStaresina(@PathVariable Integer id, @RequestBody Integer odeljenje_id) {
		NastavnikServiceResponse response = nastavnikService.izmeniOdeljenjeKomJeRazredniStaresina(id, odeljenje_id);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@DeleteMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> deletePredmetFromNastavnik(@PathVariable Integer id, @RequestBody PredmetEntity predmet) {
		NastavnikServiceResponse response = nastavnikService.deletePredmetFromNastavnik(id, predmet);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@DeleteMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> deletePredmetFromNastavnik(@PathVariable Integer id, @RequestBody Integer predmet_id) {
		NastavnikServiceResponse response = nastavnikService.deletePredmetFromNastavnik(id, predmet_id);
		return serviceResponseHandler.handleResponse(response);
	}

	@DeleteMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> deleteOdeljenjeFromNastavnik(@PathVariable Integer id, @RequestBody OdeljenjeEntity odeljenje) {
		NastavnikServiceResponse response = nastavnikService.deleteOdeljenjeFromNastavnik(id, odeljenje);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@DeleteMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> deleteOdeljenjeFromNastavnik(@PathVariable Integer id, @RequestBody Integer odeljenje_id) {
		NastavnikServiceResponse response = nastavnikService.deleteOdeljenjeFromNastavnik(id, odeljenje_id);
		return serviceResponseHandler.handleResponse(response);
	}

	
	

}
