package in.org.upay.volreg.manager;

import in.org.upay.volreg.dto.VolunteerRegistration;
import in.org.upay.volreg.email.EmailService;
import in.org.upay.volreg.email.EmailTemplate;
import in.org.upay.volreg.model.Volunteer;
import in.org.upay.volreg.repository.VolunteerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Array;
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

    private static String REGISTRATION_EMAIL_SUBJECT = "Upay volunteer registration";

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
            case "NOIDA":
            case "NEW DELHI": emails = "upaydelhi@gmail.com,upaypr@gmail.com";
            break;
            case "GURGAON": emails = "upayncr@gmail.com,upaypr@gmail.com";
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
        // todo: exception handling??
        repository.save(volunteer);
        sendRegistrationNotification(registration);
    }

    public void sendRegistrationEmail(Long id) {
        Volunteer volunteer = repository.findById(id).get();
        String[] to = new String[]{volunteer.getEmail()};
        String registration_link = getRegistrationLink(volunteer.getId());
        String text = String.format(EmailTemplate.REGISTRATION_LINK, volunteer.getName(), registration_link);
        emailService.sendEmail(to, REGISTRATION_EMAIL_SUBJECT, text);
    }

    private String getRegistrationLink(Long id) {
        // todo: update with correct url
        // todo: send uuid instead of id (add uuid in DB)
        return "https://upay.org.in/register/"+id;
    }
}

