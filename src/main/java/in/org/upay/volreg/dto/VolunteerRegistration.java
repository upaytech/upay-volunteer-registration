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
    private String cityName;
    @NotEmpty @NotNull
    private String[] contributionMethod;
    @NotEmpty @NotNull
    private String whyUpay;
    @NotEmpty @NotNull
    private String experienced;
    @NotEmpty @NotNull
    private String extraAcademy;

}
