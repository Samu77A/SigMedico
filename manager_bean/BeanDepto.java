
package manager_bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimietoDepto;
import org.primefaces.context.RequestContext;
import persistencia.Depto;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@RequestScoped
public class BeanDepto {
    
    private int id_depto =0;
    private String depto;

    private List<Depto> deptolista;

    /**
     * Creates a new instance of BeanDepto
     */
    public BeanDepto() {

    }
    
    /**
     * @return the deptolista
     */
    private final  mantenimietoDepto d = new mantenimietoDepto();
 
    /**
     * @return the id_depto
     */
    public int getId_depto() {
        return id_depto;
    }

    /**
     * @param id_depto the id_depto to set
     */
    public void setId_depto(int id_depto) {
        this.id_depto = id_depto;
    }

    /**
     * @return the depto
     */
    public String getDepto() {
        return depto;
    }

    /**
     * @param depto the depto to set
     */
    public void setDepto(String depto) {
        this.depto = depto;
    }
    
    public List<Depto> getDeptolista() {  
        
        return d.consultar();
    }

    public void eliminar(int id_depto) {    
        d.eliminar(id_depto);
    }

    public void guardarDatosDepto() {    
        Depto dep = new Depto();
        String advertencia = "";
        dep.setDepto(depto);

        if (d.insertar(dep) == 1) {
            advertencia = "Correcto";
            this.setDepto("");
        } else {
            advertencia = "Insersion incorrecta";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String actualizar(int id_depto) { 
        
        Depto dep = new Depto();
        
        dep.setDepto(depto);
    
        boolean resp = d.ActualizarDepto(id_depto, dep);
        
        if (resp) {

            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos actualizado"));
            return "mostrarDepto.xhtml?faces-redirect=true";

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos NO actualizado"));

        }
        return null;
    }
    
     public String mostrarporid(int id_depto) {


        Depto de = d.porID(id_depto);

        if (d != null) {
            this.id_depto = de.getIdDepto();
            this.depto = de.getDepto();

            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos a mostrar"));

        } else {

            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos no mostrados"));
        }
        return null;
    }
    
}
