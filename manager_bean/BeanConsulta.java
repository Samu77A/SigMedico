package manager_bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimientos.MantenimientoDiag;
import mantenimientos.MantenimientoMediConsult;
import mantenimientos.mantenimientoConsulta;
import mantenimientos.mantenimientoEnfermedad;
import mantenimientos.mantenimientoMedicamentoTipo;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import persistencia.Citas;
import persistencia.Consulta;
import persistencia.Diagnostico;
import persistencia.DiagnosticoPK;
import persistencia.Enfermedad;
import persistencia.MedicamentoConsulta;
import persistencia.MedicamentoTipo;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@RequestScoped
public class BeanConsulta implements Serializable {

    public BeanConsulta() {
    }

    
}
