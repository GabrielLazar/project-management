package com.gabriellazar.projectmanagement.services.impl;

import com.gabriellazar.projectmanagement.dto.TaskDTO;
import com.gabriellazar.projectmanagement.entity.Task;
import com.gabriellazar.projectmanagement.enums.Status;
import com.gabriellazar.projectmanagement.mapper.MapperUtil;
import com.gabriellazar.projectmanagement.repository.TaskRepository;
import com.gabriellazar.projectmanagement.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
        try {
            taskDTOPage = taskRepository.findAllByOrderByLastUpdateDateTimeDesc(pageable)
                    .map(t -> mapperUtil.convertToDTO(t, new TaskDTO()));
        } catch (Exception e) {
            log.error("Exception in getting all pageable Tasks :: {}", e);
        }
        return taskDTOPage;
    }

    @Override
    public void saveTask(TaskDTO taskDTO) {
        log.info("Starting to save task :: {}", taskDTO);
        try {
            taskDTO.setTaskStatus(Status.OPEN);
            taskDTO.setAssignedDate(LocalDate.now());
            taskDTO.setTaskCode(taskDTO.getProject().getProjectCode() + "-" + getNextTaskCodeNumber());
            taskRepository.saveAndFlush(mapperUtil.convertToEntity(taskDTO, new Task()));
        } catch (Exception e) {
            log.error("Exception in saving task :: {}, {}", taskDTO, e);
        }
        log.info("{} task was saved", taskDTO.getTaskSubject());

    }

    @Override
    public TaskDTO findTaskById(Long id) {
        TaskDTO taskDTO = null;
        try{
            taskDTO = taskRepository.findById(id).map(t -> mapperUtil.convertToDTO(t,new TaskDTO())).get();
        } catch (Exception e){
            log.error("Exception in finding task by id {} :: {}", id, e);
        }
        return taskDTO;
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        try {
            log.info("Updating task with id {} :: {}", id, taskDTO);
            Task existingTask = taskRepository.findById(id).get();
            Task currentTask = mapperUtil.convertToEntity(taskDTO, new Task());
            currentTask.setId(id);
            currentTask.setInsertDateTime(existingTask.getInsertDateTime());
            currentTask.setInsertUserId(existingTask.getInsertUserId());
            currentTask.setAssignedDate(existingTask.getAssignedDate());
            currentTask.setTaskCode(existingTask.getTaskCode());
            taskRepository.save(currentTask);
            log.info("Successfully updated task with id {} :: {}", id, currentTask);
        } catch (Exception e) {
            log.error("Exception in updating task {} :: {}", id, taskDTO);
            log.error("Exception was ::{}", e);
        }
        return findTaskById(id);
    }

    @Override
    public void deleteProject(Long id) {
        try{
            taskRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Exception in deleting the task by id {} :: {} ", id, e);
        }
        log.info("Successfully deleted task with id :: {}", id);
    }

    private Long getNextTaskCodeNumber() {
        Long maxId = taskRepository.findMaxTaskId().orElse(0L);
        return ++maxId;
    }

}
