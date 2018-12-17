package ru.fonzy.fnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.CategoryDto;
import ru.fonzy.fnotes.helpers.ErrorHelper;
import ru.fonzy.fnotes.service.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/edit")
    public String editCategoriesPage(@AuthenticationPrincipal User currentUser,
                                 Model model){
        model.addAttribute("categories", categoryService.getCategories(currentUser));

        return "categories/editCategories";
    }

    @PostMapping("/edit")
    public String editCategory(@AuthenticationPrincipal User currentUser,
                                 @Valid CategoryDto categoryDto,
                                 BindingResult bindingResult,
                                 Model model){

        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);
            model.addAttribute("categories", categoryService.getCategories(currentUser));

            return "/categories/editCategories";
        }

        categoryService.updateCategory(categoryDto, currentUser);

        return "redirect:/categories/edit";
    }

    @PostMapping("/add")
    public String addCategory(@AuthenticationPrincipal User currentUser,
                               @Valid CategoryDto categoryDto,
                               BindingResult bindingResult,
                               Model model){
        if (bindingResult.hasErrors()){
            ErrorHelper.addErrors(bindingResult, model);
            model.addAttribute("categories", categoryService.getCategories(currentUser));

            return "/categories/editCategories";
        }

        categoryService.createCategory(categoryDto, currentUser);

        return "redirect:/categories/edit";
    }

    @PostMapping("/delete")
    public String deleteCategory(@AuthenticationPrincipal User user,
                                 @RequestParam long id){
        categoryService.delete(id, user);

        return "redirect:/categories/edit";
    }

}
