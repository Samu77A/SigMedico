/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager_bean;

import java.util.AbstractList;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimientoMedicamento;
import mantenimientos.mantenimientoMedicamentoTipo;
import mantenimientos.mantenimientoTipo;
import org.primefaces.context.RequestContext;
import persistencia.Medicamento;
import persistencia.MedicamentoTipo;
import persistencia.Tipo;

/**
 *
 * @author marvin.guzmanUSAM
 */
@ManagedBean
@RequestScoped
public class BeanMedicamentoTipo {

    private int id_medicamento_tipo = 0;
    private int id_medicamento;
    private int id_tipo;
    private List<MedicamentoTipo> listmedTipo;
    private List<Medicamento> listamed;
    private List<Tipo> listTipo;
    private final mantenimientoMedicamentoTipo mmt = new mantenimientoMedicamentoTipo();
    private final mantenimientoTipo mantt = new mantenimientoTipo();
    private final mantenimientoMedicamento manmed = new mantenimientoMedicamento();

    public BeanMedicamentoTipo() {
    }

    public List<MedicamentoTipo> getListmedTipo() {
        this.listmedTipo = new ArrayList<>();
        listmedTipo = mmt.consultarTodo();
        return listmedTipo;
    }

    public void setListmedTipo(List<MedicamentoTipo> listmedTipo) {
        this.listmedTipo = listmedTipo;
    }

    public int getId_medicamento_tipo() {
        return id_medicamento_tipo;
    }

    public void setId_medicamento_tipo(int id_medicamento_tipo) {
        this.id_medicamento_tipo = id_medicamento_tipo;
    }

    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public int getId_tipo() {

        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public List<Medicamento> getListamed() {
        this.listamed = new ArrayList<>();
        listamed = manmed.consultarTodo();
        return listamed;
    }

    public void setListamed(List<Medicamento> listamed) {
        this.listamed = listamed;
    }

    public List<Tipo> getListTipo() {
        this.listTipo = new ArrayList<>();
        listTipo = mantt.consultarTodo();

        return listTipo;
    }

    public void setListTipo(List<Tipo> listTipo) {
        this.listTipo = listTipo;
    }

    public String guardarMedicamentoTipo() {

        MedicamentoTipo mt = new MedicamentoTipo();

        Medicamento md = new Medicamento();
        Tipo t = new Tipo();

        md.setIdMedicamento(id_medicamento);
        t.setIdTipo(id_tipo);

        mt.setIdMedicamentoTipo(id_medicamento_tipo);
        mt.setIdMedicamento(md);
        mt.setIdTipo(t);

        int vali = mmt.guardarMT(mt);
        if (vali == 1) {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "exito al guardar"));

        } else {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puedo Guardar"));

        }
        return null;
    }

    public String consultarByID(int id_medicamento_tipo) {
        MedicamentoTipo listmedt = mmt.consultaByID(id_medicamento_tipo);

        if (listmedt != null) {
            this.id_medicamento_tipo = listmedt.getIdMedicamentoTipo();
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos a mostrar"));
        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Invalido", "No se puede mostrar Datos"));
        }
        return null;
    }

    public void eliminarMEdTipo(int id_medicamento_tipo) {
        int r = mmt.eliminar(id_medicamento_tipo);
        if (r > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Se elimino"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puede eliminar"));
        }
    }

    public String actualizarMedTipo(int id_medicamento_tipo) {
        Medicamento m = new Medicamento();
        Tipo t = new Tipo();
        MedicamentoTipo mt = new MedicamentoTipo();

        m.setIdMedicamento(id_medicamento);
        t.setIdTipo(id_tipo);

        mt.setIdMedicamentoTipo(id_medicamento_tipo);
        mt.setIdMedicamento(m);
        mt.setIdTipo(t);
        boolean rps = mmt.actualizarMedTipo(id_medicamento_tipo, mt);
if(rps){
  RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos actualizado"));
            return "mostrarMedicamentoTipo.xhtml?faces-redirect=true";

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos NO actualizado"));

        }
return null;
    }
}
