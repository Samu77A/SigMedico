/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager_bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimientoHospital;
import org.primefaces.context.RequestContext;
import persistencia.Hospital;

/**
 *
 * @author marvin.guzmanUSAM
 */
@ManagedBean
@RequestScoped
public class BeanHospital {

    private int id_hospital = 0;
    private String hospital;
    private List<Hospital> listahosp;

    private final mantenimientoHospital manteh = new mantenimientoHospital();
    private Hospital hospita;

    public BeanHospital() {
    }

    public Hospital getHospita() {
        return hospita;
    }

    public void setHospita(Hospital hospita) {
        this.hospita = hospita;
    }

    public int getId_hospital() {
        return id_hospital;
    }

    public void setId_hospital(int id_hospital) {
        this.id_hospital = id_hospital;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public List<Hospital> getListahosp() {
        this.listahosp = new ArrayList<>();
        listahosp = manteh.consultarTodo();
        return listahosp;
    }

    public void setListahosp(List<Hospital> listahosp) {
        this.listahosp = listahosp;
    }

    public String guardarHosp() {
        Hospital ht = new Hospital();
        System.out.println("ID: " + id_hospital);

        ht.setIdHospital(id_hospital);
        System.out.println("nombre: " + hospital);
        ht.setHospital(hospital);

        int res = manteh.guardarH(ht);
        if (res == 1) {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "exito al guardar", "exito"));
        } else {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puedo Guardar"));
        }
        return null;

    }

    public String consultarByID(int id_hospital) {
        Hospital lista = manteh.consultarByID(id_hospital);
        if (lista != null) {
            this.id_hospital = lista.getIdHospital();
            this.hospital = lista.getHospital();
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

    public void eliminarHospital(int id_hospital) {

        int h = manteh.eliminar(id_hospital);
        if (h > 0) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Se elimino"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puede eliminar"));
        }
    }

    public String actualizarHosp(int id_hospital) {
        Hospital h = new Hospital();
        h.setIdHospital(id_hospital);
        h.setHospital(hospital);
        boolean p = manteh.actualizarH(id_hospital, h);
        if (p) {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos actualizado"));
            return "mostrarHospital.xhtml?faces-redirect=true";

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos NO actualizado"));

        }
        return null;
    }

}
