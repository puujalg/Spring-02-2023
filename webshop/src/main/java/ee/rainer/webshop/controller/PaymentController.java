package ee.rainer.webshop.controller;

import ee.rainer.webshop.model.database.Order;
import ee.rainer.webshop.model.database.Person;
import ee.rainer.webshop.model.database.Product;
import ee.rainer.webshop.model.request.EverypayData;
import ee.rainer.webshop.model.request.EverypayResponse;
import ee.rainer.webshop.model.request.EverypayStatus;
import ee.rainer.webshop.repository.OrderRepository;
import ee.rainer.webshop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    PersonRepository personRepository;
    @Autowired
    OrderRepository orderRepository;

    @Value("${everypay.url}") // not lombok
    private String everypayUrl;
    @Value("${everypay.username}") // not lombok
    private String everypayUsername;
    @Value("${everypay.account}") // not lombok
    private String everypayAccount;
    @Value("${everypay.customerUrl}") // not lombok
    private String everypayCustomerUrl;
    @Value("${everypay.authorization}") // not lombok
    private String everypayAuthorization;

    @PostMapping("payment/{personalCode}")
    public EverypayResponse makePayment(@PathVariable String personalCode, @RequestBody List<Product> products) {

        Person person = personRepository.findById(personalCode).get();

        Order order = new Order();
        order.setOrderProducts(products);
        order.setPaid(false);
        order.setPerson(person);
        order.setCreated(new Date());
        order.setTotalSum(123);
        Order dbOrder = orderRepository.save(order);

        String url = everypayUrl + "payments/oneoff";

        EverypayData everypayData = new EverypayData();
        everypayData.setApi_username(everypayUsername);
        everypayData.setAccount_name(everypayAccount);
        everypayData.setAmount(99999);
        //teeme arvutuse, mis on kogusumma forEach tsükli + stream
        // ei võta iga toote juures summat, mis tuleb Bodys, vaid tsükli sees pöördun ID-ga andmebaasi poola ja
        // võtan andmebaasist originaalse toote ja tema küljest hinna
        everypayData.setOrder_reference(dbOrder.getId().toString());
        // Teeme Tellimuste andmebaasimudeli ja sisestame enne maksma hakkamist andmebaasi
        everypayData.setNonce("asdasd1s" + new Date() + Math.random());
        everypayData.setTimestamp(ZonedDateTime.now().toString()); //ZoneDateTime.now().toString()
        everypayData.setCustomer_url(everypayCustomerUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, everypayAuthorization);
        HttpEntity<EverypayData> httpEntity = new HttpEntity<>(everypayData, headers);

        ResponseEntity<EverypayResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, EverypayResponse.class);

        return responseEntity.getBody();
    }

    // Päring tuleb aadressile tagasi nii makstud kui maksmata staatusega
    // Teeme ühe EveryPay päringu osa veel makse osa lõpetamiseks
    // 3.6.5 GET /payment :payment_reference

    @GetMapping("check-payment-status/{paymentReference}")
    public EverypayStatus checkPaymentStatus(@PathVariable String paymentReference) {

        String url = everypayUrl + "payments/" + paymentReference + "?api_username=" + everypayUsername;

        EverypayStatus everypayStatus = new EverypayStatus();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, everypayAuthorization);

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<EverypayStatus> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, EverypayStatus.class);

        EverypayStatus body = response.getBody();

        if (response.getBody() != null) {
            if (body.getPayment_state().equals("settled")) {
                Long orderId = Long.parseLong(response.getBody().getOrder_reference());
                Order order = orderRepository.findById(orderId).get();
                order.setPaid(true);
                orderRepository.save(order);
            }
            everypayStatus.setPayment_state(body.getPayment_state());
            everypayStatus.setOrder_reference(body.getOrder_reference());
        }

        everypayStatus.setPayment_state(body.getPayment_state());
        everypayStatus.setOrder_reference(body.getOrder_reference());

        return  everypayStatus;
    }

}
