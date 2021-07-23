package com.iktpreobuka.elektronski_dnevnik.services.user_specific;

import com.iktpreobuka.elektronski_dnevnik.dto.KorisnikDTO;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;

public interface KorisnikRoleService {
	
	public RoleEntity rolaPostoji(KorisnikDTO req);
	
	public KorisnikServiceResponse dodajRoluKorisniku(Integer id, KorisnikDTO req);
	
	public KorisnikServiceResponse obrisiRoluKorisniku(Integer id, KorisnikDTO req);

}
