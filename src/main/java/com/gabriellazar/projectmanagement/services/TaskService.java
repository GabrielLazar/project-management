package com.gabriellazar.projectmanagement.services;

import com.gabriellazar.projectmanagement.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface TaskService {

    Page<TaskDTO> findAllPageableTask(int pageNo, int pageSize);
    void saveTask(TaskDTO taskDTO);
    TaskDTO findTaskById(Long id);
    TaskDTO updateTask(Long id, TaskDTO taskDTO);

}
