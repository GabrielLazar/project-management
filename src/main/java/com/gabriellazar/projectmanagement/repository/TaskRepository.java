package com.gabriellazar.projectmanagement.repository;

import com.gabriellazar.projectmanagement.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Page<Task> findAllByOrderByLastUpdateDateTimeDesc(Pageable pageable);
}
