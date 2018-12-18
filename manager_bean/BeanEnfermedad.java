package manager_bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimieto_tipo_enfermedad;
import mantenimientos.mantenimientoEnfermedad;
import org.primefaces.context.RequestContext;
import persistencia.TipoEnfermedad;
import persistencia.Enfermedad;

@ManagedBean
@RequestScoped
public class BeanEnfermedad {

    private int id_enfermedad;
    private int id_tipo_enfermedad;
    private String enfermedad;

    private final mantenimientos.mantenimientoEnfermedad en = new mantenimientoEnfermedad();
    private final mantenimientos.mantenimieto_tipo_enfermedad ti = new mantenimieto_tipo_enfermedad();

    private Enfermedad enfermedad1;
    private List<TipoEnfermedad> listatipoEnfermedads;
    private List<Enfermedad> listeEnfermedads;
    private TipoEnfermedad d;

    public Enfermedad getEnfermedad1() {
        return enfermedad1;
    }

    public void setEnfermedad1(Enfermedad enfermedad1) {
        this.enfermedad1 = enfermedad1;
    }

    public List<TipoEnfermedad> getListatipoEnfermedads() {
        this.listatipoEnfermedads = new ArrayList<>();
        listatipoEnfermedads = ti.consultar();
        return listatipoEnfermedads;
    }

    public void setListatipoEnfermedads(List<TipoEnfermedad> listatipoEnfermedads) {
        this.listatipoEnfermedads = listatipoEnfermedads;
    }

    public List<Enfermedad> getListeEnfermedads() {
        this.listeEnfermedads = new ArrayList<>();
        listeEnfermedads = en.consultar();
        return listeEnfermedads;
    }

    public void setListeEnfermedads(List<Enfermedad> listeEnfermedads) {
        this.listeEnfermedads = listeEnfermedads;
    }
///////////////////////////////////////////

    public int getId_enfermedad() {
        return id_enfermedad;
    }

    public void setId_enfermedad(int id_enfermedad) {
        this.id_enfermedad = id_enfermedad;
    }

    public int getId_tipo_enfermedad() {
        return id_tipo_enfermedad;
    }

    public void setId_tipo_enfermedad(int id_tipo_enfermedad) {
        this.id_tipo_enfermedad = id_tipo_enfermedad;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public BeanEnfermedad() {

    }

    public String guardar() {
        Enfermedad areas = new Enfermedad();
        TipoEnfermedad hosp = new TipoEnfermedad();
        String adv = "";

        hosp.setIdTipoEnfermedad(id_tipo_enfermedad);

        areas.setIdTipoEnfermedad(hosp);

        areas.setEnfermedad(enfermedad);

        int vali = en.insertar(areas);

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

    public String actualizar1(int id_enfermedad) {
        Enfermedad area = new Enfermedad();
        TipoEnfermedad hosp = new TipoEnfermedad();

        area.setEnfermedad(enfermedad);
        System.out.println(enfermedad);
        
        hosp.setIdTipoEnfermedad(id_tipo_enfermedad);
        System.out.println(id_tipo_enfermedad);
        
        area.setIdTipoEnfermedad(hosp);
        System.out.println(id_tipo_enfermedad);
        
        area.setIdEnfermedad(10);
        System.out.println(id_enfermedad);
        
//            hosp.setIdTipoEnfermedad(id_tipo_enfermedad);
//
//        area.setIdTipoEnfermedad(hosp);
//
//        area.setEnfermedad(enfermedad);

        boolean resp = en.actualizar(id_enfermedad, area);

        if (resp) {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos actualizado"));
            return "consultar_enfermedad.xhtml?faces-redirect=true";

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos NO actualizado"));

        }
        return null;
    }

    public String consultarByID(int id_enfermedad) {

        Enfermedad lista = en.porID(id_enfermedad);

        if (lista != null) {
            this.id_enfermedad = lista.getIdEnfermedad();
            this.enfermedad = lista.getEnfermedad();

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

    public void eliminar2(int id_enfermedad) {
        mantenimientoEnfermedad man = new mantenimientoEnfermedad();
        int r = man.eliminar(id_enfermedad);
        
        if(r >0){
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos eliminados"));
        }else{
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Error", "Error al eliminar los datos..."));
        }
    }

}
