package in.org.upay.volreg.manager;

import in.org.upay.volreg.VolregApplication;
import in.org.upay.volreg.dto.VolunteerRegistration;
import in.org.upay.volreg.email.EmailService;
import in.org.upay.volreg.email.EmailTemplate;
import in.org.upay.volreg.model.Volunteer;
import in.org.upay.volreg.repository.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Utility functions for handling volunteer class
 */
@Component
public class VolunteerManager {

    @Value("${contact.email.pune}")
    private String EMAIL_PUNE;
    @Value("${contact.email.mouda}")
    private String EMAIL_MOUDA;
    @Value("${contact.email.delhi}")
    private String EMAIL_DELHI;
    @Value("${contact.email.bangalore}")
    private String EMAIL_BANGALORE;
    @Value("${contact.email.ncr}")
    private String EMAIL_NCR;
    @Value("${contact.email.default}")
    private String EMAIL_DEFAULT;
    @Value("${contact.email.nagpur}")
    private String EMAIL_NAGPUR;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VolunteerRepository repository;

    private static final Logger log = LoggerFactory.getLogger(VolregApplication.class);

    private String[] getNotificationAddresses(String cityName) {
        // todo: read emails from env based properties file
        String emails = "";
        switch (cityName.toUpperCase()) {
            case "PUNE": emails = EMAIL_PUNE;
            break;
            case "MOUDA": emails = EMAIL_MOUDA;
            break;
            case "NAGPUR": emails = EMAIL_NAGPUR;
            break;
            case "NOIDA":
            case "NEW DELHI": emails = EMAIL_DELHI;
            break;
            case "GURGAON": emails = EMAIL_NCR;
            break;
            case "BANGALORE": emails = EMAIL_BANGALORE;
            break;
            default:
                emails = EMAIL_DEFAULT;
        }

        return emails.split(",");
    }

    private void sendRegistrationNotification(VolunteerRegistration registration) {
        String[] to = getNotificationAddresses(registration.getCityName());
        String subject = String.format(EmailTemplate.REGISTRATION_EMAIL_MESSAGE, registration.getCityName());
        String text = String.format(EmailTemplate.HTML_EMAIL_CONTENT, registration.getName(), registration.getEmail(), registration.getMobile(),
                                    registration.getQualification(), registration.getCityName(), Arrays.toString(registration.getContributionMethod())
                                   , registration.getWhyUpay(), registration.getExperienced(), registration.getExtraAcademy());
        emailService.sendEmail(to, subject, text);
    }

    public void registerNewVolunteer(VolunteerRegistration registration) {
        Volunteer volunteer = new Volunteer();
        BeanUtils.copyProperties(registration, volunteer);
        // todo: exception handling??
        repository.save(volunteer);
        sendRegistrationNotification(registration);
    }
}

