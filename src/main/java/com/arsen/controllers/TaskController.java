package com.arsen.controllers;

import com.arsen.dto.TaskDto;
import com.arsen.enams.TaskStatus;
import com.arsen.enums.ResultCode;
import com.arsen.models.ResponseMessage;
import com.arsen.models.Task;
import com.arsen.models.User;
import com.arsen.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/get/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Task> getAllTask() {
        return service.getAllTask();
    }

    @PostMapping("/save")
    public ResponseMessage<TaskDto> saveTask(@RequestParam Long userId,
                                                 @RequestParam String header,
                                                 @RequestParam String description,
                                                 @RequestParam TaskStatus taskStatus) {
        try {
        return new ResponseMessage<>(
                service.newTask(userId, header, description,  taskStatus),
                ResultCode.SUCCESS,
                "Задачка успешно создана",
                ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TaskController: saveTask", e);
            return new ResponseMessage(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }



    @DeleteMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable Long id) {
        return service.deleteTaskById(id);
    }

    @PatchMapping("/update")
    public String updateTaskById(@RequestParam Long id,
                                 @RequestParam User user) {
        return service.updateTaskById(id, user);
    }

}
