package in.org.upay.volreg.email;

// todo find a good templating engine
public class EmailTemplate {

    public static final String REGISTRATION_EMAIL_MESSAGE = "New Volunteer Registration - %s";

    public static final String HTML_EMAIL_CONTENT =
            "<p>" +
                    "  Hello, <br> A new volunteer has registered. Here are the details :" +
                    "</p>" +
                    "<table style=\"width: 100%%;" +
                    "  max-width: 450px;" +
                    "  border-collapse: collapse;\">" +
                    "  <tr style='background: #d4d4d4'>" +
                    "    <td style='border: 1px solid grey; padding: 10px'><b>Name</b></td>" +
                    "    <td style='border: 1px solid grey; padding: 10px'>%s</td>" +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td style='border: 1px solid grey; padding: 10px'><b>Email Address</b></td>" +
                    "    <td style='border: 1px solid grey; padding: 10px'>%s</td>" +
                    "  </tr>" +
                    "  <tr style='background: #d4d4d4'>" +
                    "    <td style='border: 1px solid grey; padding: 10px'><b>Mobile</b></td>" +
                    "    <td style='border: 1px solid grey; padding: 10px'>%s</td>" +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td style='border: 1px solid grey; padding: 10px'><b>Qualification and Profession</b></td>" +
                    "    <td style='border: 1px solid grey; padding: 10px'>%s</td>" +
                    "  </tr>" +
                    "  <tr style='background: #d4d4d4'>" +
                    "    <td style='border: 1px solid grey; padding: 10px'><b>City</b></td>" +
                    "    <td style='border: 1px solid grey; padding: 10px'>%s</td>" +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td style='border: 1px solid grey; padding: 10px'><b>How do you wish to contribute</b></td>" +
                    "    <td style='border: 1px solid grey; padding: 10px'>%s</td>" +
                    "  </tr>" +
                    "  <tr style='background: #d4d4d4'>" +
                    "    <td style='border: 1px solid grey; padding: 10px'><b>Why do you want to join Upay (Include how did you know about upay)</b></td>" +
                    "    <td style='border: 1px solid grey; padding: 10px'>%s</td>" +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td style='border: 1px solid grey; padding: 10px'><b>Do you have any previous experience in Social Work, teaching, etc ?</b></td>" +
                    "    <td style='border: 1px solid grey; padding: 10px'>%s</td>" +
                    "  </tr>" +
                    "  <tr style='background: #d4d4d4'>" +
                    "    <td style='border: 1px solid grey; padding: 10px'><b>Do you have any certifications in extra academic activities like singing, dancing, sports etc.?" +
                    "       Are you willing to train the children?</b></td>" +
                    "    <td style='border: 1px solid grey; padding: 10px'>%s</td>" +
                    "  </tr>" +
                    "</table>" +
                    "" +
                    "<p> Thanks </p>";

}
