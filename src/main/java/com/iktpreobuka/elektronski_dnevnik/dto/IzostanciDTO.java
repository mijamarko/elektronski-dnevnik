package com.iktpreobuka.elektronski_dnevnik.dto;

import java.util.HashMap;

public class IzostanciDTO {
	
	private HashMap<String, Integer> kadaIKolikoIzostanaka = new HashMap<String, Integer>();

	public IzostanciDTO() {
		super();
	}

	public HashMap<String, Integer> getKadaIKolikoIzostanaka() {
		return kadaIKolikoIzostanaka;
	}

	public void setKadaIKolikoIzostanaka(HashMap<String, Integer> kadaIKolikoIzostanaka) {
		this.kadaIKolikoIzostanaka = kadaIKolikoIzostanaka;
	}

}
