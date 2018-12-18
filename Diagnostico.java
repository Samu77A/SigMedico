/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@Entity
@Table(name = "diagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnostico.findAll", query = "SELECT d FROM Diagnostico d")
    , @NamedQuery(name = "Diagnostico.findByIdEnfermedad", query = "SELECT d FROM Diagnostico d WHERE d.diagnosticoPK.idEnfermedad = :idEnfermedad")
    , @NamedQuery(name = "Diagnostico.findByIdConsulta", query = "SELECT d FROM Diagnostico d WHERE d.diagnosticoPK.idConsulta = :idConsulta")
    , @NamedQuery(name = "Diagnostico.findByObservaciones", query = "SELECT d FROM Diagnostico d WHERE d.observaciones = :observaciones")})
public class Diagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiagnosticoPK diagnosticoPK;
    @Basic(optional = false)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Consulta consulta;
    @JoinColumn(name = "id_enfermedad", referencedColumnName = "id_enfermedad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Enfermedad enfermedad;

    public Diagnostico() {
    }

    public Diagnostico(DiagnosticoPK diagnosticoPK) {
        this.diagnosticoPK = diagnosticoPK;
    }

    public Diagnostico(DiagnosticoPK diagnosticoPK, String observaciones) {
        this.diagnosticoPK = diagnosticoPK;
        this.observaciones = observaciones;
    }

    public Diagnostico(int idEnfermedad, int idConsulta) {
        this.diagnosticoPK = new DiagnosticoPK(idEnfermedad, idConsulta);
    }

    public DiagnosticoPK getDiagnosticoPK() {
        return diagnosticoPK;
    }

    public void setDiagnosticoPK(DiagnosticoPK diagnosticoPK) {
        this.diagnosticoPK = diagnosticoPK;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diagnosticoPK != null ? diagnosticoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diagnostico)) {
            return false;
        }
        Diagnostico other = (Diagnostico) object;
        if ((this.diagnosticoPK == null && other.diagnosticoPK != null) || (this.diagnosticoPK != null && !this.diagnosticoPK.equals(other.diagnosticoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Diagnostico[ diagnosticoPK=" + diagnosticoPK + " ]";
    }
    
}
