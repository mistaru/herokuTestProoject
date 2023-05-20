package com.arsen.models;

import com.arsen.enams.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Tasks")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence")
    @SequenceGenerator(name = "tasks_sequence", sequenceName = "tasks_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date deadline = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public Task(User owner, String header, String description, Date deadline, TaskStatus taskStatus) {
        this.owner = owner;
        this.header = header;
        this.description = description;
        this.deadline = deadline;
        this.taskStatus = taskStatus;
    }


    public Task(User user, String header, String description, TaskStatus taskStatus) {
        this.owner = user;
        this.header = header;
        this.description = description;
        this.taskStatus = taskStatus;
    }
}
