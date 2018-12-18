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
@Table(name = "enfermedad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enfermedad.findAll", query = "SELECT e FROM Enfermedad e")
    , @NamedQuery(name = "Enfermedad.findByIdEnfermedad", query = "SELECT e FROM Enfermedad e WHERE e.idEnfermedad = :idEnfermedad")
    , @NamedQuery(name = "Enfermedad.findByEnfermedad", query = "SELECT e FROM Enfermedad e WHERE e.enfermedad = :enfermedad")})
public class Enfermedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_enfermedad")
    private Integer idEnfermedad;
    @Basic(optional = false)
    @Column(name = "enfermedad")
    private String enfermedad;
    @JoinColumn(name = "id_tipo_enfermedad", referencedColumnName = "id_tipo_enfermedad")
    @ManyToOne(optional = false)
    private TipoEnfermedad idTipoEnfermedad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enfermedad")
    private Collection<Diagnostico> diagnosticoCollection;

    public Enfermedad() {
    }

    public Enfermedad(Integer idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public Enfermedad(Integer idEnfermedad, String enfermedad) {
        this.idEnfermedad = idEnfermedad;
        this.enfermedad = enfermedad;
    }

    public Integer getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(Integer idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public TipoEnfermedad getIdTipoEnfermedad() {
        return idTipoEnfermedad;
    }

    public void setIdTipoEnfermedad(TipoEnfermedad idTipoEnfermedad) {
        this.idTipoEnfermedad = idTipoEnfermedad;
    }

    @XmlTransient
    public Collection<Diagnostico> getDiagnosticoCollection() {
        return diagnosticoCollection;
    }

    public void setDiagnosticoCollection(Collection<Diagnostico> diagnosticoCollection) {
        this.diagnosticoCollection = diagnosticoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnfermedad != null ? idEnfermedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enfermedad)) {
            return false;
        }
        Enfermedad other = (Enfermedad) object;
        if ((this.idEnfermedad == null && other.idEnfermedad != null) || (this.idEnfermedad != null && !this.idEnfermedad.equals(other.idEnfermedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Enfermedad[ idEnfermedad=" + idEnfermedad + " ]";
    }
    
}
