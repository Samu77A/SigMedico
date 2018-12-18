package manager_bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimientoAreas;
import mantenimientos.mantenimientoHospital;
import org.primefaces.context.RequestContext;

import persistencia.Hospital;
import persistencia.Areas;

/**
 *
 * @author marvin.guzmanUSAM
 */
@ManagedBean
@RequestScoped
public class BeanAreas {

    private int id_areas = 0;
    private int id_hospital;
    private String tipo_areas;

    private final mantenimientos.mantenimientoHospital ho = new mantenimientoHospital();
    private final mantenimientos.mantenimientoAreas ar = new mantenimientoAreas();

    private Areas areas;
    private List<Areas> listarea;
    private List<Hospital> listhospital;
    private Hospital d;

    public int getId_areas() {
        return id_areas;
    }

    public void setId_areas(int id_areas) {
        this.id_areas = id_areas;
    }

    public int getId_hospital() {
        return id_hospital;
    }

    public void setId_hospital(int id_hospital) {
        this.id_hospital = id_hospital;
    }

    public String getTipo_areas() {
        return tipo_areas;
    }

    public void setTipo_areas(String tipo_areas) {
        this.tipo_areas = tipo_areas;
    }

    public Areas getAreas() {
        return areas;
    }

    public void setAreas(Areas areas) {
        this.areas = areas;
    }

    public List<Areas> getListarea() {
        this.listarea = new ArrayList<>();
        listarea = ar.consultar();
        return listarea;

    }

    public void setListarea(List<Areas> listarea) {
        this.listarea = listarea;
    }

    public List<Hospital> getListhospital() {
        this.listhospital = new ArrayList<>();
        Hospital host = new Hospital();
        listhospital = ho.consultarTodo();
        return listhospital;
    }

    public void setListhospital(List<Hospital> listhospital) {
        this.listhospital = listhospital;
    }

    /**
     * Creates a new instance of BeanMuni
     */
    public BeanAreas() {

    }

    public String guardarArea() {
        Areas areas = new Areas();
        Hospital hosp = new Hospital();
        String adv = "";

        /**
         * *******************
         * Guardar Objeto de tipo metodo *******************
         */
        hosp.setIdHospital(id_hospital);

        /**
         * **************
         * Guardo los Datos ********************
         */
        areas.setIdHospital(hosp);
        areas.setTipoAreas(tipo_areas);

        int vali = ar.insertar(areas);

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

    /*public String consultarByID(int id_muni) {

        Muni lista = manteMuni.consultarIdDato(id_muni);

        if (lista != null) {
            this.id_muni = lista.getIdMuni();
            this.muni = lista.getMuni();

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

    public void eliminarMuni(int id_muni) {

        int r = manteMuni.eliminar(id_muni);

        if (r > 0) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Se elimino"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "FALLO", "No se puede eliminar"));
        }

    }

    public String actualizarMuni(int id_muni) {
        Depto de = new Depto();
        Muni mu = new Muni();

        mu.setMuni(muni);
        int id_dep = Integer.parseInt(id_depto);
        de.setIdDepto(id_dep);
        mu.setIdDepto(de);

        boolean resp = manteMuni.actualizarMuni(id_muni, mu);

        if (resp) {

            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos actualizado"));
            return "consultarMuni.xhtml?faces-redirect=true";

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos NO actualizado"));

        }
        return null;
    }*/
    public String actualizarareas(int id_areas) {
        Hospital h = new Hospital();
        Areas a = new Areas();

        a.setTipoAreas(tipo_areas);
        h.setIdHospital(id_hospital);
        a.setIdHospital(h);

        boolean resp = ar.actualizar(id_areas, a);

        if (resp) {

            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos actualizado"));
            return "consultarArea.xhtml?faces-redirect=true";

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Datos NO actualizado"));

        }
        return null;
    }

    public String consultarByID(int id_areas) {

        Areas lista = ar.porID(id_areas);

        if (lista != null) {
            this.id_areas = lista.getIdAreas();
            this.tipo_areas = lista.getTipoAreas();

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
      public void eliminar2(int id_areas) {
        mantenimientoAreas man = new mantenimientoAreas();
        man.eliminar(id_areas);
    }

}
