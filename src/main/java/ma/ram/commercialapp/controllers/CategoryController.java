package ma.ram.commercialapp.controllers;
import ma.ram.commercialapp.entities.Category;

import ma.ram.commercialapp.services.CategoryServiceI;
import ma.ram.commercialapp.services.CommercialServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryServiceI categoryService;
    private final CommercialServiceI commercialServiceI;

    @Autowired
    public CategoryController(CategoryServiceI categoryService, CommercialServiceI commercialServiceI) {
        this.categoryService = categoryService;
        this.commercialServiceI = commercialServiceI;
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category,commercialServiceI.userId()));
    }

    @GetMapping
    public List<Category> showAllCommercialCategories() {

        return categoryService.showAllCommercialCategories(commercialServiceI.userId());
    }

    @PostMapping("/edit")
    public void updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
