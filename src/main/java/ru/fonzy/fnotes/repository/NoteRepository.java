package ru.fonzy.fnotes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

    Iterable<Note> getAllByAuthor(User author);


}
