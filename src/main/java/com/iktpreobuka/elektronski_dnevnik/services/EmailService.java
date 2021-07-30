package com.iktpreobuka.elektronski_dnevnik.services;

import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;

public interface EmailService {
	
	void sendMessage(OcenaEntity ocena) throws Exception;

}
