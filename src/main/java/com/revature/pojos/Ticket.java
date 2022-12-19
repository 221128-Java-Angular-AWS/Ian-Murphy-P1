package com.revature.pojos;

import java.util.Objects;

public class Ticket {

    public static final String PENDING = "PENDING";
    public static final String APPROVED = "APPROVED";
    public static final String DENIED = "DENIED";
    private Integer ticketId;
    private String description;
    private Integer amount;
    private String status;
    private Integer userId;

    public Ticket() {
    }

    public Ticket(Integer ticketId, String description, Integer amount, String status, Integer userId) {
        this.ticketId = ticketId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
    }

    public Integer getTicketId() {
        return ticketId;
    }
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(description, ticket.description) && Objects.equals(status, ticket.status) && Objects.equals(amount, ticket.amount) && Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, description, amount, status, userId);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                ", amount=" + amount +
                '}';
    }

}
