package com.mywork.dto.mapper;

import com.mywork.model.*;
import com.mywork.model.Transaccion;
import com.mywork.model.control.*;
import com.mywork.model.dto.TransaccionDTO;

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
public class TransaccionMapper implements ITransaccionMapper {
    private static final Logger log = LoggerFactory.getLogger(TransaccionMapper.class);

    /**
    * Logic injected by Spring that manages Cuenta entities
    *
    */
    @Autowired
    ICuentaLogic logicCuenta1;

    @Transactional(readOnly = true)
    public TransaccionDTO transaccionToTransaccionDTO(Transaccion transaccion)
        throws Exception {
        try {
            TransaccionDTO transaccionDTO = new TransaccionDTO();

            transaccionDTO.setId(transaccion.getId());
            transaccionDTO.setFecha(transaccion.getFecha());
            transaccionDTO.setObservacion((transaccion.getObservacion() != null)
                ? transaccion.getObservacion() : null);
            transaccionDTO.setTipotransaccion((transaccion.getTipotransaccion() != null)
                ? transaccion.getTipotransaccion() : null);
            transaccionDTO.setValor((transaccion.getValor() != null)
                ? transaccion.getValor() : null);
            transaccionDTO.setCodigocuenta_Cuenta((transaccion.getCuenta()
                                                              .getCodigocuenta() != null)
                ? transaccion.getCuenta().getCodigocuenta() : null);

            return transaccionDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Transaccion transaccionDTOToTransaccion(
        TransaccionDTO transaccionDTO) throws Exception {
        try {
            Transaccion transaccion = new Transaccion();

            transaccion.setId(transaccionDTO.getId());
            transaccion.setFecha(transaccionDTO.getFecha());
            transaccion.setObservacion((transaccionDTO.getObservacion() != null)
                ? transaccionDTO.getObservacion() : null);
            transaccion.setTipotransaccion((transaccionDTO.getTipotransaccion() != null)
                ? transaccionDTO.getTipotransaccion() : null);
            transaccion.setValor((transaccionDTO.getValor() != null)
                ? transaccionDTO.getValor() : null);

            Cuenta cuenta = new Cuenta();

            if (transaccionDTO.getCodigocuenta_Cuenta() != null) {
                cuenta = logicCuenta1.getCuenta(transaccionDTO.getCodigocuenta_Cuenta());
            }

            if (cuenta != null) {
                transaccion.setCuenta(cuenta);
            }

            return transaccion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<TransaccionDTO> listTransaccionToListTransaccionDTO(
        List<Transaccion> listTransaccion) throws Exception {
        try {
            List<TransaccionDTO> transaccionDTOs = new ArrayList<TransaccionDTO>();

            for (Transaccion transaccion : listTransaccion) {
                TransaccionDTO transaccionDTO = transaccionToTransaccionDTO(transaccion);

                transaccionDTOs.add(transaccionDTO);
            }

            return transaccionDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Transaccion> listTransaccionDTOToListTransaccion(
        List<TransaccionDTO> listTransaccionDTO) throws Exception {
        try {
            List<Transaccion> listTransaccion = new ArrayList<Transaccion>();

            for (TransaccionDTO transaccionDTO : listTransaccionDTO) {
                Transaccion transaccion = transaccionDTOToTransaccion(transaccionDTO);

                listTransaccion.add(transaccion);
            }

            return listTransaccion;
        } catch (Exception e) {
            throw e;
        }
    }
}
