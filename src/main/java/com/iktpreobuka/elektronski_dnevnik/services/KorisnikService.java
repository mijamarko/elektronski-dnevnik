package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangeEmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangePassDTO;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;

public interface KorisnikService {
	
	public KorisnikEntity doesUserExist(Integer id);
	
	public Boolean doesEmailExist(String email);
	
	public KorisnikServiceResponse createNewUser(KorisnikEntity korisnik);
	
	public KorisnikServiceResponse changeEmail(Integer id, UserChangeEmailDTO newUserData);
	
	public KorisnikServiceResponse changePassword(Integer id, UserChangePassDTO newUserData);
	
	public KorisnikServiceResponse deleteUser(Integer id);
	
	public KorisnikServiceResponse addRoleForUser(Integer id, RoleEntity role);
	
	public KorisnikServiceResponse addRoleForUser(Integer id, Integer role_id);
	
	public KorisnikServiceResponse addRoleForUser(Integer id, String  role_name);
	
	public KorisnikServiceResponse removeRoleFromUser(Integer id, RoleEntity role);
	
	public KorisnikServiceResponse removeRoleFromUser(Integer id, Integer role_id);
	
	public KorisnikServiceResponse removeRoleFromUser(Integer id, String role_name);

}
