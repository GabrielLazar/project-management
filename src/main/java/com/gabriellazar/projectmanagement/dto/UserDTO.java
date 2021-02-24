package com.gabriellazar.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private boolean enabled;
    private String phone;

}
