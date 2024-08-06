package ma.ram.commercialapp.services.servicesImpl;

import ma.ram.commercialapp.entities.Category;
import ma.ram.commercialapp.entities.Commercial;
import ma.ram.commercialapp.repositories.CategoryRepository;
import ma.ram.commercialapp.repositories.CommercialRepository;
import ma.ram.commercialapp.services.CategoryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryServiceI {


    private final CategoryRepository categoryRepository;
    private final CommercialRepository commercialRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CommercialRepository commercialRepository) {
        this.categoryRepository = categoryRepository;
        this.commercialRepository = commercialRepository;
    }

    @Override
    public Category addCategory(Category category, Long idCommercial) {
        Commercial commercial=commercialRepository.findById(idCommercial).orElseThrow(()->new RuntimeException("commercial not found"));
        commercial.getCategories().add(category);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> showAllCommercialCategories(Long idCommercial) {
        return Collections.unmodifiableList(categoryRepository.findAllByCommercialId(idCommercial).orElseThrow(()->new RuntimeException("you don't have anything")));
    }

    @Override
    public void updateCategory(Category category) {
        Category updatedCategory=categoryRepository.findById(category.getId()).orElseThrow(()->new RuntimeException("category not found"));
        updatedCategory.setCategoryDescription(category.getCategoryDescription());
        updatedCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
