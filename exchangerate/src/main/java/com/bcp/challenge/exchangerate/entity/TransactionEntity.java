package com.bcp.challenge.exchangerate.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "exchange_rate", name = "transaction")
public class TransactionEntity {

    @Id
    @SequenceGenerator(name = "TRANSACTION_SEQUENCE", schema = "exchange_rate", sequenceName = "seq_transaction", allocationSize = 2, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_SEQUENCE")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_cliente")
    private Long customerId;

    @Column(name = "codigo_cliente")
    private String customerCode;

    @Column(name = "id_source_account")
    private Long sourceAccountId;

    @Column(name = "codigo_source_account")
    private String sourceAccountCode;

    @Column(name = "id_source_currency")
    private Integer sourceCurrencyId;

    @Column(name = "codigo_source_currency")
    private String sourceCurrencyCode;

    @Column(name = "id_target_account")
    private Long targetAccountId;

    @Column(name = "codigo_target_account")
    private String targetAccountCode;

    @Column(name = "id_target_currency")
    private Integer targetCurrencyId;

    @Column(name = "codigo_target_currency")
    private String targetCurrencyCode;

    @Column(name = "source_amount")
    private Double sourceAmount;

    @Column(name = "target_amount")
    private Double targetAmount;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "fecha")
    private Date registerDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getSourceAccountCode() {
        return sourceAccountCode;
    }

    public void setSourceAccountCode(String sourceAccountCode) {
        this.sourceAccountCode = sourceAccountCode;
    }

    public Integer getSourceCurrencyId() {
        return sourceCurrencyId;
    }

    public void setSourceCurrencyId(Integer sourceCurrencyId) {
        this.sourceCurrencyId = sourceCurrencyId;
    }

    public String getSourceCurrencyCode() {
        return sourceCurrencyCode;
    }

    public void setSourceCurrencyCode(String sourceCurrencyCode) {
        this.sourceCurrencyCode = sourceCurrencyCode;
    }

    public Long getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(Long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public String getTargetAccountCode() {
        return targetAccountCode;
    }

    public void setTargetAccountCode(String targetAccountCode) {
        this.targetAccountCode = targetAccountCode;
    }

    public Integer getTargetCurrencyId() {
        return targetCurrencyId;
    }

    public void setTargetCurrencyId(Integer targetCurrencyId) {
        this.targetCurrencyId = targetCurrencyId;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    public void setTargetCurrencyCode(String targetCurrencyCode) {
        this.targetCurrencyCode = targetCurrencyCode;
    }

    public Double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", customerCode='" + customerCode + '\'' +
                ", sourceAccountId=" + sourceAccountId +
                ", sourceAccountCode='" + sourceAccountCode + '\'' +
                ", sourceCurrencyId=" + sourceCurrencyId +
                ", sourceCurrencyCode='" + sourceCurrencyCode + '\'' +
                ", targetAccountId=" + targetAccountId +
                ", targetAccountCode='" + targetAccountCode + '\'' +
                ", targetCurrencyId=" + targetCurrencyId +
                ", targetCurrencyCode='" + targetCurrencyCode + '\'' +
                ", sourceAmount=" + sourceAmount +
                ", targetAmount=" + targetAmount +
                ", exchangeRate=" + exchangeRate +
                ", registerDate=" + registerDate +
                '}';
    }
}
