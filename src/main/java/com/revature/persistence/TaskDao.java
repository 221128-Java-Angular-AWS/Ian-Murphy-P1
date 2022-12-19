package com.revature.persistence;

import com.revature.pojos.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TaskDao {
    private Connection connection;

    public TaskDao() {
        this.connection = ConnectionManager.getConnection();
    }

    public void create(Task task) {
        try {
            String sql = "INSERT INTO tasks (title, description, completed, user_id) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setBoolean(3, task.getCompleted());
            pstmt.setInt(4, task.getUserId());

            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Task getTaskById(Integer id) {
        Task task = new Task();

        try {
            String sql = "SELECT * FROM tasks WHERE task_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                task.setTaskId(rs.getInt("task_id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setCompleted(rs.getBoolean("completed"));
                task.setUserId(rs.getInt("user_id"));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return task;
    }

/*
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
 */
    public Set<Task> getAllTasksForUser(Integer userId) {
        Set<Task> tasks = new HashSet<>();
        try {
            String sql = "SELECT * FROM tasks WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();


            while(rs.next()) {
                tasks.add(new Task(rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getBoolean("completed"),
                        rs.getInt("user_id")));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void update(Task task) {
        try {
            String sql = "UPDATE tasks SET title = ?, description = ?, completed = ?, user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setBoolean(3, task.getCompleted());
            pstmt.setInt(4, task.getUserId());

            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM tasks WHERE task_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();


        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
