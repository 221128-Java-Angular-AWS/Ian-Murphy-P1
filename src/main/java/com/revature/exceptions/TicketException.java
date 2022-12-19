package com.revature.exceptions;

public class TicketException extends Exception {
    public TicketException(String msg) {
        super(msg);
    }
}

// ^ Will make sure the reimbursement ticket author provides a description and amount during submission