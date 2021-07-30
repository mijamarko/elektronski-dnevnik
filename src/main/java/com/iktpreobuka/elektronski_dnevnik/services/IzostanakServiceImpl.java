package com.iktpreobuka.elektronski_dnevnik.services;

import java.sql.Date;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakIzmenaDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanciDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.IzostanakEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;
import com.iktpreobuka.elektronski_dnevnik.repositories.IzostanakRepository;

@Service
public class IzostanakServiceImpl implements IzostanakService {

	@Autowired
	private IzostanakRepository izostanakRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ServiceResponse dodajNoveIzostanke(UcenikEntity ucenik, IzostanciDTO noviIzostanci) {
		ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
		noviIzostanci.getKadaIKolikoIzostanaka().forEach((k, v) -> {
			for (int i = 0; i < v; i++) {
				IzostanakEntity izostanak = new IzostanakEntity();
				izostanak.setDatumIzostanka(Date.valueOf(k));
				izostanak.setDatumUpisivanja(new Date(System.currentTimeMillis()));
				izostanak.setTipIzostanka(EIzostanak.NAPRAVLJEN);
				izostanak.setUcenikKojiJeIzostao(ucenik);
				izostanakRepository.save(izostanak);
				izostanci.add(izostanak);
			}
		});
		return new ServiceResponse("Izostanci uspesno dodati zadatom uceniku", HttpStatus.OK, izostanci);
	}

	@Override
	public ServiceResponse dobaviSveIzostankeUVremenskomPerioduZaUcenika(UcenikEntity ucenik, IzostanakIzmenaDTO trazeniIzostanci) {
		ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
		Date pocetniDatum = Date.valueOf(trazeniIzostanci.getPocetniDatum());
		Date zavrsniDatum = Date.valueOf(trazeniIzostanci.getZavrsniDatum());
		izostanakRepository.findByDatumIzostankaBetween(pocetniDatum, zavrsniDatum).forEach(i -> {
			if (i.getUcenikKojiJeIzostao().equals(ucenik)) {
				izostanci.add(i);
			}
		});
		if (izostanci.size() > 0) {
			return new ServiceResponse("Izostanci za trazenog ucenika", HttpStatus.OK, izostanci);
		}
		return new ServiceResponse("I-1", "Zadati ucenik nema izostanaka", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviTipIzostankaUVremenskomPerioduZaUcenika(UcenikEntity ucenik, IzostanakIzmenaDTO trazeniIzostanci) {
		ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
		Date pocetniDatum = Date.valueOf(trazeniIzostanci.getPocetniDatum());
		Date zavrsniDatum = Date.valueOf(trazeniIzostanci.getZavrsniDatum());
		EIzostanak tipIzostanka = trazeniIzostanci.getTipIzostankaZaPromenu();
		izostanakRepository.findByDatumIzostankaBetween(pocetniDatum, zavrsniDatum).forEach(i -> {
			if (i.getUcenikKojiJeIzostao().equals(ucenik) && i.getTipIzostanka() == tipIzostanka) {
				izostanci.add(i);
			}
		});
		if (izostanci.size() > 0) {
			return new ServiceResponse("Izostanci za trazenog ucenika", HttpStatus.OK, izostanci);
		}
		return new ServiceResponse("I-2", "Zadati ucenik nema izostanaka zadatog tipa u zadatom periodu",
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izmeniIzostankeUVremenskomPerioduZaUcenika(UcenikEntity ucenik, IzostanakIzmenaDTO noviPodaci) {
		Date pocetniDatum = Date.valueOf(noviPodaci.getPocetniDatum());
		Date zavrsniDatum = Date.valueOf(noviPodaci.getZavrsniDatum());
		Integer ucenikId = ucenik.getId();
		izostanakRepository
		.findByDatumIzostanakaBetweenZaUcenika(pocetniDatum, zavrsniDatum, ucenikId).forEach(i -> {
			if(i.getTipIzostanka() == noviPodaci.getTipIzostankaZaPromenu()) {
				i.setTipIzostanka(noviPodaci.getTipIzostankaUKojiSeMenja());
				i.setDatumIzmene(new Date(System.currentTimeMillis()));
				izostanakRepository.save(i);
			}
		});
		return new ServiceResponse("Tip izostanaka za trazeni vremenski period uspesno izmenjen", HttpStatus.OK);
	}

	@Override
	public ServiceResponse dobaviSveIzostankeUVremenskomPerioduZaOdeljenje(OdeljenjeEntity odeljenje, Date pocetniDatum,
			Date zavrsniDatum) {
		ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
		for(int i = 0;i < odeljenje.getUcenici().size(); i++) {
			izostanakRepository.findByDatumIzostanakaBetweenZaUcenika(pocetniDatum, zavrsniDatum, odeljenje.getUcenici().get(i).getId())
			.forEach(iz -> {
				izostanci.add(iz);
			});
		}
		if (izostanci.size() > 0) {
			return new ServiceResponse("Izostanci za trazeno odeljenje", HttpStatus.OK, izostanci);
		}
		return new ServiceResponse("I-3", "Zadato odeljenje nema izostanaka u zadatom periodu",
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviTipIzostankaUVremenskomPerioduZaOdeljenje(OdeljenjeEntity odeljenje, IzostanakIzmenaDTO trazeniIzostanci) {
		ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
		Date pocetniDatum = Date.valueOf(trazeniIzostanci.getPocetniDatum());
		Date zavrsniDatum = Date.valueOf(trazeniIzostanci.getZavrsniDatum());
		EIzostanak tipIzostanka = trazeniIzostanci.getTipIzostankaZaPromenu();
		for(int i = 0;i < odeljenje.getUcenici().size(); i++) {
			izostanakRepository.findByDatumIzostanakaBetweenZaUcenika(pocetniDatum, zavrsniDatum, odeljenje.getUcenici().get(i).getId())
			.forEach(iz -> {
				if(iz.getTipIzostanka() == tipIzostanka) {
					izostanci.add(iz);
				}
			});
		}
		if (izostanci.size() > 0) {
			return new ServiceResponse("Izostanci za trazeno odeljenje", HttpStatus.OK, izostanci);
		}
		return new ServiceResponse("I-4", "Zadato odeljenje nema izostanaka zadatog tipa u zadatom periodu",
				HttpStatus.BAD_REQUEST);
	}

}
