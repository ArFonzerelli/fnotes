package ru.fonzy.fnotes.service.impls;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.CategoryDto;
import ru.fonzy.fnotes.repository.CategoryRepository;
import ru.fonzy.fnotes.service.CategoryService;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(long categoryId, User author) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (!author.getId().equals(category.getAuthor().getId()))
            return null;

        return category;
    }

    @Override
    public Category getCategoryOrCreateNew(String categoryName, User author){
        if (Strings.isNullOrEmpty(categoryName))
            categoryName = "Без категории";

        Category category = categoryRepository.findCategoryByName(categoryName);

        if (category == null) {
            category = new Category(categoryName, author);

            categoryRepository.save(category);
        }

        return category;

    }

    @Override
    public List<Category> getCategories(User author) {
        return categoryRepository.getAllByAuthor(author);
    }

    @Override
    public void createCategory(CategoryDto categoryDto, User currentUser) {
        Category category = new Category();
        category.setName(categoryDto.getCategoryName());
        category.setAuthor(currentUser);

        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto, User currentUser) {
        Category category = categoryRepository.findById(categoryDto.getId()).orElse(null);

        if (category != null) {
            category.setName(categoryDto.getEditCategoryName());
            categoryRepository.save(category);
        }

    }

    @Override
    public void delete(long id, User author) {
        Category category = categoryRepository.findById(id).orElse(null);

        List<Note> notes = category.getNotes();

        if (notes != null){
            for (Note note : notes){
                note.setCategory(getCategoryOrCreateNew("Без категории", author));
            }
        }

        category.setNotes(null);

        categoryRepository.delete(category);
    }
}
