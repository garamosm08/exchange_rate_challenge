package com.bcp.challenge.exchangerate.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(schema = "exchange_rate", name = "currency")
public class CurrencyEntity {

    @Id
    @SequenceGenerator(name = "CURRENCY_SEQUENCE", schema = "exchange_rate", sequenceName = "seq_currency", allocationSize = 5, initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_SEQUENCE")
    @Column(name = "id")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "etiqueta")
    private String etiqueta;

    @Column(name = "fecha")
    private Date fecha;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "localCurrency")
    private Set<ExchangeTypeEntity> localExchangeTypes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foreignCurrency")
    private Set<ExchangeTypeEntity> foreignExchangeTypes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
    private Set<AccountEntity> accounts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<ExchangeTypeEntity> getLocalExchangeTypes() {
        return localExchangeTypes;
    }

    public void setLocalExchangeTypes(Set<ExchangeTypeEntity> localExchangeTypes) {
        this.localExchangeTypes = localExchangeTypes;
    }

    public Set<ExchangeTypeEntity> getForeignExchangeTypes() {
        return foreignExchangeTypes;
    }

    public void setForeignExchangeTypes(Set<ExchangeTypeEntity> foreignExchangeTypes) {
        this.foreignExchangeTypes = foreignExchangeTypes;
    }

    public Set<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountEntity> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "CurrencyEntity{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", etiqueta='" + etiqueta + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
