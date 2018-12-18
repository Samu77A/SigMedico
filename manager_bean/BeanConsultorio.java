package manager_bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimientoConsult;
import persistencia.Areas;
import persistencia.Consultorio;
import mantenimientos.mantenimientoAreas;
import org.primefaces.context.RequestContext;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@RequestScoped
public class BeanConsultorio {

    private int id_consultorio = 0;
    private int id_areas;
    private String area;
    private String consultorio;
    private List<Consultorio> listaC;
    private List<Areas> listaA;

    private final mantenimientoConsult mC = new mantenimientoConsult();
    private final mantenimientoAreas mA = new mantenimientoAreas();

    private Consultorio persisCon;

    /**
     * Creates a new instance of BeanConsultorio
     */
    public BeanConsultorio() {
    }

    public List<Consultorio> getListaC() {
        this.listaC = new ArrayList<>();
        listaC = mC.consultarTodo();
        return listaC;
    }

    public void setListaC(List<Consultorio> listaC) {
        this.listaC = listaC;
    }

    public List<Areas> getListaA() {
        this.listaA = new ArrayList<>();
        listaA =  mA.consultar();
        return listaA;
    }

    public void setListaA(List<Areas> listaA) {
        this.listaA = listaA;
    }

    public int getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(int id_consultorio) {
        this.id_consultorio = id_consultorio;
    }

    public int getId_areas() {
        return id_areas;
    }

    public void setId_areas(int id_areas) {
        this.id_areas = id_areas;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String insertConsult() {
        Consultorio c = new Consultorio();
        Areas a = new Areas();

        a.setIdAreas(id_areas);

        c.setIdAreas(a);
        c.setConsultorio(consultorio);

        int r = mC.insertarC(c);

        if (r > 0) {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Exito al guardar Consultorio"));
        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "No se pugo guardar Consultorio"));
        }

        return null;
    }

    public String consultarCId(int id_consultorio) {

        Consultorio c = mC.consultarId(id_consultorio);
        if (c != null) {
            this.id_consultorio = c.getIdConsultorio();
            this.id_areas = c.getIdAreas().getIdAreas();
            this.area = c.getIdAreas().getTipoAreas();
            this.consultorio = c.getConsultorio();
            
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_INFO, "Exito", "Mostrando consultorio por Id"));
        }else{
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_ERROR, "Error", "No se puede mostrar los datos"));
        }

        return null;
    }
    
    public String eliminarC(int id_consultorio){
     
        int r = mC.borrarC(id_consultorio);
        
        if(r>0){
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_INFO, "Exito", "Se elimino el consultorio"));
            
        }else{
             RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_ERROR, "Error", "No se puede Borrar"));
        }
        return null;
    }
    
    public String actualizarC(int id_consultorio){
        Consultorio c = new Consultorio();
        Areas a = new Areas();
        
        a.setIdAreas(id_areas);
        
        c.setIdAreas(a);
        c.setConsultorio(consultorio);
        
        boolean r = mC.actualizarC(id_consultorio, c);
        
        if(r){
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_INFO, "Exito", "Se actualizo el consultorio"));
            return "consultarCons.xhtml?faces-redirect=true";
        }else{
             RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_ERROR, "Error", "No se puede Actualizar el consultorio"));
        }
        
        return null;
    }

}
