package com.iktpreobuka.elektronski_dnevnik.controllers.user_specific;

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
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.controllers.ServiceResponseHandler;
import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangeEmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangePassDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronski_dnevnik.services.user_specific.KorisnikServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/users")
@Secured("ROLE_ADMIN")
public class KorisnikController {
	
	/**
	 * Kontroleri za korisnika generalno
	 * GET - svi korisnici
	 * GET - svi korisnici jedne role
	 * GET - get 1 korisnik
	 * POST - napravi korisnika
	 * PUT - promeni email
	 * PUT - promeni sifru
	 * DELETE - obrisi korisnika
	 * 
	 * TODO Odredi secured anotacije
	 */
	
	@Autowired
	private KorisnikServiceImpl korisnikService;
	
	@Autowired
	private ServiceResponseHandler serviceResponseHandler;
	
	@GetMapping("/")
	public ResponseEntity<?> getAllUsers(){
		KorisnikServiceResponse response = korisnikService.getAllUsers();
		return serviceResponseHandler.handleResponse(response);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllUsers(@RequestBody RoleEntity role){
		KorisnikServiceResponse response = korisnikService.getAllUsers();
		return serviceResponseHandler.handleResponse(response);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllUsers(@RequestBody Integer role_id){
		KorisnikServiceResponse response = korisnikService.getAllUsers();
		return serviceResponseHandler.handleResponse(response);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllUsers(@RequestBody String role_name){
		KorisnikServiceResponse response = korisnikService.getAllUsers();
		return serviceResponseHandler.handleResponse(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id){
		KorisnikServiceResponse response = korisnikService.getUserById(id);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createUser(@Valid @RequestBody KorisnikEntity noviKorisnik) {
		KorisnikServiceResponse response = korisnikService.createNewUser(noviKorisnik);
		return serviceResponseHandler.handleResponse(response);
	}
	
	@PutMapping(value = "/{id}/email")
	public ResponseEntity<?> changeEmail(@PathVariable Integer id, @RequestBody UserChangeEmailDTO newUserData) {
		KorisnikServiceResponse response = korisnikService.changeEmail(id, newUserData);
		return serviceResponseHandler.handleResponse(response);
		
	}

	@PutMapping(value = "/{id}/password")
	public ResponseEntity<?> changePassword(@PathVariable Integer id, @RequestBody UserChangePassDTO newUserData) {
		KorisnikServiceResponse response = korisnikService.changePassword(id, newUserData);
		return serviceResponseHandler.handleResponse(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
		KorisnikServiceResponse response = korisnikService.deleteUser(id);
		return serviceResponseHandler.handleResponse(response);
	}



}
