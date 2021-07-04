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
public class ProjectDTO {

    private Long id;
    private String projectName;
    private String projectCode;
    private UserDTO user;
    private String projectDetail;
    private Status projectStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private LocalDate endDate;
}
