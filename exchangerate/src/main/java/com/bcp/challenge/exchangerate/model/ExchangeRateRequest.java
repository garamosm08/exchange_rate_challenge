package com.bcp.challenge.exchangerate.model;

public class ExchangeRateRequest {

    private Long sourceAccountId;
    private Integer sourceCurrencyId;
    private Long targetAccountId;
    private Integer targetCurrencyId;
    private Double amount;

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public Integer getSourceCurrencyId() {
        return sourceCurrencyId;
    }

    public void setSourceCurrencyId(Integer sourceCurrencyId) {
        this.sourceCurrencyId = sourceCurrencyId;
    }

    public Long getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(Long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public Integer getTargetCurrencyId() {
        return targetCurrencyId;
    }

    public void setTargetCurrencyId(Integer targetCurrencyId) {
        this.targetCurrencyId = targetCurrencyId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
