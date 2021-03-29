package com.gabriellazar.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "projects")
public class Project  extends BaseEntity{

    private String projectName;
    private String projectCode;

//    @Temporal(value = TemporalType.DATE)
//    private LocalDate localDate;
}
