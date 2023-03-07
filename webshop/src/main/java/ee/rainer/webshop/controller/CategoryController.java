package ee.rainer.webshop.controller;

import ee.rainer.webshop.model.database.Category;
import ee.rainer.webshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("getcategories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("getcategory/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryRepository.findById(id).get();
    }

    @DeleteMapping("deletecategory/{id}")
    public List<Category> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }

    @PostMapping("addcategory")
    public List<Category> addCategory(@RequestBody Category category) {
        if (category.getId() == null || categoryRepository.findById(category.getId()).isEmpty()) {
            categoryRepository.save(category);
        }
        return categoryRepository.findAll();
    }

    @PutMapping("editcategory")
    public List<Category> editCategory(@RequestBody Category category) {
        if (categoryRepository.findById(category.getId()).isPresent()) {
            categoryRepository.save(category);
        }
        return categoryRepository.findAll();
    }

}
