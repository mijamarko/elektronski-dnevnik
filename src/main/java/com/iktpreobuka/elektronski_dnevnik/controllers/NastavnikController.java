package com.iktpreobuka.elektronski_dnevnik.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.services.NastavnikServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/korisnici/nastavnici")
public class NastavnikController {
	
	//GET all nastavnici
	//GET nastavnik by id
	//GET all predmeti by nastavnik
	//POST napravi novog nastavnika
	//POST dodaj novo odeljenje kom je razredni
	//DELETE nastavnik
	//DELETE obrisi odeljenje kom je razredni staresina
	//DELETE predmet koji predaje
	
	@Autowired
	private NastavnikServiceImpl nastavnikService;
	
	private ServiceResponseHandler handler = new ServiceResponseHandler();
	
	@GetMapping(path = "/")
	public ResponseEntity<?> dobaviSveNastavnike() {
		return handler.handleResponse(nastavnikService.dobaviSveNastavnike());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> dobaviNastavnikaPoId(@PathVariable Integer id) {
		return handler.handleResponse(nastavnikService.dobaviNastavnikaPoId(id));
	}
	
	@GetMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> dobaviSvePredmeteOvogNastavnika(@PathVariable Integer id) {
		return handler.handleResponse(nastavnikService.dobaviSvePredmeteOvogNastavnika(id));
	}
	
	@PutMapping(path ="/{id}/email")
	public ResponseEntity<?> promeniEmail(@PathVariable Integer id, @RequestBody EmailDTO noviPodaci) {
		return null;
	}
	
	@PutMapping(path ="/{id}/sifra")
	public ResponseEntity<?> promeniSifru(@PathVariable Integer id, @RequestBody SifraDTO noviPodaci) {
		return null;
	}
	
	@PostMapping(path = "/")
	public ResponseEntity<?> napraviNovogNastavnika(@Valid @RequestBody NastavnikEntity nastavnik) {
		return handler.handleResponse(nastavnikService.napraviNovogNastavnika(nastavnik));
	}
	
	@PostMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> dodajNoviPredmetKojipredaje(@PathVariable Integer id, @RequestParam Integer predmetId) {
		return handler.handleResponse(nastavnikService.dodajNoviPredmetKojiPredaje(id, predmetId));
	}
	
	@PostMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> dodajOdeljenjeNastavniku(@PathVariable Integer id, @RequestParam Integer odeljenjeId) {
		return handler.handleResponse(nastavnikService.dodajOdeljenjeNastavniku(id, odeljenjeId));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> obrisiNastavnika(@PathVariable Integer id) {
		return handler.handleResponse(nastavnikService.obrisiNastavnika(id));
	}
	
	@DeleteMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> obrisiOdeljenjeNastavniku(@PathVariable Integer id, @RequestParam Integer odeljenjeId) {
		return handler.handleResponse(nastavnikService.obrisiOdeljenjeNastaviku(id, odeljenjeId));
	}
	
	@DeleteMapping(path = "/{id}/predmeti")
	public ResponseEntity<?> obrisiPredmetNastavniku(@PathVariable Integer id, @RequestParam Integer predmetId) {
		return handler.handleResponse(nastavnikService.obrisiPredmetNastavniku(id, predmetId));
	}









}
