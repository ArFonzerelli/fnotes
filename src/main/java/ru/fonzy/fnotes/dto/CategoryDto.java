package ru.fonzy.fnotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private long id;

    @Size(min = 1, message = "Название категории не может быть пустым")
    @Size(max = 25, message = "Максимальный длина названия категории 25 символов")
    private String categoryName;

    @Size(min = 1, message = "Название категории не может быть пустым")
    @Size(max = 25, message = "Максимальный длина названия категории 25 символов")
    private String editCategoryName;
}
