package ee.rainer.kodune6telefonideedasimuuja.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public
class Product {
    private int id;
    private String title;
    private String description;
    private int price;
    private double discountPercentage;
    private double rating;
    private int stock;
    private String brand;
    private String category;
    private String thumbnail;
    private ArrayList<String> images;
}
