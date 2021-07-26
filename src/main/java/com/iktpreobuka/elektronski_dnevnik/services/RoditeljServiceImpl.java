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

	@Override
	public ServiceResponse dobaviSveRoditelje() {
		ArrayList<RoditeljEntity> roditelji = new ArrayList<RoditeljEntity>();
		roditeljRepository.findAll().forEach(n -> {
			roditelji.add((RoditeljEntity) n);
		});
		if (roditelji.size() > 0) {
			return new ServiceResponse("Pronadjeni roditelji", HttpStatus.OK,  roditelji);
		}
		return new ServiceResponse("R-1", "Nema roditelja", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviRoditeljaPoId(Integer id) {
		Optional<KorisnikEntity> korisnik = roditeljRepository.findById(id);
		if(korisnik.isPresent()) {
			RoditeljEntity roditelj = (RoditeljEntity) korisnik.get();
			return new ServiceResponse("Trazeni roditelj", HttpStatus.OK,  roditelj);
		}
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviDecuRoditelja(Integer roditeljId) {
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
			return new ServiceResponse("R-3", "Roditelj trenutno nema dete upisano u skolu", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse promeniEmail(Integer roditeljId, EmailDTO noviPodaci) {
		Optional<KorisnikEntity> korisnik = roditeljRepository.findById(roditeljId);
		if(korisnik.isPresent()) {
			RoditeljEntity roditelj = (RoditeljEntity) korisnik.get();
			//TODO security
			if(roditelj.getEmail().equals(noviPodaci.getEmail())) {
				roditelj.setEmail(noviPodaci.getNoviEmail());
				roditeljRepository.save(roditelj);
				return new ServiceResponse("Email uspesno promenjen", HttpStatus.OK);
			}
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse promeniSifru(Integer roditeljId, SifraDTO noviPodaci) {
		Optional<KorisnikEntity> korisnik = roditeljRepository.findById(roditeljId);
		if(korisnik.isPresent()) {
			RoditeljEntity roditelj = (RoditeljEntity) korisnik.get();
			//TODO security
			if(roditelj.getEmail().equals(noviPodaci.getEmail())) {
				roditelj.setSifra(noviPodaci.getNovaSifra());
				roditeljRepository.save(roditelj);
				return new ServiceResponse("Sifra uspesno promenjena", HttpStatus.OK);
			}
			return new ServiceResponse("G-1", "Podaci se ne poklapaju", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse napraviNovogRoditelja(RoditeljEntity roditelj) {
		if(roditeljRepository.findByEmail(roditelj.getEmail()) == null) {
			RoleEntity role = roleRepository.findByName("ROLE_RODITELJ");
			roditelj.setRole(role);
			roditeljRepository.save(roditelj);
			return new ServiceResponse("Roditelj uspesno kreiran", HttpStatus.OK, roditelj);
		}
		return new ServiceResponse("R-3", "Roditelj vec postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajDeteRoditelju(Integer roditeljId, Integer ucenikId) {
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
				return new ServiceResponse("R-4", "Dete je vec dodeljeno roditelju", HttpStatus.BAD_REQUEST);
			}
			return new ServiceResponse("U-1", "Ucenik ne postoji", HttpStatus.BAD_REQUEST);			
		}
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiRoditelja(Integer id) {
		if(roditeljRepository.findById(id).isPresent()) {
			RoditeljEntity roditelj = (RoditeljEntity) roditeljRepository.findById(id).get();
			roditeljRepository.delete(roditelj);
			return new ServiceResponse("Roditelj  uspesno obrisan", HttpStatus.OK);
		}
		return new ServiceResponse("R-2", "Roditelj nije pronadjen", HttpStatus.BAD_REQUEST);
	}
	
	

}
