package com.bcp.challenge.exchangerate.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "exchange_rate", name = "exchange_type")
public class ExchangeTypeEntity {

    @Id
    @SequenceGenerator(name = "EXCHANGE_RATE_SEQUENCE", schema = "exchange_rate", sequenceName = "seq_exchange_type", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_RATE_SEQUENCE")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_local_currency", nullable = false)
    private CurrencyEntity localCurrency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_foreign_currency", nullable = false)
    private CurrencyEntity foreignCurrency;

    @Column(name = "purchase_value")
    private Double purchaseValue;

    @Column(name = "sales_value")
    private Double salesValue;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CurrencyEntity getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(CurrencyEntity localCurrency) {
        this.localCurrency = localCurrency;
    }

    public CurrencyEntity getForeignCurrency() {
        return foreignCurrency;
    }

    public void setForeignCurrency(CurrencyEntity foreignCurrency) {
        this.foreignCurrency = foreignCurrency;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "ExchangeTypeEntity{" +
                "id=" + id +
                ", localCurrency=" + localCurrency +
                ", foreignCurrency=" + foreignCurrency +
                ", purchaseValue=" + purchaseValue +
                ", salesValue=" + salesValue +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
