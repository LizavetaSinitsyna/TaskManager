package com.epam.taskmanager.service.impl;

import com.epam.taskmanager.model.Subtask;
import com.epam.taskmanager.model.Task;
import com.epam.taskmanager.repository.TaskDAL;
import com.epam.taskmanager.repository.TaskRepository;
import com.epam.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskDAL taskDAL;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskDAL taskDAL) {
        this.taskRepository = taskRepository;
        this.taskDAL = taskDAL;
    }

    @Override
    public Collection<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Collection<Task> findOverdue() {
        return taskDAL.findOverdue(LocalDate.now());
    }

    @Override
    public Collection<Task> findByCategory(String category) {
        if (category == null) {
            return Collections.emptyList();
        }
        return taskRepository.findByCategory(category);
    }

    @Override
    public Collection<Task> findByDescription(String description) {
        if (description == null) {
            return Collections.emptyList();
        }
        return taskDAL.findByDescription(description);
    }

    @Override
    public Task create(Task task) {
        validateTask(task);
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        validateTask(task);
        getTaskIfExistsOrElseThrowException(task.getId());
        return taskRepository.save(task);
    }

    @Override
    public void delete(String id) {
        getTaskIfExistsOrElseThrowException(id);
        taskRepository.deleteById(id);
    }

    @Override
    public Task createSubtask(String id, Subtask subTask) {
        Task task = getTaskIfExistsOrElseThrowException(id);
        if (subTask != null) {
            task.getSubtasks().add(subTask);
        }
        return taskRepository.save(task);
    }

    @Override
    public Task updateSubtasks(String id, Collection<Subtask> subTasks) {
        Task task = getTaskIfExistsOrElseThrowException(id);
        task.setSubtasks(subTasks);
        return taskRepository.save(task);
    }

    @Override
    public void deleteSubtasks(String id) {
        Task task = getTaskIfExistsOrElseThrowException(id);
        task.setSubtasks(null);
        taskRepository.save(task);
    }

    @Override
    public Collection<Task> findBySubtaskName(String name) {
        if (name == null) {
            return Collections.emptyList();
        }
        return taskDAL.findSubtasksByName(name);
    }

    private void validateTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task can not be null!");
        }
        if (task.getCreationDate().isAfter(task.getDeadline())) {
            throw new IllegalArgumentException("Task's creation date can't be after deadline!");
        }
    }

    private Task getTaskIfExistsOrElseThrowException(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Task id can not be null!");
        }
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            throw new IllegalArgumentException(String.format("Task with id [%s] does not exist!", id));
        }
        return task.get();
    }
}

