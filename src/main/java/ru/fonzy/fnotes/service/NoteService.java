package ru.fonzy.fnotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.Importance;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.NoteDto;
import ru.fonzy.fnotes.repository.NoteRepository;

@Service
@Transactional
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private CategoryService categoryService;

    public Iterable<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Iterable<Note> getNotesByAuthor(User author) {
        return noteRepository.getAllByAuthor(author);
    }

    public void createNote(NoteDto noteDto, User author) {
        Category category = categoryService.getCategoryOrCreateNew(noteDto.getCategory());
        Note note = new Note(noteDto.getTitle(), noteDto.getText(), author, Importance.valueOf(noteDto.getImportance()), category);

        noteRepository.save(note);
    }

    public void deleteNote(int id) {
        noteRepository.deleteById((long) id);
    }
}
