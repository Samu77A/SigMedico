package manager_bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.mantenimientoLogin;
import mantenimientos.mantenimientoRole;
import org.primefaces.context.RequestContext;
import persistencia.Datos;
import persistencia.Role;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    private int id_datos = 0;
    private String id_role;
    private String usuario;
    private String clave;
    private String clave1;
    private String pin;

    private final mantenimientoLogin login = new mantenimientoLogin();
    private final mantenimientoRole rol = new mantenimientoRole();

    private Datos persisDatos;

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

    public String getClave1() {
        return clave1;
    }

    public void setClave1(String clave1) {
        this.clave1 = clave1;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    /**
     * ****************************
     *
     * @param validarIngreso
     *
     * ****************************
     */
    public String validar() {
        LoginBean lo = new LoginBean();
        persisDatos = login.validarD(usuario, clave);

        if (persisDatos != null) {

            if (persisDatos.getClave() != null) {
                Role ro = persisDatos.getIdRole();

                if (ro.getIdRole() == 1) {
                    FacesContext c = FacesContext.getCurrentInstance();

                    /*if (this.usuario.equals(usuario) && this.clave.equals(clave)) {
                        c.getExternalContext().getSessionMap().put("admin", usuario);

                        try {
                            c.getExternalContext().redirect("administrador.xhtml");
                        } catch (IOException ex) {
                            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        c.addMessage(null, new FacesMessage("Problemas en las credenciales"));
                    }*/
                    return "administrador.xhtml?faces-redirect=true";
                } else if (ro.getIdRole() == 2) {
                    return "index.xhtml?faces-redirect=true";
                } else if (ro.getIdRole() == 3) {
                    return "index.xhtml?faces-redirect=true";
                }

            }
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Contraseña", "contraseña Incorrecta"));
        }
        RequestContext.getCurrentInstance().update("grow1");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_FATAL, "Usuario", "usuario Incorrecto"));

        return null;
    }

    public void logout() {
        FacesContext c = FacesContext.getCurrentInstance();
        c.getExternalContext().invalidateSession();
    }

    /**
     * *************************
     *
     * @param validarPinSeguridad
     *
     * ************************
     */
    public String validarPin() {

        persisDatos = login.validarPin(usuario, pin);

        if (persisDatos != null) {

            if (persisDatos.getPin() != null) {

                return "recuperar.xhtml?faces-redirect=true&id_datos=" + persisDatos.getIdDatos();
            }
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Invalido", "Pin"));
        }
        RequestContext.getCurrentInstance().update("grow1");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_FATAL, "Invalido", "Pin"));
        return null;
    }

    /**
     * ***************************
     *
     * @param actualizarContra
     *
     * **************************
     */
    public String actualizarContra() {

        if (clave == clave1) {

            boolean res = login.actualizarcontra(id_datos, clave);
            if (res) {
                return "login.xhtml?faces-redirect=true";
            }
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Invalido", "no se actualizo"));

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_WARN, "Advertencia", "Contraseñas no coinciden"));
        }

        return null;
    }

}
