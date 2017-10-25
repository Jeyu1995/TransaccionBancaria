package com.mywork.rest.controllers;

import com.mywork.dto.mapper.ITransaccionMapper;

import com.mywork.model.*;
import com.mywork.model.dto.TransaccionDTO;

import com.mywork.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/transaccion")
public class TransaccionRestController {
    private static final Logger log = LoggerFactory.getLogger(TransaccionRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private ITransaccionMapper transaccionMapper;

    @PostMapping(value = "/saveTransaccion")
    public void saveTransaccion(@RequestBody
    TransaccionDTO transaccionDTO) throws Exception {
        try {
            Transaccion transaccion = transaccionMapper.transaccionDTOToTransaccion(transaccionDTO);

            businessDelegatorView.saveTransaccion(transaccion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteTransaccion/{id}")
    public void deleteTransaccion(@PathVariable("id")
    Integer id) throws Exception {
        try {
            Transaccion transaccion = businessDelegatorView.getTransaccion(id);

            businessDelegatorView.deleteTransaccion(transaccion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateTransaccion/")
    public void updateTransaccion(@RequestBody
    TransaccionDTO transaccionDTO) throws Exception {
        try {
            Transaccion transaccion = transaccionMapper.transaccionDTOToTransaccion(transaccionDTO);

            businessDelegatorView.updateTransaccion(transaccion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataTransaccion")
    public List<TransaccionDTO> getDataTransaccion() throws Exception {
        try {
            return businessDelegatorView.getDataTransaccion();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getTransaccion/{id}")
    public TransaccionDTO getTransaccion(@PathVariable("id")
    Integer id) throws Exception {
        try {
            Transaccion transaccion = businessDelegatorView.getTransaccion(id);

            return transaccionMapper.transaccionToTransaccionDTO(transaccion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
