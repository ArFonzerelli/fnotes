package ru.fonzy.fnotes.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.Importance;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.NoteDto;
import ru.fonzy.fnotes.repository.NoteRepository;
import ru.fonzy.fnotes.service.NoteService;

import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    private CategoryServiceImpl categoryService;

    @Autowired
    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Autowired
    public void setCategoryService(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Note getNoteById(long id) {
        return noteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Note> getNotes(User author) {
        return noteRepository.getAllByAuthor(author);
    }

    @Override
    public List<Note> getNotes(User author, Category category) {
        return noteRepository.getAllByAuthorAndCategory(author, category);
    }

    @Override
    public List<Note> findNotes(User author, String query) {
        return noteRepository.getAllByAuthorAndTitleContainingOrTextContaining(author, query, query);
    }

    @Override
    public void createNote(NoteDto noteDto, User author) {
        Category category;

        if (noteDto.getCategory() != null)
            category = categoryService.getCategoryOrCreateNew(noteDto.getCategory(), author);
        else
            category = categoryService.getCategoryOrCreateNew("Без категории", author);

        Note note = new Note(noteDto.getTitle(), noteDto.getText(), Importance.valueOf(noteDto.getImportance()), category, author);

        noteRepository.save(note);
    }


    @Override
    public void updateNote(NoteDto noteDto, User author) {
        Note note = getNoteById(noteDto.getId());

        Category category = categoryService.getCategoryOrCreateNew(noteDto.getCategory(), author);

        note.setTitle(noteDto.getTitle());
        note.setText(noteDto.getText());
        note.setImportance(Importance.valueOf(noteDto.getImportance()));
        note.setCategory(category);
        note.setAuthor(author);

        noteRepository.save(note);
    }

    public void deleteNote(long id) {
        noteRepository.deleteById(id);
    }
}
