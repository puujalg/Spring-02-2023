package ee.rainer.nordpool2;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
public class NordpoolController {

    // https://dashboard.elering.ee/api/nps/price
    String url = "https://dashboard.elering.ee/api/nps/price";

    @GetMapping("nordpool")
    public ArrayList<TimestampPrice> getNordpoolPrices(@RequestParam String country,
                                                       @RequestParam String start,
                                                       @RequestParam String end) {

        int startYear = Integer.parseInt(start.substring(6, 10));
        int startMonth = Integer.parseInt(start.substring(3, 5));
        int startDay = Integer.parseInt(start.substring(0, 2));
        int endYear = Integer.parseInt(start.substring(6, 10));
        int endMonth = Integer.parseInt(start.substring(3, 5));
        int endDay = Integer.parseInt(start.substring(0, 2));

        //String startDate = ZonedDateTime.of(LocalDate.of(LocalDate.of(startYear, startMonth, startDay), LocalTime.of(0, 0))).format(DateTimeFormatter.ISO_INSTANT);

        //String endDate = ZonedDateTime.of(LocalDate.of()).format(DateTimeFormatter.ISO_INSTANT);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NordpoolData> response = restTemplate.exchange(url, HttpMethod.GET, null, NordpoolData.class);

        //String date = ZonedDateTime.now(ZoneOffset.UTC)

        if (country.equals("ee")) {
            return  response.getBody().getData().getEe();
        } else if (country.equals("lv")) {
            return  response.getBody().getData().getLv();
        } else if (country.equals("lt")) {
            return  response.getBody().getData().getLt();
        } else if (country.equals("fi")) {
            return  response.getBody().getData().getFi();
        } else {
            return null;
        }

    }

}
