package in.org.upay.volreg.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String qualification;
    private String cityName;

    public Volunteer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Volunteer() {}

}
