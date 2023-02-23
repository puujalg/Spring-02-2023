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

    // {"id":240,"nimi":"sportlasenimi,"riik":"sportlaseriik","vanus":50}
    @PostMapping("lisasportlane")
    public List<Sportlane> lisaSportlane(@RequestBody Sportlane sportlane) {
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

        // Peaks lisama ka sportlase tulemuste kustutamise?

    }


    @GetMapping("sportlasepunktiseis/{id}")
    public double sportlaseHetkePunktid(@PathVariable Long id) {

        double punktiSumma = 0;

        List<Tulemus> sportlaseTulemused = tulemusteRepository.findAllBySportlaseId(id);

        for (Tulemus tulemus: sportlaseTulemused) {
            punktiSumma += tulemus.getTulemusPunktid();
        }

        return punktiSumma;
    }
}
