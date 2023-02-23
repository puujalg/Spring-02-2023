package ee.rainer.kodune3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SportlaseController {

    @Autowired
    SportlasteRepository sportlasteRepository;
    @Autowired
    TulemusteRepository tulemusteRepository;

    @PostMapping("lisasportlane")
    public List<Sportlane> lisaSpordiala(@RequestBody Sportlane sportlane) {
        if (sportlasteRepository.findById(sportlane.getId()).isEmpty()) {
            sportlasteRepository.save(sportlane);
        }
        return sportlasteRepository.findAll();
    }

    @PutMapping("muudasportlast")
    public List<Sportlane> muudaSportlast(@RequestBody Sportlane sportlane) {
        if (sportlasteRepository.findById(sportlane.getId()).isPresent()) {
            sportlasteRepository.save(sportlane);
        }
        return sportlasteRepository.findAll();
    }

    @DeleteMapping("kustutasportlane/{id}")
    public List<Sportlane> kustutaSportlane(@PathVariable Long id) {
        sportlasteRepository.deleteById(id);
        return sportlasteRepository.findAll();
    }


    @GetMapping("sportlasepunktiseis/{id}")
    public double sportlaseHetkePunktid(@PathVariable Long id) {

        double punktisumma = 0;

        List<Tulemus> sportlaseTulemused = tulemusteRepository.findAllBySportlaseId(id);

        for (Tulemus tulemus: sportlaseTulemused) {
            punktisumma += tulemus.getTulemusPunktid();
        }

        return punktisumma;
    }
}
