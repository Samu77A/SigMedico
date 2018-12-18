package manager_bean;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import mantenimientos.mantenimientoDatos;
import mantenimientos.mantenimientoMuni;
import mantenimientos.mantenimientoRole;
import mantenimientos.mantenimientoTurno;
import mantenimientos.mantenimietoDepto;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import persistencia.Datos;
import persistencia.Depto;
import persistencia.Muni;
import persistencia.Role;
import persistencia.Turno;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@ViewScoped
public class BeanDatos implements Serializable {

    private int id_datos = 0;
    private String id_muni;
    private String id_role;
    private String id_turno;
    private String nombres;
    private String apellido;
    private String tel;
    private String dui;
    private String nit;
    private int edad;
    private String direccion;
    private String usuario;
    private String clave;
    private String correo;
    private String pin;
    private String estado;
    private String genero;
    private String responsable;
    private String tel_responsable;
    private String codigo_h;
    private List<Muni> listaMuni;
    private List<Role> listaRole;
    private List<Turno> listaTurno;
    private List<Depto> listaDepto;
    private List<Datos> listaDatos;
    private List<Datos> filtros;
    private boolean skip;
    private String pin1;

    public String getPin1() {
        return pin1;
    }

    public void setPin1(String pin1) {
        this.pin1 = pin1;
    }

    private final mantenimientoDatos manteDato = new mantenimientoDatos();
    private final mantenimientoRole rol = new mantenimientoRole();
    private final mantenimientoMuni consulMuni = new mantenimientoMuni();
    private final mantenimientoTurno consultTurno = new mantenimientoTurno();
    private final mantenimietoDepto consultDepto = new mantenimietoDepto();

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    private Datos persisDatos;

    public void setFiltros(List<Datos> filtros) {
        this.filtros = filtros;
    }

    public List<Datos> getFiltros() {
        this.filtros = new ArrayList<>();
        filtros = manteDato.consultarTodoDatos();
        return filtros;
    }

    public List<Datos> getListaDatos() {
        this.listaDatos = new ArrayList<>();
        listaDatos = manteDato.consultarTodoDatos();
        return listaDatos;
    }

    public void setListaDatos(List<Datos> listaDatos) {
        this.listaDatos = listaDatos;
    }

    public List<Depto> getListaDepto() {
        this.listaDepto = new ArrayList<>();
        Depto depto = new Depto();
        listaDepto = consultDepto.consultar();
        return listaDepto;
    }

    public void setListaDepto(List<Depto> listaDepto) {
        this.listaDepto = listaDepto;
    }

    public Datos getPersisDatos() {
        return persisDatos;
    }

    public void setPersisDatos(Datos persisDatos) {
        this.persisDatos = persisDatos;
    }

    public int getId_datos() {
        return id_datos;
    }

    public void setId_datos(int id_datos) {
        this.id_datos = id_datos;
    }

    public String getId_role() {
        return id_role;
    }

