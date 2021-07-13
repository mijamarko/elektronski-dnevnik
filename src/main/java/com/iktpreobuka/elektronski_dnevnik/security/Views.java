package com.iktpreobuka.elektronski_dnevnik.security;

public class Views {
	
	public static class Ucenik{}
	public static class Roditelj extends Ucenik{}
	public static class ProsvetniRadnik extends Roditelj{}
	public static class Admin extends ProsvetniRadnik{}
	

}
