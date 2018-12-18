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
@Table(name = "hospitalizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hospitalizacion.findAll", query = "SELECT h FROM Hospitalizacion h")
    , @NamedQuery(name = "Hospitalizacion.findByIdHospitalizacion", query = "SELECT h FROM Hospitalizacion h WHERE h.idHospitalizacion = :idHospitalizacion")
    , @NamedQuery(name = "Hospitalizacion.findByFechaHoraEntrada", query = "SELECT h FROM Hospitalizacion h WHERE h.fechaHoraEntrada = :fechaHoraEntrada")
    , @NamedQuery(name = "Hospitalizacion.findByFechaHoraSalida", query = "SELECT h FROM Hospitalizacion h WHERE h.fechaHoraSalida = :fechaHoraSalida")
    , @NamedQuery(name = "Hospitalizacion.findByEstadoHospitalizacion", query = "SELECT h FROM Hospitalizacion h WHERE h.estadoHospitalizacion = :estadoHospitalizacion")})
public class Hospitalizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_hospitalizacion")
    private Integer idHospitalizacion;
    @Column(name = "fecha_hora_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEntrada;
    @Column(name = "fecha_hora_salida")
    private String fechaHoraSalida;
    @Basic(optional = false)
    @Column(name = "estado_hospitalizacion")
    private String estadoHospitalizacion;
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    @ManyToOne(optional = false)
    private Consulta idConsulta;

    public Hospitalizacion() {
    }

    public Hospitalizacion(Integer idHospitalizacion) {
        this.idHospitalizacion = idHospitalizacion;
    }

    public Hospitalizacion(Integer idHospitalizacion, String estadoHospitalizacion) {
        this.idHospitalizacion = idHospitalizacion;
        this.estadoHospitalizacion = estadoHospitalizacion;
    }

    public Integer getIdHospitalizacion() {
        return idHospitalizacion;
    }

    public void setIdHospitalizacion(Integer idHospitalizacion) {
        this.idHospitalizacion = idHospitalizacion;
    }

    public Date getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(Date fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public String getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(String fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public String getEstadoHospitalizacion() {
        return estadoHospitalizacion;
    }

    public void setEstadoHospitalizacion(String estadoHospitalizacion) {
        this.estadoHospitalizacion = estadoHospitalizacion;
    }

    public Consulta getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Consulta idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHospitalizacion != null ? idHospitalizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospitalizacion)) {
            return false;
        }
        Hospitalizacion other = (Hospitalizacion) object;
        if ((this.idHospitalizacion == null && other.idHospitalizacion != null) || (this.idHospitalizacion != null && !this.idHospitalizacion.equals(other.idHospitalizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Hospitalizacion[ idHospitalizacion=" + idHospitalizacion + " ]";
    }
    
}
