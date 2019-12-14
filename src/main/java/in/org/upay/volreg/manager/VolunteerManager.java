package in.org.upay.volreg.manager;

import in.org.upay.volreg.dto.VolunteerRegistration;
import in.org.upay.volreg.email.EmailService;
import in.org.upay.volreg.email.EmailTemplate;
import in.org.upay.volreg.model.Volunteer;
import in.org.upay.volreg.repository.VolunteerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Utility functions for handling volunteer class
 */
@Component
public class VolunteerManager {


    @Autowired
    private EmailService emailService;

    @Autowired
    private VolunteerRepository repository;

    private String[] getNotificationAddresses(String cityName) {
        // todo: initialize a map with city and emails fetched from DB
        String emails = "";
        switch (cityName.toUpperCase()) {
            case "PUNE": emails = "piyushranjan95@gmail.com,varun.iitkgp@gmail.com";
            break;
            case "MOUDA": emails = "piyushranjan95@gmail.com,directortech.upay@gmail.com";
            break;
            case "NAGPUR": emails = "piyushranjan95@gmail.com,deboshreeb11@gmail.com";
            break;
            default:
                emails = "piyushranjan95@gmail.com";
        }

        return emails.split(",");
    }

    private void sendRegistrationNotification(VolunteerRegistration registration) {
        String[] to = getNotificationAddresses(registration.getCityName());
        String subject = String.format(EmailTemplate.REGISTRATION_EMAIL_MESSAGE, registration.getCityName());
        String text = String.format(EmailTemplate.HTML_EMAIL_CONTENT, registration.getName(), registration.getEmail(), registration.getMobile(),
                                    registration.getQualification(), registration.getCityName(), Arrays.toString(registration.getContributionMethod())
                                   , registration.getWhyUpay(), registration.getExperienced());
        emailService.sendEmail(to, subject, text);
    }

    public void registerNewVolunteer(VolunteerRegistration registration) {
        Volunteer volunteer = new Volunteer();
        BeanUtils.copyProperties(registration, volunteer);
        repository.save(volunteer);
        sendRegistrationNotification(registration);
    }
}

