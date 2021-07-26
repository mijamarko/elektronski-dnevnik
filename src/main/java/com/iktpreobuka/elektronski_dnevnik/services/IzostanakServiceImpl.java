package com.iktpreobuka.elektronski_dnevnik.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.dto.IzostanciDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;
import com.iktpreobuka.elektronski_dnevnik.repositories.IzostanakRepository;

@Service
public class IzostanakServiceImpl implements IzostanakService {
	
	@Autowired
	private IzostanakRepository izostanakRepository;

	@Override
	public ServiceResponse dodajNoveIzostanke(Integer ucenikId, IzostanciDTO izostanci) {
		return null;
	}

	@Override
	public ServiceResponse dobaviSveIzostankeUVremenskomPeriodu(Integer ucenikId, Date pocetniDatum,
			Date zavrsniDatum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dobaviTipIzostankaUVremenskomPeriodu(Integer ucenikId, Date pocetniDatum, Date zavrsniDatum,
			EIzostanak tipIzostanka) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse izmeniIzostankeUVremenskomPeriodu(Integer ucenikId, Date pocetniDatum, Date zavrsniDatum,
			EIzostanak tipIzostanakaKojiSeMenja, EIzostanak tipIzostanakaUKojiSeMenja) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
