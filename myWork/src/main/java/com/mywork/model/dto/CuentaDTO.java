package com.mywork.model.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class CuentaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CuentaDTO.class);
    private String banco;
    private String codigocuenta;
    private Integer saldo;
    private Integer tipocuenta;
    private String dni_Usuario;

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCodigocuenta() {
        return codigocuenta;
    }

    public void setCodigocuenta(String codigocuenta) {
        this.codigocuenta = codigocuenta;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(Integer tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public String getDni_Usuario() {
        return dni_Usuario;
    }

    public void setDni_Usuario(String dni_Usuario) {
        this.dni_Usuario = dni_Usuario;
    }
}
