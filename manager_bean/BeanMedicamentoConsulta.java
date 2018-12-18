
package manager_bean;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import persistencia.MedicamentoTipo;
import mantenimientos.mantenimientoMedicamentoTipo;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@RequestScoped
public class BeanMedicamentoConsulta {

    private int id_medicamento_consulta;
    private int id_consulta;
    private int id_medicamento_tipo;
    private int cantidad;
    private String instrucciones;
    
    private BeanMedicamento b_medica;
    private BeanConsulta b_consulta;
    
    
    
    private List<MedicamentoTipo> listaTipoMe;
    
    
    private final mantenimientoMedicamentoTipo mMT = new mantenimientoMedicamentoTipo();
    
    public BeanMedicamentoConsulta() {
    }

    public int getId_medicamento_consulta() {
        return id_medicamento_consulta;
    }

    public void setId_medicamento_consulta(int id_medicamento_consulta) {
        this.id_medicamento_consulta = id_medicamento_consulta;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public int getId_medicamento_tipo() {
        return id_medicamento_tipo;
    }

    public void setId_medicamento_tipo(int id_medicamento_tipo) {
        this.id_medicamento_tipo = id_medicamento_tipo;
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

    public List<MedicamentoTipo> getListaTipoMe() {
        this.listaTipoMe = new VirtualFlow.ArrayLinkedList<>();
        listaTipoMe = mMT.consultarTodo();
        return listaTipoMe;
    }

    public void setListaTipoMe(List<MedicamentoTipo> listaTipoMe) {
        this.listaTipoMe = listaTipoMe;
    }

    public BeanMedicamento getB_medica() {
        return b_medica;
    }

    public void setB_medica(BeanMedicamento b_medica) {
        this.b_medica = b_medica;
    }

    public BeanConsulta getB_consulta() {
        return b_consulta;
    }

    public void setB_consulta(BeanConsulta b_consulta) {
        this.b_consulta = b_consulta;
    }
    
    
    
    
    
}
