package ru.fonzy.fnotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.Importance;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.repository.NoteRepository;

@Service
@Transactional
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Iterable<Note> getAllNotes(){
        return noteRepository.findAll();
    }


    public void createNote(String title, String text, User author, Importance importance, Category category){
        Note note = new Note(title, text, author, importance, category);
        noteRepository.save(note);
    }

    public Iterable<Note> getNotesByAuthor(User author) {
        return noteRepository.getAllByAuthor(author);
    }
}
