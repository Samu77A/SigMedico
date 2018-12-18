
package manager_bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import mantenimientos.MantenimientoDiag;
import persistencia.Diagnostico;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@RequestScoped
public class BeanDiagnostico {

    private int id_enfermedad;
    private int id_consulta;
    private String observacion;
    
    private BeanConsulta b_consulta;
    private BeanEnfermedad b_enfer;
    
    private List<Diagnostico> listaDiag;
    
    private final MantenimientoDiag mDiag = new MantenimientoDiag();
    
    public BeanDiagnostico() {
    }

    public int getId_enfermedad() {
        return id_enfermedad;
    }

    public void setId_enfermedad(int id_enfermedad) {
        this.id_enfermedad = id_enfermedad;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<Diagnostico> getListaDiag() {
        this.listaDiag = new ArrayList<>();
        listaDiag = mDiag.consultaTodoD();
        return listaDiag;
    }

    public void setListaDiag(List<Diagnostico> listaDiag) {
        this.listaDiag = listaDiag;
    }

    public BeanConsulta getB_consulta() {
        return b_consulta;
    }

    public void setB_consulta(BeanConsulta b_consulta) {
        this.b_consulta = b_consulta;
    }

    public BeanEnfermedad getB_enfer() {
        return b_enfer;
    }

    public void setB_enfer(BeanEnfermedad b_enfer) {
        this.b_enfer = b_enfer;
    }
    
    
  
    
}
