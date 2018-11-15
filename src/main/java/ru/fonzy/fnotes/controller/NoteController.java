package ru.fonzy.fnotes.controller;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.service.NoteService;

@Controller
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/notes")
    public String showNotes(@AuthenticationPrincipal User author,
                            Model model){

        model.addAttribute("notes", noteService.getNotesByAuthor(author));

        return "notes";
    }


    @PostMapping("/notes")
    public String addNote(
            @AuthenticationPrincipal User author,
            @RequestParam String title,
            @RequestParam String text){

        if (Strings.isNullOrEmpty(title))
            title = "Пустой заголовок";

        if (Strings.isNullOrEmpty(text))
            text = "Пустой текст";

        noteService.createNote(title, text, author);

        return "redirect:notes";
    }

}
