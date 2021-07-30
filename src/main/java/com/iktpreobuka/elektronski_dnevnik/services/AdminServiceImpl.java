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
import com.iktpreobuka.elektronski_dnevnik.entities.AdminEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.AdminRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ServiceResponse dobaviSveAdmine() {
		logger.info("Ulazi u dobaviSveAdmine metod");
		ArrayList<AdminEntity> administratori = new ArrayList<AdminEntity>();
		logger.info("Trazi sve administratore u repozitorijumu");
		adminRepository.findAll().forEach(n -> {
			if(n.getRole().getName().equals("ROLE_ADMIN")) {
				administratori.add((AdminEntity) n);
			}
		});
		if (administratori.size() > 0) {
			return new ServiceResponse("Pronadjeni administratori", HttpStatus.OK,  administratori);
		}
		logger.error("Administratori nisu pronadjeni");
		return new ServiceResponse("A-1", "Nema administratora", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviAdminaPoId(Integer id) {
		logger.info("Ulazi u dobaviAdminaPoId metod trazeci korisnika %d", id);
		Optional<KorisnikEntity> korisnik = adminRepository.findById(id);
		if(korisnik.isPresent()) {
			logger.info("Administrator sa id %d pronadjen", id);
			AdminEntity administrator = (AdminEntity) korisnik.get();
			return new ServiceResponse("Trazeni administrator", HttpStatus.OK, administrator);
		}
		logger.error("Administrator sa id %d nije pronadjen", id);
		return new ServiceResponse("A-2", "Administrator nije pronadjen", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ServiceResponse promeniEmail(Integer adminId, EmailDTO noviPodaci) {
		
		logger.info("Ulazi u promeniEmail metod za AdminService trazeci korisnika %d", adminId);
		Optional<KorisnikEntity> korisnik = adminRepository.findById(adminId);
		if(korisnik.isPresent()) {
			AdminEntity administrator = (AdminEntity) korisnik.get();
			if(administrator.getEmail().equals(noviPodaci.getEmail()) && encoder.matches(noviPodaci.getSifra(), administrator.getSifra())) {
				administrator.setEmail(noviPodaci.getNoviEmail());
				adminRepository.save(administrator);
				logger.info("Email promenjen administratoru sa id %d", adminId);
				return new ServiceResponse("Email uspesno promenjen", HttpStatus.OK);
			}
			logger.warn("Podaci za promenu emaila se ne poklapaju za korisnika sa id %d", adminId);
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		logger.error("Administrator sa id %d nije pronadjen", adminId);
		return new ServiceResponse("A-2", "Administrator nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse promeniSifru(Integer adminId, SifraDTO noviPodaci) {
		logger.info("Ulazi u promeniSifru metod za AdminService trazeci korisnika %d", adminId);
		Optional<KorisnikEntity> korisnik = adminRepository.findById(adminId);
		if(korisnik.isPresent()) {
			AdminEntity administrator = (AdminEntity) korisnik.get();
			if(administrator.getEmail().equals(noviPodaci.getEmail()) && encoder.matches(noviPodaci.getSifra(), administrator.getSifra())) {
				administrator.setSifra(encoder.encode(noviPodaci.getNovaSifra()));
				adminRepository.save(administrator);
				logger.info("Sifra promenjena administratoru sa id %d", adminId);
				return new ServiceResponse("Sifra uspesno promenjena", HttpStatus.OK);
			}
			logger.warn("Podaci za promenu sifre se ne poklapaju za korisnika sa id %d", adminId);
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		logger.error("Administrator sa id %d nije pronadjen", adminId);
		return new ServiceResponse("A-2", "Administrator nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse napraviNovogAdmina(AdminEntity admin) {
		logger.info("Ulazi u metodu napraviNovogAdmina");
		if(adminRepository.findByEmail(admin.getEmail()) == null) {
			RoleEntity role = roleRepository.findByName("ROLE_ADMIN");
			admin.setRole(role);
			admin.setSifra(encoder.encode(admin.getSifra()));
			adminRepository.save(admin);
			logger.info("administrator uspesno kreiran");
			return new ServiceResponse("Admin uspesno kreiran", HttpStatus.OK, admin);
		}
		logger.error("Administrator vec postoji");
		return new ServiceResponse("A-3", "Administrator vec postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiAdmina(Integer id) {
		logger.info("Ulazi u metodu obrisiAdmina");
		if(adminRepository.findById(id).isPresent()) {
			AdminEntity administrator = (AdminEntity) adminRepository.findById(id).get();
			adminRepository.delete(administrator);
			logger.info("Administrator uspesno obrisan");
			return new ServiceResponse("Administrator uspesno obrisan", HttpStatus.OK);
		}
		logger.error("Administrator sa id %d nije pronadjen", id);
		return new ServiceResponse("A-2", "Administrator nije pronadjen", HttpStatus.BAD_REQUEST);
	}
	
	

}
