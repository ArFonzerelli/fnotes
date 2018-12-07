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
//todo Попробовать сделать с наследованием
public class ProfileDto {

    private long id;

    private String username;

    @Email
    private String email;

}
