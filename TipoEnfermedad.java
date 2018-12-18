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
@Table(name = "tipo_enfermedad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEnfermedad.findAll", query = "SELECT t FROM TipoEnfermedad t")
    , @NamedQuery(name = "TipoEnfermedad.findByIdTipoEnfermedad", query = "SELECT t FROM TipoEnfermedad t WHERE t.idTipoEnfermedad = :idTipoEnfermedad")
    , @NamedQuery(name = "TipoEnfermedad.findByTipoEnfermedad", query = "SELECT t FROM TipoEnfermedad t WHERE t.tipoEnfermedad = :tipoEnfermedad")})
public class TipoEnfermedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_enfermedad")
    private Integer idTipoEnfermedad;
    @Basic(optional = false)
    @Column(name = "tipo_enfermedad")
    private String tipoEnfermedad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEnfermedad")
    private Collection<Enfermedad> enfermedadCollection;

    public TipoEnfermedad() {
    }

    public TipoEnfermedad(Integer idTipoEnfermedad) {
        this.idTipoEnfermedad = idTipoEnfermedad;
    }

    public TipoEnfermedad(Integer idTipoEnfermedad, String tipoEnfermedad) {
        this.idTipoEnfermedad = idTipoEnfermedad;
        this.tipoEnfermedad = tipoEnfermedad;
    }

    public Integer getIdTipoEnfermedad() {
        return idTipoEnfermedad;
    }

    public void setIdTipoEnfermedad(Integer idTipoEnfermedad) {
        this.idTipoEnfermedad = idTipoEnfermedad;
    }

    public String getTipoEnfermedad() {
        return tipoEnfermedad;
    }

    public void setTipoEnfermedad(String tipoEnfermedad) {
        this.tipoEnfermedad = tipoEnfermedad;
    }

    @XmlTransient
    public Collection<Enfermedad> getEnfermedadCollection() {
        return enfermedadCollection;
    }

    public void setEnfermedadCollection(Collection<Enfermedad> enfermedadCollection) {
        this.enfermedadCollection = enfermedadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEnfermedad != null ? idTipoEnfermedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEnfermedad)) {
            return false;
        }
        TipoEnfermedad other = (TipoEnfermedad) object;
        if ((this.idTipoEnfermedad == null && other.idTipoEnfermedad != null) || (this.idTipoEnfermedad != null && !this.idTipoEnfermedad.equals(other.idTipoEnfermedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TipoEnfermedad[ idTipoEnfermedad=" + idTipoEnfermedad + " ]";
    }
    
}
