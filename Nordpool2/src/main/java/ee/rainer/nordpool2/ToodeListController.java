package ee.rainer.nordpool2;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ToodeListController {

    List<Toode> autod = new ArrayList<>(Arrays.asList(
            new Toode(312311, "BMW", 7.5, true),
            new Toode(312312, "Tesla", 2.5, false),
            new Toode(312313, "Nobe", 5.5, true)
            ));

    // Listi indexi järgi üks toode
    @GetMapping("saatoode/{id}") //localhost:8080/saatoode/id
    public Toode getToodeFromList (@PathVariable int id) {
        return autod.get(id);
    }

    // Kustuta indexi järgi üks toode
    @GetMapping("kustutatoode/{id}") //localhost:8080/kustutatoode/id
    public List<Toode> deleteToodeFromList (@PathVariable int id) {
        autod.remove(id);
        return autod;
    }

    // Kustuta kõik tooted
    @GetMapping("kustutakoiktooted") //localhost:8080/kustutakoiktooted
    public List<Toode> deleteAllToode () {
        autod.clear();
        return autod;
    }

    // .........

    @GetMapping("hinnad-kokku")
    public double hinnadKokku() {
        double summa = 0;
        for (Toode toode : autod) {
            summa += toode.getHind();
        }

        return summa;
    }

    @GetMapping("suurenda-hina/{suurendus}")
    public List<Toode> suurendaHinda(@PathVariable double suurendus) {
        for (Toode toode : autod) {
            toode.setHind(toode.getHind() + suurendus);
        }
        return autod;
    }


    // ...

    @GetMapping("lisa1/{id}/{nimi}/{hind}/{aktiivne}")
    public List<Toode> lisa1 (@PathVariable int id, @PathVariable String nimi, @PathVariable double hind, @PathVariable boolean aktiivne) {
        Toode toode = new Toode(id, nimi, hind, aktiivne);
        autod.add(toode);
        return autod;
    }

    @GetMapping("lisa2")
    public List<Toode> lisa2 (@RequestParam int id, @RequestParam String nimi, @RequestParam double hind, @RequestParam boolean aktiivne) {
        Toode toode = new Toode(id, nimi, hind, aktiivne);
        autod.add(toode);
        return autod;
    }

    @PostMapping("lisa3")
    public List<Toode> lisa3 (@RequestBody Toode toode) {
        //Toode toode = new Toode(id, nimi, hind, aktiivne);
        autod.add(toode);
        return autod;
    }

    // 2xx --->  õnnestus
    // 200 OK
    // 210 Creates

    // 4xx front-end viga / päringu viga
    // 400 üldine viga
    // 401 vale kasutaja / parool
    // 402 makse nõutud
    // 403 isegi õigete sisselogismistunnustega ei saa sisse
    // 404 ---> mitteleitud API otspunkt
    // 405 ---> vale meetodi tüüp GET / POST / PUT / DELETE / PATCH
    // 415 ---> saadan JSON kuju asemel tekstina

    // 5xx ---> serve serveri viga
    // server on maas, anname indexi mis on liiga suur, koodiviga, nullpointe

}
