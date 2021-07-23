package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.UcenikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.IzostanakEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.elektronski_dnevnik.services.PredmetServiceImpl;

@Service
public class UcenikServiceImpl implements UcenikService {

	@Autowired
	private UcenikRepository ucenikRepository;
	@Autowired
	private PredmetServiceImpl predmetService;

	@Override
	public UcenikEntity korisnikJeUcenik(Integer id) {
		return ucenikRepository.existsById(id) ? ucenikRepository.findById(id).get() : null;
	}

	@Override
	public UcenikServiceResponse dobaviSveOceneUcenika(Integer ucenikId) {
		if (!korisnikJeUcenik(ucenikId).equals(null)) {
			UcenikEntity ucenik = korisnikJeUcenik(ucenikId);
			return new UcenikServiceResponse(HttpStatus.OK, "Ocene za trazenog ucenika", ucenik.getOcene());
		}
		return new UcenikServiceResponse(HttpStatus.BAD_REQUEST, "K:U-1", "Korisnik nije ucenik");
	}

	@Override
	public UcenikServiceResponse dobaviSveOceneUcenikaIzPredmeta(Integer ucenikId, KorisnikDTO req) {
		if (req.getPredmetId().equals(null) && req.getPredmetName().equals(null)) {
			return new UcenikServiceResponse(HttpStatus.BAD_REQUEST, "G-1", "Lose formiran zahtev");
		}
		if (!korisnikJeUcenik(ucenikId).equals(null)) {
			UcenikEntity ucenik = korisnikJeUcenik(ucenikId);
			PredmetEntity predmet = predmetService.predmetPostoji(req);
			ArrayList<OcenaEntity> ocene = new ArrayList<OcenaEntity>();
			ucenik.getOcene().forEach(o -> {
				if (o.getNastavnikPredmet().getPredmet().equals(predmet)) {
					ocene.add(o);
				}
			});
			if (ocene.size() > 0) {
				return new UcenikServiceResponse(HttpStatus.OK, "Ocene za trazenog ucenika iz trazenog predmeta",
						ocene);
			}
			return new UcenikServiceResponse(HttpStatus.BAD_REQUEST, "K:U-2",
					"Ucenik nema ocene za trazeni predmet ili predmet ne postoji");
		}
		return new UcenikServiceResponse(HttpStatus.BAD_REQUEST, "K:U-1", "Korisnik nije ucenik");
	}

	@Override
	public UcenikServiceResponse dobaviSveIzostankeUcenika(Integer ucenikId) {
		if (!korisnikJeUcenik(ucenikId).equals(null)) {
			UcenikEntity ucenik = korisnikJeUcenik(ucenikId);
			return new UcenikServiceResponse(HttpStatus.OK, "Izostanci za trazenog ucenika", ucenik.getIzostanci());
		}
		return new UcenikServiceResponse(HttpStatus.BAD_REQUEST, "K:U-1", "Korisnik nije ucenik");
	}

	@Override
	public UcenikServiceResponse dodajOdeljenjeKojePohadja(Integer ucenikId, KorisnikDTO req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UcenikServiceResponse dodajRoditelja(Integer ucenikId, Integer roditeljId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UcenikServiceResponse dodajIzostanakUceniku(Integer ucenikId, IzostanakEntity izostanak) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UcenikServiceResponse dodajOcenuUceniku(Integer ucenikId, OcenaEntity ocena) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UcenikServiceResponse promeniOdeljenjeKojePohadja(Integer ucenikId, KorisnikDTO req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UcenikServiceResponse promeniIzostanakUceniku(Integer ucenikId, KorisnikDTO req) {
		// TODO Auto-generated method stub
		return null;
	}

}
