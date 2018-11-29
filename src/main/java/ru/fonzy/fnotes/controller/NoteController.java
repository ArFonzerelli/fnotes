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
import ru.fonzy.fnotes.service.CategoryService;
import ru.fonzy.fnotes.service.NoteService;

import javax.validation.Valid;

@Controller
@RequestMapping("notes")
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String showNotes(@AuthenticationPrincipal User author,
                            Model model){

        model.addAttribute("notes", noteService.getNotesByAuthor(author));
        model.addAttribute("importances", Importance.values());

        return "notes/notes_page";
    }

    @GetMapping("/addNote")
    public String editNotePage(Model model){
        model.addAttribute("importances", Importance.values());
        return "notes/editNote";
    }

    @PostMapping("/deleteNote")
    public String deleteNote(@RequestParam int id){
        noteService.deleteNote(id);

        return "redirect:/notes";
    }

    @PostMapping("/submNote")
    public String submitNote(@AuthenticationPrincipal User author,
                             @Valid NoteDto noteDto,
                             BindingResult bindingResult,
                             Model model){

        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);

            return "/notes/editNote";
        }

        noteService.createNote(noteDto, author);

        return "redirect:/notes";

    }



}
