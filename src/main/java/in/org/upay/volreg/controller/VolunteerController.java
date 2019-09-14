package in.org.upay.volreg.controller;

import in.org.upay.volreg.dto.VolunteerRegistration;
import in.org.upay.volreg.manager.VolunteerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class VolunteerController {

    @Autowired
    private VolunteerManager volunteerManager;

    @PostMapping
    @CrossOrigin
    public void registerVolunteer(@Valid @RequestBody VolunteerRegistration registration) {

        // todo save volunteer to DB before sending emails
        volunteerManager.sendRegistrationNotification(registration);

    }
}



