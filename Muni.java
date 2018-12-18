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
@Table(name = "muni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muni.findAll", query = "SELECT m FROM Muni m")
    , @NamedQuery(name = "Muni.findByIdMuni", query = "SELECT m FROM Muni m WHERE m.idMuni = :idMuni")
    , @NamedQuery(name = "Muni.findByMuni", query = "SELECT m FROM Muni m WHERE m.muni = :muni")})
public class Muni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_muni")
    private Integer idMuni;
    @Basic(optional = false)
    @Column(name = "muni")
    private String muni;
    @JoinColumn(name = "id_depto", referencedColumnName = "id_depto")
    @ManyToOne(optional = false)
    private Depto idDepto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMuni")
    private Collection<Datos> datosCollection;

    public Muni() {
    }

    public Muni(Integer idMuni) {
        this.idMuni = idMuni;
    }

    public Muni(Integer idMuni, String muni) {
        this.idMuni = idMuni;
        this.muni = muni;
    }

    public Integer getIdMuni() {
        return idMuni;
    }

    public void setIdMuni(Integer idMuni) {
        this.idMuni = idMuni;
    }

    public String getMuni() {
        return muni;
    }

    public void setMuni(String muni) {
        this.muni = muni;
    }

    public Depto getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Depto idDepto) {
        this.idDepto = idDepto;
    }

    @XmlTransient
    public Collection<Datos> getDatosCollection() {
        return datosCollection;
    }

    public void setDatosCollection(Collection<Datos> datosCollection) {
        this.datosCollection = datosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMuni != null ? idMuni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Muni)) {
            return false;
        }
        Muni other = (Muni) object;
        if ((this.idMuni == null && other.idMuni != null) || (this.idMuni != null && !this.idMuni.equals(other.idMuni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Muni[ idMuni=" + idMuni + " ]";
    }
    
}
