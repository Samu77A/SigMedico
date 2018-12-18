/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@Embeddable
public class DiagnosticoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_enfermedad")
    private int idEnfermedad;
    @Basic(optional = false)
    @Column(name = "id_consulta")
    private int idConsulta;

    public DiagnosticoPK() {
    }

    public DiagnosticoPK(int idEnfermedad, int idConsulta) {
        this.idEnfermedad = idEnfermedad;
        this.idConsulta = idConsulta;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEnfermedad;
        hash += (int) idConsulta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoPK)) {
            return false;
        }
        DiagnosticoPK other = (DiagnosticoPK) object;
        if (this.idEnfermedad != other.idEnfermedad) {
            return false;
        }
        if (this.idConsulta != other.idConsulta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.DiagnosticoPK[ idEnfermedad=" + idEnfermedad + ", idConsulta=" + idConsulta + " ]";
    }
    
}
