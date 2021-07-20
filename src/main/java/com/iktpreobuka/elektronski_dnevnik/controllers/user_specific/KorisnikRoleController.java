package com.iktpreobuka.elektronski_dnevnik.controllers.user_specific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.controllers.ServiceResponseHandler;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.elektronski_dnevnik.services.user_specific.KorisnikRoleServiceImpl;
import com.iktpreobuka.elektronski_dnevnik.util.RestError;

@RestController
@RequestMapping(path = "/api/v1/users/roles")
@Secured("ROLE_ADMIN")
public class KorisnikRoleController {
	
	@Autowired
	private KorisnikRepository korisnikRepository;	
	
	@Autowired
	private KorisnikRoleServiceImpl korisnikRoleService;
	
	@Autowired
	private ServiceResponseHandler serviceResponseHandler;
	
	/**
	 * Kontroler za manipulisanje rolama korisnika
	 * GET - sve role jednog korisnika
	 * PUT - dodaj rolu korisniku
	 * PUT - ukloni rolu korisniku
	 */
	
	@GetMapping("/")
	public ResponseEntity<?> getRolesForSingleUser(Integer id) {
		if(!korisnikRepository.findAll().equals(null)) {
			return new ResponseEntity<Iterable<RoleEntity>>(korisnikRepository.findById(id).get().getRoles(), HttpStatus.OK);
		}
		return new ResponseEntity<RestError>(new RestError("K:R-1", "Korisnik nema role"), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value = "/{id}/add")
	public ResponseEntity<?> addRoleForUser(@PathVariable Integer id, @RequestBody RoleEntity role) {
		KorisnikServiceResponse korisnik = korisnikRoleService.addRoleForUser(id, role);
		return serviceResponseHandler.handleResponse(korisnik);
	}
	
	@PostMapping(value = "/{id}/add")
	public ResponseEntity<?> addRoleForUser(@PathVariable Integer id, @RequestBody Integer role_id) {
		KorisnikServiceResponse korisnik = korisnikRoleService.addRoleForUser(id, role_id);
		return serviceResponseHandler.handleResponse(korisnik);
	}
	
	@PostMapping(value = "/{id}/add")
	public ResponseEntity<?> addRoleForUser(@PathVariable Integer id, @RequestBody String role_name) {
		KorisnikServiceResponse korisnik = korisnikRoleService.addRoleForUser(id, role_name);
		return serviceResponseHandler.handleResponse(korisnik);
	}
	
	@PutMapping(value = "/{id}/remove")
	public ResponseEntity<?> removeRoleFromUser(@PathVariable Integer id, @RequestBody RoleEntity role) {
		KorisnikServiceResponse korisnik = korisnikRoleService.removeRoleFromUser(id, role);
		return serviceResponseHandler.handleResponse(korisnik);
	}
	
	@PutMapping(value = "/{id}/remove")
	public ResponseEntity<?> removeRoleFromUser(@PathVariable Integer id, @RequestBody Integer role_id) {
		KorisnikServiceResponse korisnik = korisnikRoleService.removeRoleFromUser(id, role_id);
		return serviceResponseHandler.handleResponse(korisnik);
	}
	
	@PutMapping(value = "/{id}/remove")
	public ResponseEntity<?> removeRoleFromUser(@PathVariable Integer id, @RequestBody String role_name) {
		KorisnikServiceResponse korisnik = korisnikRoleService.removeRoleFromUser(id, role_name);
		return serviceResponseHandler.handleResponse(korisnik);
	}

}
