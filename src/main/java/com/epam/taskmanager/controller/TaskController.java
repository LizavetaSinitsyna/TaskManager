package com.epam.taskmanager.controller;

import com.epam.taskmanager.model.Subtask;
import com.epam.taskmanager.model.Task;
import com.epam.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Collection<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/overdue")
    public ResponseEntity<Collection<Task>> findOverdue() {
        return ResponseEntity.ok(taskService.findOverdue());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Collection<Task>> findByCategory(@PathVariable String category) {
        return ResponseEntity.ok(taskService.findByCategory(category));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<Collection<Task>> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok(taskService.findByDescription(description));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Task> create(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.create(task));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> update(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(task));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/subtasks")
    public ResponseEntity<Task> createSubtask(@PathVariable String id, @RequestBody Subtask subTask) {
        return ResponseEntity.ok(taskService.createSubtask(id, subTask));
    }

    @PutMapping("/{id}/subtasks")
    public ResponseEntity<Task> updateSubtasks(@PathVariable String id,
                                               @RequestBody Collection<Subtask> subTasks) {
        return ResponseEntity.ok(taskService.updateSubtasks(id, subTasks));
    }

    @DeleteMapping("/{id}/subtasks")
    public ResponseEntity<Void> deleteSubtasks(@PathVariable String id) {
        taskService.deleteSubtasks(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subtasks/name/{name}")
    public ResponseEntity<Collection<Task>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(taskService.findBySubtaskName(name));
    }
}

