package com.mywork.model;

import org.hibernate.validator.constraints.*;

import java.util.Date;

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
@Table(name = "transaccion", schema = "${schema}")
public class Transaccion implements java.io.Serializable {
    @Id
    @NotNull
    private Integer id;
    @NotNull
    private Cuenta cuenta;
    @NotNull
    private Date fecha;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String observacion;
    @NotNull
    private Integer tipotransaccion;
    @NotNull
    private Integer valor;

    public Transaccion() {
    }

    public Transaccion(Integer id, Cuenta cuenta, Date fecha,
        String observacion, Integer tipotransaccion, Integer valor) {
        this.id = id;
        this.cuenta = cuenta;
        this.fecha = fecha;
        this.observacion = observacion;
        this.tipotransaccion = tipotransaccion;
        this.valor = valor;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cuenta getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getTipotransaccion() {
        return this.tipotransaccion;
    }

    public void setTipotransaccion(Integer tipotransaccion) {
        this.tipotransaccion = tipotransaccion;
    }

    public Integer getValor() {
        return this.valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
