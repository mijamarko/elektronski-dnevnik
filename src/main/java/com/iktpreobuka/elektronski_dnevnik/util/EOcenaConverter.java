package com.iktpreobuka.elektronski_dnevnik.util;

import com.iktpreobuka.elektronski_dnevnik.enums.EOcena;

public class EOcenaConverter {
	
	public String convertEOcenaToString(EOcena ocena) {
		switch (ocena.toString()) {
		case "NEDOVOLJAN":
			return "1";
		case "DOVOLJAN":
			return "2";
		case "DOBAR":
			return "3";
		case "VRLO_DOBAR":
			return "4";
		default:
			return "5";
		}
	}

}
