package ee.rainer.webshop.controller;

// https://www.omniva.ee/locations.json
// https://www.smartpost.ee/places.json

import ee.rainer.webshop.model.request.OmnivaPM;
import ee.rainer.webshop.model.request.ParcelMachineResponse;
import ee.rainer.webshop.model.request.SmartpostPM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ParcelMachineController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("parcelmachines/{country}")
    public ParcelMachineResponse getParcelMachines(@PathVariable String country) {
        country = country.toUpperCase();
        String omnivaUrl = "https://www.omniva.ee/locations.json";
        String smartpostUrl = "https://www.smartpost.ee/places.json";

        ResponseEntity<OmnivaPM[]> omnivaResponse =
                restTemplate.exchange(omnivaUrl, HttpMethod.GET, null, OmnivaPM[].class);

        List<OmnivaPM> omniva = new ArrayList<>();
        if (omnivaResponse.getBody() != null) {
            for (OmnivaPM pm: omnivaResponse.getBody()) {
                if (pm.getA0_NAME().equals(country)) {
                    omniva.add(pm);
                }
            }
        }

        /**
        List<OmnivaPM> omniva = new ArrayList<>();
        if (omnivaResponse.getBody() != null) {
            omniva = List.of(omnivaResponse.getBody());
            String finalCountry = country;
            omniva = omniva.stream().filter(e -> e.a0_NAME.equals(finalCountry)).collect(Collectors.toList());
        }
         */

        ResponseEntity<SmartpostPM[]> smartpostResponse =
                restTemplate.exchange(smartpostUrl, HttpMethod.GET, null, SmartpostPM[].class);

        ParcelMachineResponse response = new ParcelMachineResponse();

        if (country.equals("EE") && smartpostResponse.getBody() != null) {
            List<SmartpostPM> smartpost = List.of(smartpostResponse.getBody());
            response.setSmartpostPMs(smartpost);
        } else {
            response.setOmnivaPMs(new ArrayList<>());
        }

        response.setOmnivaPMs(omniva);

        return response;
    }

}
