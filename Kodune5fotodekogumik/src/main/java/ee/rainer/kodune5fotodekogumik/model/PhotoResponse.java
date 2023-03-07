package ee.rainer.kodune5fotodekogumik.model;

import lombok.Data;

@Data
public class PhotoResponse {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

}