    public void setId_role(String id_role) {
        this.id_role = id_role;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getId_muni() {
        return id_muni;
    }

    public void setId_muni(String id_muni) {
        this.id_muni = id_muni;
    }

    public String getId_turno() {
        return id_turno;
    }

    public void setId_turno(String id_turno) {
        this.id_turno = id_turno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTel_responsable() {
        return tel_responsable;
    }

    public void setTel_responsable(String tel_responsable) {
        this.tel_responsable = tel_responsable;
    }

    public String getCodigo_h() {
        return codigo_h;
    }

    public void setCodigo_h(String codigo_h) {
        this.codigo_h = codigo_h;
    }

    public List<Muni> getListaMuni() {
        this.listaMuni = new ArrayList<>();
        Muni munic = new Muni();
        listaMuni = consulMuni.consultTodoMuni();
        return listaMuni;
    }

    public void setListaMuni(List<Muni> listaMuni) {
        this.listaMuni = listaMuni;
    }

    public List<Role> getListaRole() {
        this.listaRole = new ArrayList<>();
        Role role = new Role();
        listaRole = rol.consultTodo();
        return listaRole;
    }

    public void setListaRole(List<Role> listaRole) {
        this.listaRole = listaRole;
    }

    public List<Turno> getListaTurno() {
        this.listaTurno = new ArrayList<>();
        Turno turno = new Turno();
        listaTurno = consultTurno.consultTodoTurno();

        return listaTurno;
    }

    public void setListaTurno(List<Turno> listaTurno) {
        this.listaTurno = listaTurno;
    }

    /**
     * Creates a new instance of BeanDatos
     */
    public BeanDatos() {

    }

    public String handleKeyEvent() {

        int p = (int) Math.round(Math.random() * 1000000000);

        pin1 = pin + "_" + p + "";

        return pin1;
    }

    public String insertBean() {
        Datos d = new Datos();
        Role r = new Role();
        Turno t = new Turno();
        Muni m = new Muni();

        int ro = Integer.parseInt(id_role);
        r.setIdRole(ro);
        int tu = Integer.parseInt(id_turno);
        t.setIdTurno(tu);
        int mu = Integer.parseInt(id_muni);
        m.setIdMuni(mu);

        System.out.println(id_datos);
        System.out.println(nombres);
        System.out.println(apellido);
        System.out.println(tel);
        System.out.println(r);
        System.out.println(t);
        System.out.println(m);
        System.out.println(dui);
        System.out.println(nit);
        System.out.println(edad);
        System.out.println(direccion);
        System.out.println(usuario);
        System.out.println(clave);
        System.out.println(correo);
        System.out.println(pin1);
        System.out.println("A");
        System.out.println(genero);
        System.out.println(responsable);
        System.out.println(tel_responsable);
        System.out.println(codigo_h);

        d.setIdDatos(id_datos);
        d.setNombres(nombres);
        d.setApellidos(apellido);
        d.setTel(tel);
        d.setIdRole(r);
        d.setIdTurno(t);
        d.setIdMuni(m);
        d.setDui(dui);
        d.setNit(nit);
        d.setEdad(edad);
        d.setDireccion(direccion);
        d.setUsuario(usuario);
        d.setClave(clave);
        d.setCorreo(correo);
        d.setPin(pin1);
        d.setEstado("A");
        d.setGenero(genero);
        d.setResponsable(responsable);
        d.setTelResponsable(tel_responsable);
        d.setCodigoH(codigo_h);

        int user = manteDato.validarUser(usuario);
        int docUI = manteDato.validarDui(dui);

        if (docUI > 0) {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "El Dui ya esta Ingresado en la base de datos"));
            
            }else{
            if (user > 0) {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Error", "El usuario ya esta creado"));
            } else {
                int vali = manteDato.insert(d);

                if (vali == 1) {

                    RequestContext.getCurrentInstance().update("grow1");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "Exito", "Exito al guardar los datos"));

                } else {

                    RequestContext.getCurrentInstance().update("grow1");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_FATAL, "Invalido", "No se pudo Guardar"));

                }
            }
        }
        return null;
    }

    public String consultarIdD(int id_datos) {

        Datos listado = manteDato.consultarIdDato(id_datos);

        if (listado != null) {
            this.id_datos = listado.getIdDatos();
            this.nombres = listado.getNombres();
            this.apellido = listado.getApellidos();
            this.tel = listado.getTel();
            this.dui = listado.getDui();
            this.nit = listado.getNit();
            this.edad = listado.getEdad();
            this.direccion = listado.getDireccion();
            this.usuario = listado.getUsuario();
            this.clave = listado.getClave();
            this.correo = listado.getCorreo();
            this.pin = listado.getPin();
            this.estado = listado.getEstado();
            this.genero = listado.getGenero();
            this.responsable = listado.getResponsable();
            this.tel_responsable = listado.getTelResponsable();
            this.codigo_h = listado.getCodigoH();

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

    public String actualizarDato(int id_datos) {
        Datos dato = new Datos();
        Turno t = new Turno();
        Role r = new Role();
        Muni m = new Muni();

        dato.setNombres(nombres);
        dato.setApellidos(apellido);
        dato.setTel(tel);
        dato.setDui(dui);
        dato.setNit(nit);
        dato.setEdad(edad);
        dato.setDireccion(direccion);
        dato.setUsuario(usuario);
        dato.setClave(clave);
        dato.setCorreo(correo);
        dato.setPin(pin);
        dato.setEstado(estado);
        dato.setGenero(genero);
        dato.setResponsable(responsable);
        dato.setTelResponsable(tel_responsable);
        dato.setCodigoH(codigo_h);

        /**
         * ***********************
         * @param parseo de datos ***********************
         */
        int roles = Integer.parseInt(id_role);
        int municipio = Integer.parseInt(id_muni);
        int turnos = Integer.parseInt(id_turno);

        /**
         * *******************
         * @param guardo en el objeto de tipo metodo ********************
         */
        r.setIdRole(roles);
        m.setIdMuni(municipio);
        t.setIdTurno(turnos);

        /**
         * ****************
         * @param guardoDatos ***************
         */
        dato.setIdRole(r);
        dato.setIdMuni(m);
        dato.setIdTurno(t);

        boolean vali = manteDato.actualizarDatos(id_datos, dato);

        if (vali) {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Datos Actualizados"));
            return "consultarUsuario.xhtml?faces-redirect=true";
        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Error", "Datos No Actualizados"));
        }
        return null;
    }

    public void limpiar() {

        this.nombres = "";
        this.apellido = "";
        this.tel = "";
        this.dui = "";
        this.nit = "";
        this.edad = 0;
        this.direccion = "";
        this.usuario = "";
        this.clave = "";
        this.correo = "";
        this.pin = "";
        this.estado = "";
        this.genero = "";
        this.responsable = "";
        this.tel_responsable = "";
        this.codigo_h = "";

        RequestContext.getCurrentInstance().update("grow1");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_WARN, "Advertencia", "Limpieza de datos"));
    }

    public String eliminarD(int id_datos) {

        try {
            int r = manteDato.eliminarDatos(id_datos);

            if (r == 1) {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Exito", "Datos Eliminados"));
            } else {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_FATAL, "Error", "Datos no Eliminados"));
            }

        } catch (Exception e) {
            System.out.println("Metodo eliminar " + e);
        }
        return null;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //restablecer en caso de que el usuario vuelva
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
}
