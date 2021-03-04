package com.gabriellazar.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private String confirmPassword;
    private LocalDate birthDate;
    private boolean enabled;
    private String phone;

}
