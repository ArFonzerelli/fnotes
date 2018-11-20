package ru.fonzy.fnotes.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fonzy.fnotes.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findCategoryByName(String name);
}
