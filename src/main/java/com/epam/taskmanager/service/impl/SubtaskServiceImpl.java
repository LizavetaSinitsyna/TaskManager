package com.epam.taskmanager.service.impl;

import com.epam.taskmanager.model.Subtask;
import com.epam.taskmanager.repository.TaskDAL;
import com.epam.taskmanager.service.SubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class SubtaskServiceImpl implements SubtaskService {
    private final TaskDAL taskDAL;

    @Autowired
    public SubtaskServiceImpl(TaskDAL taskDAL) {
        this.taskDAL = taskDAL;
    }

    @Override
    public Collection<Subtask> findByCategory(String category) {
        if (category == null) {
            return Collections.emptyList();
        }
        return taskDAL.findSubtasksByCategory(category);
    }
}
