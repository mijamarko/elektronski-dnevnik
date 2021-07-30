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

import com.iktpreobuka.elektronski_dnevnik.entities.RazredEntity;
import com.iktpreobuka.elektronski_dnevnik.services.RazredServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/razredi")
public class RazredController {
	
	//GET svi razredi
	//GET razred by id
	//GET sva odeljenja razreda
	//POST napravi novi razred
	//POST dodaj novo odeljenje razredu
	//DELETE odeljenje koje pripada razredu
	//DELETE razred
	
	private ServiceResponseHandler handler = new ServiceResponseHandler();
	
	@Autowired
	private RazredServiceImpl razredService;
	
	@GetMapping(path = "/")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dobaviSveRazrede() {
		return handler.handleResponse(razredService.dobaviSveRazrede());
	}

	@GetMapping(path = "/{razredId}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dobaviRazredPoId(@PathVariable Integer razredId) {
		return handler.handleResponse(razredService.dobaviRazredPoId(razredId));
	}
	
	@GetMapping(path = "/{razredId}/odeljenja")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dobaviSvaOdeljenjaRazreda(@PathVariable Integer razredId) {
		return handler.handleResponse(razredService.dobaviSvaOdeljenjaRazreda(razredId));
	}
	
	@PostMapping(path = "/")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> napraviNoviRazred(@Valid @RequestBody RazredEntity razred) {
		return handler.handleResponse(razredService.napraviNoviRazred(razred));
	}

	@PostMapping(path = "/{razredId}/odeljenja")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> dodajNovoOdeljenjeRazredu(@PathVariable Integer razredId, @RequestParam Integer odeljenjeId) {
		return handler.handleResponse(razredService.dodajNovoOdeljenjeRazredu(razredId, odeljenjeId));
	}
	
	@DeleteMapping(path = "/{razredId}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> obrisiRazred(@PathVariable Integer razredId) {
		return handler.handleResponse(razredService.obrisiRazred(razredId));
	}
	
	@DeleteMapping(path = "/{razredId}/odeljenja")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> obrisiOdeljenjeKojePripadaRazredu(@PathVariable Integer razredId, @RequestParam Integer odeljenjeId) {
		return handler.handleResponse(razredService.obrisiOdeljenjeKojePripadaRazredu(razredId, odeljenjeId));
	}

}
