package com.epam.taskmanager.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Collection;

@Data
@Document("tasks")
public class Task {
    @Id
    private String id;
    private LocalDate creationDate;
    private LocalDate deadline;
    private String name;
    @TextIndexed
    private String description;
    private Collection<Subtask> subtasks;
    @Indexed
    private String category;

}
