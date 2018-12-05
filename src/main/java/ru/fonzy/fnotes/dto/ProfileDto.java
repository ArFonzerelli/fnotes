package ru.fonzy.fnotes.dto;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    private long id;

    private String username;

    @Email
    private String email;

    private String oldPassword;

    @Size(min = 5, message = "Минимальная длина пароля 5 символов")
    @Size(max = 20, message = "Максимальная длина пароля 20 символов")
    private String password;

    private String confirmPassword;

    public ProfileDto(long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    @AssertTrue(message = "Пароли не совпадают")
    private boolean isPasswordsMatch(){
        if (Strings.isNullOrEmpty(this.password))
            return true;

        return this.password.equals(this.confirmPassword);
    }

}
