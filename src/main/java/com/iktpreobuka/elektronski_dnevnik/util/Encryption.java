package com.iktpreobuka.elektronski_dnevnik.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryption {
	
	//check if the submitted password matches one in the DB
	public static boolean validatePassword(String password, String encryptedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(password, encryptedPassword);
	}

}
