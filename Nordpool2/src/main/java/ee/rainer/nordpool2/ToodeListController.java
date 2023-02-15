package ee.rainer.nordpool2;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ToodeListController {

    List<Toode> tooted = new ArrayList<>(Arrays.asList(
            new Toode(312312, "Coca", 7.5, true),
            new Toode(312345, "Fanta", 7.3, true),
            new Toode(312423, "Sprite", 7.1, true)
            ));

    // Listi indexi järgi üks toode
    @GetMapping("saatoode/{id}") //localhost:8080/saatoode/id
    public Toode getToodeFromList (@PathVariable int id) {
        return tooted.get(id);
    }

    // Kustuta indexi järgi üks toode
    @GetMapping("kustutatoode/{id}") //localhost:8080/kustutatoode/id
    public List<Toode> deleteToodeFromList (@PathVariable int id) {
        tooted.remove(id);
        return tooted;
    }

    // Kustuta kõik tooted
    @GetMapping("kustutakoiktooted/{id}") //localhost:8080/kustutakoiktooted
    public List<Toode> deleteAllToode (@PathVariable int id) {
        tooted.clear();
        return tooted;
    }

}
