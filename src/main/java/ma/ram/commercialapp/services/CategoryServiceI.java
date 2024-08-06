package ma.ram.commercialapp.services;

import ma.ram.commercialapp.entities.Category;

import java.util.List;

public interface CategoryServiceI {
    Category addCategory(Category category,Long idCommercial);
    List<Category> showAllCommercialCategories(Long idCommercial);
    void updateCategory(Category category);
    void delete(Long id);
}
