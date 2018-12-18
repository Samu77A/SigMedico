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
import mantenimientos.mantenimientoTipo;
import org.primefaces.context.RequestContext;
import persistencia.Tipo;

/**
 *
 * @author marvin.guzmanUSAM
 */
@ManagedBean
@RequestScoped
public class BeanTipo {

    private int id_tipo=0;
    private String tipo_medicamento;
    private List<Tipo> listaTipo;
    
    private final mantenimientoTipo mantTipo= new mantenimientoTipo();
    
    public BeanTipo() {
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getTipo_medicamento() {
        return tipo_medicamento;
    }

    public void setTipo_medicamento(String tipo_medicamento) {
        this.tipo_medicamento = tipo_medicamento;
    }

    public List<Tipo> getListaTipo() {
        this.listaTipo=new ArrayList<>();
        listaTipo=mantTipo.consultarTodo();
        return listaTipo;
    }

    public void setListaTipo(List<Tipo> listaTipo) {
        this.listaTipo = listaTipo;
    }
    
    public String guardarTipo(){
    Tipo t= new Tipo();
    t.setIdTipo(id_tipo);
    t.setTipoMedicamento(tipo_medicamento);
    int r= mantTipo.guardarTipo(t);
    if(r==1){
    RequestContext.getCurrentInstance().update("growl");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "exito al guardar", "exito"));
        } else {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puedo Guardar"));
        }
    return null;
        
    }
    
    public String consultarID(int id_tipo){
    Tipo lista=mantTipo.consultarByID(id_tipo);
    if(lista!=null){
    this.id_tipo=lista.getIdTipo();
    this.tipo_medicamento=lista.getTipoMedicamento();
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
    
    public void eliminar(int id_tipo){
    int tipo=mantTipo.eliminar(id_tipo);
    if(tipo>0){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Se elimino"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puede eliminar"));
        }
    
    }
    
    public String actualizarTipo(int id_tipo){
    Tipo t=new Tipo();
    t.setIdTipo(id_tipo);
    t.setTipoMedicamento(tipo_medicamento);
    
    boolean r=mantTipo.actualizarTip(id_tipo, t);
    if(r){
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
    
}
