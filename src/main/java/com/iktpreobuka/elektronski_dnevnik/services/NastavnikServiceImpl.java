package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
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
	
	@Autowired
	private OdeljenjeServiceImpl odeljenjeService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ServiceResponse dobaviSveNastavnike() {
		logger.info("Ulazi u dobaviSveNastavnike metod");
		ArrayList<NastavnikEntity> nastavnici = new ArrayList<NastavnikEntity>();
		nastavnikRepository.findAll().forEach(n -> {
			if(n.getRole().getName().equals("ROLE_NASTAVNIK")) {
				nastavnici.add((NastavnikEntity) n);
			}	
		});
		if (nastavnici.size() > 0) {
			return new ServiceResponse("Pronadjeni nastavnici", HttpStatus.OK,  nastavnici);
		}
		logger.error("Nema nastavnika");
		return new ServiceResponse("N-1", "Nema nastavnika", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviNastavnikaPoId(Integer id) {
		logger.info("Ulazi u dobaviNastavnikaPoId metod i trazi nastavnika %d", id);
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(id);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			return new ServiceResponse("Trazeni nastavnik", HttpStatus.OK, nastavnik);
		}
		logger.error("Nastavnik sa id %d nije pronadjen", id);
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviSvePredmeteOvogNastavnika(Integer id) {
		logger.info("Ulazi u dobaviSvePredmeteOvogNastavnika metod i trazi predmete nastavnika %d", id);
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(id);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
			nastavnik.getPredmetiKojePredaje().forEach(p -> predmeti.add(p));
			if(predmeti.size() > 0) {
				return new ServiceResponse("Predmeti za trazenog nastavnika", HttpStatus.OK, predmeti);
			}
			logger.error("Nastavnik sa id %d ne predaje nijedan predmet", id);
			return new ServiceResponse("N-2", "Nastavnik ne predaje nijedan predmet", HttpStatus.BAD_REQUEST);
		}
		logger.error("Nastavnik sa id %d nije pronadjen", id);
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ServiceResponse promeniEmail(Integer nastavnikId, EmailDTO noviPodaci) {
		logger.info("Ulazi u promeniEmail metod za NastavnikService trazeci korisnika %d", nastavnikId);
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			if(nastavnik.getEmail().equals(noviPodaci.getEmail()) && encoder.matches(noviPodaci.getSifra(), nastavnik.getSifra())) {
				nastavnik.setEmail(noviPodaci.getNoviEmail());
				nastavnikRepository.save(nastavnik);
				logger.info("Email promenjen nastavniku sa id %d", nastavnikId);
				return new ServiceResponse("Email uspesno promenjen", HttpStatus.OK);
			}
			logger.warn("Podaci za promenu emaila se ne poklapaju za korisnika sa id %d", nastavnikId);
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		logger.error("Nastavnik sa id %d nije pronadjen", nastavnikId);
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse promeniSifru(Integer nastavnikId, SifraDTO noviPodaci) {
		logger.info("Ulazi u promeniSifru metod za NastavnikService trazeci korisnika %d", nastavnikId);
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			if(nastavnik.getEmail().equals(noviPodaci.getEmail()) && encoder.matches(noviPodaci.getSifra(), nastavnik.getSifra())) {
				nastavnik.setSifra(encoder.encode(noviPodaci.getNovaSifra()));
				nastavnikRepository.save(nastavnik);
				logger.info("Sifra promenjena nastavniku sa id %d", nastavnikId);
				return new ServiceResponse("Sifra uspesno promenjena", HttpStatus.OK);
			}
			logger.warn("Podaci za promenu sifre se ne poklapaju za korisnika sa id %d", nastavnikId);
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		logger.error("Nastavnik sa id %d nije pronadjen", nastavnikId);
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse napraviNovogNastavnika(NastavnikEntity nastavnik) {
		logger.info("Ulazi u metodu napraviNovogNastavnika");
		if(nastavnikRepository.findByEmail(nastavnik.getEmail()) == null) {
			RoleEntity role = roleRepository.findByName("ROLE_NASTAVNIK");
			nastavnik.setRole(role);
			nastavnik.setSifra(encoder.encode(nastavnik.getSifra()));
			nastavnikRepository.save(nastavnik);
			logger.info("Uspesno kreiran nastavnik %s %s", nastavnik.getIme(), nastavnik.getPrezime());
			return new ServiceResponse("Nastavnik uspesno kreiran", HttpStatus.OK, nastavnik);
		}
		logger.warn("Nastavnik sa emailom %s vec postoji!", nastavnik.getEmail());
		return new ServiceResponse("N-3", "Nastavnik vec postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajNoviPredmetKojiPredaje(Integer nastavnikId, Integer predmetId) {
		logger.info("Ulazi u dodajNoviPredmetKojiPredaje metod trazeci nastavnika sa id %d", nastavnikId);
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			Optional<PredmetEntity> noviPredmet = predmetRepository.findById(predmetId);
			if(noviPredmet.isPresent()) {
				if(!nastavnik.getPredmetiKojePredaje().contains(noviPredmet.get())) {
					nastavnik.getPredmetiKojePredaje().add(noviPredmet.get());
					nastavnikRepository.save(nastavnik);
					predmetService.dodajNovogNastavnikaKojiPredajePredmet(predmetId, nastavnikId);
					logger.info("Nastanviku sa id %d uspesno dodat predmet sa id %d", nastavnikId, predmetId);
					return new ServiceResponse("Predmet uspesno dodat nastavniku", HttpStatus.OK);
				}
				logger.warn("Nastanvik sa id %d vec predaje predmet sa id %d", nastavnikId, predmetId);
				return new ServiceResponse("N-4", "Nastavnik vec predaje ovaj predmet", HttpStatus.BAD_REQUEST);
			}
			logger.warn("Predmet sa id %d ne postoji", predmetId);
			return new ServiceResponse("P-1", "Predmet ne postoji", HttpStatus.BAD_REQUEST);
		}
		logger.error("Nastavnik sa id %d nije pronadjen", nastavnikId);
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajOdeljenjeNastavniku(Integer nastavnikId, Integer odeljenjeId) {
		logger.info("Ulazi u dodajOdeljenjeNastavniku metod trazeci nastavnika sa id %d", nastavnikId);
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			if(nastavnik.getOdeljenjeKomJeRazredni() == null) {
				ServiceResponse response = odeljenjeService.dodajRazrednogStaresinuOdeljenju(odeljenjeId, nastavnikId);
				if(response.getHttpStatus() != HttpStatus.BAD_REQUEST) {
					nastavnik.setOdeljenjeKomJeRazredni((OdeljenjeEntity) response.getValue());
					nastavnikRepository.save(nastavnik);
					logger.info("Nastanviku sa id %d uspesno dodato odeljenje sa id %d", nastavnikId, odeljenjeId);
					return new ServiceResponse("Odeljenje uspesno dodato nastavniku", HttpStatus.OK);
				}
				logger.warn("Odeljenje sa id %d ne postoji", odeljenjeId);
				return response;
			}
			logger.warn("Nastavnik sa id %d je vec razredni staresina odeljenju %d", nastavnikId, nastavnik.getOdeljenjeKomJeRazredni().getId());
			return new ServiceResponse("N-4", "Nastavnik je vec razredni nekom odeljenju", HttpStatus.BAD_REQUEST);
		}
		logger.error("Nastavnik sa id %d nije pronadjen", nastavnikId);
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiNastavnika(Integer id) {
		logger.info("Ulazi u obrisiNastavnika metod trazeci nastavnika sa id %d", id);
		if(nastavnikRepository.findById(id).isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) nastavnikRepository.findById(id).get();
			nastavnikRepository.delete(nastavnik);
			logger.info("Nastavnik sa id %d obrisan", id);
			return new ServiceResponse("Nastavnik uspesno obrisan", HttpStatus.OK);
		}
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiOdeljenjeNastaviku(Integer nastavnikId, Integer odeljenjeId) {
		logger.info("Ulazi u obrisiOdeljenjeNastaviku metod trazeci nastavnika sa id %d", nastavnikId);
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
					logger.info("Nastavniku sa id %d uspesno obrisano odeljenje sa id %d", nastavnikId, odeljenjeId);
					return new ServiceResponse("Odeljenje uspesno obrisano nastavniku", HttpStatus.OK);
				}
				logger.warn("Odeljenje sa id %d ne postoji", odeljenjeId);
				return new ServiceResponse("O-1", "Odeljenje ne postoji", HttpStatus.BAD_REQUEST);
			}
			logger.warn("Nastavnik sa id %d nije razredni staresina nijednom odeljenju", nastavnikId);
			return new ServiceResponse("N-5", "Nastavnik nije razredni nijednom odeljenju", HttpStatus.BAD_REQUEST);
		}
		logger.error("Nastavnik sa id %d nije pronadjen", nastavnikId);
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiPredmetNastavniku(Integer nastavnikId, Integer predmetId) {
		logger.info("Ulazi u obrisiPredmetNastavniku metod trazeci nastavnika sa id %d", nastavnikId);
		Optional<KorisnikEntity> korisnik = nastavnikRepository.findById(nastavnikId);
		if(korisnik.isPresent()) {
			NastavnikEntity nastavnik = (NastavnikEntity) korisnik.get();
			Optional<PredmetEntity> oppredmetZaBrisanje = predmetRepository.findById(predmetId);
			if(oppredmetZaBrisanje.isPresent()) {
				PredmetEntity predmetZaBrisanje = oppredmetZaBrisanje.get();
				if(nastavnik.getPredmetiKojePredaje().contains(predmetZaBrisanje)) {
					nastavnik.getPredmetiKojePredaje().removeIf(p -> p.equals(predmetZaBrisanje));
					nastavnikRepository.save(nastavnik);
					logger.info("Nastavniku sa id %d uspesno obrisan predmet sa id %d", nastavnikId, predmetId);
					return new ServiceResponse("Predmet uspesno obrisan nastavniku", HttpStatus.OK);
				}
				logger.warn("Nastanvik sa id %d ne predaje predmet sa id %d", nastavnikId, predmetId);
				return new ServiceResponse("N-6", "Nastavnik ne predaje ovaj predmet", HttpStatus.BAD_REQUEST);
			}
			logger.warn("Predmet sa id %d ne postoji", predmetId);
			return new ServiceResponse("P-1", "Predmet ne postoji", HttpStatus.BAD_REQUEST);
		}
		logger.error("Nastavnik sa id %d nije pronadjen", nastavnikId);
		return new ServiceResponse("N-1", "Nastavnik nije pronadjen", HttpStatus.BAD_REQUEST);
	}
	
	

}
