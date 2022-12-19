package com.revature.service;

import com.revature.persistence.TicketDao;
import com.revature.pojos.Ticket;

import java.util.Set;

public class TicketService {
    private TicketDao dao;


    public TicketService(TicketDao dao) {
        this.dao = dao;
    }

    public void createNewTicket(Ticket ticket) {
        dao.create(ticket);
    }

    public Ticket getTicket(Integer TicketId) {
        return dao.getTicketById(TicketId);
    }

    public Ticket getTicket(Ticket ticket) {
        return dao.getTicketById(ticket.getTicketId());
    }

    public void updateTicket(Ticket ticket) {
        dao.update(ticket);
    }

    public void deleteTicket(Integer TicketId) {
        dao.delete(TicketId);
    }

    public void deleteTicket(Ticket ticket) {
        dao.delete(ticket.getTicketId());
    }

    public Set<Ticket> getAllTicketsForUser(Integer userId) {
        return dao.getAllTicketsForUser(userId);
    }

    public Set<Ticket> getAllTicketsForUser(Ticket ticket) {
        return dao.getAllTicketsForUser(ticket.getUserId());
    }
}
