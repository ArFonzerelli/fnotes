package ru.fonzy.fnotes.controller;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.service.NoteService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/notes")
    public String showNotes(Model model){
        model.addAttribute("notes", noteService.getAllNotes());

        return "notes";
    }


    @PostMapping("/notes")
    public String addNote(
            @AuthenticationPrincipal User author,
            @RequestParam String title,
            @RequestParam String text){

        if (Strings.isNullOrEmpty(title))
            title = "Пустрой заголовок";

        if (Strings.isNullOrEmpty(text))
            text = "Пустой текст";

        noteService.createNote(title, text, author);

        return "redirect:notes";
    }

}
