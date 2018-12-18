/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager_bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimientoMedicamento;
import org.primefaces.context.RequestContext;
import persistencia.Medicamento;

/**
 *
 * @author marvin.guzmanUSAM
 */
@ManagedBean
@RequestScoped
public class BeanMedicamento {

    private int id_medicamento = 0;
    private String medicamento;
    private Date fecha_vencimiento; 
    private List<Medicamento> listamed;

    private final mantenimientoMedicamento mantmed = new mantenimientoMedicamento();

    public BeanMedicamento() {
    }

    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public List<Medicamento> getListamed() {
        this.listamed = new ArrayList<>();
        listamed = mantmed.consultarTodo();
        return listamed;
    }

    public void setListamed(List<Medicamento> listamed) {
        this.listamed = listamed;
    }

    public String guardarMed() {

        Medicamento md = new Medicamento();
        md.setIdMedicamento(id_medicamento);
        md.setMedicamento(medicamento);
        md.setFechaVencimiento(fecha_vencimiento);

        int rep = mantmed.guardar(md);
        if (rep == 1) {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "exito al guardar", "exito"));
        } else {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puedo Guardar"));
        }
        return null;
    }

    public String consultarByID(int id_medicamento) {
        Medicamento lista = mantmed.consultarById(id_medicamento);
        if (lista != null) {
            this.id_medicamento = lista.getIdMedicamento();
            this.medicamento = lista.getMedicamento();
            this.fecha_vencimiento = lista.getFechaVencimiento();
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

    public void eliminarMed(int id_medicamento) {

        int med = mantmed.eliminar(id_medicamento);
        if (med > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Se elimino"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puede eliminar"));
        }

    }

    public String actualizarMed(int id_medicamento) {

        Medicamento md = new Medicamento();
        
        md.setIdMedicamento(id_medicamento);
        System.out.println("ID:"+id_medicamento);
        md.setMedicamento(medicamento);
        System.out.println("medicamento: "+medicamento);
        md.setFechaVencimiento(fecha_vencimiento);
        System.out.println("fecha: "+fecha_vencimiento);
        
        boolean r = mantmed.actualizarMedi(id_medicamento, md);
        if (r) {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos actualizado"));
            return "mostrarMedicamento.xhtml?faces-redirect=true";

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos NO actualizado"));

        }
        return null;

    }

}
