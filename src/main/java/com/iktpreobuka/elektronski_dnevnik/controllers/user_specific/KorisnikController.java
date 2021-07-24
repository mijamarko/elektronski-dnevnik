package com.iktpreobuka.elektronski_dnevnik.controllers.user_specific;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.iktpreobuka.elektronski_dnevnik.controllers.ServiceResponseHandler;
import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.services.user_specific.KorisnikServiceImpl;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(path = "/api/v1/users")
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
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private KorisnikServiceImpl korisnikService;
	
	@Autowired
	private ServiceResponseHandler serviceResponseHandler;
	
	
	@GetMapping("/")
	public ResponseEntity<?> dobaviSveKorisnike(){
		KorisnikServiceResponse response = korisnikService.dobaviSveKorisnike();
		return serviceResponseHandler.handleResponse((KorisnikServiceResponse) response);
	}
	
	@GetMapping("/roles/{roleId}")
	public ResponseEntity<?> dobaviSveKorisnike(@PathVariable Integer roleId){
		KorisnikServiceResponse response = korisnikService.dobaviSveKorisnike(roleId);
		return serviceResponseHandler.handleResponse((KorisnikServiceResponse) response);
	}
	@GetMapping("/roles")
	public ResponseEntity<?> dobaviSveKorisnike(@RequestParam String roleName){
		KorisnikServiceResponse response = korisnikService.dobaviSveKorisnike(roleName);
		return serviceResponseHandler.handleResponse((KorisnikServiceResponse) response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> dobaviKorisnikaPoId(@PathVariable Integer id){
		KorisnikServiceResponse response = korisnikService.dobaviKorisnikaPoId(id);
		return serviceResponseHandler.handleResponse((KorisnikServiceResponse) response);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> napraviNovogKorisnika(@Valid @RequestBody KorisnikEntity noviKorisnik) {
		logger.info("Pogodjen post endpoint za pravljenje korisnika");
		KorisnikServiceResponse response = korisnikService.napraviNovogKorisnika(noviKorisnik);
		return serviceResponseHandler.handleResponse((KorisnikServiceResponse) response);
	}
	
	@PutMapping("/{id}/email")
	public ResponseEntity<?> promeniEmail(@PathVariable Integer id, @RequestBody KorisnikDTO noviPodaci) {
		KorisnikServiceResponse response = korisnikService.promeniEmail(id, noviPodaci);
		return serviceResponseHandler.handleResponse((KorisnikServiceResponse) response);
		
	}

	@PutMapping("/{id}/password")
	public ResponseEntity<?> promeniSifru(@PathVariable Integer id, @RequestBody KorisnikDTO noviPodaci) {
		KorisnikServiceResponse response = korisnikService.promeniSifru(id, noviPodaci);
		return serviceResponseHandler.handleResponse((KorisnikServiceResponse) response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> obrisiKorisnika(@PathVariable Integer id) {
		KorisnikServiceResponse response = korisnikService.obrisiKorisnika(id);
		return serviceResponseHandler.handleResponse((KorisnikServiceResponse) response);
	}



}
