package com.mywork.dto.mapper;

import com.mywork.model.*;
import com.mywork.model.Cuenta;
import com.mywork.model.control.*;
import com.mywork.model.dto.CuentaDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Component
@Scope("singleton")
public class CuentaMapper implements ICuentaMapper {
    private static final Logger log = LoggerFactory.getLogger(CuentaMapper.class);

    /**
    * Logic injected by Spring that manages Usuario entities
    *
    */
    @Autowired
    IUsuarioLogic logicUsuario1;

    @Transactional(readOnly = true)
    public CuentaDTO cuentaToCuentaDTO(Cuenta cuenta) throws Exception {
        try {
            CuentaDTO cuentaDTO = new CuentaDTO();

            cuentaDTO.setCodigocuenta(cuenta.getCodigocuenta());
            cuentaDTO.setBanco((cuenta.getBanco() != null) ? cuenta.getBanco()
                                                           : null);
            cuentaDTO.setSaldo((cuenta.getSaldo() != null) ? cuenta.getSaldo()
                                                           : null);
            cuentaDTO.setTipocuenta((cuenta.getTipocuenta() != null)
                ? cuenta.getTipocuenta() : null);
            cuentaDTO.setDni_Usuario((cuenta.getUsuario().getDni() != null)
                ? cuenta.getUsuario().getDni() : null);

            return cuentaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Cuenta cuentaDTOToCuenta(CuentaDTO cuentaDTO)
        throws Exception {
        try {
            Cuenta cuenta = new Cuenta();

            cuenta.setCodigocuenta(cuentaDTO.getCodigocuenta());
            cuenta.setBanco((cuentaDTO.getBanco() != null)
                ? cuentaDTO.getBanco() : null);
            cuenta.setSaldo((cuentaDTO.getSaldo() != null)
                ? cuentaDTO.getSaldo() : null);
            cuenta.setTipocuenta((cuentaDTO.getTipocuenta() != null)
                ? cuentaDTO.getTipocuenta() : null);

            Usuario usuario = new Usuario();

            if (cuentaDTO.getDni_Usuario() != null) {
                usuario = logicUsuario1.getUsuario(cuentaDTO.getDni_Usuario());
            }

            if (usuario != null) {
                cuenta.setUsuario(usuario);
            }

            return cuenta;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<CuentaDTO> listCuentaToListCuentaDTO(List<Cuenta> listCuenta)
        throws Exception {
        try {
            List<CuentaDTO> cuentaDTOs = new ArrayList<CuentaDTO>();

            for (Cuenta cuenta : listCuenta) {
                CuentaDTO cuentaDTO = cuentaToCuentaDTO(cuenta);

                cuentaDTOs.add(cuentaDTO);
            }

            return cuentaDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Cuenta> listCuentaDTOToListCuenta(List<CuentaDTO> listCuentaDTO)
        throws Exception {
        try {
            List<Cuenta> listCuenta = new ArrayList<Cuenta>();

            for (CuentaDTO cuentaDTO : listCuentaDTO) {
                Cuenta cuenta = cuentaDTOToCuenta(cuentaDTO);

                listCuenta.add(cuenta);
            }

            return listCuenta;
        } catch (Exception e) {
            throw e;
        }
    }
}
