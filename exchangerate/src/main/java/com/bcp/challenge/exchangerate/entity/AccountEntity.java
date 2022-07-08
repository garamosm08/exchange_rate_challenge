package com.bcp.challenge.exchangerate.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "exchange_rate", name = "account")
public class AccountEntity {

    @Id
    @SequenceGenerator(name = "ACCOUNT_SEQUENCE", schema = "exchange_rate", sequenceName = "seq_account", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQUENCE")
    @Column(name = "id")
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "etiqueta")
    private String etiqueta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_currency", nullable = false)
    private CurrencyEntity currency;

    @Column(name = "saldo")
    private Double balance;

    @Column(name = "fecha_creacion")
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", etiqueta='" + etiqueta + '\'' +
                ", user=" + user +
                ", currency=" + currency +
                ", balance='" + balance + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
