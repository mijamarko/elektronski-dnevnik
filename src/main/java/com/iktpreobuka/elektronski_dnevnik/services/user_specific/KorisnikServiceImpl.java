package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangeEmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangePassDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;

@Service
public class KorisnikServiceImpl implements KorisnikService{
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepository;

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
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-3", "Korisnik vec postoji", (KorisnikEntity) null);
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
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-4", "Dostavljeni odaci se ne poklapaju sa sacuvanim podacima", (KorisnikEntity) null);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", (KorisnikEntity) null);
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
				return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-4", "Dostavljeni odaci se ne poklapaju sa sacuvanim podacima", (KorisnikEntity) null);
			}
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", (KorisnikEntity) null);
	}

	@Override
	public KorisnikServiceResponse deleteUser(Integer id) {
		KorisnikEntity korisnik = doesUserExist(id);
		if(!korisnik.equals(null)) {
			korisnikRepository.delete(korisnik);
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Korisnik uspesno obrisan", korisnik);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", (KorisnikEntity) null);
	}


	@Override
	public KorisnikServiceResponse getAllUsers() {
		if(!korisnikRepository.findAll().equals(null)) {
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Pronadjeni korisnici", (ArrayList<KorisnikEntity>) korisnikRepository.findAll());
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-1", "Nisu pronadjeni korisnici", (KorisnikEntity) null);	
	}


	@Override
	public KorisnikServiceResponse getAllUsers(RoleEntity role) {
		ArrayList<KorisnikEntity> korisnici = new ArrayList<KorisnikEntity>();
		korisnikRepository.findAll().forEach((korisnik) ->{
			if(korisnik.getRoles().contains(role)) {
				korisnici.add(korisnik);	
			}
		});
		if(korisnici.size() > 0) {
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Pronadjeni korisnici", korisnici);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-1", "Nisu pronadjeni korisnici ili rola ne postoji", (KorisnikEntity) null);
	}


	@Override
	public KorisnikServiceResponse getAllUsers(Integer role_id) {
		ArrayList<KorisnikEntity> korisnici = new ArrayList<KorisnikEntity>();
		RoleEntity role = roleRepository.findById(role_id).get();
		korisnikRepository.findAll().forEach((korisnik) ->{
			if(korisnik.getRoles().contains(role)) {
				korisnici.add(korisnik);	
			}
		});
		if(korisnici.size() > 0) {
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Pronadjeni korisnici", korisnici);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-1", "Nisu pronadjeni korisnici ili rola ne postoji", (KorisnikEntity) null);
	}


	@Override
	public KorisnikServiceResponse getAllUsers(String role_name) {
		ArrayList<KorisnikEntity> korisnici = new ArrayList<KorisnikEntity>();
		RoleEntity role = roleRepository.findByName(role_name);
		korisnikRepository.findAll().forEach((korisnik) ->{
			if(korisnik.getRoles().contains(role)) {
				korisnici.add(korisnik);	
			}
		});
		if(korisnici.size() > 0) {
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Pronadjeni korisnici", korisnici);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-1", "Nisu pronadjeni korisnici ili rola ne postoji", (KorisnikEntity) null);
	}

	@Override
	public KorisnikServiceResponse getUserById(Integer id) {
		KorisnikEntity korisnik = doesUserExist(id);
		if(!korisnik.equals(null)) {
			return new KorisnikServiceResponse(HttpStatus.OK, "", "Korisnik pronadjen", korisnik);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", (KorisnikEntity) null );

	}

}
