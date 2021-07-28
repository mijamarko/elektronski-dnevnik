package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;

@Service
public class NastavnikServiceImpl implements NastavnikService {
	
	@Autowired
	private NastavnikRepository nastavnikRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private PredmetServiceImpl predmetService;


	@Override
	public ServiceResponse dobaviSveNastavnike() {
		ArrayList<NastavnikEntity> nastavnici = new ArrayList<NastavnikEntity>();
		nastavnikRepository.findAll().forEach(n -> {
			if(n.getRole().getName().equals("ROLE_NASTAVNIK")) {
				nastavnici.add((NastavnikEntity) n);
			}	
		});
		if (nastavnici.size() > 0) {
			return new ServiceResponse("Pronadjeni nastavnici", HttpStatus.OK,  nastavnici);
		}
		return new ServiceResponse("N-1", "Nema nastavnika", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviNastavnikaPoId(Integer id) {
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(id);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			return new ServiceResponse("Trazeni nastavnik", HttpStatus.OK, nastavnik);
		}
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSvePredmeteOvogNastavnika(Integer id) {
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(id);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
			nastavnik.getPredmetiKojePredaje().forEach(p -> predmeti.add(p));
			if(predmeti.size() > 0) {
				return new ServiceResponse("Predmeti za trazenog nastavnika", HttpStatus.OK, predmeti);
			}
			return new ServiceResponse("N-2", "Nastavnik ne predaje nijedan predmet", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ServiceResponse promeniEmail(Integer nastavnikId, EmailDTO noviPodaci) {
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			//TODO security
			if(nastavnik.getEmail().equals(noviPodaci.getEmail())) {
				nastavnik.setEmail(noviPodaci.getNoviEmail());
				nastavnikRepository.save(nastavnik);
				return new ServiceResponse("Email uspesno promenjen", HttpStatus.OK);
			}
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse promeniSifru(Integer nastavnikId, SifraDTO noviPodaci) {
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			//TODO security
			if(nastavnik.getEmail().equals(noviPodaci.getEmail())) {
				nastavnik.setSifra(noviPodaci.getNovaSifra());
				nastavnikRepository.save(nastavnik);
				return new ServiceResponse("Sifra uspesno promenjena", HttpStatus.OK);
			}
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse napraviNovogNastavnika(NastavnikEntity nastavnik) {
		if(nastavnikRepository.findByEmail(nastavnik.getEmail()) == null) {
			RoleEntity role = roleRepository.findByName("ROLE_NASTAVNIK");
			nastavnik.setRole(role);
			nastavnikRepository.save(nastavnik);
			return new ServiceResponse("Nastavnik uspesno kreiran", HttpStatus.OK, nastavnik);
		}
		return new ServiceResponse("N-3", "Nastavnik vec postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajNoviPredmetKojiPredaje(Integer nastavnikId, Integer predmetId) {
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			Optional<PredmetEntity> noviPredmet = predmetRepository.findById(predmetId);
			if(noviPredmet.isPresent()) {
				if(!nastavnik.getPredmetiKojePredaje().contains(noviPredmet.get())) {
					nastavnik.getPredmetiKojePredaje().add(noviPredmet.get());
					nastavnikRepository.save(nastavnik);
					predmetService.dodajNovogNastavnikaKojiPredajePredmet(predmetId, nastavnikId);
					return new ServiceResponse("Predmet uspesno dodat nastavniku", HttpStatus.OK);
					
				}
				return new ServiceResponse("N-4", "Nastavnik vec predaje ovaj predmet", HttpStatus.BAD_REQUEST);
			}
			return new ServiceResponse("P-1", "Predmet ne postoji", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajOdeljenjeNastavniku(Integer nastavnikId, Integer odeljenjeId) {
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			if(nastavnik.getOdeljenjeKomJeRazredni() == null) {
				Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
				if(odeljenje.isPresent()) {
					nastavnik.setOdeljenjeKomJeRazredni(odeljenje.get());
					nastavnikRepository.save(nastavnik);
					odeljenje.get().setRazredniStaresina(nastavnik);
					odeljenjeRepository.save(odeljenje.get());
					return new ServiceResponse("Odeljenje uspesno dodato nastavniku", HttpStatus.OK);
				}
				return new ServiceResponse("O-1", "Odeljenje ne postoji", HttpStatus.BAD_REQUEST);
			}
			return new ServiceResponse("N-4", "Nastavnik je vec razredni nekom odeljenju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiNastavnika(Integer id) {
		if(nastavnikRepository.findById(id).isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) nastavnikRepository.findById(id).get();
			nastavnikRepository.delete(nastavnik);
			return new ServiceResponse("Nastavnik uspesno obrisan", HttpStatus.OK);
		}
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiOdeljenjeNastaviku(Integer nastavnikId, Integer odeljenjeId) {
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			if(nastavnik.getOdeljenjeKomJeRazredni() != null) {
				Optional<OdeljenjeEntity> odeljenje = odeljenjeRepository.findById(odeljenjeId);
				if(odeljenje.isPresent()) {
					nastavnik.setOdeljenjeKomJeRazredni(null);
					nastavnikRepository.save(nastavnik);
					odeljenje.get().setRazredniStaresina(null);
					odeljenjeRepository.save(odeljenje.get());
					return new ServiceResponse("Odeljenje uspesno obrisano nastavniku", HttpStatus.OK);
				}
				return new ServiceResponse("O-1", "Odeljenje ne postoji", HttpStatus.BAD_REQUEST);
			}
			return new ServiceResponse("N-5", "Nastavnik nije razredni nijednom odeljenju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiPredmetNastavniku(Integer nastavnikId, Integer predmetId) {
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			Optional<PredmetEntity> oppredmetZaBrisanje = predmetRepository.findById(predmetId);
			if(oppredmetZaBrisanje.isPresent()) {
				PredmetEntity predmetZaBrisanje = oppredmetZaBrisanje.get();
				if(nastavnik.getPredmetiKojePredaje().contains(predmetZaBrisanje)) {
					nastavnik.getPredmetiKojePredaje().removeIf(p -> p.equals(predmetZaBrisanje));
					nastavnikRepository.save(nastavnik);
					return new ServiceResponse("Predmet uspesno obrisan nastavniku", HttpStatus.OK);
				}
				return new ServiceResponse("N-6", "Nastavnik ne predaje ovaj predmet", HttpStatus.BAD_REQUEST);
			}
			return new ServiceResponse("P-1", "Predmet ne postoji", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}
	
	

}
