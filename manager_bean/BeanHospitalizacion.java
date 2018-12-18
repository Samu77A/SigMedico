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

import mantenimientos.mantenimientoConsulta;
import mantenimientos.mantenimientoHospitalizacion;
import org.primefaces.context.RequestContext;
import persistencia.Consulta;

import persistencia.Hospitalizacion;

/**
 *
 * @author marvin.guzmanUSAM
 */
@ManagedBean
@RequestScoped
public class BeanHospitalizacion {

    private int id_hospitalizacion = 0;
    private int id_consulta;
    private Date fecha_hora_entrada;
    private Date fecha_hora_salida;
    private String estado_hospitalizacion;

    private List<Hospitalizacion> listhost;
    private List<Consulta> listconsul;

    private final mantenimientoHospitalizacion manteHosp = new mantenimientoHospitalizacion();
    private final mantenimientoConsulta manteConsulta = new mantenimientoConsulta();

    public BeanHospitalizacion() {
    }

    public int getId_hospitalizacion() {
        return id_hospitalizacion;
    }

    public void setId_hospitalizacion(int id_hospitalizacion) {
        this.id_hospitalizacion = id_hospitalizacion;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public Date getFecha_hora_entrada() {
        return fecha_hora_entrada;
    }

    public void setFecha_hora_entrada(Date fecha_hora_entrada) {
        this.fecha_hora_entrada = fecha_hora_entrada;
    }

    public Date getFecha_hora_salida() {
        return fecha_hora_salida;
    }

    public void setFecha_hora_salida(Date fecha_hora_salida) {
        this.fecha_hora_salida = fecha_hora_salida;
    }

    public String getEstado_hospitalizacion() {
        return estado_hospitalizacion;
    }

    public void setEstado_hospitalizacion(String estado_hospitalizacion) {
        this.estado_hospitalizacion = estado_hospitalizacion;
    }

    public List<Hospitalizacion> getListhost() {
        this.listhost = new ArrayList<>();
        listhost = manteHosp.consultarTodo();
        return listhost;
    }

    public void setListhost(List<Hospitalizacion> listhost) {
        this.listhost = listhost;
    }

    public List<Consulta> getListconsul() {
        this.listconsul = new ArrayList<>();
        //listconsul = manteConsulta.consultarTodo();
        return listconsul;
    }

    public void setListconsul(List<Consulta> listconsul) {
        this.listconsul = listconsul;
    }

    public String guardarHospi() {
        Hospitalizacion h = new Hospitalizacion();
        Consulta c = new Consulta();

        c.setIdConsulta(id_consulta);

        h.setIdHospitalizacion(id_hospitalizacion);
        h.setFechaHoraEntrada(fecha_hora_entrada);
        //h.setFechaHoraSalida(fecha_hora_salida);
        h.setEstadoHospitalizacion(estado_hospitalizacion);
        h.setIdConsulta(c);

        int vali = manteHosp.guardarHosp(h);
        if (vali == 1) {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "exito al guardar", "exito"));
        } else {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puedo Guardar"));
        }
        return null;

    }

    public String consultarByID(int id_hospitalizacion) {
        Hospitalizacion hdp = manteHosp.consultarByID(id_hospitalizacion);

        if (hdp != null) {
            this.id_hospitalizacion = hdp.getIdHospitalizacion();
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

    public String update(int id_hospitalizacion) {
        Hospitalizacion hdp = new Hospitalizacion();
        Consulta c = new Consulta();

        hdp.setIdHospitalizacion(id_hospitalizacion);
        c.setIdConsulta(id_consulta);

        hdp.setFechaHoraEntrada(fecha_hora_entrada);
        //hdp.setFechaHoraSalida(fecha_hora_salida);
        hdp.setEstadoHospitalizacion(estado_hospitalizacion);
        hdp.setIdConsulta(c);

        boolean rp = manteHosp.ActualizarHospi(id_hospitalizacion, hdp);
        if (rp) {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos actualizado"));
            return "mostrarTipo.xhtml?faces-redirect=true";

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos NO actualizado"));

        }
        return null;
    }

    public void eliminarh(int id_hospitalizacion) {
        int h = manteHosp.eliminarhossp(id_hospitalizacion);
        if (h > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Se elimino"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puede eliminar"));
        }

    }

}
