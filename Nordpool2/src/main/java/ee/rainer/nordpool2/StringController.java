package ee.rainer.nordpool2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringController {

    @GetMapping("hi") //localhost:8080/hi
    public String getWord() {

        return "Hello world!";

    }

    @GetMapping("hi/{nimi}") //localhost:8080/hi/nimi
    public String getNimi(@PathVariable String nimi) {

        return "Hello " + nimi;

    }

    @GetMapping("multiply/{nr1}/{nr2}") //localhost:8080/multiply/nr1/nr2
    public int korrutaNumber(@PathVariable int nr1, @PathVariable int nr2) {

        return nr1 * nr2;

    }

    @GetMapping("do-logs/{mitu}") //localhost:8080/do-logs/...
    public void teeLogi(@PathVariable int mitu) {

        for (int i = 0; i < mitu; i++) {
            System.out.println("Logi nr " + i);
        }

    }

}
