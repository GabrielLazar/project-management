package com.gabriellazar.projectmanagement.entity;

import com.gabriellazar.projectmanagement.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "projects")
public class Project  extends BaseEntity{

    private String projectName;
    private String projectCode;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status projectStatus;
    private String projectDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
