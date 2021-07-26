package com.iktpreobuka.elektronski_dnevnik.dto;

import java.util.HashMap;

public class IzostanakDTO {
	
	private HashMap<String, Integer> kadaIKolikoIzostanaka = new HashMap<String, Integer>();

	public IzostanakDTO() {
		super();
	}

	public HashMap<String, Integer> getKadaIKolikoIzostanaka() {
		return kadaIKolikoIzostanaka;
	}

	public void setKadaIKolikoIzostanaka(HashMap<String, Integer> kadaIKolikoIzostanaka) {
		this.kadaIKolikoIzostanaka = kadaIKolikoIzostanaka;
	}

}
