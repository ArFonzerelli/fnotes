package ru.fonzy.fnotes.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.NoteDto;

@Service
@Transactional
public interface NoteService {

    Note getNoteById(long id);

    Iterable<Note> getNotesByAuthor(User author);

    void createNote(NoteDto noteDto, User author);

    void updateNote(NoteDto noteDto, User author);

    void deleteNote(long id);



}
