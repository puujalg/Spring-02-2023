package ee.rainer.nordpool2;


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

}
