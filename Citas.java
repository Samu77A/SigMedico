/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@Entity
@Table(name = "citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c")
    , @NamedQuery(name = "Citas.findByIdCitas", query = "SELECT c FROM Citas c WHERE c.idCitas = :idCitas")
    , @NamedQuery(name = "Citas.findByTipoCita", query = "SELECT c FROM Citas c WHERE c.tipoCita = :tipoCita")
    , @NamedQuery(name = "Citas.findByFechaInicio", query = "SELECT c FROM Citas c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Citas.findByHoraInicio", query = "SELECT c FROM Citas c WHERE c.horaInicio = :horaInicio")
    , @NamedQuery(name = "Citas.findByEstadoCita", query = "SELECT c FROM Citas c WHERE c.estadoCita = :estadoCita")
    , @NamedQuery(name = "Citas.findByInconveniente", query = "SELECT c FROM Citas c WHERE c.inconveniente = :inconveniente")
    , @NamedQuery(name = "Citas.findByAgregado", query = "SELECT c FROM Citas c WHERE c.agregado = :agregado")})
public class Citas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_citas")
    private Integer idCitas;
    @Basic(optional = false)
    @Column(name = "tipo_cita")
    private String tipoCita;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @Column(name = "estado_cita")
    private String estadoCita;
    @Column(name = "inconveniente")
    private String inconveniente;
    @Column(name = "agregado")
    private String agregado;
    @JoinColumn(name = "id_consultorio", referencedColumnName = "id_consultorio")
    @ManyToOne(optional = false)
    private Consultorio idConsultorio;
    @JoinColumn(name = "id_medico", referencedColumnName = "id_datos")
    @ManyToOne(optional = false)
    private Datos idMedico;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_datos")
    @ManyToOne(optional = false)
    private Datos idPaciente;

    public Citas() {
    }

    public Citas(Integer idCitas) {
        this.idCitas = idCitas;
    }

    public Citas(Integer idCitas, String tipoCita, Date fechaInicio, Date horaInicio, String estadoCita) {
        this.idCitas = idCitas;
        this.tipoCita = tipoCita;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.estadoCita = estadoCita;
    }

    public Integer getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(Integer idCitas) {
        this.idCitas = idCitas;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public String getInconveniente() {
        return inconveniente;
    }

    public void setInconveniente(String inconveniente) {
        this.inconveniente = inconveniente;
    }

    public String getAgregado() {
        return agregado;
    }

    public void setAgregado(String agregado) {
        this.agregado = agregado;
    }

    public Consultorio getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(Consultorio idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public Datos getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Datos idMedico) {
        this.idMedico = idMedico;
    }

    public Datos getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Datos idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCitas != null ? idCitas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.idCitas == null && other.idCitas != null) || (this.idCitas != null && !this.idCitas.equals(other.idCitas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Citas[ idCitas=" + idCitas + " ]";
    }
    
}
