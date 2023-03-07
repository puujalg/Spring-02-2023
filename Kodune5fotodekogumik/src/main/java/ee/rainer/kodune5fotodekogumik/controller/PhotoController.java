package ee.rainer.kodune5fotodekogumik.controller;

import ee.rainer.kodune5fotodekogumik.model.Photo;
import ee.rainer.kodune5fotodekogumik.model.PhotoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PhotoController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("getphotos")
    public Photo getPhotos() {

        String url = "https://jsonplaceholder.typicode.com/photos";

        ResponseEntity<PhotoResponse[]> photoResponse = restTemplate.exchange(url, HttpMethod.GET, null, PhotoResponse[].class);

        List<PhotoResponse> response = new ArrayList<>();

        if (photoResponse.getBody() != null) {
            for (PhotoResponse pr: photoResponse.getBody()) {
                response.add(pr);
            }
        }

        Photo finalList = new Photo();
        finalList.setPhotoList(response);

        return finalList;
    }

}
