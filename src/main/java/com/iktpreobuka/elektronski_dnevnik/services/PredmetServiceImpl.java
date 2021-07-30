package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;

@Service
public class PredmetServiceImpl implements PredmetService{
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	@Autowired
	private NastavnikServiceImpl nastavnikService;
	
	@Autowired
	private OdeljenjeServiceImpl odeljenjeService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ServiceResponse dobaviSvePredmete() {
		logger.info("ulazi u metod dobaviSvePredmete");
		ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
		predmetRepository.findAll().forEach(p -> predmeti.add(p));
		if(predmeti.size() > 0) {
			return new ServiceResponse("Svi predmeti", HttpStatus.OK, predmeti);
		}
		logger.error("Nema predmeta");
		return new ServiceResponse("P-1", "Nema predmeta", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviPredmetPoId(Integer predmetId) {
		logger.info("ulazi u metod dobaviPredmetPoId trazeci predmet sa id %d", predmetId);
		Optional<PredmetEntity> predmet = predmetRepository.findById(predmetId);
		if(predmet.isPresent()) {
			return new ServiceResponse("Trazeni predmet",HttpStatus.OK, predmet.get());
		}
		logger.error("Predmet sa id %d ne postoji", predmetId);
		return new ServiceResponse("P-2", "Trazeni predmet ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse napraviNoviPredmet(PredmetEntity predmet) {
		logger.info("ulazi u metod napraviNoviPredmet");
		if(predmetRepository.findByNazivPredmeta(predmet.getNazivPredmeta()) == null) {
			predmetRepository.save(predmet);
			return new ServiceResponse("Predmet uspesno kreiran", HttpStatus.OK);
		}
		logger.error("Predmet sa imenom %s vec postoji", predmet.getNazivPredmeta());
		return new ServiceResponse("P-3", "Predmet sa takvim imenom vec postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajNovogNastavnikaKojiPredajePredmet(Integer predmetId, Integer nastavnikId) {
		logger.info("ulazi u metod dodajNovogNastavnikaKojiPredajePredmet trazeci predmet sa id %d", predmetId);
		Optional<PredmetEntity> oppredmet = predmetRepository.findById(predmetId);
		if(oppredmet.isPresent()) {
			ServiceResponse response = nastavnikService.dobaviNastavnikaPoId(nastavnikId);
			if(response.getValue() instanceof NastavnikEntity) {
				PredmetEntity predmet = oppredmet.get();
				NastavnikEntity nastavnik = (NastavnikEntity) response.getValue();
				if(nastavnik.getPredmetiKojePredaje().contains(predmet)) {
					logger.error("Nastavnik %d vec predaje predmet %d", nastavnikId, predmetId);
					return new ServiceResponse("N-3", "Nastavnik vec predaje ovaj predmet", HttpStatus.BAD_REQUEST);
				}
				nastavnikService.dodajNoviPredmetKojiPredaje(nastavnikId, predmetId);
				predmet.getPredavaci().add(nastavnik);
				predmetRepository.save(predmet);
				return new ServiceResponse("Nastavnik uspesno dodat kao predavac", HttpStatus.OK);
			}
			logger.error("Nastavnik sa id %d ne postoji", nastavnikId);
			return new ServiceResponse("N-2", "Nastavnik ne postoji", HttpStatus.BAD_REQUEST);
		}
		logger.error("Predmet sa id %d ne postoji", predmetId);
		return new ServiceResponse("P-2", "Trazeni predmet ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dodajNovoOdeljenjeKojeSlusaPredmet(Integer predmetId, Integer odeljenjeId) {
		logger.info("ulazi u metod dodajNovoOdeljenjeKojeSlusaPredmet trazeci predmet sa id %d", predmetId);
		Optional<PredmetEntity> oppredmet = predmetRepository.findById(predmetId);
		if(oppredmet.isPresent()) {
				if(odeljenjeRepository.findById(odeljenjeId).isPresent()) {
					PredmetEntity predmet = oppredmet.get();
					OdeljenjeEntity odeljenje = odeljenjeRepository.findById(odeljenjeId).get();
					ServiceResponse response = odeljenjeService.dodajPredmetKojiOdeljenjeSlusa(odeljenjeId, predmetId);
					if(response.getHttpStatus() != HttpStatus.BAD_REQUEST) {
						predmet.getOdeljenjaKojaSlusajuPredmet().add(odeljenje);
						predmetRepository.save(predmet);
						return response;
					}
					return response;
				}
				logger.error("Odeljenje sa id %d ne postoji", odeljenjeId);
				return new ServiceResponse("O-2", "Odeljenje ne postoji", HttpStatus.BAD_REQUEST);
		}
		logger.error("Predmet sa id %d ne postoji", predmetId);
		return new ServiceResponse("P-2", "Trazeni predmet ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiPredmet(Integer predmetId) {
		logger.info("ulazi u metod obrisiPredmet trazeci predmet sa id %d", predmetId);
		if(predmetRepository.existsById(predmetId)) {
			PredmetEntity predmet = predmetRepository.findById(predmetId).get();
			predmet.getPredavaci().forEach(n -> {
				nastavnikService.obrisiPredmetNastavniku(n.getId(), predmetId);
			});
			predmet.getOdeljenjaKojaSlusajuPredmet().forEach(o -> {
				odeljenjeService.obrisiPredmetKojiOdeljenjeSlusa(o.getId(), predmetId);
			});
			predmetRepository.delete(predmet);
			return new ServiceResponse("Predmet uspesno obrisan", HttpStatus.OK);
		}
		logger.error("Predmet sa id %d ne postoji", predmetId);
		return new ServiceResponse("P-2", "Trazeni predmet ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiNastavnikaKojiPredajePredmet(Integer predmetId, Integer nastavnikId) {
		if(predmetRepository.existsById(predmetId)) {
			PredmetEntity predmet = predmetRepository.findById(predmetId).get();
			nastavnikService.obrisiPredmetNastavniku(nastavnikId, predmetId);
			predmet.getPredavaci().removeIf(p -> p.getId() == nastavnikId);
			predmetRepository.save(predmet);
			return new ServiceResponse("Nastavnik uspesno obrisan iz liste predavaca", HttpStatus.OK);
		}
		return new ServiceResponse("P-2", "Trazeni predmet ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse obrisiOdeljenjeKojeSlusaPredmet(Integer predmetId, Integer odeljenjeId) {
		if(predmetRepository.existsById(predmetId)) {
			PredmetEntity predmet = predmetRepository.findById(predmetId).get();
			predmet.getOdeljenjaKojaSlusajuPredmet().removeIf(o -> o.getId() == odeljenjeId);
			odeljenjeService.obrisiPredmetKojiOdeljenjeSlusa(odeljenjeId, predmetId);
			predmetRepository.save(predmet);
			return new ServiceResponse("Odeljenje uspesno obrisana iz liste odeljenja koja slusaju predmet", HttpStatus.OK);
		}
		return new ServiceResponse("P-2", "Trazeni predmet ne postoji", HttpStatus.BAD_REQUEST);
	}
	
	

}
