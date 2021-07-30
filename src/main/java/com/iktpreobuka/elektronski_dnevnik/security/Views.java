package com.iktpreobuka.elektronski_dnevnik.security;

import java.util.HashMap;
import java.util.Map;

public class Views {
	
	public enum Role {
		ROLE_UCENIK, ROLE_NASTAVNIK, ROLE_RODITELJ, ROLE_ADMIN, ROLE_ANONYMOUS
	}
	
	public static final Map<Role, Class> MAPPING = new HashMap<>();
	
	public static class Ucenik {}
	public static class Roditelj extends Ucenik {}
	public static class Nastavnik extends Roditelj{}
	public static class Admin extends Nastavnik{}
	
	static {
		MAPPING.put(Role.ROLE_UCENIK, Ucenik.class);
		MAPPING.put(Role.ROLE_RODITELJ, Roditelj.class);
		MAPPING.put(Role.ROLE_NASTAVNIK, Nastavnik.class);
		MAPPING.put(Role.ROLE_ADMIN, Admin.class);
	}

	public static Map<Role, Class> getMapping() {
		return MAPPING;
	}
	
	

}
