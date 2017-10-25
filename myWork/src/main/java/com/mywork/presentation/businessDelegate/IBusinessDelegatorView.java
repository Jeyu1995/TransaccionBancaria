package com.mywork.presentation.businessDelegate;

import com.mywork.model.Cuenta;
import com.mywork.model.Transaccion;
import com.mywork.model.Usuario;
import com.mywork.model.control.CuentaLogic;
import com.mywork.model.control.ICuentaLogic;
import com.mywork.model.control.ITransaccionLogic;
import com.mywork.model.control.IUsuarioLogic;
import com.mywork.model.control.TransaccionLogic;
import com.mywork.model.control.UsuarioLogic;
import com.mywork.model.dto.CuentaDTO;
import com.mywork.model.dto.TransaccionDTO;
import com.mywork.model.dto.UsuarioDTO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<Cuenta> getCuenta() throws Exception;

    public void saveCuenta(Cuenta entity) throws Exception;

    public void deleteCuenta(Cuenta entity) throws Exception;

    public void updateCuenta(Cuenta entity) throws Exception;

    public Cuenta getCuenta(String codigocuenta) throws Exception;

    public List<Cuenta> findByCriteriaInCuenta(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Cuenta> findPageCuenta(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCuenta() throws Exception;

    public List<CuentaDTO> getDataCuenta() throws Exception;

    public void validateCuenta(Cuenta cuenta) throws Exception;

    public List<Transaccion> getTransaccion() throws Exception;

    public void saveTransaccion(Transaccion entity) throws Exception;

    public void deleteTransaccion(Transaccion entity) throws Exception;

    public void updateTransaccion(Transaccion entity) throws Exception;

    public Transaccion getTransaccion(Integer id) throws Exception;

    public List<Transaccion> findByCriteriaInTransaccion(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Transaccion> findPageTransaccion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTransaccion() throws Exception;

    public List<TransaccionDTO> getDataTransaccion() throws Exception;

    public void validateTransaccion(Transaccion transaccion)
        throws Exception;

    public List<Usuario> getUsuario() throws Exception;

    public void saveUsuario(Usuario entity) throws Exception;

    public void deleteUsuario(Usuario entity) throws Exception;

    public void updateUsuario(Usuario entity) throws Exception;

    public Usuario getUsuario(String dni) throws Exception;

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuario() throws Exception;

    public List<UsuarioDTO> getDataUsuario() throws Exception;

    public void validateUsuario(Usuario usuario) throws Exception;
}
