package com.iktpreobuka.elektronski_dnevnik.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendMessage(OcenaEntity ocena) throws Exception {
		
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		try {
			
			List<String> to = new ArrayList<String>();
			ocena.getUcenik().getRoditelji().forEach(r -> to.add(r.getEmail()));
			String predmet = ocena.getPredmetIzKogJeOcena().getNazivPredmeta();
			String ucenik = ocena.getUcenik().getIme() + " " + ocena.getUcenik().getPrezime();
			Integer vrednostOcene = ocena.getOcena().intValue();
			String nastavnik = ocena.getNastavnikKojiJeDaoOcenu().getIme() + " " + ocena.getNastavnikKojiJeDaoOcenu().getPrezime();
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String datum = df.format(ocena.getDatumDodele());
			String text = "<html><body><p> Poštovani, <br>"
					+ "Vaše dete " + ucenik + "je dana " + datum + " dobilo ocenu " + vrednostOcene 
					+ " iz predmeta " + predmet
					+ " od strane nastavnika " + nastavnik + "</p></body></html>";
			
			helper = new MimeMessageHelper(mail, true);
			if(to.size() > 1) {
				helper.setTo(to.get(0) + ", " + to.get(1));
			} else {
				helper.setTo(to.get(0));
			}
			helper.setSubject("Nova ocena iz predmeta " + predmet);
			helper.setText(text, true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		emailSender.send(mail);
		
	}
	
	

}
