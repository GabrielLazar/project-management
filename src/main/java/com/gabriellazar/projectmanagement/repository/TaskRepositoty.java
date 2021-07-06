package com.gabriellazar.projectmanagement.repository;

import com.gabriellazar.projectmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoty extends JpaRepository<Task,Long> {
}
