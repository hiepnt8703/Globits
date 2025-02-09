package com.example.rest_api.service;

import com.example.rest_api.dto.TaskDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(TaskDTO taskDTO);
    Page<TaskDTO> pagingStaffSalaryItemValue(int pageNo , int pageSize , String keyword);
    TaskDTO updateTask(long id , TaskDTO taskDTO);

    TaskDTO getTaskById(Long id);
    List<TaskDTO> getAll();

    void deleteTask(long id);
}
