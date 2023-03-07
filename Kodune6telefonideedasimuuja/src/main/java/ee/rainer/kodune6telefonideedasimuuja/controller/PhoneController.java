package ee.rainer.kodune6telefonideedasimuuja.controller;

import ee.rainer.kodune6telefonideedasimuuja.model.PhoneResponse;
import ee.rainer.kodune6telefonideedasimuuja.model.Product;
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
public class PhoneController {

    @Autowired
    RestTemplate restTemplate;

    String phoneUrl = "https://dummyjson.com/products";

    @GetMapping("getallphones")
    public List<PhoneResponse> getAllPhones () {

        ResponseEntity<PhoneResponse> phoneResponseEntity =
                restTemplate.exchange(phoneUrl, HttpMethod.GET, null, PhoneResponse.class);

        List<PhoneResponse> telefonideList = List.of(phoneResponseEntity.getBody());

        return telefonideList;
    }

    @GetMapping("getphonebybrand/{brand}")
    public List<Product> getPhoneByBrand (@PathVariable String brand) {

        List<Product> telefonideList = new ArrayList<>();
        if (getAllPhones().isEmpty() == false) {
            for (PhoneResponse pr: getAllPhones()) {
                for (Product ph: pr.getProducts()) {
                    if (ph.getBrand().equals(brand)) {
                        telefonideList.add(ph);
                    }
                }
            }
        }
        return telefonideList;
    }

    // jne..

}
