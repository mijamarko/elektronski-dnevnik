package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.NastavnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.relationships.NastavnikPredajePredmet;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.PredmetRepository;

@Service
public class NastavnikServiceImpl implements NastavnikService {

	@Autowired
	private KorisnikServiceImpl korisnikService;

	@Autowired
	private NastavnikRepository nastavnikRepository;

	@Autowired
	private PredmetRepository predmetRepository;

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	public NastavnikEntity isUserNastavnik(Integer user_id) {
		NastavnikEntity nastavnik;
		try {
			nastavnik = (NastavnikEntity) korisnikService.doesUserExist(user_id);
		} catch (ClassCastException e) {
			e.getMessage();
			e.printStackTrace();
			return null;
		}
		return nastavnik;
	}

	@Override
	public NastavnikServiceResponse getAllPredmetiOvogNastavnika(Integer id) {
		if (!isUserNastavnik(id).equals(null)) {
			NastavnikEntity nastavnik = isUserNastavnik(id);
			ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
			nastavnik.getPredmetiKojePredaje().forEach(predmet -> {
				predmeti.add(predmet.getPredmet());
			});
			if (predmeti.size() > 0) {
				return new NastavnikServiceResponse(HttpStatus.OK, "", "Predmeti ovog nastavnika", predmeti,
						(OdeljenjeEntity) null);
			}
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-2", "Nastavnik ne predaje nijedan predmet",
					(PredmetEntity) null, (OdeljenjeEntity) null);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik",
				(PredmetEntity) null, (OdeljenjeEntity) null);

	}

	@Override
	public NastavnikServiceResponse addNewPredmet(Integer user_id, PredmetEntity predmet) {
		if (!isUserNastavnik(user_id).equals(null)) {
			NastavnikEntity nastavnik = isUserNastavnik(user_id);
			ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
			nastavnik.getPredmetiKojePredaje().forEach(p -> {
				predmeti.add(p.getPredmet());
			});
			if (predmeti.contains(predmet)) {
				return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-2",
						"Nastavnik vec predaje ovaj predmet", (PredmetEntity) null, (OdeljenjeEntity) null);
			}
			nastavnik.getPredmetiKojePredaje().add(new NastavnikPredajePredmet(nastavnik, predmet));
			nastavnikRepository.save(nastavnik);
			return new NastavnikServiceResponse(HttpStatus.OK, "", "Predmet uspesno dodat", predmet,
					(OdeljenjeEntity) null);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik",
				(PredmetEntity) null, (OdeljenjeEntity) null);
	}

	@Override
	public NastavnikServiceResponse addNewPredmet(Integer user_id, Integer predmet_id) {
		if (!isUserNastavnik(user_id).equals(null)) {
			NastavnikEntity nastavnik = isUserNastavnik(user_id);
			PredmetEntity predmet = predmetRepository.findById(predmet_id).get();
			if (!predmet.equals(null)) {
				ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
				nastavnik.getPredmetiKojePredaje().forEach(p -> {
					predmeti.add(p.getPredmet());
				});
				if (predmeti.contains(predmet)) {
					return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-2",
							"Nastavnik vec predaje ovaj predmet", (PredmetEntity) null, (OdeljenjeEntity) null);
				}
				nastavnik.getPredmetiKojePredaje().add(new NastavnikPredajePredmet(nastavnik, predmet));
				nastavnikRepository.save(nastavnik);
				return new NastavnikServiceResponse(HttpStatus.OK, "", "Predmet uspesno dodat", predmet,
						(OdeljenjeEntity) null);
			}
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "P - 1", "Predmet ne postoji",
					(PredmetEntity) null, (OdeljenjeEntity) null);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik",
				(PredmetEntity) null, (OdeljenjeEntity) null);
	}

	@Override
	public NastavnikServiceResponse addNewPredmet(Integer user_id, String predmet_name) {
		if (!isUserNastavnik(user_id).equals(null)) {
			NastavnikEntity nastavnik = isUserNastavnik(user_id);
			PredmetEntity predmet = predmetRepository.findByName(predmet_name);
			if (!predmet.equals(null)) {
				ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
				nastavnik.getPredmetiKojePredaje().forEach(p -> {
					predmeti.add(p.getPredmet());
				});
				if (predmeti.contains(predmet)) {
					return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-2",
							"Nastavnik vec predaje ovaj predmet", (PredmetEntity) null, (OdeljenjeEntity) null);
				}
				nastavnik.getPredmetiKojePredaje().add(new NastavnikPredajePredmet(nastavnik, predmet));
				nastavnikRepository.save(nastavnik);
				return new NastavnikServiceResponse(HttpStatus.OK, "", "Predmet uspesno dodat", predmet,
						(OdeljenjeEntity) null);
			}
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "P - 1", "Predmet ne postoji",
					(PredmetEntity) null, (OdeljenjeEntity) null);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik",
				(PredmetEntity) null, (OdeljenjeEntity) null);
	}

	@Override
	public NastavnikServiceResponse addNewOdeljenje(Integer user_id, OdeljenjeEntity odeljenje) {
		if (!isUserNastavnik(user_id).equals(null)) {
			NastavnikEntity nastavnik = isUserNastavnik(user_id);
			OdeljenjeEntity odeljenjeKomPredaje = nastavnik.getOdeljenje();
			if (odeljenjeKomPredaje.equals(null)) {
				if (!odeljenjeRepository.findById(odeljenje.getId()).get().equals(null)) {
					nastavnik.setOdeljenje(odeljenje);
					nastavnikRepository.save(nastavnik);
					return new NastavnikServiceResponse(HttpStatus.OK, "", "Odeljenje uspesno dodato",
							(PredmetEntity) null, odeljenje);
				}
				return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "O -1", "Odeljenje ne postoji",
						(PredmetEntity) null, (OdeljenjeEntity) null);
			}
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-3", "Nastavnik je vec razredni staresina",
					(PredmetEntity) null, (OdeljenjeEntity) null);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik",
				(PredmetEntity) null, (OdeljenjeEntity) null);
	}

	@Override
	public NastavnikServiceResponse addNewOdeljenje(Integer user_id, Integer odeljenje_id) {
		if (!isUserNastavnik(user_id).equals(null)) {
			NastavnikEntity nastavnik = isUserNastavnik(user_id);
			OdeljenjeEntity odeljenjeKomPredaje = nastavnik.getOdeljenje();
			if (odeljenjeKomPredaje.equals(null)) {
				if (!odeljenjeRepository.findById(odeljenje_id).get().equals(null)) {
					OdeljenjeEntity novoOdeljenje = (odeljenjeRepository.findById(odeljenje_id).get());
					nastavnik.setOdeljenje(novoOdeljenje);
					nastavnikRepository.save(nastavnik);
					return new NastavnikServiceResponse(HttpStatus.OK, "", "Odeljenje uspesno dodato",
							(PredmetEntity) null, novoOdeljenje);
				}
				return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "O -1", "Odeljenje ne postoji",
						(PredmetEntity) null, (OdeljenjeEntity) null);
			}
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-3", "Nastavnik je vec razredni staresina",
					(PredmetEntity) null, (OdeljenjeEntity) null);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik",
				(PredmetEntity) null, (OdeljenjeEntity) null);
	}

	@Override
	public NastavnikServiceResponse izmeniOdeljenjeKomJeRazredniStaresina(Integer user_id, OdeljenjeEntity odeljenje) {
		if (!isUserNastavnik(user_id).equals(null)) {
			NastavnikEntity nastavnik = isUserNastavnik(user_id);
			if (!odeljenjeRepository.findById(odeljenje.getId()).get().equals(null)) {
				nastavnik.setOdeljenje(odeljenje);
				nastavnikRepository.save(nastavnik);
				return new NastavnikServiceResponse(HttpStatus.OK, "", "Odeljenje uspesno promenjeno",
						(PredmetEntity) null, odeljenje);
			}
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "O -1", "Odeljenje ne postoji",
					(PredmetEntity) null, (OdeljenjeEntity) null);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik",
				(PredmetEntity) null, (OdeljenjeEntity) null);
	}

	@Override
	public NastavnikServiceResponse izmeniOdeljenjeKomJeRazredniStaresina(Integer user_id, Integer odeljenje_id) {
		if (!isUserNastavnik(user_id).equals(null)) {
			NastavnikEntity nastavnik = isUserNastavnik(user_id);
			if (!odeljenjeRepository.findById(odeljenje_id).equals(null)) {
				OdeljenjeEntity odeljenje = odeljenjeRepository.findById(odeljenje_id).get();
				nastavnik.setOdeljenje(odeljenje);
				nastavnikRepository.save(nastavnik);
				return new NastavnikServiceResponse(HttpStatus.OK, "", "Odeljenje uspesno promenjeno",
						(PredmetEntity) null, odeljenje);
			}
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "O -1", "Odeljenje ne postoji",
					(PredmetEntity) null, (OdeljenjeEntity) null);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik",
				(PredmetEntity) null, (OdeljenjeEntity) null);
	}

}
