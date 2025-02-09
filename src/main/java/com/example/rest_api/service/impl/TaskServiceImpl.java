package com.example.rest_api.service.impl;

import com.example.rest_api.domain.Person;
import com.example.rest_api.domain.Project;
import com.example.rest_api.domain.Task;
import com.example.rest_api.dto.TaskDTO;
import com.example.rest_api.repository.PersonRepository;
import com.example.rest_api.repository.ProjectRepository;
import com.example.rest_api.repository.TaskRepository;
import com.example.rest_api.service.TaskService;
import com.example.rest_api.util.TaskPriority;
import com.example.rest_api.util.TaskStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PersonRepository personRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task();
        Project project = projectRepository.findById(taskDTO.getProject().getId()).orElseThrow(() -> new RuntimeException("Project not found"));
        Person person = personRepository.findById(taskDTO.getPerson().getId()).orElseThrow(() -> new RuntimeException("Person not found"));
        task.setProject(project);
        task.setPerson(person);
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(TaskPriority.valueOf(taskDTO.getPriority().name()));
        task.setStatus(TaskStatus.valueOf(taskDTO.getStatus().name()));
        task.setStartTime(taskDTO.getStartTime());
        task.setEndTime(taskDTO.getEndTime());

        this.taskRepository.save(task);
        return new TaskDTO(task);
    }

    @Override
    public Page<TaskDTO> pagingStaffSalaryItemValue(int pageNo, int pageSize, String keyword) {

        if (pageNo > 0) {
            pageNo--;
        } else {
            pageNo = 0;
        }

        String whereClause = " where (1=1) ";

        String sqlCount = "select count( entity.id) from Task as entity ";
        String sql = "select new com.example.rest_api.dto.TaskDTO(entity) from Task as entity ";

        if (keyword != null && StringUtils.hasText(keyword)) {
            whereClause += " AND ( entity.name = :text ) ";
        }

        sql += whereClause;
        sqlCount += whereClause;

        Query query = entityManager.createQuery(sql, TaskDTO.class);
        Query qCount = entityManager.createQuery(sqlCount);

        if (keyword != null && StringUtils.hasText(keyword)) {
            query.setParameter("text", "%" + keyword + "%");
            qCount.setParameter("text", "%" + keyword + "%");
        }
        List<TaskDTO> entities = new ArrayList<>();

        long count = (long) qCount.getSingleResult();
        Page<TaskDTO> result;
        int startPosition = pageNo * pageSize;
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        entities = query.getResultList();
        result = new PageImpl<>(entities, pageable, count);
        return result;
    }

    @Override
    public TaskDTO updateTask(long id, TaskDTO taskDTO) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()){
            Task task = taskOptional.get();
            task.setName(taskDTO.getName());
            task.setDescription(taskDTO.getDescription());
            task.setStartTime(taskDTO.getStartTime());
            task.setEndTime(taskDTO.getEndTime());
            task.setPriority(taskDTO.getPriority());
            task.setStatus(taskDTO.getStatus());

            Optional<Project> projectOptional = projectRepository.findById(taskDTO.getProject().getId());
            if (projectOptional.isPresent()){
                task.setProject(projectOptional.get());
            } else {
                throw new RuntimeException("Project with ID " + taskDTO.getProject().getId() + " not found");
            }

            Optional<Person> personOptional = personRepository.findById(taskDTO.getPerson().getId());
            if (personOptional.isPresent()){
                task.setPerson(personOptional.get());
            }else {
                throw new RuntimeException("Person with ID " + taskDTO.getPerson().getId() + " not found");
            }
            this.taskRepository.save(task);
            return new TaskDTO(task);
        }else {
            throw new RuntimeException("Task with ID " + taskDTO.getId() + " not found");
        }
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()){
           return new TaskDTO(taskOptional.get());
        }
        else {
            throw new RuntimeException("Task with ID: " + id + " not found");
        }
    }

    @Override
    public List<TaskDTO> getAll() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> taskDTOS = new ArrayList<>();
        for (Task task : tasks){
            TaskDTO taskDTO = new TaskDTO(task);
            taskDTOS.add(taskDTO);
        }
        return taskDTOS;
    }

    @Override
    public void deleteTask(long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()){
            taskRepository.delete(taskOptional.get());
        }else {
            throw new RuntimeException("Task with ID: " + id + " not found");
        }
    }


}
