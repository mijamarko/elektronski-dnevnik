package com.iktpreobuka.elektronski_dnevnik.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
import com.iktpreobuka.elektronski_dnevnik.services.OdeljenjeServiceImpl;

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
	
	@Autowired
	private OdeljenjeServiceImpl odeljenjeService;
	
	private ServiceResponseHandler handler = new ServiceResponseHandler();
	
	@GetMapping(path = "/")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dobaviSvaOdeljenja() {
		return handler.handleResponse(odeljenjeService.dobaviSvaOdeljenja());
	}
	
	@GetMapping(path = "/{odeljenjeId}")
	@Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
	public ResponseEntity<?> dobaviOdeljenjePoId(@PathVariable Integer odeljenjeId) {
		return handler.handleResponse(odeljenjeService.dobaviOdeljenjePoId(odeljenjeId));
	}
	
	@GetMapping(path = "/{odeljenjeId}/ucenici")
	@Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
	public ResponseEntity<?> dobaviSveUcenikeJednogOdeljenja(@PathVariable Integer odeljenjeId) {
		return handler.handleResponse(odeljenjeService.dobaviSveUcenikeJednogOdeljenja(odeljenjeId));
	}
	
	@GetMapping(path = "/{odeljenjeId}/razredni")
	@Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
	public ResponseEntity<?> dobaviRazrednogStaresinuOdeljenja(@PathVariable Integer odeljenjeId) {
		return handler.handleResponse(odeljenjeService.dobaviRazrednogStaresinuOdeljenja(odeljenjeId));
	}
	
	@GetMapping(path = "/{odeljenjeId}/predmeti")
	@Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
	public ResponseEntity<?> dobaviSvePredmeteKojeOdeljenjeSlusa(@PathVariable Integer odeljenjeId) {
		return handler.handleResponse(odeljenjeService.dobaviSvePredmeteKojeOdeljenjeSlusa(odeljenjeId));
	}
	
	@GetMapping(path = "/{odeljenjeId}/razred")
	@Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
	public ResponseEntity<?> dobaviRazredKomOdeljenjePripada(@PathVariable Integer odeljenjeId) {
		return handler.handleResponse(odeljenjeService.dobaviRazredKomOdeljenjePripada(odeljenjeId));
	}
	
	@PutMapping(path = "/{odeljenjeId}/razred")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> izmeniRazredKomOdeljenjePripada(@PathVariable Integer odeljenjeId, @RequestParam Integer razredId) {
		return handler.handleResponse(odeljenjeService.izmeniRazredKomOdeljenjePripada(odeljenjeId, razredId));
	}
	
	@PutMapping(path = "/{odeljenjeId}/razredni")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> izmeniRazrednogStaresinuOdeljenja(@PathVariable Integer odeljenjeId, @RequestParam Integer nastavnikId) {
		return handler.handleResponse(odeljenjeService.izmeniRazrednogStaresinuOdeljenja(odeljenjeId, nastavnikId));
	}
	
	@PostMapping(path = "/")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> napraviNovoOdeljenje(@Valid @RequestBody OdeljenjeEntity odeljenje, @RequestParam Integer razredId) {
		return handler.handleResponse(odeljenjeService.napraviNovoOdeljenje(odeljenje, razredId));
	}
	
	@PostMapping(path = "/{odeljenjeId}/razred")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dodajRazredKomOdeljenjePripada(@PathVariable Integer odeljenjeId, @RequestParam Integer razredId) {
		return handler.handleResponse(odeljenjeService.dodajRazredKomOdeljenjePripada(odeljenjeId, razredId));
	}
	
	@PostMapping(path = "/{odeljenjeId}/ucenici")
	@Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
	public ResponseEntity<?> dodajUcenikaUOdeljenje(@PathVariable Integer odeljenjeId, @RequestParam Integer ucenikId) {
		return handler.handleResponse(odeljenjeService.dodajUcenikaUOdeljenje(odeljenjeId, ucenikId));
	}
	
	@PostMapping(path = "/{odeljenjeId}/predmeti")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dodajPredmetKojiOdeljenjeSlusa(@PathVariable Integer odeljenjeId, @RequestParam Integer predmetId) {
		return handler.handleResponse(odeljenjeService.dodajPredmetKojiOdeljenjeSlusa(odeljenjeId, predmetId));
	}

	@PostMapping(path = "/{odeljenjeId}/razredni")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dodajRazrednogStaresinuOdeljenju(@PathVariable Integer odeljenjeId, @RequestParam Integer nastavnikId) {
		return handler.handleResponse(odeljenjeService.dodajRazrednogStaresinuOdeljenju(odeljenjeId, nastavnikId));
	}
	
	@DeleteMapping(path = "/{odeljenjeId}/predmeti")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> obrisiPredmetKojiOdeljenjeSlusa(@PathVariable Integer odeljenjeId, @RequestParam Integer predmetId) {
		return handler.handleResponse(odeljenjeService.obrisiPredmetKojiOdeljenjeSlusa(odeljenjeId, predmetId));
	}
	
	@DeleteMapping(path = "/{odeljenjeId}/ucenici")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> obrisiUcenikaIzOdeljenja(@PathVariable Integer odeljenjeId, @RequestParam Integer ucenikId) {
		return handler.handleResponse(odeljenjeService.obrisiUcenikaIzOdeljenja(odeljenjeId, ucenikId));
	}

}
