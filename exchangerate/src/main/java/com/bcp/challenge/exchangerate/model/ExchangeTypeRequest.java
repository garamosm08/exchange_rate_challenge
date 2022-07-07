package com.bcp.challenge.exchangerate.model;

public class ExchangeTypeRequest {

    private Integer localCurrencyId;
    private Integer foreignCurrencyId;
    private Double purchaseValue;
    private Double salesValue;

    public Integer getLocalCurrencyId() {
        return localCurrencyId;
    }

    public void setLocalCurrencyId(Integer localCurrencyId) {
        this.localCurrencyId = localCurrencyId;
    }

    public Integer getForeignCurrencyId() {
        return foreignCurrencyId;
    }

    public void setForeignCurrencyId(Integer foreignCurrencyId) {
        this.foreignCurrencyId = foreignCurrencyId;
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
}
