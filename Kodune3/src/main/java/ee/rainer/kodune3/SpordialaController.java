package ee.rainer.kodune3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpordialaController {

    @Autowired
    SpordialadeRepository spordiAladeRepository;

    @GetMapping("kuvaspordialad")
    public List<Spordiala> kuvaSpordialad() {
        return spordiAladeRepository.findAll();
    }
    @PostMapping("lisaspordiala")
    public List<Spordiala> lisaSpordiala(@RequestBody Spordiala spordiala) {
        if (spordiAladeRepository.findById(spordiala.getId()).isEmpty()) {
            spordiAladeRepository.save(spordiala);
        }
        return spordiAladeRepository.findAll();
    }

    @PutMapping("muudaspordiala")
    public List<Spordiala> editProduct(@RequestBody Spordiala spordiala) {
        if (spordiAladeRepository.findById(spordiala.getId()).isPresent()) {
            spordiAladeRepository.save(spordiala);
        }
        return spordiAladeRepository.findAll();
    }

    @DeleteMapping("kustutaspordiala/{id}")
    public List<Spordiala> deleteProduct(@PathVariable Long id) {
        spordiAladeRepository.deleteById(id);
        return spordiAladeRepository.findAll();
    }
}
