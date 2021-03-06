package com.gabriellazar.projectmanagement.entity;

import com.gabriellazar.projectmanagement.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private LocalDate birthDate;
    private Integer phoneNumber;
    private String password;
    private boolean enabled;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
