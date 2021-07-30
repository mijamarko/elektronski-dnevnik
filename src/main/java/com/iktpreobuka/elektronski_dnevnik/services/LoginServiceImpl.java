package com.iktpreobuka.elektronski_dnevnik.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public String getJWT(KorisnikEntity korisnik, String secretKey, Integer tokenDuration) {
		
		List<GrantedAuthority> grantedAuthority = AuthorityUtils.commaSeparatedStringToAuthorityList(korisnik.getRole().getName());
		
		String token = Jwts.builder().setId("dajtemimakspoena")
				.setSubject(korisnik.getEmail()).claim("authorities", grantedAuthority
						.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + tokenDuration))
						.signWith(SignatureAlgorithm.HS512, secretKey).compact();
		
		return "Bearer " + token;
	}

}
