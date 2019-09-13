package in.org.upay.volreg.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    public void sendEmail(String[] to, String subject, String text) {
        logger.info("Sending email to " + Arrays.toString(to));
        InternetAddress[] internetAddresses = convertStringsToEmails(to);
        javaMailSender.send(createMimeMailMessage(internetAddresses, subject, text));
        logger.info("Email Sent successfully.");
    }

    private InternetAddress[] convertStringsToEmails(String[] to) {
        InternetAddress[] addresses = new InternetAddress[to.length];
        for (int i=0; i<addresses.length; i++) {
            try {
                addresses[i] = new InternetAddress(to[i]);
            } catch (AddressException e) {
                e.printStackTrace();
            }
        }
        return addresses;
    }

    private MimeMessagePreparator createMimeMailMessage(InternetAddress[] to, String subject, String text) {

        return new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipients(Message.RecipientType.TO, to);
                mimeMessage.setSubject(subject);
                mimeMessage.setContent(text, "text/html");
            }
        };
    }

}
