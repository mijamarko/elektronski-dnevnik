package com.iktpreobuka.elektronski_dnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.dto.UserLoginDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.UserTokenDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.elektronski_dnevnik.services.LoginServiceImpl;
import com.iktpreobuka.elektronski_dnevnik.util.Encryption;

@RestController
@RequestMapping(path = "/login")
public class LoginController {
	
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private LoginServiceImpl loginServiceImpl;
	
	@Value("${spring.security.secret-key}")
	private String secretKey;
	
	@Value("${spring.security.token-duration}")
	private Integer tokenDuration;
	
	@PostMapping()
	public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginData){	
		KorisnikEntity korisnik = korisnikRepository.findByEmail(userLoginData.getEmail());
		
		if(korisnik != null && Encryption.validatePassword(userLoginData.getSifra(), korisnik.getSifra())) {
			
			String token = loginServiceImpl.getJWT(korisnik, secretKey, tokenDuration);
			
			UserTokenDTO response = new UserTokenDTO(korisnik.getKorisnickoIme(), token);
			
			return new ResponseEntity<UserTokenDTO>(response, HttpStatus.OK);
		}	
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	

}
