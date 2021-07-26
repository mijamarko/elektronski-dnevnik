package com.iktpreobuka.elektronski_dnevnik.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakIzmenaDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;

@RestController
@RequestMapping(path = "/api/v1/korisnici/ucenici")
public class UcenikController {
	
	//GET svi ucenici
	//GET ucenik by id
	//GET odeljenje koje pohadja
	//GET svi izostanci
	//GET svi izostanci jednog tipa
	//GET svi izostanci u vremenskom periodu
	//GET svi izostanci jednog tipa u vremenskom periodu
	//GET roditelji
	//GET sve ocene
	//GET sve ocene iz jednog predmeta
	//PUT izmeni odeljenje koje pohadja
	//PUT modifikuj izostanke ovog ucenika za specifican period
	//PUT modifikuj specificnu ocenu iz specificnog predmeta
	//PUT promeni email
	//PUT promeni sifru
	//POST novi ucenik
	//POST napravi nove izostanke za ucenika
	//POST dodaj novu ocenu uceniku
	//DELETE obrisi ucenika
	
	@GetMapping(path = "/")
	public ResponseEntity<?> dobaviSveUcenike() {
		return null;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> dobaviUcenikaPoId(@PathVariable Integer ucenikId) {
		return null;
	}
	
	@GetMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> dobaviOdeljenjeKojeUcenikPohadja(@PathVariable Integer ucenikId) {
		return null;
	}
	
	@GetMapping(path = "/{id}/izostanci")
	public ResponseEntity<?> dobaviSveIzostankeUcenika(@PathVariable Integer ucenikId) {
		return null;
	}
	
	@GetMapping(path = "/{id}/izostanci/tip")
	public ResponseEntity<?> dobaviSveIzostankeUcenikaPoTipu(@PathVariable Integer ucenikId, @RequestParam EIzostanak tipIzostanka) {
		return null;
	}
	
	@GetMapping(path = "/{id}/izostanci/vremenski-period")
	public ResponseEntity<?> dobaviSveIzostankeUVremenskomPeriodu(@PathVariable Integer ucenikId, @RequestBody IzostanakIzmenaDTO noviPodaci) {
		return null;
	}
	
	@GetMapping(path = "/{id}/izostanci/tip-vremenski-period")
	public ResponseEntity<?> dobaviTipIzostankaUVremenskomPeriodu(@PathVariable Integer ucenikId, @RequestBody IzostanakIzmenaDTO noviPodaci) {
		return null;
	}
	
	@GetMapping(path = "/{id}/roditelji")
	public ResponseEntity<?> dobaviRoditeljeUcenika(@PathVariable Integer ucenikId) {
		return null;
	}
	
	@GetMapping(path = "/{id}/ocene")
	public ResponseEntity<?> dobaviSveOceneUcenika(@PathVariable Integer ucenikId) {
		return null;
	}
	
	@GetMapping(path = "/{id}/ocene/predmeti")
	public ResponseEntity<?> dobaviSveOceneIzJednogPredmeta(@PathVariable Integer ucenikId, @RequestParam Integer predmetId) {
		return null;
	}
	
	@PutMapping(path = "/{id}/odeljenje")
	public ResponseEntity<?> izmeniOdeljenjeKojePohadja(@PathVariable Integer ucenikId, @RequestParam Integer odeljenjeId) {
		
		return null;
	}
	
	@PutMapping(path = "/{id}/izostanci/vremenski-period")
	public ResponseEntity<?> izmeniIzostankeUVremenskomPeriodu(@PathVariable Integer ucenikId, @RequestBody IzostanakIzmenaDTO noviPodaci) {
		
		return null;
	}
	
	@PutMapping(path = "/{id}/ocene/predmeti")
	public ResponseEntity<?> izmeniOcenuIzPredmeta(@PathVariable Integer ucenikId, @RequestParam Integer ocenaId, @RequestParam Integer novaOcena) {
		
		return null;
	}
	
	@PutMapping(path = "/{id}/email")
	public ResponseEntity<?> izmeniEmail(@PathVariable Integer ucenikId, @RequestBody EmailDTO noviPodaci) {
		
		return null;
	}
	
	@PutMapping(path = "/{id}/sifra")
	public ResponseEntity<?> izmeniSifru(@PathVariable Integer ucenikId, @RequestBody SifraDTO noviPodaci) {
		
		return null;
	}

	@PostMapping(path = "/")
	public ResponseEntity<?> napraviNovogUcenika(@Valid @RequestBody UcenikEntity ucenik) {
		
		return null;
	}
	
	@PostMapping(path = "/{id}/ocene/predmeti")
	public ResponseEntity<?> dodajNovuOcenuIzOdredjenogPredmeta(@Valid @RequestBody OcenaEntity ocena) {
		
		return null;
	}
	
	@PostMapping(path = "/{id}/izostanci")
	public ResponseEntity<?> dodajNoveIzostanke(@RequestBody IzostanakDTO izostanci) {
		
		return null;
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> obrisiUcenika(@PathVariable Integer ucenikId) {
		return null;
	}



}
