package sg.comp.tcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Async
	public void sendMail(SimpleMailMessage email) {  
		System.out.println("Enviando email de " + email.getSubject());
		
		javaMailSender.send(email);
		
		System.out.println("Email enviado!");
	}
	
}
