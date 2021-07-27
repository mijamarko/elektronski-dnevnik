package com.iktpreobuka.elektronski_dnevnik.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakIzmenaDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanciDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;
import com.iktpreobuka.elektronski_dnevnik.enums.EPolugodiste;
import com.iktpreobuka.elektronski_dnevnik.enums.ETip_Ocene;
import com.iktpreobuka.elektronski_dnevnik.services.UcenikServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/korisnici/ucenici")
public class UcenikController {

	// GET svi ucenici
	// GET ucenik by id
	// GET odeljenje koje pohadja
	// GET svi izostanci
	// GET svi izostanci jednog tipa
	// GET svi izostanci u vremenskom periodu
	// GET svi izostanci jednog tipa u vremenskom periodu
	// GET roditelji
	// GET sve ocene
	// GET sve ocene iz jednog predmeta
	// PUT izmeni odeljenje koje pohadja
	// PUT modifikuj izostanke ovog ucenika za specifican period
	// PUT modifikuj specificnu ocenu iz specificnog predmeta
	// PUT promeni email
	// PUT promeni sifru
	// POST novi ucenik
	// POST napravi nove izostanke za ucenika
	// POST dodaj novu ocenu uceniku
	// DELETE obrisi ucenika

	private ServiceResponseHandler handler = new ServiceResponseHandler();

	@Autowired
	private UcenikServiceImpl ucenikService;

	@GetMapping(path = "/")
	public ResponseEntity<?> dobaviSveUcenike() {
		return handler.handleResponse(ucenikService.dobaviSveUcenike());
	}

	@GetMapping(path = "/{ucenikId}")
	public ResponseEntity<?> dobaviUcenikaPoId(@PathVariable Integer ucenikId) {
		return handler.handleResponse(ucenikService.dobaviUcenikaPoId(ucenikId));
	}

	@GetMapping(path = "/{ucenikId}/odeljenje")
	public ResponseEntity<?> dobaviOdeljenjeKojeUcenikPohadja(@PathVariable Integer ucenikId) {
		return handler.handleResponse(ucenikService.dobaviOdeljenjeKojeUcenikPohadja(ucenikId));
	}

	@GetMapping(path = "/{ucenikId}/izostanci")
	public ResponseEntity<?> dobaviSveIzostankeUcenika(@PathVariable Integer ucenikId) {
		return handler.handleResponse(ucenikService.dobaviSveIzostankeUcenika(ucenikId));
	}

	@GetMapping(path = "/{ucenikId}/izostanci/tip")
	public ResponseEntity<?> dobaviSveIzostankeUcenikaPoTipu(@PathVariable Integer ucenikId,
			@RequestParam EIzostanak tipIzostanka) {
		return handler.handleResponse(ucenikService.dobaviSveIzostankeUcenikaPoTipu(ucenikId, tipIzostanka));
	}

	@GetMapping(path = "/{ucenikId}/izostanci/vremenski-period")
	public ResponseEntity<?> dobaviSveIzostankeUVremenskomPeriodu(@PathVariable Integer ucenikId,
			@RequestBody IzostanakIzmenaDTO noviPodaci) {
		return handler.handleResponse(ucenikService.dobaviSveIzostankeUVremenskomPeriodu(ucenikId, noviPodaci));
	}

	@GetMapping(path = "/{ucenikId}/izostanci/tip-vremenski-period")
	public ResponseEntity<?> dobaviTipIzostankaUVremenskomPeriodu(@PathVariable Integer ucenikId,
			@RequestBody IzostanakIzmenaDTO noviPodaci) {
		return handler.handleResponse(ucenikService.dobaviTipIzostankaUVremenskomPerioduZaUcenika(ucenikId, noviPodaci));
	}

	@GetMapping(path = "/{ucenikId}/roditelji")
	public ResponseEntity<?> dobaviRoditeljeUcenika(@PathVariable Integer ucenikId) {
		return handler.handleResponse(ucenikService.dobaviRoditeljeUcenika(ucenikId));
	}

	@GetMapping(path = "/{ucenikId}/ocene")
	public ResponseEntity<?> dobaviSveOceneUcenika(@PathVariable Integer ucenikId) {
		return handler.handleResponse(ucenikService.dobaviSveOceneUcenika(ucenikId));
	}

	@GetMapping(path = "/{ucenikId}/ocene/predmeti")
	public ResponseEntity<?> dobaviSveOceneIzJednogPredmeta(@PathVariable Integer ucenikId,
			@RequestParam Integer predmetId) {
		return handler.handleResponse(ucenikService.dobaviSveOceneIzJednogPredmeta(ucenikId, predmetId));
	}
	
