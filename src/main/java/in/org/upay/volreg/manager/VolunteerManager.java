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
        // todo: read emails from env based properties file
        String emails = "";
        switch (cityName.toUpperCase()) {
            case "PUNE": emails = "upaypune@gmail.com,upaypr@gmail.com";
            break;
            case "MOUDA": emails = "upaymda@gmail.com,upaypr@gmail.com";
            break;
            case "NAGPUR": emails = "upayngp@gmail.com,upaypr@gmail.com";
            break;
            case "DELHI": emails = "upaydelhi@gmail.com,upaypr@gmail.com";
            break;
            case "GURUGRAM": emails = "upayncr@gmail.com,upaypr@gmail.com";
            break;
            case "BANGALORE": emails = "upaybangalore@gmail.com,upaypr@gmail.com";
            break;
            default:
                emails = "upaypr@gmail.com";
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

