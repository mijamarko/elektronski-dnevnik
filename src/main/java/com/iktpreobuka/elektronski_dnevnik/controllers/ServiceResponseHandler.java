package com.iktpreobuka.elektronski_dnevnik.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.ServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.util.RestError;

public class ServiceResponseHandler {
	
	public ResponseEntity<?> handleResponse(ServiceResponse response){
		if(response.getHttpStatus() == HttpStatus.OK) {
			return new ResponseEntity<ServiceResponse>(new ServiceResponse(response.getPoruka(), response.getHttpStatus(), response.getValue()), HttpStatus.OK);
		}
		return new ResponseEntity<RestError>(new RestError(response.getKod(), response.getPoruka()), HttpStatus.BAD_REQUEST);
	}

}
