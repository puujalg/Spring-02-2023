package ee.rainer.kodune4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @PostMapping("addlocation")
    public String addLocation(@RequestBody Location location) {

        if (locationRepository.findById(location.getId()).isEmpty()) {
            locationRepository.save(location);
            return "New location added with an ID " + location.getId();
        } else {
            return "This ID already exists.";
        }

    }

    @DeleteMapping("delelocation/{id}")
    public String deleteLocation(@PathVariable Long id) {

        if (locationRepository.findById(id).isEmpty()) {
            return "This ID doesn't exist.";
        } else {
            locationRepository.deleteById(id);
            return "Location with an ID of " + id + " deleted.";
        }

    }

    @PutMapping("editlocation")
    public String editLocation(@RequestBody Location location) {

        if (locationRepository.findById(location.getId()).isEmpty()) {
            return "This ID doesn't exist.";
        } else {
            locationRepository.save(location);
            return "Location with an ID of " + location.getId() + " has been changed.";
        }

    }

    @GetMapping("getlocation/({id}")
    public Optional<Location> getLocation(@PathVariable Long id) {

        if (locationRepository.findById(id).isEmpty()) {
            return null;
        } else {
            return locationRepository.findById(id);
        }

    }

}
