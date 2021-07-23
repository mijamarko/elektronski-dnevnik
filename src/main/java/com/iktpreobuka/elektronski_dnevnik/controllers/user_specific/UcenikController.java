package com.iktpreobuka.elektronski_dnevnik.controllers.user_specific;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.IzostanakEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EPolugodiste;

@RestController
@RequestMapping(path = "/api/v1/users/ucenici")
public class UcenikController {

	/**
	 * Upravljanje obelezjima specificnim za ucenike 
	 * TODO GET - izracunaj prosecnu ocenu jednog ucenika za jedan predmet za jedno polugodiste 
	 * TODO GET - izracunaj prosecnu ocenu jednog ucenika za jedan predmet za celu godinu 
	 * TODO GET - izracunaj prosecnu ocenu jednog ucenika za celo polugodiste
	 * TODO GET - izracunaj prosecnu ocenu jednog ucenika za celu godinu 
	 * TODO GET - sve ocene ucenika 
	 * TODO GET - sve ocene ucenika iz jednog predmeta
	 * TODO GET - svi izostanci (ili izostanci nekog tipa) 
	 * TODO POST - dodaj odeljenje koje pohadja 
	 * TODO POST - dodaj roditelja 
	 * TODO POST - dodaj izostanak 
	 * TODO POST - dodaj ocenu uceniku 
	 * TODO PUT - promeni odeljenje koje pohadja 
	 * TODO PUT - izmeni izostanak
	 * TODO DELETE - ukloni (boze oprosti) roditelja
	 * 
	 */

	
	//------------------------------------------------------------------------------------------
	@GetMapping(path = "/{ucenikId}/ocene/prosek-predmeta")
	public ResponseEntity<?> dobaviProsekPredmetaPolugodista(@PathVariable Integer ucenikId,
			@RequestBody KorisnikDTO req) {
		return null;
	}

	@GetMapping(path = "/{ucenikId}/ocene/prosek-predmeta-godina")
	public ResponseEntity<?> dobaviProsekPredmetaGodina(@PathVariable Integer ucenikId,
			@RequestBody KorisnikDTO req) {
		return null;
	}

	@GetMapping(path = "/{ucenikId}/ocene/prosek-polugodista")
	public ResponseEntity<?> dobaviProsekUcenikaPolugodista(@PathVariable Integer ucenikId,
			@RequestParam EPolugodiste polugodiste) {
		return null;
	}

	@GetMapping(path = "/{ucenikId}/ocene/prosek-godine")
	public ResponseEntity<?> dobaviProsekUcenikaGodina(@PathVariable Integer ucenikId) {
		return null;
	}
	//---------------------------------------------------------------------------------------------
	
	@GetMapping(path = "/{ucenikId}/ocene")
	public ResponseEntity<?> dobaviSveOceneUcenika(@PathVariable Integer ucenikId) {
		return null;
	}
	
	@GetMapping(path = "/{ucenikId}/ocene/predmet")
	public ResponseEntity<?> dobaviSveOceneUcenikaIzPredmeta(@PathVariable Integer ucenikId,
			@RequestBody KorisnikDTO req) {
		return null;
	}
	
	@GetMapping("/{ucenikId}/izostanci")
	public ResponseEntity<?> dobaviSveIzostankeUcenika(@RequestParam Integer ucenikId) {
		return null;
	}


	@PostMapping(path = "/{ucenikId}/odeljenje")
	public ResponseEntity<?> dodajOdeljenjeKojePohadja(@PathVariable Integer ucenikId,
			@RequestBody KorisnikDTO req) {
		return null;
	}
	
	@PostMapping(path = "/{ucenikId}/roditelji")
	public ResponseEntity<?> dodajRoditelja(@PathVariable Integer ucenikId,
			@RequestParam Integer roditeljId) {
		return null;
	}
	
	@PostMapping(path = "/{ucenikId}/izostanci")
	public ResponseEntity<?> dodajIzostanakUceniku(@PathVariable Integer ucenikId,
			@Valid @RequestBody IzostanakEntity izostanak) {
		return null;
	}
	
	@PostMapping(path = "/{ucenikId}/ocene")
	public ResponseEntity<?> dodajOcenuUceniku(@PathVariable Integer ucenikId,
			@Valid @RequestBody OcenaEntity ocena) {
		return null;
	}
	
	@PutMapping(path = "/{ucenikId}/odeljenje")
	public ResponseEntity<?> promeniOdeljenjeKojePohadja(@PathVariable Integer ucenikId,
			@RequestBody KorisnikDTO req) {
		return null;
	}
	
	@PutMapping(path = "/{ucenikId}/izostanci")
	public ResponseEntity<?> promeniIzostanakUceniku(@PathVariable Integer ucenikId,
			@RequestBody KorisnikDTO req) {
		return null;
	}

}
