package com.mywork.model;

import org.hibernate.validator.constraints.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "cuenta", schema = "${schema}")
public class Cuenta implements java.io.Serializable {
    @Id
    @NotNull
    private String codigocuenta;
    @NotNull
    private Usuario usuario;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String banco;
    @NotNull
    private Integer saldo;
    @NotNull
    private Integer tipocuenta;
    private Set<Transaccion> transaccions = new HashSet<Transaccion>(0);

    public Cuenta() {
    }

    public Cuenta(String codigocuenta, String banco, Integer saldo,
        Integer tipocuenta, Set<Transaccion> transaccions, Usuario usuario) {
        this.codigocuenta = codigocuenta;
        this.usuario = usuario;
        this.banco = banco;
        this.saldo = saldo;
        this.tipocuenta = tipocuenta;
        this.transaccions = transaccions;
    }

    public String getCodigocuenta() {
        return this.codigocuenta;
    }

    public void setCodigocuenta(String codigocuenta) {
        this.codigocuenta = codigocuenta;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getBanco() {
        return this.banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Integer getSaldo() {
        return this.saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getTipocuenta() {
        return this.tipocuenta;
    }

    public void setTipocuenta(Integer tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public Set<Transaccion> getTransaccions() {
        return this.transaccions;
    }

    public void setTransaccions(Set<Transaccion> transaccions) {
        this.transaccions = transaccions;
    }
}
