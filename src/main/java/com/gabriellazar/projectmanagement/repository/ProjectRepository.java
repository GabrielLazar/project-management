package com.gabriellazar.projectmanagement.repository;

import com.gabriellazar.projectmanagement.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Page<Project> findAllByOrderByLastUpdateDateTimeDesc(Pageable pageable);
    Optional<Project> findByProjectCodeIgnoreCase(String userName);
}
