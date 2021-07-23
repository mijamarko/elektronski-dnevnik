package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.KorisnikRepository;

@Service
public class KorisnikServiceImpl implements KorisnikService{
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public KorisnikEntity korisnikPostoji(Integer id) {
		if(korisnikRepository.existsById(id)) {
			return korisnikRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public Boolean emailPostoji(String email) {
		if(!korisnikRepository.findByEmail(email).equals(null)) {
			return true;
		}
		return false;
	}
	
	@Override
	public KorisnikServiceResponse dobaviSveKorisnike(KorisnikDTO req) {
		ArrayList<KorisnikEntity> korisnici = new ArrayList<KorisnikEntity>();
		if(!req.getRoleId().equals(null)) {
			korisnikRepository.findAll().forEach(k -> {
				k.getRoles().forEach(r -> {
					if(r.getId() == req.getRoleId()) {
						korisnici.add(k);
					}
				});
			});
		} else if (!req.getRoleName().equals(null)) {
			korisnikRepository.findAll().forEach(k -> {
				k.getRoles().forEach(r -> {
					if(r.getName().equals(req.getRoleName())) {
						korisnici.add(k);
					}
				});
			});
		} else if (req.getRoleId().equals(null) && req.getRoleName().equals(null)) {
			korisnikRepository.findAll().forEach(k -> korisnici.add(k));	
		} else {
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "G-1", "Lose formiran zahtev");
		}
		
		if(korisnici.size() > 0) {
			return new KorisnikServiceResponse(HttpStatus.OK, "Pronadjeni korisnici", korisnici);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-1", "Nisu pronadjeni korisnici ili rola ne postoji");
	}

	@Override
	public KorisnikServiceResponse napraviNovogKorisnika(KorisnikEntity korisnik) {
		if(!emailPostoji(korisnik.getEmail())) {
			korisnik.setSifra(encoder.encode(korisnik.getSifra()));
			korisnikRepository.save(korisnik);
			return new KorisnikServiceResponse(HttpStatus.OK, "Korisnik uspesno kreiran", korisnik);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-3", "Korisnik vec postoji");
	}

	@Override
	public KorisnikServiceResponse dobaviKorisnikaPoId(Integer id) {
		KorisnikEntity korisnik = korisnikPostoji(id);
		if(!korisnik.equals(null)) {
			return new KorisnikServiceResponse(HttpStatus.OK, "Korisnik pronadjen", korisnik);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen");

	}

	@Override
	public KorisnikServiceResponse promeniEmail(Integer id, KorisnikDTO noviPodaci) {
		KorisnikEntity korisnik = korisnikPostoji(id);
		if(!korisnik.equals(null)) {
			String stariEmail = noviPodaci.getEmail();
			String sifra = noviPodaci.getSifra();
			if(korisnik.getEmail().equals(stariEmail) && encoder.matches(sifra, korisnik.getSifra())) {
				korisnik.setEmail(noviPodaci.getNoviEmail());
				korisnikRepository.save(korisnik);
				return new KorisnikServiceResponse(HttpStatus.OK, "Email uspesno promenjen", korisnik);
			}
			return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-4", "Dostavljeni odaci se ne poklapaju sa sacuvanim podacima");
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen");
	}

	@Override
	public KorisnikServiceResponse promeniSifru(Integer id, KorisnikDTO noviPodaci) {
		KorisnikEntity korisnik = korisnikPostoji(id);
		if(!korisnik.equals(null)) {
			String email = noviPodaci.getEmail();
			String staraSifra = noviPodaci.getSifra();
			if(korisnik.getEmail().equals(email) && encoder.matches(staraSifra, korisnik.getSifra())) {
				korisnik.setSifra(encoder.encode(noviPodaci.getNovaSifra()));
				korisnikRepository.save(korisnik);
				return new KorisnikServiceResponse(HttpStatus.OK, "Sifra uspesno promenjena", korisnik);
			} else {
				return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-4", "Dostavljeni odaci se ne poklapaju sa sacuvanim podacima");
			}
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen");
	}

	@Override
	public KorisnikServiceResponse obrisiKorisnika(Integer id) {
		KorisnikEntity korisnik = korisnikPostoji(id);
		if(!korisnik.equals(null)) {
			korisnikRepository.delete(korisnik);
			return new KorisnikServiceResponse(HttpStatus.OK, "Korisnik uspesno obrisan", korisnik);
		}
		return new KorisnikServiceResponse(HttpStatus.BAD_REQUEST, "K:G-2", "Korisnik nije pronadjen");
	}


}
