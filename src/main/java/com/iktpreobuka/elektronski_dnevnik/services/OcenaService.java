package com.iktpreobuka.elektronski_dnevnik.services;

import java.sql.Date;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik.enums.EPolugodiste;
import com.iktpreobuka.elektronski_dnevnik.enums.ETip_Ocene;

public interface OcenaService {

	public ServiceResponse izmeniOcenuIzPredmeta(UcenikEntity ucenik, Integer ocenaId, Double novaOcena);

	public ServiceResponse dodajNovuOcenu(UcenikEntity ucenik, Integer predmetId, Integer nastavnikId,
			Double ocena, ETip_Ocene tipOcene, EPolugodiste polugodiste);

	public ServiceResponse izracunajProsecnuOcenuIzPredmetaUPolugodistu(UcenikEntity ucenik, Integer predmetId,
			EPolugodiste polugodiste, Integer nastavnikId);

	public ServiceResponse izracunajProsecnuOcenuIzPredmetaUGodini(UcenikEntity ucenik, Integer predmetId, Integer nastavnikId);

	public ServiceResponse izracunajUspehUcenikaUPolugodistu(UcenikEntity ucenik, EPolugodiste polugodiste);

	public ServiceResponse izracunajUspehUcenikaUGodini(UcenikEntity ucenik);

	public ServiceResponse izracunajIzlaznostNaOdredjeniZadatak(OdeljenjeEntity odeljenje, Date datumZadatka,
			ETip_Ocene tipZadatka);

	public ServiceResponse izracunajProsekOdeljenjaNaOdredjenomZadatku(OdeljenjeEntity odeljenje, Date datumZadatka,
			ETip_Ocene tipZadatka);

}
