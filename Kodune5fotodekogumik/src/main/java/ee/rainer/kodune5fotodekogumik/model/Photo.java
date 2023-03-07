package ee.rainer.kodune5fotodekogumik.model;

import lombok.Data;

import java.util.List;

@Data
public class Photo {

    private List<PhotoResponse> photoList;

}
