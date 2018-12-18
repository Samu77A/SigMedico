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
@Table(name = "consultorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultorio.findAll", query = "SELECT c FROM Consultorio c")
    , @NamedQuery(name = "Consultorio.findByIdConsultorio", query = "SELECT c FROM Consultorio c WHERE c.idConsultorio = :idConsultorio")
    , @NamedQuery(name = "Consultorio.findByConsultorio", query = "SELECT c FROM Consultorio c WHERE c.consultorio = :consultorio")})
public class Consultorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_consultorio")
    private Integer idConsultorio;
    @Basic(optional = false)
    @Column(name = "consultorio")
    private String consultorio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsultorio")
    private Collection<Citas> citasCollection;
    @JoinColumn(name = "id_areas", referencedColumnName = "id_areas")
    @ManyToOne(optional = false)
    private Areas idAreas;

    public Consultorio() {
    }

    public Consultorio(Integer idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public Consultorio(Integer idConsultorio, String consultorio) {
        this.idConsultorio = idConsultorio;
        this.consultorio = consultorio;
    }

    public Integer getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(Integer idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    @XmlTransient
    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
    }

    public Areas getIdAreas() {
        return idAreas;
    }

    public void setIdAreas(Areas idAreas) {
        this.idAreas = idAreas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsultorio != null ? idConsultorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultorio)) {
            return false;
        }
        Consultorio other = (Consultorio) object;
        if ((this.idConsultorio == null && other.idConsultorio != null) || (this.idConsultorio != null && !this.idConsultorio.equals(other.idConsultorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Consultorio[ idConsultorio=" + idConsultorio + " ]";
    }
    
}
