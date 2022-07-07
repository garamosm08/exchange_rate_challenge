package com.bcp.challenge.exchangerate.model;

public class ExchangeRateResponse {

    private Long transactionId;

    public ExchangeRateResponse(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
