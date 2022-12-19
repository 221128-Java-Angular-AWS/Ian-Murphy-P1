package com.revature.persistence;

import com.revature.pojos.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TicketDao {
    private Connection connection;

    public TicketDao() {
        this.connection = ConnectionManager.getConnection();
    }

    //Completion Checklist: Can submit new reimbursement tickets
    public void create(Ticket ticket) {
        try {
            String sql = "INSERT INTO tickets (type, description, status, user_id, ticket_id, amount) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, ticket.getType());
            pstmt.setString(2, ticket.getDescription());
            pstmt.setString(3, ticket.getStatus());
            pstmt.setInt(4, ticket.getUserId());
            pstmt.setInt(5, ticket.getTicketId());
            pstmt.setInt(6, ticket.getAmount());

            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Ticket getTicketById(Integer id) {
        Ticket ticket = new Ticket();

        try {
            String sql = "SELECT * FROM tickets WHERE ticket_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setType(rs.getString("type"));
                ticket.setDescription(rs.getString("description"));
                ticket.setStatus(rs.getString("status"));
                ticket.setUserId(rs.getInt("user_id"));
                ticket.setAmount(rs.getInt("amount"));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return ticket;
    }

    //Completion Checklist #8: Employees can see a list of their previous submissions:
    public Set<Ticket> getAllTicketsForUser(Integer userId) {
        Set<Ticket> tickets = new HashSet<>();
        try {
            String sql = "SELECT * FROM tickets WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();


            while(rs.next()) {
                tickets.add(new Ticket(rs.getInt("user_id"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("user_id"),
                        rs.getInt("amount")));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public void update(Ticket ticket) {
        try {
            String sql = "UPDATE tickets SET type = ?, description = ?, status = ?, user_id = ?, ticket_id =?, amount = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, ticket.getType());
            pstmt.setString(2, ticket.getDescription());
            pstmt.setString(3, ticket.getStatus());
            pstmt.setInt(4, ticket.getUserId());
            pstmt.setInt(5, ticket.getTicketId());
            pstmt.setInt(6, ticket.getAmount());

            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM tickets WHERE ticket_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();


        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}

// TODO:

// Method using enum to set ticket status

// Method for managers to approve/deny tickets

// Method for managers to view all pending tickets

// Update method for employees to view past tickets using cookies to get id