
package manager_bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimientoTurno;
import org.primefaces.context.RequestContext;
import persistencia.Turno;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@RequestScoped
public class BeanTurno {

    private int id_turno =0;
    private String tipo_turno;
    private Date desde;
    private Date hasta;
    
    private List<Turno> listaTurno;
    
    private final mantenimientoTurno mTurno = new mantenimientoTurno(); 
    
    public BeanTurno() {
    }

    public List<Turno> getListaTurno() {
        this.listaTurno = new ArrayList<>();
        listaTurno = mTurno.consultTodoTurno();
        return listaTurno;
    }

    public void setListaTurno(List<Turno> listaTurno) {
        this.listaTurno = listaTurno;
    }

    
    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public String getTipo_turno() {
        return tipo_turno;
    }

    public void setTipo_turno(String tipo_turno) {
        this.tipo_turno = tipo_turno;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
    
    public String guardarTurno(){
        Turno t = new Turno();
        t.setTipoTurno(tipo_turno);
        
        t.setDesde(desde);
        t.setHasta(hasta);
        
        int r = mTurno.insertarTurno(t);
        
        if(r > 0){
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Exito al guardar Turno"));
        }else{
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar Turno"));
        }
        return null;
    }
    
    public String consultarIdTurno(int id_turno){
        
        Turno lista = mTurno.consultarIdTurno(id_turno);
        
        if(lista != null){
            this.id_turno = lista.getIdTurno();
            this.tipo_turno = lista.getTipoTurno();
            this.desde = lista.getDesde();
            this.hasta = lista.getHasta();
            
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Exito al consultar Turno"));
        }else{
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Error al consultar Turno"));
        }
        
        return null;
    }
    
    public String actualizarTurno(int id_turno){
        
        Turno t = new Turno();
        
        t.setTipoTurno(tipo_turno);
        t.setDesde(desde);
        t.setHasta(hasta);
        
        boolean r = mTurno.actualizarTurno(id_turno, t);
        
        if(r){
             RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Turno Actualizado"));
            return "consultarTurno?faces-redirect=true";
        }else{
             RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Error al actualizar turno"));
        }
        
        return null;
    }
    
    public String eliminarTurno(int id_turno){
        
        int r = mTurno.eliminarTurno(id_turno);
        
        if(r > 0){
             RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Turno Eliminado"));
        }else{
             RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar turno"));
        }
        return null;
    }
    
    public void limpiar() throws ParseException{
        
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        this.tipo_turno = "";
        this.desde = f.parse("00:00:00");
        this.hasta = f.parse("00:00:00");
        
        RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Limpieza en progreso..."));
    }
    
}
