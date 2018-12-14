package ru.fonzy.fnotes.helpers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ErrorHelper {

    public static void addErrors(BindingResult bindingResult, Model model){
        List<ObjectError> errors = bindingResult.getAllErrors();

        for (ObjectError error : errors){
            String field = ((FieldError) error).getField();
            model.addAttribute(field.concat("_failed"), error.getDefaultMessage());
            System.out.println(field.concat("_failed " + error.getDefaultMessage()));
        }
    }
}
