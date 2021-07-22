package com.gabriellazar.projectmanagement.repository;

import com.gabriellazar.projectmanagement.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Page<Task> findAllByOrderByLastUpdateDateTimeDesc(Pageable pageable);

    @Query(value = "SELECT MAX(id) FROM tasks",nativeQuery = true)
    Optional<Long> findMaxTaskId();
}
