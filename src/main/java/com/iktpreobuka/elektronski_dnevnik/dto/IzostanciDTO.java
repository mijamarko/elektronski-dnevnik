package com.iktpreobuka.elektronski_dnevnik.dto;

import java.util.HashMap;

public class IzostanciDTO {
	
	//datum i koliko izostanaka je napravljeno tada
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
