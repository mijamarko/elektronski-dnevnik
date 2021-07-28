package com.iktpreobuka.elektronski_dnevnik.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;

@RestController
@RequestMapping(path = "/api/v1/odeljenja")
public class OdeljenjeController {
	
	//GET sva odeljenja
	//GET odeljenje by id
	//GET ucenici koji pripadaju odeljenju
	//GET razredni staresina odeljenja
	//GET predmeti koje odeljenje slusa
	//PUT izmeni razred kom odeljenje pripada
	//PUT izmeni razrednog staresinu odeljenja
	//POST napravi novo odeljenje
	//POST dodaj razred kome odeljenje pripada
	//POST dodaj ucenika u odeljenje
	//POST dodaj predmet koji odeljenje slusa
	//POST dodaj razrednog staresinu
	//DELETE obrisi predmet koji odeljenje slusa
	//DELETE obrisi ucenika iz odeljenja
	
	private ServiceResponseHandler handler = new ServiceResponseHandler();
	
	@GetMapping(path = "/")
	public ResponseEntity<?> dobaviSvaOdeljenja() {
		return null;
	}
	
	@GetMapping(path = "/{odeljenjeId}")
	public ResponseEntity<?> dobaviOdeljenjePoId(@PathVariable Integer odeljenjeId) {
		return null;
	}
	
	@GetMapping(path = "/{odeljenjeId}/ucenici")
	public ResponseEntity<?> dobaviSveUcenikeJednogOdeljenja(@PathVariable Integer odeljenjeId) {
		return null;
	}
	
	@GetMapping(path = "/{odeljenjeId}/razredni")
	public ResponseEntity<?> dobaviRazrednogStaresinuOdeljenja(@PathVariable Integer odeljenjeId) {
		return null;
	}
	
	@GetMapping(path = "/{odeljenjeId}/predmeti")
	public ResponseEntity<?> dobaviSvePredmeteKojeOdeljenjeSlusa(@PathVariable Integer odeljenjeId) {
		return null;
	}
	
	@GetMapping(path = "/{odeljenjeId}/razred")
	public ResponseEntity<?> dobaviRazredKomOdeljenjePripada(@PathVariable Integer odeljenjeId) {
		return null;
	}
	
	@PutMapping(path = "/{odeljenjeId}/razred")
	public ResponseEntity<?> izmeniRazredKomOdeljenjePripada(@PathVariable Integer odeljenjeId, @RequestParam Integer razredId) {
		return null;
	}
	
	@PutMapping(path = "/{odeljenjeId}/razredni")
	public ResponseEntity<?> izmeniRazrednogStaresinuOdeljenja(@PathVariable Integer odeljenjeId, @RequestParam Integer nastavnikId) {
		return null;
	}
	
	@PostMapping(path = "/")
	public ResponseEntity<?> napraviNovoOdeljenje(@Valid @RequestBody OdeljenjeEntity odeljenje, @RequestParam Integer razredId) {
		return null;
	}
	
	@PostMapping(path = "/{odeljenjeId}/razred")
	public ResponseEntity<?> dodajRazredKomOdeljenjePripada(@PathVariable Integer odeljenjeId, @RequestParam Integer razredId) {
		return null;
	}
	
	@PostMapping(path = "/{odeljenjeId}/ucenici")
	public ResponseEntity<?> dodajUcenikaUOdeljenje(@PathVariable Integer odeljenjeId, @RequestParam Integer ucenikId) {
		return null;
	}
	
	@PostMapping(path = "/{odeljenjeId}/predmeti")
	public ResponseEntity<?> dodajPredmetKojiOdeljenjeSlusa(@PathVariable Integer odeljenjeId, @RequestParam Integer predmetId) {
		return null;
	}

	@PostMapping(path = "/{odeljenjeId}/razredni")
	public ResponseEntity<?> dodajRazrednogStaresinuOdeljenju(@PathVariable Integer odeljenjeId, @RequestParam Integer nastavnikId) {
		return null;
	}
	
	@DeleteMapping(path = "/{odeljenjeId}/predmeti")
	public ResponseEntity<?> obrisiPredmetKojiOdeljenjeSlusa(@PathVariable Integer odeljenjeId, @RequestParam Integer predmetId) {
		return null;
	}
	
	@DeleteMapping(path = "/{odeljenjeId}/ucenici")
	public ResponseEntity<?> obrisiUcenikaIzOdeljenja(@PathVariable Integer odeljenjeId, @RequestParam Integer ucenikId) {
		return null;
	}

}
