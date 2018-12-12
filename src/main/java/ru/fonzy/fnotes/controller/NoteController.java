package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.Importance;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.NoteDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.service.CategoryService;
import ru.fonzy.fnotes.service.NoteService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("notes")
public class NoteController {

    private NoteService noteService;

    private CategoryService categoryService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public String showNotes(@AuthenticationPrincipal User author,
                            Model model){
        List<Note> notes = noteService.getNotes(author);
        List<Category> categories = categoryService.getCategories(author);

        if (notes.size() == 0)
            model.addAttribute("no_notes", "У вас пока нет ни одной заметки");
        else
            model.addAttribute("notes", notes);

        model.addAttribute("categories", categories);

        model.addAttribute("importances", Importance.values());

        return "notes/notesPage";
    }

    @GetMapping("/category/{id}")
    public String showNotesWithCategory(@AuthenticationPrincipal User author,
                                        @PathVariable long id,
                                        Model model){

        Category category = categoryService.getCategory(id, author);

        List<Category> categories = categoryService.getCategories(author);

        List<Note> notes = noteService.getNotes(author, category);

        if (notes.size() == 0)
            model.addAttribute("no_notes", "Не найдено заметок в данной категории");
        else
            model.addAttribute("notes", notes);

        model.addAttribute("categories", categories);

        model.addAttribute("importances", Importance.values());

        return "notes/notesPage";

    }

    @GetMapping("/search")
    public String search(@AuthenticationPrincipal User author,
                         @RequestParam String query,
                         Model model){

        List<Note> notes = noteService.findNotes(author, query);
        List<Category> categories = categoryService.getCategories(author);

        if (notes.size() == 0)
            model.addAttribute("no_notes", "К сожалению, ничего не найдено");
        else
            model.addAttribute("notes", notes);

        model.addAttribute("categories", categories);

        model.addAttribute("importances", Importance.values());

        return "notes/notesPage";
    }

    @GetMapping("/new")
    public String newNote(Model model){
        model.addAttribute("importances", Importance.values());

        return "notes/newNote";
    }

    @PostMapping("/create")
    public String createNote(@AuthenticationPrincipal User author,
                             @Valid NoteDto noteDto,
                             BindingResult bindingResult,
                             Model model){

        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);

            model.addAttribute("importances", Importance.values());
            return "/notes/newNote";
        }

        noteService.createNote(noteDto, author);

        return "redirect:/notes/all";

    }

    @GetMapping("/edit")
    public String editNote(@AuthenticationPrincipal User user,
                           @RequestParam long id,
                           Model model){
        Note note = noteService.getNoteById(id);

        if (note == null)
            return "redirect:/notes/all";

        if(!note.getAuthor().getId().equals(user.getId()))
            return "redirect:/notes/all";

        NoteDto noteDto = new NoteDto(note.getId(), note.getTitle(), note.getText(), note.getCategory().toString(), note.getImportance().toString());

        model.addAttribute("note", noteDto);
        model.addAttribute("importances", Importance.values());

        return "notes/editNote";
    }

    @PostMapping("/update")
    public String updateNote(@AuthenticationPrincipal User author,
                             @Valid NoteDto noteDto,
                             BindingResult bindingResult,
                             Model model){

        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);
            model.addAttribute("note", noteDto);
            model.addAttribute("importances", Importance.values());

            return "/notes/editNote";
        }

        noteService.updateNote(noteDto, author);

        return "redirect:/notes/all";
    }

    @PostMapping("/delete")
    public String deleteNote(@AuthenticationPrincipal User user,
                             @RequestParam long id){

        noteService.deleteNote(id);

        return "redirect:/notes/all";
    }

}
