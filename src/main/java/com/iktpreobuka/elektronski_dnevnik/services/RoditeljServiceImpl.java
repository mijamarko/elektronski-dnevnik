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
import com.iktpreobuka.elektronski_dnevnik.entities.RoditeljEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;

@Service
public class RoditeljServiceImpl implements RoditeljService {
	
	@Autowired
	private RoditeljRepository roditeljRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public ServiceResponse dobaviSveRoditelje() {
		logger.info("Ulazi u metodu dobaviSveRoditelje");
		ArrayList<RoditeljEntity> roditelji = new ArrayList<RoditeljEntity>();
		roditeljRepository.findAll().forEach(n -> {
			roditelji.add((RoditeljEntity) n);
		});
		if (roditelji.size() > 0) {
			return new ServiceResponse("Pronadjeni roditelji", HttpStatus.OK,  roditelji);
		}
		logger.error("Nema roditelja");
		return new ServiceResponse("R-1", "Nema roditelja", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviRoditeljaPoId(Integer id) {
		logger.info("Ulazi u metodu dobaviRoditeljaPoId trazeci roditelja %d", id);
		Optional<KorisnikEntity> korisnik = roditeljRepository.findById(id);
		if(korisnik.isPresent()) {
			RoditeljEntity roditelj = (RoditeljEntity) korisnik.get();
			return new ServiceResponse("Trazeni roditelj", HttpStatus.OK,  roditelj);
		}
		logger.error("Roditelj sa id %d ne postoji", id);
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviDecuRoditelja(Integer roditeljId) {
		logger.info("Ulazi u metodu dobaviDecuRoditelja trazeci roditelja %d", roditeljId);
		Optional<KorisnikEntity> korisnik = roditeljRepository.findById(roditeljId);
		if(korisnik.isPresent()) {
			RoditeljEntity roditelj = (RoditeljEntity) korisnik.get();
			ArrayList<UcenikEntity> deca = new ArrayList<UcenikEntity>();
			roditelj.getDeca().forEach(d -> {
				deca.add(d);
			});
			if(deca.size() > 0) {
				return new ServiceResponse("Deca izbranog roditelja", HttpStatus.OK, deca);
			}
			logger.error("Roditelj %d trenutno nema dete upisano u skolu", roditeljId);
			return new ServiceResponse("R-3", "Roditelj trenutno nema dete upisano u skolu", HttpStatus.BAD_REQUEST);
		}
		logger.error("Roditelj sa id %d ne postoji", roditeljId);
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse promeniEmail(Integer roditeljId, EmailDTO noviPodaci) {
		logger.info("Ulazi u promeniEmail metod za RoditeljService trazeci korisnika %d", roditeljId);
		Optional<KorisnikEntity> korisnik = roditeljRepository.findById(roditeljId);
		if(korisnik.isPresent()) {
			RoditeljEntity roditelj = (RoditeljEntity) korisnik.get();
			if(roditelj.getEmail().equals(noviPodaci.getEmail()) && encoder.matches(noviPodaci.getSifra(), roditelj.getSifra())) {
				roditelj.setEmail(noviPodaci.getNoviEmail());
				roditeljRepository.save(roditelj);
				logger.info("Email promenjen roditelju sa id %d", roditeljId);
				return new ServiceResponse("Email uspesno promenjen", HttpStatus.OK);
			}
			logger.warn("Podaci za promenu emaila se ne poklapaju za korisnika sa id %d", roditeljId);
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		logger.error("Roditelj sa id %d ne postoji", roditeljId);
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse promeniSifru(Integer roditeljId, SifraDTO noviPodaci) {
		logger.info("Ulazi u promeniSifru metod za RoditeljService trazeci korisnika %d", roditeljId);
		Optional<KorisnikEntity> korisnik = roditeljRepository.findById(roditeljId);
		if(korisnik.isPresent()) {
			RoditeljEntity roditelj = (RoditeljEntity) korisnik.get();
			if(roditelj.getEmail().equals(noviPodaci.getEmail()) && encoder.matches(noviPodaci.getSifra(), roditelj.getSifra())) {
				roditelj.setSifra(encoder.encode(noviPodaci.getNovaSifra()));
				roditeljRepository.save(roditelj);
				logger.info("Sifra promenjena roditelju sa id %d", roditeljId);
				return new ServiceResponse("Sifra uspesno promenjena", HttpStatus.OK);
			}
			logger.warn("Podaci za promenu sifre se ne poklapaju za korisnika sa id %d", roditeljId);
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		logger.error("Roditelj sa id %d ne postoji", roditeljId);
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse napraviNovogRoditelja(RoditeljEntity roditelj) {
		logger.info("Ulazi u metodu napraviNovogRoditelja ");
		if(roditeljRepository.findByEmail(roditelj.getEmail()) == null) {
			RoleEntity role = roleRepository.findByName("ROLE_RODITELJ");
			roditelj.setRole(role);
			roditelj.setSifra(encoder.encode(roditelj.getSifra()));
			roditeljRepository.save(roditelj);
			return new ServiceResponse("Roditelj uspesno kreiran", HttpStatus.OK, roditelj);
		}
		logger.error("Roditelj sa emailom %s vec postoji", roditelj.getEmail());
		return new ServiceResponse("R-3", "Roditelj vec postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajDeteRoditelju(Integer roditeljId, Integer ucenikId) {
		logger.info("Ulazi u metodu dodajDeteRoditelju trazeci roditelja %d", roditeljId);
		Optional<KorisnikEntity> korisnik = roditeljRepository.findById(roditeljId);
		if(korisnik.isPresent()) {
			RoditeljEntity roditelj = (RoditeljEntity) korisnik.get();
			ArrayList<UcenikEntity> deca = new ArrayList<UcenikEntity>();
			roditelj.getDeca().forEach(d -> {
				deca.add(d);
			});
			Optional<KorisnikEntity> ucenik = ucenikRepository.findById(ucenikId);
			if(ucenik.isPresent()) {
				UcenikEntity dete = (UcenikEntity) ucenik.get();
				if(!(deca.contains(dete))) {
					roditelj.getDeca().add(dete);
					dete.getRoditelji().add(roditelj);
					ucenikRepository.save(dete);
					roditeljRepository.save(roditelj);
					return new ServiceResponse("Dete uspesno dodato roditelju", HttpStatus.OK, roditelj);
				}
				logger.error("Dete %d je vec dodeljeno roditelju %d", ucenikId, roditeljId);
				return new ServiceResponse("R-4", "Dete je vec dodeljeno roditelju", HttpStatus.BAD_REQUEST);
			}
			logger.error("Ucenik %d ne postoji", ucenikId);
			return new ServiceResponse("U-1", "Ucenik ne postoji", HttpStatus.BAD_REQUEST);			
		}
		logger.error("Roditelj sa id %d ne postoji", roditeljId);
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiRoditelja(Integer id) {
		logger.info("Ulazi u metodu obrisiRoditelja trazeci roditelja %d", id);
		if(roditeljRepository.findById(id).isPresent()) {
			RoditeljEntity roditelj = (RoditeljEntity) roditeljRepository.findById(id).get();
			roditeljRepository.delete(roditelj);
			logger.info("Roditelj sa id %d uspesno obrisan", id);
			return new ServiceResponse("Roditelj  uspesno obrisan", HttpStatus.OK);
		}
		logger.error("Roditelj sa id %d ne postoji", id);
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}
	
	

}
