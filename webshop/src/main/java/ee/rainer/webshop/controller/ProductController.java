package ee.rainer.webshop.controller;

import ee.rainer.webshop.repository.ProductRepository;
import ee.rainer.webshop.model.database.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("product")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    @DeleteMapping("product/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PostMapping("addproduct")
    public List<Product> addProduct(@RequestBody Product product) {
        if (product.getId() == null || productRepository.findById(product.getId()).isEmpty()) {
            productRepository.save(product);
        }
        return productRepository.findAll();
    }

    @PutMapping("product")
    public List<Product> editProduct(@RequestBody Product product) {
        if (productRepository.findById(product.getId()).isPresent()) {
            productRepository.save(product);
        }
        return productRepository.findAll();
    }

}
