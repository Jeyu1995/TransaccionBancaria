package com.mywork.presentation.backingBeans;

import com.mywork.exceptions.*;

import com.mywork.model.*;
import com.mywork.model.dto.CuentaDTO;

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
public class CuentaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CuentaView.class);
    private InputText txtBanco;
    private InputText txtSaldo;
    private InputText txtTipocuenta;
    private InputText txtDni_Usuario;
    private InputText txtCodigocuenta;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CuentaDTO> data;
    private CuentaDTO selectedCuenta;
    private Cuenta entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CuentaView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedCuenta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCuenta = null;

        if (txtBanco != null) {
            txtBanco.setValue(null);
            txtBanco.setDisabled(true);
        }

        if (txtSaldo != null) {
            txtSaldo.setValue(null);
            txtSaldo.setDisabled(true);
        }

        if (txtTipocuenta != null) {
            txtTipocuenta.setValue(null);
            txtTipocuenta.setDisabled(true);
        }

        if (txtDni_Usuario != null) {
            txtDni_Usuario.setValue(null);
            txtDni_Usuario.setDisabled(true);
        }

        if (txtCodigocuenta != null) {
            txtCodigocuenta.setValue(null);
            txtCodigocuenta.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            String codigocuenta = FacesUtils.checkString(txtCodigocuenta);
            entity = (codigocuenta != null)
                ? businessDelegatorView.getCuenta(codigocuenta) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtBanco.setDisabled(false);
            txtSaldo.setDisabled(false);
            txtTipocuenta.setDisabled(false);
            txtDni_Usuario.setDisabled(false);
            txtCodigocuenta.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtBanco.setValue(entity.getBanco());
            txtBanco.setDisabled(false);
            txtSaldo.setValue(entity.getSaldo());
            txtSaldo.setDisabled(false);
            txtTipocuenta.setValue(entity.getTipocuenta());
            txtTipocuenta.setDisabled(false);
            txtDni_Usuario.setValue(entity.getUsuario().getDni());
            txtDni_Usuario.setDisabled(false);
            txtCodigocuenta.setValue(entity.getCodigocuenta());
            txtCodigocuenta.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCuenta = (CuentaDTO) (evt.getComponent().getAttributes()
                                         .get("selectedCuenta"));
        txtBanco.setValue(selectedCuenta.getBanco());
        txtBanco.setDisabled(false);
        txtSaldo.setValue(selectedCuenta.getSaldo());
        txtSaldo.setDisabled(false);
        txtTipocuenta.setValue(selectedCuenta.getTipocuenta());
        txtTipocuenta.setDisabled(false);
        txtDni_Usuario.setValue(selectedCuenta.getDni_Usuario());
        txtDni_Usuario.setDisabled(false);
        txtCodigocuenta.setValue(selectedCuenta.getCodigocuenta());
        txtCodigocuenta.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCuenta == null) && (entity == null)) {
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
            entity = new Cuenta();

            String codigocuenta = FacesUtils.checkString(txtCodigocuenta);

            entity.setBanco(FacesUtils.checkString(txtBanco));
            entity.setCodigocuenta(codigocuenta);
            entity.setSaldo(FacesUtils.checkInteger(txtSaldo));
            entity.setTipocuenta(FacesUtils.checkInteger(txtTipocuenta));
            entity.setUsuario((FacesUtils.checkString(txtDni_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkString(
                        txtDni_Usuario)) : null);
            businessDelegatorView.saveCuenta(entity);
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
                String codigocuenta = new String(selectedCuenta.getCodigocuenta());
                entity = businessDelegatorView.getCuenta(codigocuenta);
            }

            entity.setBanco(FacesUtils.checkString(txtBanco));
            entity.setSaldo(FacesUtils.checkInteger(txtSaldo));
            entity.setTipocuenta(FacesUtils.checkInteger(txtTipocuenta));
            entity.setUsuario((FacesUtils.checkString(txtDni_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkString(
                        txtDni_Usuario)) : null);
            businessDelegatorView.updateCuenta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCuenta = (CuentaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedCuenta"));

            String codigocuenta = new String(selectedCuenta.getCodigocuenta());
            entity = businessDelegatorView.getCuenta(codigocuenta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String codigocuenta = FacesUtils.checkString(txtCodigocuenta);
            entity = businessDelegatorView.getCuenta(codigocuenta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCuenta(entity);
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

    public String action_modifyWitDTO(String banco, String codigocuenta,
        Integer saldo, Integer tipocuenta, String dni_Usuario)
        throws Exception {
        try {
            entity.setBanco(FacesUtils.checkString(banco));
            entity.setSaldo(FacesUtils.checkInteger(saldo));
            entity.setTipocuenta(FacesUtils.checkInteger(tipocuenta));
            businessDelegatorView.updateCuenta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CuentaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtBanco() {
        return txtBanco;
    }

    public void setTxtBanco(InputText txtBanco) {
        this.txtBanco = txtBanco;
    }

    public InputText getTxtSaldo() {
        return txtSaldo;
    }

    public void setTxtSaldo(InputText txtSaldo) {
        this.txtSaldo = txtSaldo;
    }

    public InputText getTxtTipocuenta() {
        return txtTipocuenta;
    }

    public void setTxtTipocuenta(InputText txtTipocuenta) {
        this.txtTipocuenta = txtTipocuenta;
    }

    public InputText getTxtDni_Usuario() {
        return txtDni_Usuario;
    }

    public void setTxtDni_Usuario(InputText txtDni_Usuario) {
        this.txtDni_Usuario = txtDni_Usuario;
    }

    public InputText getTxtCodigocuenta() {
        return txtCodigocuenta;
    }

    public void setTxtCodigocuenta(InputText txtCodigocuenta) {
        this.txtCodigocuenta = txtCodigocuenta;
    }

    public List<CuentaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCuenta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CuentaDTO> cuentaDTO) {
        this.data = cuentaDTO;
    }

    public CuentaDTO getSelectedCuenta() {
        return selectedCuenta;
    }

    public void setSelectedCuenta(CuentaDTO cuenta) {
        this.selectedCuenta = cuenta;
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
