package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.relationships.NastavnikPredajePredmet;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;

@Service
public class PredmetServiceImpl implements PredmetService{
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private NastavnikRepository nastavnikRepository;
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private NastavnikServiceImpl nastavnikService;

	@Override
	public ServiceResponse dobaviSvePredmete() {
		ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
		predmetRepository.findAll().forEach(p -> predmeti.add(p));
		if(predmeti.size() > 0) {
			return new ServiceResponse("Svi predmeti", HttpStatus.OK, predmeti);
		}
		return new ServiceResponse("P-1", "Nema predmeta", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviPredmetPoId(Integer predmetId) {
		Optional<PredmetEntity> predmet = predmetRepository.findById(predmetId);
		if(predmet.isPresent()) {
			return new ServiceResponse("Trazeni predmet",HttpStatus.OK, predmet.get());
		}
		return new ServiceResponse("P-2", "Trazeni predmet ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse napraviNoviPredmet(PredmetEntity predmet) {
		if(predmetRepository.findByNazivPredmeta(predmet.getNazivPredmeta()) != null) {
			predmetRepository.save(predmet);
			return new ServiceResponse("Predmet uspesno kreiran", HttpStatus.OK);
		}
		return new ServiceResponse("P-3", "Predmet sa takvim imenom vec postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajNovogNastavnikaKojiPredajePredmet(Integer predmetId, Integer nastavnikId) {
		Optional<PredmetEntity> oppredmet = predmetRepository.findById(predmetId);
		if(oppredmet.isPresent()) {
			ServiceResponse response = nastavnikService.dobaviNastavnikaPoId(nastavnikId);
			if(response.getValue() instanceof NastavnikEntity) {
				PredmetEntity predmet = oppredmet.get();
				NastavnikEntity nastavnik = (NastavnikEntity) response.getValue();
				NastavnikPredajePredmet npp = new NastavnikPredajePredmet(nastavnik, predmet);
				if(nastavnik.getPredmetiKojePredaje().contains(npp)) {
					return new ServiceResponse("N-3", "Nastavnik vec predaje ovaj predmet", HttpStatus.BAD_REQUEST);
				}
				nastavnik.getPredmetiKojePredaje().add(npp);
				predmet.getPredavaci().add(npp);
				predmetRepository.save(predmet);
				nastavnikRepository.save(nastavnik);
				return new ServiceResponse("Nastavnik uspesno dodat kao predavac", HttpStatus.OK);
			}
			return new ServiceResponse("N-2", "Nastavnik ne postoji", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("P-2", "Trazeni predmet ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajNovoOdeljenjeKojeSlusaPredmet(Integer predmetId, Integer nastavnikId,
			Integer odeljenjeId) {
		Optional<PredmetEntity> oppredmet = predmetRepository.findById(predmetId);
		if(oppredmet.isPresent()) {
			ServiceResponse response = nastavnikService.dobaviNastavnikaPoId(nastavnikId);
			if(response.getValue() instanceof NastavnikEntity) {
				if(odeljenjeRepository.findById(odeljenjeId).isPresent()) {
					PredmetEntity predmet = oppredmet.get();
					NastavnikEntity nastavnik = (NastavnikEntity) response.getValue();
					OdeljenjeEntity odeljenje = odeljenjeRepository.findById(odeljenjeId).get();
					NastavnikPredajePredmet npp = new NastavnikPredajePredmet(nastavnik, predmet);
					if(!(predmet.getPredavaci().contains(npp))) {
						predmet.getPredavaci().add(npp);
					}
					predmet.getOdeljenjaKojaSlusajuPredmet().add(odeljenje);
					predmetRepository.save(predmet);
					nastavnik.getPredmetiKojePredaje().add(npp);
					nastavnikRepository.save(nastavnik);
					odeljenje.getPredmetiKojeOdeljenjeSlusa().add(predmet);
					odeljenjeRepository.save(odeljenje);
					return new ServiceResponse("Predmet uspesno dodeljen odeljenju", HttpStatus.OK);
				}
				return new ServiceResponse("O-2", "Odeljenje ne postoji", HttpStatus.BAD_REQUEST);
			}
			return new ServiceResponse("N-2", "Nastavnik ne postoji", HttpStatus.BAD_REQUEST);
		}
		return new ServiceResponse("P-2", "Trazeni predmet ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiPredmet(Integer predmetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse obrisiNastavnikaKojiPredajePredmet(Integer predmetId, Integer nastavnikId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse obrisiOdeljenjeKojeSlusaPredmet(Integer predmetId, Integer odeljenjeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
