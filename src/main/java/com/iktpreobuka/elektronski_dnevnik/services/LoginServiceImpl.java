package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;
import com.iktpreobuka.elektronski_dnevnik.repositories.RoleRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public String getJWT(KorisnikEntity korisnik, String secretKey, Integer tokenDuration) {
		//get all roles for specified id
		ArrayList<RoleEntity> roles = roleRepository.findAllByUserId(korisnik.getId());
		
		//najponosniji na ovo ikada
		List<GrantedAuthority> grantedAuthority = AuthorityUtils.commaSeparatedStringToAuthorityList(roles
				.stream().map(RoleEntity::getName)
				.reduce("", (a,b) -> a + " " + b + ",")
				.transform(s -> s.substring(0, s.length()-1)));
		
		String token = Jwts.builder().setId("dajtemimakspoena")
				.setSubject(korisnik.getEmail()).claim("authorities", grantedAuthority
						.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + tokenDuration))
						.signWith(SignatureAlgorithm.HS512, secretKey).compact();
		
		return "Bearer " + token;
	}

}
