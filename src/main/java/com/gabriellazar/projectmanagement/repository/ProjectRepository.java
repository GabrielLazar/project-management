package com.gabriellazar.projectmanagement.repository;

import com.gabriellazar.projectmanagement.entity.Project;
import com.gabriellazar.projectmanagement.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProjectRepository extends JpaRepository<Project,Long> {

    Page<Project> findAllByOrderByLastUpdateDateTimeDesc(Pageable pageable);
    Optional<Project> findByProjectCodeIgnoreCase(String userName);
    List<Project> findAllByProjectStatusNotIn(List<Status> statuses);
}
