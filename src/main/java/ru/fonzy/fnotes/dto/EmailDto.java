package ru.fonzy.fnotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    @Email(message = "Некорректный почтовый адрес")
    private String email;

}
