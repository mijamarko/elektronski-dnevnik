package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import com.iktpreobuka.elektronski_dnevnik.dto.UserChangeEmailDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.UserChangePassDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;

public interface KorisnikService {
	
	public KorisnikServiceResponse getAllUsers();
	
	public KorisnikServiceResponse getAllUsers(RoleEntity role);
	
	public KorisnikServiceResponse getAllUsers(Integer role_id);
	
	public KorisnikServiceResponse getAllUsers(String role_name);
	
	public KorisnikServiceResponse getUserById(Integer id);
	
	public KorisnikEntity doesUserExist(Integer id);
	
	public Boolean doesEmailExist(String email);
	
	public KorisnikServiceResponse createNewUser(KorisnikEntity korisnik);
	
	public KorisnikServiceResponse changeEmail(Integer id, UserChangeEmailDTO newUserData);
	
	public KorisnikServiceResponse changePassword(Integer id, UserChangePassDTO newUserData);
	
	public KorisnikServiceResponse deleteUser(Integer id);

}
