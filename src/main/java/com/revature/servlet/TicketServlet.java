package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.RoleIncorrectException;
import com.revature.persistence.TicketDao;
import com.revature.pojos.Ticket;
import com.revature.pojos.User;
import com.revature.service.TicketService;
import com.revature.util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class TicketServlet extends HttpServlet {
    TicketService service;
    ObjectMapper mapper;


    @Override
    public void init() throws ServletException {
        this.service = new TicketService(new TicketDao());
        this.mapper = new ObjectMapper();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("ticket_id"));
        Ticket ticket = service.getTicket(id);
        String json = mapper.writeValueAsString(ticket);

        resp.getWriter().println(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CookieUtil.verifyUserType(req, User.EMPLOYEE);

            int userId = CookieUtil.getUserIdCookie(req);

            Ticket ticket = mapper.readValue(req.getReader(), Ticket.class);
            ticket.setUserId(userId);
            ticket.setStatus(Ticket.PENDING);

            service.createNewTicket(ticket);

            resp.setStatus(201);
        } catch (RoleIncorrectException e) {
            resp.getWriter().print(e.getMessage());
            resp.setStatus(401);
        } catch (SQLException e) {
            resp.getWriter().print("Invalid ticket");
            resp.setStatus(400);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ticket ticket = mapper.readValue(req.getReader(), Ticket.class);
        service.updateTicket(ticket);

        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("ticket_id"));
        service.deleteTicket(id);

        resp.setStatus(200);
    }


}
