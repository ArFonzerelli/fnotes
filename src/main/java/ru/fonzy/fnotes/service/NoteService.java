package ru.fonzy.fnotes.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.NoteDto;

import java.util.List;

@Service
@Transactional
public interface NoteService {

    Note getNoteById(long id);

    List<Note> getNotes(User author);

    List<Note> getNotes(User author, Category category);

    void createNote(NoteDto noteDto, User author);

    void updateNote(NoteDto noteDto, User author);

    void deleteNote(long id);



}
