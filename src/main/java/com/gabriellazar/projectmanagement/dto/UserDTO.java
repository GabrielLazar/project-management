package com.gabriellazar.projectmanagement.dto;

import com.gabriellazar.projectmanagement.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank

    private String firstName;
    @NotBlank
    @Size
    private String lastName;
    @NotBlank
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private boolean enabled;
    private Gender gender;
    private RoleDTO role;

}
