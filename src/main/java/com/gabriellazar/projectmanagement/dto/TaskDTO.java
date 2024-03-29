package com.gabriellazar.projectmanagement.dto;

import com.gabriellazar.projectmanagement.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskDTO {

    private Long id;
    private String taskSubject;
    private String taskDetail;
    private Status taskStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate assignedDate;
    private String taskCode;
    private ProjectDTO project;
    private UserDTO assignedUser;
}
