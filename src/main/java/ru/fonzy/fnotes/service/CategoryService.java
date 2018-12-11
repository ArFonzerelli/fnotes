package ru.fonzy.fnotes.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.NoteDto;

import java.util.List;

@Service
@Transactional
public interface CategoryService {

    Category getCategoryOrCreateNew(String categoryName, User author);

    Category getCategory(long categoryId, User author);

    List<Category> getCategories(User author);


}
