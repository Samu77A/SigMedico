
package manager_bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimietoDepto;
import mantenimientos.mantenimieto_tipo_enfermedad;
import org.primefaces.context.RequestContext;
import persistencia.TipoEnfermedad;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@RequestScoped
public class Beantipo_emfermedad {
    
    private int id_tipo_enfermedad =0;
    private String tipo_emfermedad;

    private List<TipoEnfermedad> listatipo;

    /**
     * Creates a new instance of BeanDepto
     */
    public Beantipo_emfermedad() {

    }
    
    /**
     * @return the deptolista
     */
    private final mantenimientos.mantenimieto_tipo_enfermedad d = new mantenimientos.mantenimieto_tipo_enfermedad();
 
  

    public int getId_tipo_enfermedad() {
        return id_tipo_enfermedad;
    }

    public void setId_tipo_enfermedad(int id_tipo_enfermedad) {
        this.id_tipo_enfermedad = id_tipo_enfermedad;
    }

    public List<TipoEnfermedad> getListatipo() {
        return listatipo;
    }

    /**
     * @return the id_depto
     */
    public void setListatipo(List<TipoEnfermedad> listatipo) {  
        this.listatipo = listatipo;
    }

    public String getTipo_emfermedad() {
        return tipo_emfermedad;
    }

    public void setTipo_emfermedad(String tipo_emfermedad) {
        this.tipo_emfermedad = tipo_emfermedad;
    }

    
    
    public List<TipoEnfermedad> getDeptolista() {  
        
        return d.consultar();
    }

//    public void eliminar(int id_depto) {    
//        d.(id_depto);
//    }
//
    public void guardarDatos() {    
         TipoEnfermedad ma = new TipoEnfermedad();
        String advertencia = "";
        ma.setTipoEnfermedad(tipo_emfermedad);

        if (d.insertar(ma) == 1) {
            advertencia = "Correcto";
            this.setTipo_emfermedad("");
        } else {
            advertencia = "Insersion incorrecta";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
//    
//    public String actualizar(int id_depto) { 
//        
//        Depto dep = new Depto();
//        
//        dep.setDepto(depto);
//    
//        boolean resp = d.ActualizarDepto(id_depto, dep);
//        
//        if (resp) {
//
//            RequestContext.getCurrentInstance().update("grow1");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
//                    FacesMessage.SEVERITY_INFO, "Exito", "Datos actualizado"));
//            return "mostrarDepto.xhtml?faces-redirect=true";
//
//        } else {
//            RequestContext.getCurrentInstance().update("grow1");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
//                    FacesMessage.SEVERITY_ERROR, "Error", "Datos NO actualizado"));
//
//        }
//        return null;
//    }
    //////////////////////////////////////////
//    
     public String mostrarporid(int id_tipo_nfermedad) {


        TipoEnfermedad de = d.porID(id_tipo_nfermedad);

        if (d != null) {
            this.id_tipo_enfermedad = de.getIdTipoEnfermedad();
            this.tipo_emfermedad = de.getTipoEnfermedad();

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
     /////////////////////////////////////////////////////////
      public void eliminar(int id_tipo_enfermedad) {
        mantenimieto_tipo_enfermedad man = new mantenimieto_tipo_enfermedad();
        int r = man.eliminar(id_tipo_enfermedad);
        
        if(r>0){
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos a Eliminados"));
        }else{
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos no eliminados"));
        }
        
    }
///////////////////////////////////////////////////////////
     public String actualizar(int id_tipo_enfermedad) {
       
        TipoEnfermedad a = new TipoEnfermedad();

        a.setIdTipoEnfermedad(this.id_tipo_enfermedad);
       a.setTipoEnfermedad(tipo_emfermedad);

        boolean resp = d.Actualizar(id_tipo_enfermedad, a);

        if (resp) {

            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos actualizado"));
            return "mostrar_tipo_emfermedad.xhtml?faces-redirect=true";

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos NO actualizado"));

        }
        return null;
    }
    
    
}
