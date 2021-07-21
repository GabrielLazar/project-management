package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.dto.TaskDTO;
import com.gabriellazar.projectmanagement.mapper.MapperUtil;
import com.gabriellazar.projectmanagement.repository.TaskRepository;
import com.gabriellazar.projectmanagement.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private MapperUtil mapperUtil;

    public TaskServiceImpl(TaskRepository taskRepository, MapperUtil mapperUtil) {
        this.taskRepository = taskRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public Page<TaskDTO> findAllPageableTask(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<TaskDTO> taskDTOPage = null;
        try{
            taskDTOPage = taskRepository.findAllByOrderByLastUpdateDateTimeDesc(pageable)
                    .map(t -> mapperUtil.convertToDTO(t,new TaskDTO()));
        } catch (Exception e){
            log.error("Exception in getting all pageable Tasks :: {}", e);
        }
        return taskDTOPage;
    }
}
