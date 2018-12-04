package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fonzy.fnotes.domain.Importance;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.NoteDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.service.NoteService;

import javax.validation.Valid;

@Controller
@RequestMapping("notes")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping("/all")
    public String showNotes(@AuthenticationPrincipal User author,
                            Model model){

        model.addAttribute("notes", noteService.getNotesByAuthor(author));
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
