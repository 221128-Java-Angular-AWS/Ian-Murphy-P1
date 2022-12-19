package com.revature.pojos;

import java.util.Objects;

public class Ticket {
    private Integer ticketId;
    private String type;
    private String description;
    private String status;
    private Integer userId;
    private Integer amount;

    public Ticket() {
    }

    public Ticket(Integer ticketId, String type, String description, String status, Integer userId, Integer amount) {
        this.ticketId = ticketId;
        this.type = type;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.amount = amount;
    }

    public Integer getTicketId() {
        return ticketId;
    }
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
    public void setAmount(Integer userId) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(type, ticket.type) && Objects.equals(description, ticket.description) && Objects.equals(status, ticket.status) && Objects.equals(amount, ticket.amount) && Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, type, description, status, userId, amount);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                ", amount=" + amount +
                '}';
    }

}
