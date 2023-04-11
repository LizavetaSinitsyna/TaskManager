package com.epam.taskmanager.service;

import com.epam.taskmanager.model.Subtask;
import com.epam.taskmanager.model.Task;

import java.util.Collection;


public interface TaskService {
    Collection<Task> findAll();

    Collection<Task> findOverdue();

    Collection<Task> findByCategory(String category);

    Collection<Task> findByDescription(String description);

    Task create(Task task);

    Task update(Task task);

    void delete(String id);

    Task createSubtask(String id, Subtask subTask);

    Task updateSubtasks(String id, Collection<Subtask> subTasks);

    void deleteSubtasks(String id);

    Collection<Task> findBySubtaskName(String name);
}
