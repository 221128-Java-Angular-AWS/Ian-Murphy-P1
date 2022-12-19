package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.persistence.TaskDao;
import com.revature.pojos.Task;
import com.revature.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaskServlet extends HttpServlet {
    TaskService service;
    ObjectMapper mapper;


    @Override
    public void init() throws ServletException {
        this.service = new TaskService(new TaskDao());
        this.mapper = new ObjectMapper();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("task_id"));
        Task task = service.getTask(id);
        String json = mapper.writeValueAsString(task);

        resp.getWriter().println(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task = mapper.readValue(req.getReader(), Task.class);
        service.createNewTask(task);

        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task = mapper.readValue(req.getReader(), Task.class);
        service.updateTask(task);

        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("task_id"));
        service.deleteTask(id);

        resp.setStatus(200);
    }


}
