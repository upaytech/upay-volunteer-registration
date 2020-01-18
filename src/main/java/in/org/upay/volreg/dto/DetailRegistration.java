package in.org.upay.volreg.dto;

import lombok.Data;

@Data
public class DetailRegistration {

    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private String maritalStatus;
    private String nationality;
    private String dob;
    private String address;
    private String bloodGroup;
    private String hobby;
    private String educationalQualification;
    private String Occupation;
    private String purpose;
    private String city;
    private String center;
    private String hoursPerDay;
    private String daysPerWeek;
    private boolean availPetrolAllowance;
    private double centerDistance;

}
