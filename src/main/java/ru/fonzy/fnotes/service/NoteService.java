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
        Note note = new Note(noteDto.getTitle(), noteDto.getText(), Importance.valueOf(noteDto.getImportance()), category, author);

        noteRepository.save(note);
    }

    public void deleteNote(long id) {
        noteRepository.deleteById(id);
    }

    public Note getNoteById(long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public void updateNote(NoteDto noteDto, User author) {
        Category category = categoryService.getCategoryOrCreateNew(noteDto.getCategory());

        Note note = noteRepository.findById(noteDto.getId()).orElse(null);

        note.setTitle(noteDto.getTitle());
        note.setText(noteDto.getText());
        note.setImportance(Importance.valueOf(noteDto.getImportance()));
        note.setCategory(category);
        note.setAuthor(author);

        noteRepository.save(note);
    }
}
