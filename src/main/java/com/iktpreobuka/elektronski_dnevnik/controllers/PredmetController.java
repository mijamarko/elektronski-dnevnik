package com.iktpreobuka.elektronski_dnevnik.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;

@RestController
@RequestMapping(path = "/api/v1/predmeti")
public class PredmetController {
	
	ServiceResponseHandler handler = new ServiceResponseHandler();
	
	//GET svi predmeti
	//GET predmet by id
	//POST novi predmet
	//POST novog nastavnik koji predaje predmet
	//POST novo odeljenje koje slusa predmet
	//DELETE predmet
	//DELETE obrisi nastavnika koji predaje predmet
	//DELETE obrisi odeljenje koje slusa predmet
	
	@GetMapping(path = "/")
	public ResponseEntity<?> dobaviSvePredmete() {
		return null;
	}
	
	@GetMapping(path = "/{predmetId}")
	public ResponseEntity<?> dobaviPredmetPoId(@PathVariable Integer predmetId) {
		return null;
	}
	
	@PostMapping(path = "/")
	public ResponseEntity<?> napraviNoviPredmet(@Valid @RequestBody PredmetEntity predmet) {
		return null;
	}
	
	@PostMapping(path = "/{predmetId}/nastavnici")
	public ResponseEntity<?> dodajNovogNastavnikaKojiPredajePredmet(@PathVariable Integer predmetId, @RequestParam Integer nastavnikId) {
		return null;
	}

	@PostMapping(path = "/{predmetId}/odeljenja")
	public ResponseEntity<?> dodajNovoOdeljenjeKojeSlusaPredmet(@PathVariable Integer predmetId, @RequestParam Integer nastavnikId, @RequestParam Integer odeljenjeId) {
		return null;
	}
	
	@DeleteMapping(path = "/{predmetId}")
	public ResponseEntity<?> obrisiPredmet(@PathVariable Integer predmetId) {
		return null;
	}
	
	@DeleteMapping(path = "/{predmetId}/nastavnici")
	public ResponseEntity<?> obrisiNastavnikaKojiPredajePredmet(@PathVariable Integer predmetId, @RequestParam Integer nastavnikId) {
		return null;
	}
	
	@DeleteMapping(path = "/{predmetId}/odeljenja")
	public ResponseEntity<?> obrisiOdeljenjeKojeSlusaPredmet(@PathVariable Integer predmetId, @RequestParam Integer odeljenjeId) {
		return null;
	}


}
