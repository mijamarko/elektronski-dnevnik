package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;

@Service
public class KorisnikRoleServiceImpl implements KorisnikRoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private KorisnikServiceImpl korisnikService;
	
	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public KorisnikServiceResponse addRoleForUser(Integer id, RoleEntity role) {
		KorisnikEntity korisnik = korisnikService.doesUserExist(id);
		if(!korisnik.equals(null)) {
			if(!roleRepository.findByName(role.getName()).equals(null)) {
				if(korisnik.getRoles().contains(role)) {
					return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-2", "Korisnik vec ima specificiranu rolu", (KorisnikEntity) null);	
				}
				korisnik.getRoles().add(role);
				korisnikRepository.save(korisnik);
				return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno dodata", korisnik);
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-4", "Rola ne postoji", (KorisnikEntity) null);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", (KorisnikEntity) null);
	}
	
	@Override
	public KorisnikServiceResponse addRoleForUser(Integer id, Integer role_id) {
		KorisnikEntity korisnik = korisnikService.doesUserExist(id);
		if(!korisnik.equals(null)) {
			if(roleRepository.findById(role_id).isPresent()) {
				RoleEntity role = roleRepository.findById(role_id).get();
				if(korisnik.getRoles().contains(role)) {
					return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-2", "Korisnik vec ima specificiranu rolu", (KorisnikEntity) null);	
				}
				korisnik.getRoles().add(role);
				korisnikRepository.save(korisnik);
				return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno dodata", korisnik);
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-4", "Rola ne postoji", (KorisnikEntity) null);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", (KorisnikEntity) null);
	}
	
	@Override
	public KorisnikServiceResponse addRoleForUser(Integer id, String  role_name) {
		KorisnikEntity korisnik = korisnikService.doesUserExist(id);
		if(!korisnik.equals(null)) {
			if(!roleRepository.findByName(role_name).equals(null)) {
				RoleEntity role = roleRepository.findByName(role_name);
				if(korisnik.getRoles().contains(role)) {
					return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-2", "Korisnik vec ima specificiranu rolu", (KorisnikEntity) null);	
				}
				korisnik.getRoles().add(role);
				korisnikRepository.save(korisnik);
				return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno dodata", korisnik);
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-4", "Rola ne postoji", (KorisnikEntity) null);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", (KorisnikEntity) null);
	}
	
	@Override
	public KorisnikServiceResponse removeRoleFromUser(Integer id, RoleEntity role) {
		KorisnikEntity korisnik = korisnikService.doesUserExist(id);
		if(!korisnik.equals(null)) {
			if(korisnik.getRoles().contains(role)) {
				korisnik.getRoles().remove(role);
				korisnikRepository.save(korisnik);
				return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno uklonjena", korisnik);
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-3", "Korisnik nema specificiranu rolu, ili rola ne postoji", (KorisnikEntity) null);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", (KorisnikEntity) null);
	}
	
	@Override
	public KorisnikServiceResponse removeRoleFromUser(Integer id, Integer role_id) {
		KorisnikEntity korisnik = korisnikService.doesUserExist(id);
		if(!korisnik.equals(null)) {
			RoleEntity role = roleRepository.findById(role_id).get();
			if(korisnik.getRoles().contains(role)) {
				korisnik.getRoles().remove(role);
				korisnikRepository.save(korisnik);
				return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno uklonjena", korisnik);
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-3", "Korisnik nema specificiranu rolu, ili rola ne postoji", (KorisnikEntity) null);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", (KorisnikEntity) null);
	}

	@Override
	public KorisnikServiceResponse removeRoleFromUser(Integer id, String role_name) {
		KorisnikEntity korisnik = korisnikService.doesUserExist(id);
		if(!korisnik.equals(null)) {
			RoleEntity role = roleRepository.findByName(role_name);
			if(korisnik.getRoles().contains(role)) {
				korisnik.getRoles().remove(role);
				korisnikRepository.save(korisnik);
				return new KorisnikServiceResponse(HttpStatus.OK, "", "Rola uspesno uklonjena", korisnik);
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-3", "Korisnik nema specificiranu rolu, ili rola ne postoji", (KorisnikEntity) null);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen", (KorisnikEntity) null);
	}

}
