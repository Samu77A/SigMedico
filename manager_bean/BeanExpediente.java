package manager_bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimientoDatos;
import mantenimientos.mantenimientoExpediente;
import org.primefaces.context.RequestContext;
import persistencia.Expediente;
import persistencia.Datos;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@RequestScoped
public class BeanExpediente {

    private int id_expediente = 0;
    private int id_datos;
    private String cod_expediente;
    private Date fecha_creacion;
    private String estado;
    private String id_dato;

    private final mantenimientoExpediente manteExpe = new mantenimientoExpediente();
    private final mantenimientoDatos manteDatos = new mantenimientoDatos();

    private List<Expediente> listExpe;
    private List<Datos> listaDatos;
    private List<Expediente> filtro;

    private Datos datos;

    /**
     * Creates a new instance of BeanExpediente
     */
    public BeanExpediente() {

    }

    public String getId_dato() {
        return id_dato;
    }

    public void setId_dato(String id_dato) {
        this.id_dato = id_dato;
    }

    
    public List<Expediente> getFiltro() {
        this.filtro = new ArrayList<>();
        filtro = manteExpe.consultarTodoExpe();
        return filtro;
    }

    public void setFiltro(List<Expediente> filtro) {
        this.filtro = filtro;
    }

    public List<Datos> getListaDatos() {
        this.listaDatos = new ArrayList<>();
        listaDatos = manteDatos.consultaDatosPaci(3);
        return listaDatos;
    }

    public void setListaDatos(List<Datos> listaDatos) {
        this.listaDatos = listaDatos;
    }

    public List<Expediente> getListExpe() {
        this.listExpe = new ArrayList<>();
        listExpe = manteExpe.consultarTodoExpe();
        return listExpe;
    }

    public void setListExpe(List<Expediente> listExpe) {
        this.listExpe = listExpe;
    }

    public int getId_expediente() {
        return id_expediente;
    }

    public void setId_expediente(int id_expediente) {
        this.id_expediente = id_expediente;
    }

    public int getId_datos() {
        return id_datos;
    }

    public void setId_datos(int id_datos) {
        this.id_datos = id_datos;
    }

    public String getCod_expediente() {
        return cod_expediente;
    }

    public void setCod_expediente(String cod_expediente) {
        this.cod_expediente = cod_expediente;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String insertarExpe() throws ParseException {

        Expediente e = new Expediente();
        Datos d = new Datos();
        SimpleDateFormat f = new SimpleDateFormat("yy-MM-dd");
        Date hoy = new Date();

        d.setIdDatos(id_datos);

        e.setIdExpediente(id_expediente);
        e.setIdDatos(d);
        e.setCodExpediente(cod_expediente);
        e.setFechaCreacion(hoy);
        e.setEstado(estado);

            int resp = manteExpe.insertarExpe(e);

            if (resp > 0) {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Exito", "Expediente Guardado"));
            } else {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_FATAL, "Error", "No se inserto Expediente"));
            }
        return null;
    }

    public String consultarExpeID(int id_expediente) {

        Expediente list = manteExpe.consultarIdExpe(id_expediente);

        if (list != null) {
            this.id_expediente = list.getIdExpediente();
            this.id_datos = list.getIdDatos().getIdDatos();
            this.id_dato = list.getIdDatos().getNombres();
            this.cod_expediente = list.getCodExpediente();
            this.fecha_creacion = list.getFechaCreacion();
            this.estado = list.getEstado();
            RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Exito", "Expediente Mostrando.."));
        }else{
             RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_FATAL, "Error", "Expediente no se pudo mostrar"));
        }
        return null;
    }

    public void eliminarExpe(int id_expediente) {

            int expe = manteExpe.EliminarExpe(id_expediente);

            if (expe == 1) {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Exito", "Expediente Eliminado"));
            } else {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_FATAL, "Error", "Expediente no eliminado"));
            }
    }
    
    public void limpiar(){
        this.id_expediente= 0;
        this.id_datos = 0;
        this.cod_expediente ="";
        this.fecha_creacion = new Date();
        this.estado = "";
        
        RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Exito", "Limpieza de datos"));

    }
    
    public String actualizarExpe(int id_expediente){
        
        Expediente expe = new Expediente();
        Datos dato = new Datos();
        
        dato.setIdDatos(id_datos);
        
        expe.setIdDatos(dato);
        expe.setCodExpediente(cod_expediente);
        expe.setFechaCreacion(fecha_creacion);
        expe.setEstado(estado);
        
       boolean r = manteExpe.actualizarExpe(id_expediente, expe);
       if(r){
           RequestContext.getCurrentInstance().update("grow1");
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
           FacesMessage.SEVERITY_INFO, "Exito", "Expediente Actualizado"));
           return "consultarExpe.xhtml?faces-redirect=true";
           
       }else{
           RequestContext.getCurrentInstance().update("grow1");
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
           FacesMessage.SEVERITY_FATAL, "Error", "Expediente no actualizado"));
           
       }
        return null;
    }

}
