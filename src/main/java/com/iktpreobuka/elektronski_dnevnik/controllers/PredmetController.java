package com.iktpreobuka.elektronski_dnevnik.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.services.PredmetServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/predmeti")
public class PredmetController {
	
	ServiceResponseHandler handler = new ServiceResponseHandler();
	
	@Autowired
	private PredmetServiceImpl predmetService;
	
	//GET svi predmeti
	//GET predmet by id
	//POST novi predmet
	//POST novog nastavnik koji predaje predmet
	//POST novo odeljenje koje slusa predmet
	//DELETE predmet
	//DELETE obrisi nastavnika koji predaje predmet
	//DELETE obrisi odeljenje koje slusa predmet
	
	@GetMapping(path = "/")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dobaviSvePredmete() {
		return handler.handleResponse(predmetService.dobaviSvePredmete());
	}
	
	@GetMapping(path = "/{predmetId}")
	@Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
	public ResponseEntity<?> dobaviPredmetPoId(@PathVariable Integer predmetId) {
		return handler.handleResponse(predmetService.dobaviPredmetPoId(predmetId));
	}
	
	@PostMapping(path = "/")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> napraviNoviPredmet(@Valid @RequestBody PredmetEntity predmet) {
		return handler.handleResponse(predmetService.napraviNoviPredmet(predmet));
	}
	
	@PostMapping(path = "/{predmetId}/nastavnici")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dodajNovogNastavnikaKojiPredajePredmet(@PathVariable Integer predmetId, @RequestParam Integer nastavnikId) {
		return handler.handleResponse(predmetService.dodajNovogNastavnikaKojiPredajePredmet(predmetId, nastavnikId));
	}

	@PostMapping(path = "/{predmetId}/odeljenja")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dodajNovoOdeljenjeKojeSlusaPredmet(@PathVariable Integer predmetId, @RequestParam Integer odeljenjeId) {
		return handler.handleResponse(predmetService.dodajNovoOdeljenjeKojeSlusaPredmet(predmetId, odeljenjeId));
	}
	
	@DeleteMapping(path = "/{predmetId}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> obrisiPredmet(@PathVariable Integer predmetId) {
		return handler.handleResponse(predmetService.obrisiPredmet(predmetId));
	}
	
	@DeleteMapping(path = "/{predmetId}/nastavnici")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> obrisiNastavnikaKojiPredajePredmet(@PathVariable Integer predmetId, @RequestParam Integer nastavnikId) {
		return handler.handleResponse(predmetService.obrisiNastavnikaKojiPredajePredmet(predmetId, nastavnikId));
	}
	
	@DeleteMapping(path = "/{predmetId}/odeljenja")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> obrisiOdeljenjeKojeSlusaPredmet(@PathVariable Integer predmetId, @RequestParam Integer odeljenjeId) {
		return handler.handleResponse(predmetService.obrisiOdeljenjeKojeSlusaPredmet(predmetId, odeljenjeId));
	}


}
