package ru.fonzy.fnotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fonzy.fnotes.service.UserService;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private UserService userService;

    private long id;

    private String username;

    @Email
    private String email;

    private String oldPassword;

    @Size(min = 5, message = "Минимальная длина пароля 5 символов")
    @Size(max = 20, message = "Максимальная длина пароля 20 символов")
    private String newPassword;

    private String newPasswordConfirm;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @AssertTrue(message = "Новые пароли не совпадают")
    private boolean isPasswordsMatch(){
        return this.newPassword.equals(this.newPasswordConfirm);
    }

    @AssertTrue(message = "Старый пароль неверный")
    private boolean isOldPasswordCorrect(){
        return userService.checkPassword(oldPassword);
    }
}
