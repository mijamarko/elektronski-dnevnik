package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
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

	@Override
	public ServiceResponse dobaviSveAdmine() {
		ArrayList<AdminEntity> administratori = new ArrayList<AdminEntity>();
		adminRepository.findAll().forEach(n -> {
			administratori.add((AdminEntity) n);
		});
		if (administratori.size() > 0) {
			return new ServiceResponse("Pronadjeni administratori", HttpStatus.OK,  administratori);
		}
		return new ServiceResponse("A-1", "Nema administratora", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviAdminaPoId(Integer id) {
		Optional<KorisnikEntity> korisnik = adminRepository.findById(id);
		if(korisnik.isPresent()) {
			AdminEntity administrator = (AdminEntity) korisnik.get();
			return new ServiceResponse("Trazeni administrator", HttpStatus.OK, administrator);
		}
		return new ServiceResponse("A-2", "Administrator nije pronadjen", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ServiceResponse promeniEmail(Integer adminId, EmailDTO noviPodaci) {
		Optional<KorisnikEntity> korisnik = adminRepository.findById(adminId);
		if(korisnik.isPresent()) {
			AdminEntity administrator = (AdminEntity) korisnik.get();
			//TODO security
			if(administrator.getEmail().equals(noviPodaci.getEmail())) {
				administrator.setEmail(noviPodaci.getNoviEmail());
				adminRepository.save(administrator);
				return new ServiceResponse("Email uspesno promenjen", HttpStatus.OK);
			}
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("A-2", "Administrator nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse promeniSifru(Integer adminId, SifraDTO noviPodaci) {
		Optional<KorisnikEntity> korisnik = adminRepository.findById(adminId);
		if(korisnik.isPresent()) {
			AdminEntity administrator = (AdminEntity) korisnik.get();
			//TODO security
			if(administrator.getEmail().equals(noviPodaci.getEmail())) {
				administrator.setSifra(noviPodaci.getNovaSifra());
				adminRepository.save(administrator);
				return new ServiceResponse("Sifra uspesno promenjena", HttpStatus.OK);
			}
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("A-2", "Administrator nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse napraviNovogAdmina(AdminEntity admin) {
		if(adminRepository.findByEmail(admin.getEmail()) == null) {
			RoleEntity role = roleRepository.findByName("ROLE_ADMIN");
			admin.setRole(role);
			adminRepository.save(admin);
			return new ServiceResponse("Admin uspesno kreiran", HttpStatus.OK, admin);
		}
		return new ServiceResponse("A-3", "Administrator vec postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiAdmina(Integer id) {
		if(adminRepository.findById(id).isPresent()) {
			AdminEntity administrator = (AdminEntity) adminRepository.findById(id).get();
			adminRepository.delete(administrator);
			return new ServiceResponse("Administrator uspesno obrisan", HttpStatus.OK);
		}
		return new ServiceResponse("A-2", "Administrator nije pronadjen", HttpStatus.BAD_REQUEST);
	}
	
	

}
