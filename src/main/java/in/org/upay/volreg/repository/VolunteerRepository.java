package in.org.upay.volreg.repository;

import in.org.upay.volreg.model.Volunteer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {

    List<Volunteer> findByName(String name);

    Volunteer findById(long id);
}
