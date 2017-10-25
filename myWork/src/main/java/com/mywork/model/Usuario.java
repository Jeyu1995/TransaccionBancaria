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
@Table(name = "usuario", schema = "${schema}")
public class Usuario implements java.io.Serializable {
    @Id
    @NotNull
    private String dni;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String apellido;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String email;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String nombre;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String telefono;
    private Set<Cuenta> cuentas = new HashSet<Cuenta>(0);

    public Usuario() {
    }

    public Usuario(String dni, String apellido, Set<Cuenta> cuentas,
        String email, String nombre, String telefono) {
        this.dni = dni;
        this.apellido = apellido;
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cuentas = cuentas;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Cuenta> getCuentas() {
        return this.cuentas;
    }

    public void setCuentas(Set<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
