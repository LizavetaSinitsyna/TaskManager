package com.epam.taskmanager.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;

@Data
public class Subtask {
    @TextIndexed
    private String name;
    private String description;
}
