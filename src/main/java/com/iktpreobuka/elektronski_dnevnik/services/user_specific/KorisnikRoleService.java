package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;

public interface KorisnikRoleService {
	
public KorisnikServiceResponse addRoleForUser(Integer id, RoleEntity role);
	
	public KorisnikServiceResponse addRoleForUser(Integer id, Integer role_id);
	
	public KorisnikServiceResponse addRoleForUser(Integer id, String  role_name);
	
	public KorisnikServiceResponse removeRoleFromUser(Integer id, RoleEntity role);
	
	public KorisnikServiceResponse removeRoleFromUser(Integer id, Integer role_id);
	
	public KorisnikServiceResponse removeRoleFromUser(Integer id, String role_name);

}
