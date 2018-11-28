package ru.fonzy.fnotes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, message = "Минимальная длина пароля 3 символа")
    @Size(max = 15, message = "Максимальная длина пароля 15 символов")
    private String username;

    @NotNull
    @Size(min = 5, message = "Минимальная длина пароля 5 символов")
    @Size(max = 20, message = "Максимальная длина пароля 20 символов")
    private String password;

    private String confirmPassword;

    @Email(message = "Некорректный почтовый адрес")
    private String email;

    @AssertTrue(message = "Пароли не совпадают")
    private boolean isPasswordsMatch(){
        return this.password.equals(this.confirmPassword);
    }


}
