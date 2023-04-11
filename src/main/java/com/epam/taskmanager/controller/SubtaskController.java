package com.epam.taskmanager.controller;

import com.epam.taskmanager.model.Subtask;
import com.epam.taskmanager.service.SubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/subtasks")
public class SubtaskController {
    private final SubtaskService subtaskService;

    @Autowired
    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Collection<Subtask>> findByCategory(@PathVariable String category) {
        return ResponseEntity.ok(subtaskService.findByCategory(category));
    }

}
