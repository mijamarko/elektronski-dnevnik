package com.iktpreobuka.elektronski_dnevnik.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.dto.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.security.Views;
import com.iktpreobuka.elektronski_dnevnik.util.RestError;

@Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK", "ROLE_RODITELJ", "ROLE_UCENIK"})
@JsonView(Views.Ucenik.class)
public class ServiceResponseHandler {
	

	public ResponseEntity<?> handleResponse(ServiceResponse response){
		if(response.getHttpStatus().equals(HttpStatus.OK)) {
			return new ResponseEntity<ServiceResponse>(new ServiceResponse(response.getPoruka(), response.getHttpStatus(), response.getValue()), HttpStatus.OK);
		} else {
			return new ResponseEntity<RestError>(new RestError(response.getKod(), response.getPoruka()), HttpStatus.BAD_REQUEST);
//			System.out.println(new RestError("da li", "ovo radi"));
//			return new ResponseEntity<RestError>(new RestError("da li", "ovo radi"), HttpStatus.BAD_REQUEST);
		}
		
	}

}
