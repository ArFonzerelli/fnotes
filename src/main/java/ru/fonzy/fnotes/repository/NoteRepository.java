package ru.fonzy.fnotes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

    List<Note> getAllByAuthor(User author);

    List<Note> getAllByAuthorAndCategory(User author, Category category);
}
