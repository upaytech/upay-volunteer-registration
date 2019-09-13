package in.org.upay.volreg.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VolunteerRegistration {
    @NotEmpty @NotNull
    private String name;
    @NotEmpty @NotNull
    private String email;
    @NotEmpty @NotNull
    private String mobile;
    @NotEmpty @NotNull
    private String qualification;
    @NotEmpty @NotNull
    private String centerName;
    @NotEmpty @NotNull
    private String[] contributionMethod;
    @NotEmpty @NotNull
    private String whyUpay;
    @NotNull
    private boolean experienced;

    /**
     {"name": "Piyush", "email": "piyushranjan95@gmail.com", "mobile": "9504875678", "qualification": "BE", "centerName": "pune", "contributionMethod": ["teaching"], "whyUpay": "ksjvn jnkjsfv", "experienced": false}
     */

}
