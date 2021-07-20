package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.NastavnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;

@Service
public class NastavnikServiceImpl implements NastavnikService{
	
	@Autowired
	private KorisnikServiceImpl korisnikService;

	@Override
	public NastavnikServiceResponse getAllPredmetiOvogNastavnika(Integer id) {
		NastavnikEntity nastavnik;
		try {
			nastavnik = (NastavnikEntity) korisnikService.doesUserExist(id);
		} catch (ClassCastException e) {
			e.getMessage();
			e.printStackTrace();
			return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-1", "Korisnik nije nastavnik", (PredmetEntity) null, (OdeljenjeEntity) null);
		}
		ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
		nastavnik.getPredmetiKojePredaje().forEach(predmet -> {
			predmeti.add(predmet.getPredmet());
		});
		if(predmeti.size() > 0) {
			return new NastavnikServiceResponse(HttpStatus.OK, "", "Predmeti ovog nastavnika", predmeti, (OdeljenjeEntity) null);
		}
		return new NastavnikServiceResponse(HttpStatus.BAD_REQUEST, "K:N-2", "Nastavnik ne predaje nijedan predmet", (PredmetEntity) null, (OdeljenjeEntity) null);
	}

	
	
}
