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
@Table(name = "areas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areas.findAll", query = "SELECT a FROM Areas a")
    , @NamedQuery(name = "Areas.findByIdAreas", query = "SELECT a FROM Areas a WHERE a.idAreas = :idAreas")
    , @NamedQuery(name = "Areas.findByIdTipoMaterial", query = "SELECT a FROM Areas a WHERE a.idTipoMaterial = :idTipoMaterial")
    , @NamedQuery(name = "Areas.findByTipoAreas", query = "SELECT a FROM Areas a WHERE a.tipoAreas = :tipoAreas")})
public class Areas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_areas")
    private Integer idAreas;
    @Basic(optional = false)
    @Column(name = "id_tipo_material")
    private int idTipoMaterial;
    @Basic(optional = false)
    @Column(name = "tipo_areas")
    private String tipoAreas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAreas")
    private Collection<Consultorio> consultorioCollection;
    @JoinColumn(name = "id_hospital", referencedColumnName = "id_hospital")
    @ManyToOne(optional = false)
    private Hospital idHospital;

    public Areas() {
    }

    public Areas(Integer idAreas) {
        this.idAreas = idAreas;
    }

    public Areas(Integer idAreas, int idTipoMaterial, String tipoAreas) {
        this.idAreas = idAreas;
        this.idTipoMaterial = idTipoMaterial;
        this.tipoAreas = tipoAreas;
    }

    public Integer getIdAreas() {
        return idAreas;
    }

    public void setIdAreas(Integer idAreas) {
        this.idAreas = idAreas;
    }

    public int getIdTipoMaterial() {
        return idTipoMaterial;
    }

    public void setIdTipoMaterial(int idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
    }

    public String getTipoAreas() {
        return tipoAreas;
    }

    public void setTipoAreas(String tipoAreas) {
        this.tipoAreas = tipoAreas;
    }

    @XmlTransient
    public Collection<Consultorio> getConsultorioCollection() {
        return consultorioCollection;
    }

    public void setConsultorioCollection(Collection<Consultorio> consultorioCollection) {
        this.consultorioCollection = consultorioCollection;
    }

    public Hospital getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Hospital idHospital) {
        this.idHospital = idHospital;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAreas != null ? idAreas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areas)) {
            return false;
        }
        Areas other = (Areas) object;
        if ((this.idAreas == null && other.idAreas != null) || (this.idAreas != null && !this.idAreas.equals(other.idAreas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Areas[ idAreas=" + idAreas + " ]";
    }
    
}
