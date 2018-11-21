package ru.fonzy.fnotes.controller;

import com.google.common.base.Strings;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fonzy.fnotes.domain.Category;
import ru.fonzy.fnotes.domain.Importance;
import ru.fonzy.fnotes.domain.Note;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.repository.CategoryRepository;
import ru.fonzy.fnotes.service.CategoryService;
import ru.fonzy.fnotes.service.NoteService;
import java.util.Map;

@Controller
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/notes")
    public String showNotes(@AuthenticationPrincipal User author,
                            Model model){

        model.addAttribute("notes", noteService.getNotesByAuthor(author));
        model.addAttribute("importances", Importance.values());

        return "notes/notes";
    }


    @PostMapping("/notes")
    public String addNote(
            @AuthenticationPrincipal User author,
            @RequestParam Map<String, String> form){

        String title = form.get("title");
        String text = form.get("text");
        String categoryStr = form.get("category");

        if (Strings.isNullOrEmpty(title))
            title = "Пустой заголовок";

        if (Strings.isNullOrEmpty(text))
            text = "Пустой текст";

        if (Strings.isNullOrEmpty(categoryStr))
            categoryStr = "Обычные заметки";

        Category category = categoryService.getCategoryOrCreateNew(categoryStr);

        Importance importance = Importance.valueOf(form.get("importance"));

        noteService.createNote(title, text, author, importance, category);

        return "redirect:notes";
    }

}
