package com.gabriellazar.projectmanagement.dto;

import com.gabriellazar.projectmanagement.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password","confirmPassword"})
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
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
