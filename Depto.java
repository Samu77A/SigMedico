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
@Table(name = "depto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Depto.findAll", query = "SELECT d FROM Depto d")
    , @NamedQuery(name = "Depto.findByIdDepto", query = "SELECT d FROM Depto d WHERE d.idDepto = :idDepto")
    , @NamedQuery(name = "Depto.findByDepto", query = "SELECT d FROM Depto d WHERE d.depto = :depto")})
public class Depto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_depto")
    private Integer idDepto;
    @Basic(optional = false)
    @Column(name = "depto")
    private String depto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepto")
    private Collection<Muni> muniCollection;

    public Depto() {
    }

    public Depto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    public Depto(Integer idDepto, String depto) {
        this.idDepto = idDepto;
        this.depto = depto;
    }

    public Integer getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    @XmlTransient
    public Collection<Muni> getMuniCollection() {
        return muniCollection;
    }

    public void setMuniCollection(Collection<Muni> muniCollection) {
        this.muniCollection = muniCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepto != null ? idDepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Depto)) {
            return false;
        }
        Depto other = (Depto) object;
        if ((this.idDepto == null && other.idDepto != null) || (this.idDepto != null && !this.idDepto.equals(other.idDepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Depto[ idDepto=" + idDepto + " ]";
    }
    
}
