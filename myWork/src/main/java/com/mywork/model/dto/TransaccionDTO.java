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
public class TransaccionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TransaccionDTO.class);
    private Date fecha;
    private Integer id;
    private String observacion;
    private Integer tipotransaccion;
    private Integer valor;
    private String codigocuenta_Cuenta;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getTipotransaccion() {
        return tipotransaccion;
    }

    public void setTipotransaccion(Integer tipotransaccion) {
        this.tipotransaccion = tipotransaccion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getCodigocuenta_Cuenta() {
        return codigocuenta_Cuenta;
    }

    public void setCodigocuenta_Cuenta(String codigocuenta_Cuenta) {
        this.codigocuenta_Cuenta = codigocuenta_Cuenta;
    }
}
