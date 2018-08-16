package com.nice.wfm.dto;

/**
 * Created on 8/15/18.
 * Author: filmon
 * Nice Systems Ltd
 */

public class CreditCardApplicationResponse {
    private Status status;
    public CreditCardApplicationResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status{
        GRANTED
    }
}
