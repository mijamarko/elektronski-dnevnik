package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.KorisnikServiceResponse;
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
	
	public RoleEntity rolaPostoji(KorisnikDTO req) {
		if(req.getRoleId().equals(null) && req.getRoleName().equals(null)) {
			return null;
		}
		if(!req.getRoleId().equals(null)) {
			if(roleRepository.existsById(req.getRoleId())) {
				return roleRepository.findById(req.getRoleId()).get();
			}
		} else if (!req.getRoleName().equals(null)) {
			if(!roleRepository.findByName(req.getRoleName()).equals(null)){
				return roleRepository.findByName(req.getRoleName());
			}
		}
		return null;	
	}

	@Override
	public KorisnikServiceResponse dodajRoluKorisniku(Integer id, KorisnikDTO req) {
		if(req.getRoleId().equals(null) && req.getRoleName().equals(null)) {
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "G-1", "Lose formiran zahtev");
		}
		if(!rolaPostoji(req).equals(null)) {
			if(!korisnikService.korisnikPostoji(id).equals(null)) {
				KorisnikEntity korisnik = korisnikService.korisnikPostoji(id);
				RoleEntity rola = rolaPostoji(req);
				if(!korisnik.getRoles().contains(rola)) {
					korisnik.getRoles().add(rola);
					korisnikRepository.save(korisnik);
					return new KorisnikServiceResponse(HttpStatus.OK, "Rola uspesno dodata", korisnik);
				}
				return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-2", "Korisnik vec ima specificiranu rolu");
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen");
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-4", "Rola ne postoji");
	}
	
	@Override
	public KorisnikServiceResponse obrisiRoluKorisniku(Integer id, KorisnikDTO req) {
		if(req.getRoleId().equals(null) && req.getRoleName().equals(null)) {
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "G-1", "Lose formiran zahtev");
		}
		if(!rolaPostoji(req).equals(null)) {
			if(!korisnikService.korisnikPostoji(id).equals(null)) {
				KorisnikEntity korisnik = korisnikService.korisnikPostoji(id);
				RoleEntity rola = rolaPostoji(req);
				if(korisnik.getRoles().contains(rola)) {
					korisnik.getRoles().remove(rola);
					korisnikRepository.save(korisnik);
					return new KorisnikServiceResponse(HttpStatus.OK, "Rola uspesno obrisana", korisnik);
				}
				return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-3", "Korisnik nema specificiranu rolu");
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen");
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:R-4", "Rola ne postoji");
	}
}
