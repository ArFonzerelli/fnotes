package ru.fonzy.fnotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryOrCreateNew(String categoryName){
        Category category = categoryRepository.findCategoryByName(categoryName);

        if (category == null) {
            category = new Category(categoryName);
            categoryRepository.save(category);
        }

        return category;

    }
}
