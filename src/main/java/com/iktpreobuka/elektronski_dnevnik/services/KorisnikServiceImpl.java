package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangeEmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangePassDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;
import com.iktpreobuka.elektronski_dnevnik.util.RestError;

@Service
public class KorisnikServiceImpl implements KorisnikService{
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepository;
	
	public ResponseEntity<?> handleResponse(KorisnikServiceResponse response){
		if(response.getHttpResponseCode().equals(HttpStatus.OK)) {
			return new ResponseEntity<KorisnikEntity>(response.getKorisnik(), response.getHttpResponseCode());
		}
		return new ResponseEntity<RestError>(new RestError(response.getCode(), response.getMessage()), response.getHttpResponseCode());
	}

	@Override
	public KorisnikEntity doesUserExist(Integer id) {
		if(korisnikRepository.existsById(id)) {
			return korisnikRepository.findById(id).get();
		}
		return null;
	}
	

	@Override
	public Boolean doesEmailExist(String email) {
		if(!korisnikRepository.findByEmail(email).equals(null)) {
			return true;
		}
		return false;
	}

	@Override
	public KorisnikServiceResponse createNewUser(KorisnikEntity korisnik) {
		if(!doesEmailExist(korisnik.getEmail())) {
			korisnik.setSifra(encoder.encode(korisnik.getSifra()));
			korisnikRepository.save(korisnik);
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Korisnik uspesno kreiran", korisnik);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-3", "Korisnik vec postoji", null);
	}

	@Override
	public KorisnikServiceResponse changeEmail(Integer id, UserChangeEmailDTO newUserData) {
		KorisnikEntity korisnik = doesUserExist(id);
		if(!korisnik.equals(null)) {
			String stariEmail = newUserData.getStariEmail();
			String sifra = newUserData.getSifra();
			if(korisnik.getEmail().equals(stariEmail) && encoder.matches(sifra, korisnik.getSifra())) {
				korisnik.setEmail(newUserData.getNoviEmail());
				korisnikRepository.save(korisnik);
				return new KorisnikServiceResponse(HttpStatus.OK, "", "Email uspesno promenjen", korisnik);
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-4", "Dostavljeni odaci se ne poklapaju sa sacuvanim podacima", null);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", null);
	}


	@Override
	public KorisnikServiceResponse changePassword(Integer id, UserChangePassDTO newUserData) {
		KorisnikEntity korisnik = doesUserExist(id);
		if(!korisnik.equals(null)) {
			String email = newUserData.getEmail();
			String staraSifra = newUserData.getStaraSifra();
			if(korisnik.getEmail().equals(email) && encoder.matches(staraSifra, korisnik.getSifra())) {
				korisnik.setSifra(encoder.encode(newUserData.getNovaSifra()));
				korisnikRepository.save(korisnik);
				return new KorisnikServiceResponse(HttpStatus.OK, "", "Sifra uspesno promenjena", korisnik);
			} else {
				return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-4", "Dostavljeni odaci se ne poklapaju sa sacuvanim podacima", null);
			}
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", null);
	}

	@Override
	public KorisnikServiceResponse deleteUser(Integer id) {
		KorisnikEntity korisnik = doesUserExist(id);
		if(!korisnik.equals(null)) {
			korisnikRepository.delete(korisnik);
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Korisnik uspesno obrisan", korisnik);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", null);
	}

	@Override
	public KorisnikServiceResponse addRoleForUser(Integer id, RoleEntity role) {
		KorisnikEntity korisnik = doesUserExist(id);
		if(!korisnik.equals(null)) {
			List<RoleEntity> roles = korisnik.getRoles();
				for(RoleEntity rola: roles) {
					if(rola.equals(role)) {
						return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-2", "Korisnik vec ima specificiranu rolu", null);
					}
				}
			korisnik.addRole(role);
			korisnikRepository.save(korisnik);
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno dodata", korisnik);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", null);
	}
	
	@Override
	public KorisnikServiceResponse addRoleForUser(Integer id, Integer role_id) {
		KorisnikEntity korisnik = doesUserExist(id);
		if(!korisnik.equals(null)) {
			List<RoleEntity> roles = korisnik.getRoles();
			RoleEntity role = roleRepository.findById(role_id).get();
			for(RoleEntity rola: roles) {
				if(!role.equals(null) && rola.equals(role)) {
					return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-2", "Korisnik vec ima specificiranu rolu", null);
				}
				return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-4", "Rola ne postoji", null);
			}
			korisnik.addRole(role);
			korisnikRepository.save(korisnik);
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno dodata", korisnik);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", null);
	}
	
	@Override
	public KorisnikServiceResponse addRoleForUser(Integer id, String  role_name) {
		KorisnikEntity korisnik = doesUserExist(id);
		if(!korisnik.equals(null)) {
			List<RoleEntity> roles = korisnik.getRoles();
			RoleEntity role = roleRepository.findByName(role_name);
			for(RoleEntity rola: roles) {
				if(!role.equals(null) && rola.equals(role)) {
					return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-2", "Korisnik vec ima specificiranu rolu", null);
				} else if(role.equals(null) && rola.equals(role)) {
					return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-4", "Rola ne postoji", null);
				}			
			}
			korisnik.addRole(role);
			korisnikRepository.save(korisnik);
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno dodata", korisnik);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", null);
	}

	//TODO sredi if-else
	
	@Override
	public KorisnikServiceResponse removeRoleFromUser(Integer id, RoleEntity role) {
		KorisnikEntity korisnik = doesUserExist(id);
		if(!korisnik.equals(null)) {
			List<RoleEntity> roles = korisnik.getRoles();
			for(RoleEntity rola : roles) {
				if (rola.equals(role)){
					korisnik.getRoles().remove(role);
					korisnikRepository.save(korisnik);
					return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno uklonjena", korisnik);
				}
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-3", "Korisnik nema specificiranu rolu", null);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", null);
	}
	
	@Override
	public KorisnikServiceResponse removeRoleFromUser(Integer id, Integer role_id) {
		KorisnikEntity korisnik = doesUserExist(id);
		if(!korisnik.equals(null)) {
			List<RoleEntity> roles = korisnik.getRoles();
			for(RoleEntity rola : roles) {
				if (rola.equals(role)){
					korisnik.getRoles().remove(role);
					korisnikRepository.save(korisnik);
					return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno uklonjena", korisnik);
				}
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-3", "Korisnik nema specificiranu rolu", null);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", null);
	}
	
	
	
	
	
	
	
	

}
