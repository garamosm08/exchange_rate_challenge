package com.bcp.challenge.exchangerate.model;

public class ExchangeTypeResponse {

    private Long id;
    private String localCurrencyCode;
    private String localCurrencyLabel;
    private String foreignCurrencyCode;
    private String foreignCurrencyLabel;
    private Double purchaseValue;
    private Double salesValue;

    public ExchangeTypeResponse() {
    }

    public ExchangeTypeResponse(Long id, String localCurrencyCode, String localCurrencyLabel, String foreignCurrencyCode,
                                String foreignCurrencyLabel, Double purchaseValue, Double salesValue) {
        this.id = id;
        this.localCurrencyCode = localCurrencyCode;
        this.localCurrencyLabel = localCurrencyLabel;
        this.foreignCurrencyCode = foreignCurrencyCode;
        this.foreignCurrencyLabel = foreignCurrencyLabel;
        this.purchaseValue = purchaseValue;
        this.salesValue = salesValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalCurrencyCode() {
        return localCurrencyCode;
    }

    public void setLocalCurrencyCode(String localCurrencyCode) {
        this.localCurrencyCode = localCurrencyCode;
    }

    public String getForeignCurrencyCode() {
        return foreignCurrencyCode;
    }

    public void setForeignCurrencyCode(String foreignCurrencyCode) {
        this.foreignCurrencyCode = foreignCurrencyCode;
    }

    public Double getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(Double purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public Double getSalesValue() {
        return salesValue;
    }

    public void setSalesValue(Double salesValue) {
        this.salesValue = salesValue;
    }

    public String getLocalCurrencyLabel() {
        return localCurrencyLabel;
    }

    public void setLocalCurrencyLabel(String localCurrencyLabel) {
        this.localCurrencyLabel = localCurrencyLabel;
    }

    public String getForeignCurrencyLabel() {
        return foreignCurrencyLabel;
    }

    public void setForeignCurrencyLabel(String foreignCurrencyLabel) {
        this.foreignCurrencyLabel = foreignCurrencyLabel;
    }
}
