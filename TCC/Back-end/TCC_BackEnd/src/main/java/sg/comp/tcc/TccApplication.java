package sg.comp.tcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication//(exclude= {UserDetailsServiceAutoConfiguration.class}) -> tira a tela de login do spring security
@EnableAsync
public class TccApplication {

	public static void main(String[] args) {
		SpringApplication.run(TccApplication.class, args);
	}

}
