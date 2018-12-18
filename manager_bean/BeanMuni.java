package manager_bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimientoMuni;
import mantenimientos.mantenimietoDepto;
import org.primefaces.context.RequestContext;
import persistencia.Depto;
import persistencia.Muni;

/**
 *
 * @author marvin.guzmanUSAM
 */
@ManagedBean
@RequestScoped
public class BeanMuni {

    private int id_muni = 0;
    private String id_depto;
    private String muni;

    private final mantenimientoMuni manteMuni = new mantenimientoMuni();
    private final mantenimietoDepto manteDepto = new mantenimietoDepto();

    private Muni municipio;
    private List<Depto> listaDepto;
    private List<Muni> listaMuni;
    private Depto d;

    public List<Depto> getListaDepto() {
        this.listaDepto = new ArrayList<>();
        listaDepto = manteDepto.consultar();
        return listaDepto;
    }

    public void setListaDepto(List<Depto> listaDepto) {
        this.listaDepto = listaDepto;
    }

    public List<Muni> getListaMuni() {
        this.listaMuni = new ArrayList<>();
        listaMuni = manteMuni.consultTodoMuni();
        return listaMuni;
    }

    public void setListaMuni(List<Muni> listaMuni) {
        this.listaMuni = listaMuni;
    }

    public Depto getD() {
        return d;
    }

    public void setD(Depto d) {
        this.d = d;
    }

    public int getId_muni() {
        return id_muni;
    }

    public void setId_muni(int id_muni) {
        this.id_muni = id_muni;
    }

    public String getId_depto() {
        return id_depto;
    }

    public void setId_depto(String id_depto) {
        this.id_depto = id_depto;
    }

    public String getMuni() {
        return muni;
    }

    public void setMuni(String muni) {
        this.muni = muni;
    }

    /**
     * Creates a new instance of BeanMuni
     */
    public BeanMuni() {

    }

    public String guardarMuni() {
        Depto depTo = new Depto();
        Muni mun = new Muni();
        String adv = "";
        /**
         * *************************
         * parseo de datos *************************
         */
        int Depto = Integer.parseInt(id_depto);

        /**
         * *******************
         * Guardar Objeto de tipo metodo *******************
         */
        depTo.setIdDepto(Depto);

        /**
         * **************
         * Guardo los Datos ********************
         */
        mun.setIdMuni(id_muni);
        mun.setIdDepto(depTo);
        mun.setMuni(muni);

        int vali = manteMuni.guardar(mun);

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

    public String consultarByID(int id_muni) {

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
    }

}
