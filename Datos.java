/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@Entity
@Table(name = "datos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datos.findAll", query = "SELECT d FROM Datos d")
    ,@NamedQuery(name = "Datos.todo", query = "SELECT d FROM Datos d  WHERE d.usuario = :usuario  AND d.clave = :clave")
    ,@NamedQuery(name = "Datos.todoClave", query = "SELECT d FROM Datos d  WHERE d.usuario = :usuario  AND d.pin = :pin")
    ,@NamedQuery(name = "Datos.finByTipo", query = "SELECT d.idRole FROM Datos d WHERE d.usuario = :usuario")
    , @NamedQuery(name = "Datos.findByIdDatos", query = "SELECT d FROM Datos d WHERE d.idDatos = :idDatos")
    , @NamedQuery(name = "Datos.findByNombres", query = "SELECT d FROM Datos d WHERE d.nombres = :nombres")
    , @NamedQuery(name = "Datos.findByApellidos", query = "SELECT d FROM Datos d WHERE d.apellidos = :apellidos")
    , @NamedQuery(name = "Datos.findByTel", query = "SELECT d FROM Datos d WHERE d.tel = :tel")
    , @NamedQuery(name = "Datos.findByDui", query = "SELECT d FROM Datos d WHERE d.dui = :dui")
    , @NamedQuery(name = "Datos.findByNit", query = "SELECT d FROM Datos d WHERE d.nit = :nit")
    , @NamedQuery(name = "Datos.findByEdad", query = "SELECT d FROM Datos d WHERE d.edad = :edad")
    , @NamedQuery(name = "Datos.findByDireccion", query = "SELECT d FROM Datos d WHERE d.direccion = :direccion")
    , @NamedQuery(name = "Datos.findByUsuario", query = "SELECT d FROM Datos d WHERE d.usuario = :usuario")
    , @NamedQuery(name = "Datos.findByClave", query = "SELECT d FROM Datos d WHERE d.clave = :clave")
    , @NamedQuery(name = "Datos.findByCorreo", query = "SELECT d FROM Datos d WHERE d.correo = :correo")
    , @NamedQuery(name = "Datos.findByPin", query = "SELECT d FROM Datos d WHERE d.pin = :pin")
    , @NamedQuery(name = "Datos.findByEstado", query = "SELECT d FROM Datos d WHERE d.estado = :estado")
    , @NamedQuery(name = "Datos.findByGenero", query = "SELECT d FROM Datos d WHERE d.genero = :genero")
    , @NamedQuery(name = "Datos.findByResponsable", query = "SELECT d FROM Datos d WHERE d.responsable = :responsable")
    , @NamedQuery(name = "Datos.findByTelResponsable", query = "SELECT d FROM Datos d WHERE d.telResponsable = :telResponsable")
    , @NamedQuery(name = "Datos.findByCodigoH", query = "SELECT d FROM Datos d WHERE d.codigoH = :codigoH")})
public class Datos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos")
    private Integer idDatos;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "tel")
    private String tel;
    @Column(name = "dui")
    private String dui;
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @Column(name = "edad")
    private int edad;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "pin")
    private String pin;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    @Column(name = "responsable")
    private String responsable;
    @Column(name = "tel_responsable")
    private String telResponsable;
    @Basic(optional = false)
    @Column(name = "codigo_h")
    private String codigoH;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMedico")
    private Collection<Citas> citasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private Collection<Citas> citasCollection1;
    @OneToMany(mappedBy = "idDatos")
    private Collection<Expediente> expedienteCollection;
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    @ManyToOne(optional = false)
    private Role idRole;
    @JoinColumn(name = "id_turno", referencedColumnName = "id_turno")
    @ManyToOne
    private Turno idTurno;
    @JoinColumn(name = "id_muni", referencedColumnName = "id_muni")
    @ManyToOne(optional = false)
    private Muni idMuni;

    public Datos() {
    }

    public Datos(Integer idDatos) {
        this.idDatos = idDatos;
    }

    public Datos(Integer idDatos, String nombres, String apellidos, String tel, int edad, String direccion, String usuario, String clave, String pin, String estado, String genero, String codigoH) {
        this.idDatos = idDatos;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tel = tel;
        this.edad = edad;
        this.direccion = direccion;
        this.usuario = usuario;
        this.clave = clave;
        this.pin = pin;
        this.estado = estado;
        this.genero = genero;
        this.codigoH = codigoH;
    }

    public Integer getIdDatos() {
        return idDatos;
    }

    public void setIdDatos(Integer idDatos) {
        this.idDatos = idDatos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
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

    public String getTelResponsable() {
        return telResponsable;
    }

    public void setTelResponsable(String telResponsable) {
        this.telResponsable = telResponsable;
    }

    public String getCodigoH() {
        return codigoH;
    }

    public void setCodigoH(String codigoH) {
        this.codigoH = codigoH;
    }

    @XmlTransient
    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
    }

    @XmlTransient
    public Collection<Citas> getCitasCollection1() {
        return citasCollection1;
    }

    public void setCitasCollection1(Collection<Citas> citasCollection1) {
        this.citasCollection1 = citasCollection1;
    }

    @XmlTransient
    public Collection<Expediente> getExpedienteCollection() {
        return expedienteCollection;
    }

    public void setExpedienteCollection(Collection<Expediente> expedienteCollection) {
        this.expedienteCollection = expedienteCollection;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    public Turno getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Turno idTurno) {
        this.idTurno = idTurno;
    }

    public Muni getIdMuni() {
        return idMuni;
    }

    public void setIdMuni(Muni idMuni) {
        this.idMuni = idMuni;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatos != null ? idDatos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datos)) {
            return false;
        }
        Datos other = (Datos) object;
        if ((this.idDatos == null && other.idDatos != null) || (this.idDatos != null && !this.idDatos.equals(other.idDatos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Datos[ idDatos=" + idDatos + " ]";
    }
    
}
