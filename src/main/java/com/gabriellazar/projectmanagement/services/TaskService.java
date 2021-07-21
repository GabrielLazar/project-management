package com.gabriellazar.projectmanagement.services;

import com.gabriellazar.projectmanagement.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    Page<TaskDTO> findAllPageableTask(int pageNo, int pageSize);
}
