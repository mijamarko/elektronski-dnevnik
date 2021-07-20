package com.iktpreobuka.elektronski_dnevnik.controllers.user_specific;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangeEmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangePassDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.elektronski_dnevnik.services.KorisnikServiceImpl;
import com.iktpreobuka.elektronski_dnevnik.util.RestError;

@RestController
@RequestMapping(path = "/api/v1/users")
@Secured("ROLE_ADMIN")
public class KorisnikController {
	
	/**
	 * Kontroleri za korisnika generalno
	 * GET - svi korisnici
	 * GET - get 1 korisnik
	 * POST - napravi korisnika
	 * PUT - promeni email
	 * PUT - promeni sifru
	 * DELETE - obrisi korisnika
	 * 
	 * TODO Odredi secured anotacije
	 */
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private KorisnikServiceImpl korisnikService;
	
	@GetMapping("/")
	public ResponseEntity<?> getAllKorisnici(){
		if(!korisnikRepository.findAll().equals(null)) {
			return new ResponseEntity<Iterable<KorisnikEntity>>(korisnikRepository.findAll(), HttpStatus.OK);
		}
		return new ResponseEntity<RestError>(new RestError("K:G-1", "Nisu pronadjeni korisnici"), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id){
		KorisnikEntity korisnik = korisnikService.doesUserExist(id);
		if(!korisnik.equals(null)) {
			return new ResponseEntity<KorisnikEntity>(korisnik, HttpStatus.OK);
		}
		return new ResponseEntity<RestError>(new RestError("K:G-2", "Korisnik nije pronadjen"), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createUser(@Valid @RequestBody KorisnikEntity noviKorisnik) {
		KorisnikServiceResponse response = korisnikService.createNewUser(noviKorisnik);
		return korisnikService.handleResponse(response);
	}
	
	@PutMapping(value = "/{id}/email")
	public ResponseEntity<?> changeEmail(@PathVariable Integer id, @RequestBody UserChangeEmailDTO newUserData) {
		KorisnikServiceResponse response = korisnikService.changeEmail(id, newUserData);
		return korisnikService.handleResponse(response);
		
	}

	@PutMapping(value = "/{id}/password")
	public ResponseEntity<?> changePassword(@PathVariable Integer id, @RequestBody UserChangePassDTO newUserData) {
		KorisnikServiceResponse response = korisnikService.changePassword(id, newUserData);
		return korisnikService.handleResponse(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
		KorisnikServiceResponse response = korisnikService.deleteUser(id);
		return korisnikService.handleResponse(response);
	}



}
