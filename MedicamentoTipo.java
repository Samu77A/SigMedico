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
@Table(name = "medicamento_tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentoTipo.findAll", query = "SELECT m FROM MedicamentoTipo m")
    , @NamedQuery(name = "MedicamentoTipo.findByIdMedicamentoTipo", query = "SELECT m FROM MedicamentoTipo m WHERE m.idMedicamentoTipo = :idMedicamentoTipo")})
public class MedicamentoTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_medicamento_tipo")
    private Integer idMedicamentoTipo;
    @JoinColumn(name = "id_medicamento", referencedColumnName = "id_medicamento")
    @ManyToOne(optional = false)
    private Medicamento idMedicamento;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private Tipo idTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMedicamentoTipo")
    private Collection<MedicamentoConsulta> medicamentoConsultaCollection;

    public MedicamentoTipo() {
    }

    public MedicamentoTipo(Integer idMedicamentoTipo) {
        this.idMedicamentoTipo = idMedicamentoTipo;
    }

    public Integer getIdMedicamentoTipo() {
        return idMedicamentoTipo;
    }

    public void setIdMedicamentoTipo(Integer idMedicamentoTipo) {
        this.idMedicamentoTipo = idMedicamentoTipo;
    }

    public Medicamento getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Medicamento idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public Tipo getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Tipo idTipo) {
        this.idTipo = idTipo;
    }

    @XmlTransient
    public Collection<MedicamentoConsulta> getMedicamentoConsultaCollection() {
        return medicamentoConsultaCollection;
    }

    public void setMedicamentoConsultaCollection(Collection<MedicamentoConsulta> medicamentoConsultaCollection) {
        this.medicamentoConsultaCollection = medicamentoConsultaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedicamentoTipo != null ? idMedicamentoTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoTipo)) {
            return false;
        }
        MedicamentoTipo other = (MedicamentoTipo) object;
        if ((this.idMedicamentoTipo == null && other.idMedicamentoTipo != null) || (this.idMedicamentoTipo != null && !this.idMedicamentoTipo.equals(other.idMedicamentoTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.MedicamentoTipo[ idMedicamentoTipo=" + idMedicamentoTipo + " ]";
    }
    
}
