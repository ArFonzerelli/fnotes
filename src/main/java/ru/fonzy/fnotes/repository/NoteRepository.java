package ru.fonzy.fnotes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fonzy.fnotes.domain.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

}
