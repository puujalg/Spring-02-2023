package ee.rainer.kodune3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TulemuseController {

    @Autowired
    TulemusteRepository tulemusteRepository;
    @Autowired
    SpordialadeRepository spordialadeRepository;

    @GetMapping("kuvatulemused")
    public List<Tulemus> kuvaSpordialad() {
        return tulemusteRepository.findAll();
    }

    // {"tulemuseId":1102,"sportlaseId":202,"spordialaId":102,"tulemus":410,"tulemusPunktid":0.0}
    @PostMapping("lisatulemus")
    public List<Tulemus> lisaTulemus(@RequestBody Tulemus tulemus) {
        if (tulemusteRepository.findById(tulemus.getTulemuseId()).isEmpty()) {

            tulemus.setTulemusPunktid(arvutaPunktideks(spordialadeRepository.findById(tulemus.getSpordialaId()).get().isJooksuala(),
                    spordialadeRepository.findById(tulemus.getSpordialaId()).get().getA(),
                    spordialadeRepository.findById(tulemus.getSpordialaId()).get().getB(),
                    spordialadeRepository.findById(tulemus.getSpordialaId()).get().getC(),
                    tulemus.getTulemus()));


            tulemusteRepository.save(tulemus);
        }
        return tulemusteRepository.findAll();
    }

    // tulemuse arvutatakse punktideks (p = jooksud SEKUNDID / heited MEETRID / hüpped SENTIMEETRID
    public double arvutaPunktideks(boolean kasJooksuala, double a, double b, double c, double p) {
        double alaPunktid = 0;

        if (kasJooksuala) {
            alaPunktid = Math.pow(b-p, c) * a;
        } else {
            alaPunktid = Math.pow(p-b, c) * a;
        }

        return alaPunktid;
    }
}