	@GetMapping(path = "/{ucenikId}/ocene/prosek-polugodiste")
	public ResponseEntity<?> izracunajUspehUcenikaUPolugodistu(@PathVariable Integer ucenikId, @RequestParam EPolugodiste polugodiste) {
		return handler.handleResponse(ucenikService.izracunajUspehUcenikaUPolugodistu(ucenikId, polugodiste));
	}
	
	@GetMapping(path = "/{ucenikId}/ocene/prosek-godina")
	public ResponseEntity<?> izracunajUspehUcenikaUGodini(@PathVariable Integer ucenikId) {
		return handler.handleResponse(ucenikService.izracunajUspehUcenikaUGodini(ucenikId));
	}


	@PutMapping(path = "/{ucenikId}/odeljenje")
	public ResponseEntity<?> izmeniOdeljenjeKojePohadja(@PathVariable Integer ucenikId,
			@RequestParam Integer odeljenjeId) {
		return handler.handleResponse(ucenikService.izmeniOdeljenjeKojePohadja(ucenikId, odeljenjeId));
	}

	@PutMapping(path = "/{ucenikId}/izostanci/vremenski-period")
	public ResponseEntity<?> izmeniIzostankeUVremenskomPeriodu(@PathVariable Integer ucenikId,
			@RequestBody IzostanakIzmenaDTO noviPodaci) {
		return handler.handleResponse(ucenikService.izmeniIzostankeUVremenskomPeriodu(ucenikId, noviPodaci));
	}

	@PutMapping(path = "/{ucenikId}/ocene/predmeti")
	public ResponseEntity<?> izmeniOcenuIzPredmeta(@PathVariable Integer ucenikId, @RequestParam Integer ocenaId,
			@RequestParam Double novaOcena) {
		return handler.handleResponse(ucenikService.izmeniOcenuIzPredmeta(ucenikId, ocenaId, novaOcena));
	}

	@PutMapping(path = "/{ucenikId}/email")
	public ResponseEntity<?> izmeniEmail(@PathVariable Integer ucenikId, @RequestBody EmailDTO noviPodaci) {
		return handler.handleResponse(ucenikService.izmeniEmail(ucenikId, noviPodaci));
	}

	@PutMapping(path = "/{ucenikId}/sifra")
	public ResponseEntity<?> izmeniSifru(@PathVariable Integer ucenikId, @RequestBody SifraDTO noviPodaci) {
		return handler.handleResponse(ucenikService.izmeniSifru(ucenikId, noviPodaci));
	}

	@PostMapping(path = "/")
	public ResponseEntity<?> napraviNovogUcenika(@Valid @RequestBody UcenikEntity ucenik) {
		return handler.handleResponse(ucenikService.napraviNovogUcenika(ucenik));
	}
	
	@PostMapping(path = "/{ucenikId}/ocene/predmeti/{predmetId}")
	public ResponseEntity<?> dodajNovuOcenuIzOdredjenogPredmeta(@PathVariable Integer ucenikId,
			@PathVariable Integer predmetId,
			@RequestParam Integer nastavnikId, @RequestParam Double ocena,
			@RequestParam ETip_Ocene tipOcene, @RequestParam EPolugodiste polugodiste) {
		return handler.handleResponse(ucenikService.dodajNovuOcenuIzOdredjenogPredmeta(ucenikId, predmetId, nastavnikId,
				ocena, tipOcene, polugodiste));
	}

	@PostMapping(path = "/{ucenikId}/ocene/predmeti/{predmetId}/polugodiste")
	public ResponseEntity<?> dodajProsecnuOcenuIzPredmetaUPolugodistu(@PathVariable Integer ucenikId, @PathVariable Integer predmetId,
			@RequestParam EPolugodiste polugodiste, @RequestParam Integer nastavnikId) {
		return handler.handleResponse(ucenikService.dodajProsecnuOcenuIzPredmetaUPolugodistu(ucenikId, predmetId, polugodiste, nastavnikId));
	}

	@PostMapping(path = "/{id}/ocene/predmeti/{predmetId}/polugodiste")
	public ResponseEntity<?> dodajProsecnuOcenuIzPredmetaUGodini(@PathVariable Integer ucenikId, @PathVariable Integer predmetId,
			@RequestParam Integer nastavnikId) {
		return handler.handleResponse(ucenikService.dodajProsecnuOcenuIzPredmetaUGodini(ucenikId, predmetId, nastavnikId));
	}

	@PostMapping(path = "/{id}/izostanci")
	public ResponseEntity<?> dodajNoveIzostanke(@PathVariable Integer ucenikId, @RequestBody IzostanciDTO izostanci) {
		return handler.handleResponse(ucenikService.dodajNoveIzostanke(ucenikId, izostanci));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> obrisiUcenika(@PathVariable Integer ucenikId) {
		return handler.handleResponse(ucenikService.obrisiUcenika(ucenikId));
	}

}
