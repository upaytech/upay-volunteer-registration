package in.org.upay.volreg.controller;

import in.org.upay.volreg.dto.VolunteerRegistration;
import in.org.upay.volreg.manager.VolunteerManager;
import in.org.upay.volreg.model.Volunteer;
import in.org.upay.volreg.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class VolunteerController {

    @Autowired
    private VolunteerManager volunteerManager;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @PostMapping
    @CrossOrigin
    public void registerVolunteer(@Valid @RequestBody VolunteerRegistration registration) {

        volunteerManager.registerNewVolunteer(registration);

    }

    @GetMapping
    @CrossOrigin
    public Iterable<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<Volunteer> getVolunteerById(@Valid @PathVariable(value = "id") Long id) {
        return volunteerRepository.findById(id);
    }

}



