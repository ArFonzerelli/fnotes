package ru.fonzy.fnotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.repository.NoteRepository;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Iterable<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public void addNote(Note note){
        noteRepository.save(note);
    }
}
