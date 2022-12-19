package com.revature.service;

import com.revature.persistence.TaskDao;
import com.revature.pojos.Task;

import java.util.Set;

public class TaskService {
    private TaskDao dao;


    public TaskService(TaskDao dao) {
        this.dao = dao;
    }

    public void createNewTask(Task task) {
        dao.create(task);
    }

    public Task getTask(Integer TaskId) {
        return dao.getTaskById(TaskId);
    }

    public Task getTask(Task task) {
        return dao.getTaskById(task.getTaskId());
    }

    public void updateTask(Task task) {
        dao.update(task);
    }

    public void deleteTask(Integer TaskId) {
        dao.delete(TaskId);
    }

    public void deleteTask(Task task) {
        dao.delete(task.getTaskId());
    }

    public Set<Task> getAllTasksForUser(Integer userId) {
        return dao.getAllTasksForUser(userId);
    }

    public Set<Task> getAllTasksForUser(Task task) {
        return dao.getAllTasksForUser(task.getUserId());
    }
}
