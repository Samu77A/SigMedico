/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@Entity
@Table(name = "medicamento_consulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentoConsulta.findAll", query = "SELECT m FROM MedicamentoConsulta m")
    , @NamedQuery(name = "MedicamentoConsulta.findByIdMedicamentoConsulta", query = "SELECT m FROM MedicamentoConsulta m WHERE m.idMedicamentoConsulta = :idMedicamentoConsulta")
    , @NamedQuery(name = "MedicamentoConsulta.findByCantidad", query = "SELECT m FROM MedicamentoConsulta m WHERE m.cantidad = :cantidad")
    , @NamedQuery(name = "MedicamentoConsulta.findByInstrucciones", query = "SELECT m FROM MedicamentoConsulta m WHERE m.instrucciones = :instrucciones")})
public class MedicamentoConsulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_medicamento_consulta")
    private Integer idMedicamentoConsulta;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "instrucciones")
    private String instrucciones;
    @JoinColumn(name = "id_medicamento_tipo", referencedColumnName = "id_medicamento_tipo")
    @ManyToOne(optional = false)
    private MedicamentoTipo idMedicamentoTipo;
    @JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
    @ManyToOne(optional = false)
    private Consulta idConsulta;

    public MedicamentoConsulta() {
    }

    public MedicamentoConsulta(Integer idMedicamentoConsulta) {
        this.idMedicamentoConsulta = idMedicamentoConsulta;
    }

    public MedicamentoConsulta(Integer idMedicamentoConsulta, int cantidad, String instrucciones) {
        this.idMedicamentoConsulta = idMedicamentoConsulta;
        this.cantidad = cantidad;
        this.instrucciones = instrucciones;
    }

    public Integer getIdMedicamentoConsulta() {
        return idMedicamentoConsulta;
    }

    public void setIdMedicamentoConsulta(Integer idMedicamentoConsulta) {
        this.idMedicamentoConsulta = idMedicamentoConsulta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public MedicamentoTipo getIdMedicamentoTipo() {
        return idMedicamentoTipo;
    }

    public void setIdMedicamentoTipo(MedicamentoTipo idMedicamentoTipo) {
        this.idMedicamentoTipo = idMedicamentoTipo;
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
        hash += (idMedicamentoConsulta != null ? idMedicamentoConsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoConsulta)) {
            return false;
        }
        MedicamentoConsulta other = (MedicamentoConsulta) object;
        if ((this.idMedicamentoConsulta == null && other.idMedicamentoConsulta != null) || (this.idMedicamentoConsulta != null && !this.idMedicamentoConsulta.equals(other.idMedicamentoConsulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.MedicamentoConsulta[ idMedicamentoConsulta=" + idMedicamentoConsulta + " ]";
    }
    
}
