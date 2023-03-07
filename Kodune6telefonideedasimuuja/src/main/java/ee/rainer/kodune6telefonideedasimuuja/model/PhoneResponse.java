package ee.rainer.kodune6telefonideedasimuuja.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PhoneResponse {

    private ArrayList<Product> products;
    private int total;
    private int skip;
    private int limit;

}

