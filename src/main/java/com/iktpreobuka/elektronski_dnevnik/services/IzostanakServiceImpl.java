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
		logger.info("Ulazi u dodajNoveIzostanke metodu");
		ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
		noviIzostanci.getKadaIKolikoIzostanaka().forEach((k, v) -> {
			logger.info("Kreira %d izostanaka za ucenika %d", v, ucenik.getId());
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
		logger.info("Izostanci uspesno dodati uceniku %d", ucenik.getId());
		return new ServiceResponse("Izostanci uspesno dodati zadatom uceniku", HttpStatus.OK, izostanci);
	}

	@Override
	public ServiceResponse dobaviSveIzostankeUVremenskomPerioduZaUcenika(UcenikEntity ucenik,
			IzostanakIzmenaDTO trazeniIzostanci) {
		logger.info("Ulazi u dobaviSveIzostankeUVremenskomPerioduZaUcenika metodu");
		ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
		Date pocetniDatum = Date.valueOf(trazeniIzostanci.getPocetniDatum());
		Date zavrsniDatum = Date.valueOf(trazeniIzostanci.getZavrsniDatum());
		logger.info("Trazi izostanke za ucenika %d u vremenskom periodu izmedju %s i %s", ucenik.getId(),
				trazeniIzostanci.getPocetniDatum(), trazeniIzostanci.getZavrsniDatum());
		izostanakRepository.findByDatumIzostankaBetween(pocetniDatum, zavrsniDatum).forEach(i -> {
			if (i.getUcenikKojiJeIzostao().equals(ucenik)) {
				izostanci.add(i);
			}
		});
		if (izostanci.size() > 0) {
			return new ServiceResponse("Izostanci za trazenog ucenika", HttpStatus.OK, izostanci);
		}
		logger.error("Ucenik %d nema izostanke za zadati period", ucenik.getId());
		return new ServiceResponse("I-1", "Zadati ucenik nema izostanaka", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviTipIzostankaUVremenskomPerioduZaUcenika(UcenikEntity ucenik,
			IzostanakIzmenaDTO trazeniIzostanci) {
		logger.info("Ulazi u dobaviTipIzostankaUVremenskomPerioduZaUcenika metodu");
		ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
		Date pocetniDatum = Date.valueOf(trazeniIzostanci.getPocetniDatum());
		Date zavrsniDatum = Date.valueOf(trazeniIzostanci.getZavrsniDatum());
		EIzostanak tipIzostanka = trazeniIzostanci.getTipIzostankaZaPromenu();
		logger.info("Trazi izostanke tipa %s za ucenika %d u vremenskom periodu izmedju %s i %s",
				tipIzostanka.toString(), ucenik.getId(), trazeniIzostanci.getPocetniDatum(),
				trazeniIzostanci.getZavrsniDatum());
		izostanakRepository.findByDatumIzostankaBetween(pocetniDatum, zavrsniDatum).forEach(i -> {
			if (i.getUcenikKojiJeIzostao().equals(ucenik) && i.getTipIzostanka() == tipIzostanka) {
				izostanci.add(i);
			}
		});
		if (izostanci.size() > 0) {
			return new ServiceResponse("Izostanci za trazenog ucenika", HttpStatus.OK, izostanci);
		}
		logger.error("Ucenik %d nema izostanke za zadati period", ucenik.getId());
		return new ServiceResponse("I-2", "Zadati ucenik nema izostanaka zadatog tipa u zadatom periodu",
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse izmeniIzostankeUVremenskomPerioduZaUcenika(UcenikEntity ucenik,
			IzostanakIzmenaDTO noviPodaci) {
		logger.info("Ulazi u izmeniIzostankeUVremenskomPerioduZaUcenika metodu");
		Date pocetniDatum = Date.valueOf(noviPodaci.getPocetniDatum());
		Date zavrsniDatum = Date.valueOf(noviPodaci.getZavrsniDatum());
		Integer ucenikId = ucenik.getId();
		logger.info("Trazi izostanke tipa %s za ucenika %d u vremenskom periodu izmedju %s i %s i menja ih u",
				noviPodaci.getTipIzostankaZaPromenu().toString(), ucenik.getId(), noviPodaci.getPocetniDatum(),
				noviPodaci.getZavrsniDatum(), noviPodaci.getTipIzostankaUKojiSeMenja().toString());
		izostanakRepository.findByDatumIzostanakaBetweenZaUcenika(pocetniDatum, zavrsniDatum, ucenikId).forEach(i -> {
			if (i.getTipIzostanka() == noviPodaci.getTipIzostankaZaPromenu()) {
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
		logger.info("Ulazi u dobaviSveIzostankeUVremenskomPerioduZaOdeljenje metodu");
		ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
		logger.info("Trazi izostanke za odeljenje sa id %d", odeljenje.getId());
		for (int i = 0; i < odeljenje.getUcenici().size(); i++) {
			izostanakRepository.findByDatumIzostanakaBetweenZaUcenika(pocetniDatum, zavrsniDatum,
					odeljenje.getUcenici().get(i).getId()).forEach(iz -> {
						izostanci.add(iz);
					});
		}
		if (izostanci.size() > 0) {
			return new ServiceResponse("Izostanci za trazeno odeljenje", HttpStatus.OK, izostanci);
		}
		logger.error("Odeljenje id %d nema izostanaka", odeljenje.getId());
		return new ServiceResponse("I-3", "Zadato odeljenje nema izostanaka u zadatom periodu", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ServiceResponse dobaviTipIzostankaUVremenskomPerioduZaOdeljenje(OdeljenjeEntity odeljenje,
			IzostanakIzmenaDTO trazeniIzostanci) {
		logger.info("Ulazi u dobaviTipIzostankaUVremenskomPerioduZaOdeljenje metodu");
		ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
		Date pocetniDatum = Date.valueOf(trazeniIzostanci.getPocetniDatum());
		Date zavrsniDatum = Date.valueOf(trazeniIzostanci.getZavrsniDatum());
		EIzostanak tipIzostanka = trazeniIzostanci.getTipIzostankaZaPromenu();
		logger.info("Trazi izostanke tipa %s za odeljenje sa id %d", tipIzostanka.toString() ,odeljenje.getId());
		for (int i = 0; i < odeljenje.getUcenici().size(); i++) {
			izostanakRepository.findByDatumIzostanakaBetweenZaUcenika(pocetniDatum, zavrsniDatum,
					odeljenje.getUcenici().get(i).getId()).forEach(iz -> {
						if (iz.getTipIzostanka() == tipIzostanka) {
							izostanci.add(iz);
						}
					});
		}
		if (izostanci.size() > 0) {
			return new ServiceResponse("Izostanci za trazeno odeljenje", HttpStatus.OK, izostanci);
		}
		logger.error("Odeljenje id %d nema izostanaka tipa %s", odeljenje.getId(), tipIzostanka.toString());
		return new ServiceResponse("I-4", "Zadato odeljenje nema izostanaka zadatog tipa u zadatom periodu",
				HttpStatus.BAD_REQUEST);
	}

}
