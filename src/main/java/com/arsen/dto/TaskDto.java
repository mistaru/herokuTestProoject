package com.arsen.dto;

import com.arsen.enams.TaskStatus;
import com.arsen.models.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {
    private Long id;
    private String header;
    private String description;
    private Date deadline;
    private TaskStatus taskStatus;

    public TaskDto(String header, String description, Date deadline, TaskStatus taskStatus) {
        this.header = header;
        this.description = description;
        this.deadline = deadline;
        this.taskStatus = taskStatus;
    }
}
