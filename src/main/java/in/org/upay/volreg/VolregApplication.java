package in.org.upay.volreg;

import in.org.upay.volreg.model.Volunteer;
import in.org.upay.volreg.repository.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;

@SpringBootApplication
public class VolregApplication {

	@Autowired
	private JavaMailSender javaMailSender;

	private static final Logger log = LoggerFactory.getLogger(VolregApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VolregApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(VolunteerRepository repository) {
		return (args) -> {
				repository.save(new Volunteer("Piyush Ranjan", "piyushranjan95@gmail.com"));
				repository.save(new Volunteer("Akshay", "aks@gmail.com"));

				log.info("volunteers with find all");
				for (Volunteer volunteer :  repository.findAll()) {
					log.info(volunteer.toString());
				}
		};
	}

}
