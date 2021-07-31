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
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dobaviSveRoditelje() {
		return handler.handleResponse(roditeljService.dobaviSveRoditelje());
	}
	
	@GetMapping(path = "/{id}")
	@Secured({"ROLE_ADMIN", "ROLE_RODITELJ"})
	public ResponseEntity<?> dobaviRoditeljaPoId(@RequestParam Integer roditeljId) {
		return handler.handleResponse(roditeljService.dobaviRoditeljaPoId(roditeljId));
	}
	
	@GetMapping(path = "/{id}/deca")
	@Secured({"ROLE_ADMIN", "ROLE_RODITELJ"})
	public ResponseEntity<?> dobaviDecuRoditelja(@RequestParam Integer roditeljId) {
		return handler.handleResponse(roditeljService.dobaviDecuRoditelja(roditeljId));
	}

	
	@PutMapping(path = "/{id}/email")
	@Secured({"ROLE_ADMIN", "ROLE_RODITELJ"})
	public ResponseEntity<?> promeniEmail(@PathVariable Integer roditeljId, @RequestBody EmailDTO noviPodaci) {
		return handler.handleResponse(roditeljService.promeniEmail(roditeljId, noviPodaci));
	}
	
	@PutMapping(path = "/{id}/sifra")
	@Secured({"ROLE_ADMIN", "ROLE_RODITELJ"})
	public ResponseEntity<?> promeniSifru(@PathVariable Integer roditeljId, @RequestBody SifraDTO noviPodaci) {
		return handler.handleResponse(roditeljService.promeniSifru(roditeljId, noviPodaci));
	}
	
	@PostMapping(path = "/")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> napraviNovogRoditelja(@Valid @RequestBody RoditeljEntity roditelj) {
		return handler.handleResponse(roditeljService.napraviNovogRoditelja(roditelj));
	}

	@PostMapping(path = "/{id}/deca")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dodajDeteRoditelju(@PathVariable Integer roditeljId, @RequestParam Integer ucenikId) {
		return handler.handleResponse(roditeljService.dodajDeteRoditelju(roditeljId, ucenikId));
	}

	@DeleteMapping(path = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> obrisiRoditelja(@PathVariable Integer roditeljId) {
		return handler.handleResponse(roditeljService.obrisiRoditelja(roditeljId));
	}


}
