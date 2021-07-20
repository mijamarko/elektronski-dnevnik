package com.iktpreobuka.elektronski_dnevnik.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.iktpreobuka.elektronski_dnevnik.dto.responses.KorisnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.dto.responses.NastavnikServiceResponse;
import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik.util.RestError;

@Component
public class ServiceResponseHandler {
	
	public ResponseEntity<?> handleResponse(KorisnikServiceResponse response){
		if(response.getHttpResponseCode().equals(HttpStatus.OK)) {
			return new ResponseEntity<Iterable<KorisnikEntity>>(response.getKorisnici(), response.getHttpResponseCode());
		}
		return new ResponseEntity<RestError>(new RestError(response.getCode(), response.getMessage()), response.getHttpResponseCode());
	}
	
	
	public ResponseEntity<?> handleResponse(NastavnikServiceResponse response){
		if(response.getHttpResponseCode().equals(HttpStatus.OK)) {
			if(!response.getPredmeti().equals(null)) {
				return new ResponseEntity<Iterable<PredmetEntity>>(response.getPredmeti(), response.getHttpResponseCode());
			}
			return new ResponseEntity<OdeljenjeEntity>(response.getOdeljenje(), response.getHttpResponseCode());
		}
		return new ResponseEntity<RestError>(new RestError(response.getCode(), response.getMessage()), response.getHttpResponseCode());
	}

}
