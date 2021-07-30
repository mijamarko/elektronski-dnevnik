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
import com.iktpreobuka.elektronski_dnevnik.entities.AdminEntity;
import com.iktpreobuka.elektronski_dnevnik.services.AdminServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/admin")
@Secured("ROLE_ADMIN")
public class AdminController {
	
	@Autowired
	private AdminServiceImpl adminService;
	
	ServiceResponseHandler handler = new ServiceResponseHandler();
	
	@GetMapping(path = "/")
	public ResponseEntity<?> dobaviSveAdmine() {
		return handler.handleResponse(adminService.dobaviSveAdmine());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> dobaviAdminaPoId(@RequestParam Integer id) {
		return handler.handleResponse(adminService.dobaviAdminaPoId(id));
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
	public ResponseEntity<?> napraviNovogAdmina(@Valid @RequestBody AdminEntity admin) {
		return handler.handleResponse(adminService.napraviNovogAdmina(admin));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> obrisiAdmina(@PathVariable Integer id) {
		return handler.handleResponse(adminService.obrisiAdmina(id));
	}




}
