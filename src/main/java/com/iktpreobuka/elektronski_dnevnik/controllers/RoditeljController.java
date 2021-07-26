package com.iktpreobuka.elektronski_dnevnik.controllers;

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
import com.iktpreobuka.elektronski_dnevnik.entities.RoditeljEntity;
import com.iktpreobuka.elektronski_dnevnik.services.RoditeljServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/korisnici/roditelji")
public class RoditeljController {
	
	//GET all roditelji
	//GET roditelj by id
	//PUT promeni email
	//PUT promeni sifru
	//POST novog roditelja
	//POST dete
	//DELETE roditelj
	@Autowired
	private RoditeljServiceImpl roditeljService;
	
	ServiceResponseHandler handler = new ServiceResponseHandler();
	
	@GetMapping(path = "/")
	public ResponseEntity<?> dobaviSveRoditelje() {
		return handler.handleResponse(roditeljService.dobaviSveRoditelje());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> dobaviRoditeljaPoId(@RequestParam Integer roditeljId) {
		return handler.handleResponse(roditeljService.dobaviRoditeljaPoId(roditeljId));
	}
	
	@GetMapping(path = "/{id}/deca")
	public ResponseEntity<?> dobaviDecuRoditelja(@RequestParam Integer roditeljId) {
		return handler.handleResponse(roditeljService.dobaviDecuRoditelja(roditeljId));
	}

	
	@PutMapping(path = "/{id}/email")
	public ResponseEntity<?> promeniEmail(@PathVariable Integer roditeljId, @RequestBody EmailDTO noviPodaci) {
		return handler.handleResponse(roditeljService.promeniEmail(roditeljId, noviPodaci));
	}
	
	@PutMapping(path = "/{id}/sifra")
	public ResponseEntity<?> promeniSifru(@PathVariable Integer roditeljId, @RequestBody SifraDTO noviPodaci) {
		return handler.handleResponse(roditeljService.promeniSifru(roditeljId, noviPodaci));
	}
	
	@PostMapping(path = "/")
	public ResponseEntity<?> napraviNovogRoditelja(RoditeljEntity roditelj) {
		return handler.handleResponse(roditeljService.napraviNovogRoditelja(roditelj));
	}

	@PostMapping(path = "/{id}/deca")
	public ResponseEntity<?> dodajDeteRoditelju(Integer roditeljId, Integer ucenikId) {
		return handler.handleResponse(roditeljService.dodajDeteRoditelju(roditeljId, ucenikId));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> obrisiRoditelja(Integer roditeljId) {
		return handler.handleResponse(roditeljService.obrisiRoditelja(roditeljId));
	}


}
