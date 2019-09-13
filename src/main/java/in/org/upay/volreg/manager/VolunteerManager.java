package in.org.upay.volreg.manager;

import in.org.upay.volreg.dto.VolunteerRegistration;
import in.org.upay.volreg.email.EmailService;
import in.org.upay.volreg.email.EmailTemplate;
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

    private String[] getNotificationAddresses(String centerName) {
        // todo add a logic to get map of zone id to email addresses
        String[] emails = {"piyushranjan95@gmail.com"};
        return emails;
    }

    public void sendRegistrationNotification(VolunteerRegistration registration) {
        String[] to = getNotificationAddresses(registration.getCenterName());
        String subject = String.format(EmailTemplate.REGISTRATION_EMAIL_MESSAGE, registration.getCenterName());
        String text = String.format(EmailTemplate.HTML_EMAIL_CONTENT, registration.getName(), registration.getEmail(), registration.getMobile(),
                                    registration.getQualification(), registration.getCenterName(), Arrays.toString(registration.getContributionMethod())
                                   , registration.getWhyUpay(), registration.isExperienced() ? "Yes" : "No");
        emailService.sendEmail(to, subject, text);
    }
}

