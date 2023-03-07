package ee.rainer.webshop.controller;

import ee.rainer.webshop.model.database.Product;
import ee.rainer.webshop.model.request.EverypayData;
import ee.rainer.webshop.model.request.EverypayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class PaymentController {

    // https://support.every-pay.com/downloads/everypay_apiv4_integration_documentation.pdf

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("payment")
    public EverypayResponse makePayment(@RequestBody List<Product> products) {

        String url = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";

        EverypayData everypayData = new EverypayData();
        everypayData.setApi_username("92ddcfab96e34a5f");
        everypayData.setAccount_name("EUR3D1");
        everypayData.setAmount(100);
        //teeme arvutuse, mis on kogusumma forEach tsükli + stream
        // ei võta iga toote juures summat, mis tuleb Bodys, vaid tsükli sees pöördun ID-ga andmebaasi poola ja
        // võtan andmebaasist originaalse toote ja tema küljest hinna
        everypayData.setOrder_reference("aabc123");
        // Teeme Tellimuste andmebaasimudeli ja sisestame enne maksma hakkamist andmebaasi
        everypayData.setNonce("asdasd" + new Date() + Math.random());
        everypayData.setTimestamp(ZonedDateTime.now().toString()); //ZoneDateTime.now().toString()
        everypayData.setCustomer_url("https://err.ee");

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Basic OTJkZGNmYWI5NmUzNGE1Zjo4Y2QxOWU5OWU5YzJjMjA4ZWU1NjNhYmY3ZDBlNGRhZA==");

        HttpEntity<EverypayData> httpEntity = new HttpEntity<>(everypayData, headers);

        ResponseEntity<EverypayResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, EverypayResponse.class);

        return responseEntity.getBody();
    }

    // Päring tuleb aadressile tagasi nii makstud kui maksmata staatusega
    // Teeme ühe EveryPay päringu osa veel makse osa lõpetamiseks
    // 3.6.5 GET /payment :payment_reference

}
