package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.NastavnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.relationships.NastavnikPredajePredmet;
import com.iktpreobuka.elektronski_dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik.services.OdeljenjeServiceImpl;
import com.iktpreobuka.elektronski_dnevnik.services.PredmetServiceImpl;

@Service
public class NastavnikServiceImpl implements NastavnikService {

	@Autowired
	private KorisnikServiceImpl korisnikService;

	@Autowired
	private PredmetServiceImpl predmetService;

	@Autowired
	private OdeljenjeServiceImpl odeljenjeService;

	@Autowired
	private NastavnikRepository nastavnikRepository;

	public NastavnikEntity korisnikJeNastavnik(Integer userId) {
		NastavnikEntity nastavnik;
		try {
			nastavnik = (NastavnikEntity) korisnikService.korisnikPostoji(userId);
		} catch (ClassCastException e) {
			e.getMessage();
			e.printStackTrace();
			return null;
		}
		return nastavnik;
	}

	@Override
	public NastavnikServiceResponse dobaviSvePredmeteOvogNastavnika(Integer id) {
		if (!korisnikJeNastavnik(id).equals(null)) {
			NastavnikEntity nastavnik = korisnikJeNastavnik(id);
			ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
			nastavnik.getPredmetiKojePredaje().forEach(predmet -> {
				predmeti.add(predmet.getPredmet());
			});
			if (predmeti.size() > 0) {
				return new NastavnikServiceResponse(HttpStatus.OK, "Predmeti ovog nastavnika", predmeti);
			}
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-2",
					"Nastavnik ne predaje nijedan predmet");
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik");

	}

	@Override
	public NastavnikServiceResponse dodajNoviPredmetNastavniku(Integer userId, KorisnikDTO req) {
		if (req.getPredmetId().equals(null) && req.getPredmetName().equals(null)) {
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "G-1", "Lose formiran zahtev");
		}
		if (!korisnikJeNastavnik(userId).equals(null)) {
			if (!predmetService.predmetPostoji(req).equals(null)) {
				PredmetEntity predmet = predmetService.predmetPostoji(req);
				ArrayList<PredmetEntity> predmetiKojePredaje = new ArrayList<PredmetEntity>();
				NastavnikEntity nastavnik = korisnikJeNastavnik(userId);
				nastavnik.getPredmetiKojePredaje().forEach(p -> {
					predmetiKojePredaje.add(p.getPredmet());
				});
				if (predmetiKojePredaje.contains(predmet)) {
					return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-3",
							"Nastavnik vec predaje ovaj predmet");
				}
				nastavnik.getPredmetiKojePredaje().add(new NastavnikPredajePredmet(nastavnik, predmet));
				nastavnikRepository.save(nastavnik);
				return new NastavnikServiceResponse(HttpStatus.OK, "Predmet uspesno dodat", predmet);
			}
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "P-1", "Predmet ne postoji");
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik");
	}

	@Override
	public NastavnikServiceResponse dodajNovoOdeljenjeNastavniku(Integer userId, KorisnikDTO req) {
		if (req.getOdeljenjeId().equals(null)) {
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "G-1", "Lose formiran zahtev");
		}
		if (!korisnikJeNastavnik(userId).equals(null)) {
			if (odeljenjeService.odeljenjePostoji(req).getOdeljenje().equals(null)) {
				return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "O -1", "Odeljenje ne postoji");
			}
			NastavnikEntity nastavnik = korisnikJeNastavnik(userId);
			if (nastavnik.getOdeljenje().equals(null)) {
				return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-3",
						"Nastavnik je vec razredni staresina");
			}
			OdeljenjeEntity odeljenje = odeljenjeService.odeljenjePostoji(req).getOdeljenje();
			nastavnik.setOdeljenje(odeljenje);
			return new NastavnikServiceResponse(HttpStatus.OK, "Odeljenje uspesno dodato", odeljenje);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik");
	}

	@Override
	public NastavnikServiceResponse izmeniOdeljenjeKomJeRazredniStaresina(Integer userId, KorisnikDTO req) {
		if (req.getOdeljenjeId().equals(null)) {
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "G-1", "Lose formiran zahtev");
		}
		if (!korisnikJeNastavnik(userId).equals(null)) {
			if (odeljenjeService.odeljenjePostoji(req).getOdeljenje().equals(null)) {
				return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "O -1", "Odeljenje ne postoji");
			}
			NastavnikEntity nastavnik = korisnikJeNastavnik(userId);
			OdeljenjeEntity odeljenje = odeljenjeService.odeljenjePostoji(req).getOdeljenje();
			nastavnik.setOdeljenje(odeljenje);
			return new NastavnikServiceResponse(HttpStatus.OK, "Odeljenje uspesno izmenjeno", odeljenje);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik");
	}

	@Override
	public NastavnikServiceResponse obrisiPredmetNastavniku(Integer userId, KorisnikDTO req) {
		if (req.getPredmetId().equals(null) && req.getPredmetName().equals(null)) {
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "G-1", "Lose formiran zahtev");
		}
		if (!korisnikJeNastavnik(userId).equals(null)) {
			if (!predmetService.predmetPostoji(req).equals(null)) {
				PredmetEntity predmet = predmetService.predmetPostoji(req);
				ArrayList<PredmetEntity> predmetiKojePredaje = new ArrayList<PredmetEntity>();
				NastavnikEntity nastavnik = korisnikJeNastavnik(userId);
				nastavnik.getPredmetiKojePredaje().forEach(p -> {
					predmetiKojePredaje.add(p.getPredmet());
				});
				if (predmetiKojePredaje.contains(predmet)) {
					nastavnik.getPredmetiKojePredaje().forEach(p -> {
						if (p.getPredmet().equals(predmet)) {
							nastavnik.getPredmetiKojePredaje().remove(p);
						}
					});
					nastavnikRepository.save(nastavnik);
					return new NastavnikServiceResponse(HttpStatus.OK, "Predmet uspesno obrisan", predmet);
				}
				return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-4",
						"Nastavnik ne predaje ovaj predmet");
			}
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "P-1", "Predmet ne postoji");
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik");
	}

	public NastavnikServiceResponse obrisiOdeljenjeNastavniku(Integer userId, KorisnikDTO req) {
		if (req.getOdeljenjeId().equals(null)) {
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "G-1", "Lose formiran zahtev");
		}
		if (!korisnikJeNastavnik(userId).equals(null)) {
			if (odeljenjeService.odeljenjePostoji(req).getOdeljenje().equals(null)) {
				return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "O -1", "Odeljenje ne postoji");
			}
			NastavnikEntity nastavnik = korisnikJeNastavnik(userId);
			nastavnik.setOdeljenje(null);
			return new NastavnikServiceResponse(HttpStatus.OK, "Odeljenje uspesno obrisano");
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik");
	}

}
