package com.iktpreobuka.elektronski_dnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.iktpreobuka.elektronski_dnevnik.dto.EmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.IzostanakIzmenaDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.SifraDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;
import com.iktpreobuka.elektronski_dnevnik.repositories.IzostanakRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.OcenaRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;
import com.iktpreobuka.elektronski_dnevnik.repositories.UcenikRepository;

public class UcenikServiceImpl implements UcenikService {
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	@Autowired
	private IzostanakRepository izostanakRepository;
	
	@Autowired
	private OcenaRepository ocenaRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public ServiceResponse dobaviSveUcenike() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dobaviOdeljenjeKojeUcenikPohadja(Integer ucenikId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dobaviSveIzostankeUcenika(Integer ucenikId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dobaviSveIzostankeUcenikaPoTipu(Integer ucenikId, EIzostanak tipIzostanka) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dobaviSveIzostankeUVremenskomPeriodu(Integer ucenikId, IzostanakIzmenaDTO izostanci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dobaviRoditeljeUcenika(Integer ucenikId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dobaviSveOceneUcenika(Integer ucenikId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dobaviSveOceneIzJednogPredmeta(Integer ucenikId, Integer predmetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse izmeniOdeljenjeKojePohadja(Integer ucenikId, Integer odeljenjeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse izmeniIzostankeUVremenskomPeriodu(Integer ucenikId, IzostanakIzmenaDTO noviPodaci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse izmeniOcenuIzPredmeta(Integer ucenikId, Integer ocenaId, Integer novaOcena) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse izmeniEmail(Integer ucenikId, EmailDTO noviPodaci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse izmeniSifru(Integer ucenikId, SifraDTO noviPodaci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse napraviNovogUcenika(UcenikEntity ucenik) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dodajNovuOcenuIzOdredjenogPredmeta(OcenaEntity ocena) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse dodajNoveIzostanke(IzostanakDTO izostanci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse obrisiUcenika(Integer ucenikId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
