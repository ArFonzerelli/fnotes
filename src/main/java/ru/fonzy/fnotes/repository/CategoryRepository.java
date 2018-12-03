package ru.fonzy.fnotes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fonzy.fnotes.domain.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findCategoryByName(String name);
}
