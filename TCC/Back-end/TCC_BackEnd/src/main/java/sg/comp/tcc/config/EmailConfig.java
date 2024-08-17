package sg.comp.tcc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import sg.comp.tcc.entity.Usuario;
import sg.comp.tcc.service.EmailService;

@Configuration
public class EmailConfig {
	@Value("${spring.mail.username}")
	private String sender;
	
	@Autowired
	private EmailService emailService;
	
	public void sendEmail(String para, String assunto, String texto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(para);
		message.setSubject(assunto);
		message.setText("Inscrição realizada: \n" + texto + "\n\n\nAlterdata Gestão de Competências");
		emailService.sendMail(message);
	}
	
	public void sendEmailUsuario(Usuario usuario) {
		 String template = "Olá, " + usuario.getNome() + "\n\n"
				 		 + "Seu cadastro foi realizado com sucesso!";
		 SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(sender);
			message.setTo(usuario.getEmail());
			message.setSubject("Cadastro de Usuario");
			message.setText(template);
			emailService.sendMail(message);
	}
	
}
