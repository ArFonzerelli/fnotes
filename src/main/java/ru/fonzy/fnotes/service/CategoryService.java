package ru.fonzy.fnotes.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.NoteDto;

@Service
@Transactional
public interface CategoryService {

    Category getCategoryOrCreateNew(String categoryName);
}
