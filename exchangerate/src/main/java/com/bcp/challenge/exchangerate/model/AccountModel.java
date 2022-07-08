package com.bcp.challenge.exchangerate.model;

public class AccountModel {

    private Long id;
    private String code;
    private String label;
    private String customerCode;
    private String currencyCode;
    private Double balance;

    public AccountModel() {
    }

    public AccountModel(Long id, String code, String label, String customerCode, String currencyCode, Double balance) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.customerCode = customerCode;
        this.currencyCode = currencyCode;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
