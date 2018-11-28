package ru.fonzy.fnotes.controller;

import com.google.common.base.Strings;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.Importance;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.NoteDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.repository.CategoryRepository;
import ru.fonzy.fnotes.service.CategoryService;
import ru.fonzy.fnotes.service.NoteService;

import javax.validation.Valid;
import java.util.Map;

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
