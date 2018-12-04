package ru.fonzy.fnotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.fonzy.fnotes.domain.Role;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class UserDto {

    private long id;

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

    private boolean enabled;

    private Set<Role> roles;

    public UserDto(long id, String username, boolean enabled, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.enabled = enabled;
        this.roles = roles;
    }

    @AssertTrue(message = "Пароли не совпадают")
    private boolean isPasswordsMatch(){
        return this.password.equals(this.confirmPassword);
    }


}
