package ee.rainer.kodune3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpordialaController {

    @Autowired
    SpordialadeTabel spordiAladeTabel;

    @PostMapping("lisaspordiala")
    public List<Spordiala> lisaSpordiala(@RequestBody Spordiala spordiala) {
        if (spordiAladeTabel.findById(spordiala.getId()).isEmpty()) {
            spordiAladeTabel.save(spordiala);
        }
        return spordiAladeTabel.findAll();
    }

    @PutMapping("muudaspordiala")
    public List<Spordiala> editProduct(@RequestBody Spordiala spordiala) {
        if (spordiAladeTabel.findById(spordiala.getId()).isPresent()) {
            spordiAladeTabel.save(spordiala);
        }
        return spordiAladeTabel.findAll();
    }

    @DeleteMapping("kustutaspordiala/{id}")
    public List<Spordiala> deleteProduct(@PathVariable Long id) {
        spordiAladeTabel.deleteById(id);
        return spordiAladeTabel.findAll();
    }
}
