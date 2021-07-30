package com.iktpreobuka.elektronski_dnevnik.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter{
	
	
	private String securityKey;
	public JWTAuthorizationFilter(String securityKey) {
		super();
		this.securityKey = securityKey;		
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		logger.info("Entering doFilterInternal method");
		
		if(checkJWT(request)) {
			Claims claims = validateToken(request);
			if(claims.get("authorities") != null) {
				logger.info("Token has authorities");
				setUpSpringAuthentication(claims);
			} else {
				logger.error("Invalid token");
				SecurityContextHolder.clearContext();
			}
			
		} else {
			logger.error("Token does not exist");
			SecurityContextHolder.clearContext();
		}
		filterChain.doFilter(request, response);
	}
	
	private boolean checkJWT(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			logger.error("Incorrect header");
			return false;
		}
		logger.info("Valid headers, token exists");
		return true;
	}
	
	private Claims validateToken(HttpServletRequest request) {
		String jwt = request.getHeader("Authorization").replace("Bearer ", "");
		return Jwts.parser().setSigningKey(this.securityKey).parseClaimsJws(jwt).getBody();
	}
	
	private void setUpSpringAuthentication(Claims claims) {
		List<String> authorities = (List<String>) claims.get("authorities");
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
