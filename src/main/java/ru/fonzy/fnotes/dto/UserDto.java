package ru.fonzy.fnotes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, max = 15, message = "Логин должен быть между 3 и 15 символами")
    private String username;

    @NotNull
    @Size(min = 5, max = 20, message = "Длина пароля должна быть между 5 и 20 символами")
    private String password;

    private String confirmPassword;

    @Email(message = "Некорректный почтовый адрес")
    private String email;

    @AssertTrue(message = "Пароли не совпадают")
    private boolean isPasswordsMatch(){
        return this.password.equals(this.confirmPassword);
    }


}
