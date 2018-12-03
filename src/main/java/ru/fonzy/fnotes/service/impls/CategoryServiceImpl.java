package ru.fonzy.fnotes.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.repository.CategoryRepository;
import ru.fonzy.fnotes.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category getCategoryOrCreateNew(String categoryName){
        Category category = categoryRepository.findCategoryByName(categoryName);

        if (category == null) {
            category = new Category(categoryName);
            categoryRepository.save(category);
        }

        return category;

    }
}
