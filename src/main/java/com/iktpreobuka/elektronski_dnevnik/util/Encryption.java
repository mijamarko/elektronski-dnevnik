package com.iktpreobuka.elektronski_dnevnik.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryption {
	
	//check if the submitted password matches one in the DB
	public static boolean validatePassword(String password, String encryptedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(password, encryptedPassword);
	}
	
	public static void encodePasswordPls(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode(password));
	}
	
	public static void main (String[] args) {
		encodePasswordPls("Sifra123!");
	}

	public Encryption() {
		super();
	}	
	

}
