package com.mywork.presentation.backingBeans;

import com.mywork.exceptions.*;

import com.mywork.model.*;
import com.mywork.model.dto.TransaccionDTO;

import com.mywork.presentation.businessDelegate.*;

import com.mywork.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class TransaccionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TransaccionView.class);
    private InputText txtObservacion;
    private InputText txtTipotransaccion;
    private InputText txtValor;
    private InputText txtCodigocuenta_Cuenta;
    private InputText txtId;
    private Calendar txtFecha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TransaccionDTO> data;
    private TransaccionDTO selectedTransaccion;
    private Transaccion entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TransaccionView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedTransaccion = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTransaccion = null;

        if (txtObservacion != null) {
            txtObservacion.setValue(null);
            txtObservacion.setDisabled(true);
        }

        if (txtTipotransaccion != null) {
            txtTipotransaccion.setValue(null);
            txtTipotransaccion.setDisabled(true);
        }

        if (txtValor != null) {
            txtValor.setValue(null);
            txtValor.setDisabled(true);
        }

        if (txtCodigocuenta_Cuenta != null) {
            txtCodigocuenta_Cuenta.setValue(null);
            txtCodigocuenta_Cuenta.setDisabled(true);
        }

        if (txtFecha != null) {
            txtFecha.setValue(null);
            txtFecha.setDisabled(true);
        }

        if (txtId != null) {
            txtId.setValue(null);
            txtId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFecha() {
        Date inputDate = (Date) txtFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = (id != null) ? businessDelegatorView.getTransaccion(id)
                                  : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtObservacion.setDisabled(false);
            txtTipotransaccion.setDisabled(false);
            txtValor.setDisabled(false);
            txtCodigocuenta_Cuenta.setDisabled(false);
            txtFecha.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtFecha.setValue(entity.getFecha());
            txtFecha.setDisabled(false);
            txtObservacion.setValue(entity.getObservacion());
            txtObservacion.setDisabled(false);
            txtTipotransaccion.setValue(entity.getTipotransaccion());
            txtTipotransaccion.setDisabled(false);
            txtValor.setValue(entity.getValor());
            txtValor.setDisabled(false);
            txtCodigocuenta_Cuenta.setValue(entity.getCuenta().getCodigocuenta());
            txtCodigocuenta_Cuenta.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTransaccion = (TransaccionDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedTransaccion"));
        txtFecha.setValue(selectedTransaccion.getFecha());
        txtFecha.setDisabled(false);
        txtObservacion.setValue(selectedTransaccion.getObservacion());
        txtObservacion.setDisabled(false);
        txtTipotransaccion.setValue(selectedTransaccion.getTipotransaccion());
        txtTipotransaccion.setDisabled(false);
        txtValor.setValue(selectedTransaccion.getValor());
        txtValor.setDisabled(false);
        txtCodigocuenta_Cuenta.setValue(selectedTransaccion.getCodigocuenta_Cuenta());
        txtCodigocuenta_Cuenta.setDisabled(false);
        txtId.setValue(selectedTransaccion.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTransaccion == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new Transaccion();

            Integer id = FacesUtils.checkInteger(txtId);

            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setId(id);
            entity.setObservacion(FacesUtils.checkString(txtObservacion));
            entity.setTipotransaccion(FacesUtils.checkInteger(
                    txtTipotransaccion));
            entity.setValor(FacesUtils.checkInteger(txtValor));
            entity.setCuenta((FacesUtils.checkString(txtCodigocuenta_Cuenta) != null)
                ? businessDelegatorView.getCuenta(FacesUtils.checkString(
                        txtCodigocuenta_Cuenta)) : null);
            businessDelegatorView.saveTransaccion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Integer id = new Integer(selectedTransaccion.getId());
                entity = businessDelegatorView.getTransaccion(id);
            }

            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setObservacion(FacesUtils.checkString(txtObservacion));
            entity.setTipotransaccion(FacesUtils.checkInteger(
                    txtTipotransaccion));
            entity.setValor(FacesUtils.checkInteger(txtValor));
            entity.setCuenta((FacesUtils.checkString(txtCodigocuenta_Cuenta) != null)
                ? businessDelegatorView.getCuenta(FacesUtils.checkString(
                        txtCodigocuenta_Cuenta)) : null);
            businessDelegatorView.updateTransaccion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTransaccion = (TransaccionDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedTransaccion"));

            Integer id = new Integer(selectedTransaccion.getId());
            entity = businessDelegatorView.getTransaccion(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getTransaccion(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTransaccion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String action_modifyWitDTO(Date fecha, Integer id,
        String observacion, Integer tipotransaccion, Integer valor,
        String codigocuenta_Cuenta) throws Exception {
        try {
            entity.setFecha(FacesUtils.checkDate(fecha));
            entity.setObservacion(FacesUtils.checkString(observacion));
            entity.setTipotransaccion(FacesUtils.checkInteger(tipotransaccion));
            entity.setValor(FacesUtils.checkInteger(valor));
            businessDelegatorView.updateTransaccion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TransaccionView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtObservacion() {
        return txtObservacion;
    }

    public void setTxtObservacion(InputText txtObservacion) {
        this.txtObservacion = txtObservacion;
    }

    public InputText getTxtTipotransaccion() {
        return txtTipotransaccion;
    }

    public void setTxtTipotransaccion(InputText txtTipotransaccion) {
        this.txtTipotransaccion = txtTipotransaccion;
    }

    public InputText getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(InputText txtValor) {
        this.txtValor = txtValor;
    }

    public InputText getTxtCodigocuenta_Cuenta() {
        return txtCodigocuenta_Cuenta;
    }

    public void setTxtCodigocuenta_Cuenta(InputText txtCodigocuenta_Cuenta) {
        this.txtCodigocuenta_Cuenta = txtCodigocuenta_Cuenta;
    }

    public Calendar getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Calendar txtFecha) {
        this.txtFecha = txtFecha;
    }

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<TransaccionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTransaccion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TransaccionDTO> transaccionDTO) {
        this.data = transaccionDTO;
    }

    public TransaccionDTO getSelectedTransaccion() {
        return selectedTransaccion;
    }

    public void setSelectedTransaccion(TransaccionDTO transaccion) {
        this.selectedTransaccion = transaccion;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
