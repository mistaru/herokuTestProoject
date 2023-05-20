package com.arsen.services;

import com.arsen.dto.TaskDto;
import com.arsen.enams.TaskStatus;
import com.arsen.models.Task;
import com.arsen.models.User;
import com.arsen.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Transactional
    public TaskDto newTask(Long userId, String header, String description, TaskStatus taskStatus) throws Exception {

         var user = userService.getById(userId).orElseThrow(() -> new Exception ("Не найден автор с id " +  userId));

        Task task = new Task(user, header, description, taskStatus);
        taskRepository.save(task);

        userService.updateUserById(user.getId(), "Create Task with header " + header );

        return new TaskDto(
                task.getId(),
                task.getHeader(),
                task.getDescription(),
                task.getDeadline(),
                task.getTaskStatus()
        );
    }

    public Long newTaskPro(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public String deleteTaskById(Long id) {
        taskRepository.deleteById(id);
        return "Task id: " + id + " is deleted!";
    }

    public String updateTaskById(Long id, User user) {
        Task task = taskRepository.findById(id).orElse(null);
        if(task == null) return null;
        task.setOwner(user);
        return "Task id: " + id + " new username" + user.getName();
    }

}
