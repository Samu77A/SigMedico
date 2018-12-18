package manager_bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.ws.Response;
import mantenimientos.MantenimientoDiag;
import mantenimientos.MantenimientoMediConsult;
import mantenimientos.mantenimientoCitas;
import mantenimientos.mantenimientoConsult;
import mantenimientos.mantenimientoConsulta;
import mantenimientos.mantenimientoDatos;
import mantenimientos.mantenimientoEnfermedad;
import mantenimientos.mantenimientoMedicamentoTipo;
import org.eclipse.persistence.internal.sessions.ArrayRecord;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import persistencia.Areas;
import persistencia.Citas;
import persistencia.Consulta;
import persistencia.Consultorio;
import persistencia.Datos;
import persistencia.Diagnostico;
import persistencia.DiagnosticoPK;
import persistencia.Enfermedad;
import persistencia.Expediente;
import persistencia.Hospital;
import persistencia.MedicamentoConsulta;
import persistencia.MedicamentoTipo;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean(name = "prueba")
@ViewScoped
public class beanCitas implements Serializable {

    private int id_citas = 0;
    private int id_medico;
    private int id_paciente;
    private int id_consultorio;
    private String tipo_cita;
    private Date fecha_inicio;
    private Date hora_inicio;
    private String estado_cita;
    private String inconveniente;
    private Expediente codigoExpe;
    public String color = null;
    private boolean skip;
    private String n_enfermedad;
    private String n_medicamento;
    
    /*para consulta*/
    private int id_consulta = 0;
    private Date fecha_fin;
    private Date hora_fin;
    private String estado;

    private BeanDiagnostico b_diagnostico;
    private BeanMedicamentoConsulta b_mC;

    /*para diagnostico*/
    private int id_enfermedad;
    private String observaciones;

    /*para medicamento*/
    private int id_medicamento_tipo;
    private int cantidad;
    private String instrucciones;

    private Citas selectedCitas;

    private int paci = 3;
    private int doc = 2;

    private String n_medico;
    private String n_paciente;
    private String n_consult;

    private List<Citas> listaCita;
    private List<Datos> listaMedico;
    private List<Datos> listaPaciente;
    private List<Consultorio> listaConsult;

    /**
     * ***mantenimientos
     */
    private final mantenimientoCitas mCitas = new mantenimientoCitas();
    private final mantenimientoDatos mDatos = new mantenimientoDatos();
    private final mantenimientoConsult mConsult = new mantenimientoConsult();
    private final mantenimientoConsulta mConsulta = new mantenimientoConsulta();
    private final MantenimientoDiag mDiag = new MantenimientoDiag();
    private final MantenimientoMediConsult mmC = new MantenimientoMediConsult();
    private final mantenimientoEnfermedad mEn = new mantenimientoEnfermedad();
    private final mantenimientoMedicamentoTipo mMt = new mantenimientoMedicamentoTipo();
    
    private List<Enfermedad> listaEnfer;
    private List<MedicamentoTipo> listaMediTipo;

    public List<Enfermedad> getListaEnfer() {
        this.listaEnfer = new ArrayList<>();
        listaEnfer = mEn.consultar();
        return listaEnfer;
    }

    public void setListaEnfer(List<Enfermedad> listaEnfer) {
        this.listaEnfer = listaEnfer;
    }

    public List<MedicamentoTipo> getListaMediTipo() {
        this.listaMediTipo = new ArrayList<>();
        listaMediTipo = mMt.consultarTodo();
        return listaMediTipo;
    }

    public void setListaMediTipo(List<MedicamentoTipo> listaMediTipo) {
        this.listaMediTipo = listaMediTipo;
    }

    private ScheduleModel Eventos;
    private ScheduleModel LazyEventos;
    private ScheduleEvent event = new DefaultScheduleEvent();

    public beanCitas() {
    }

    public List<Citas> getListaCita() {
        listaCita = new ArrayList<>();
        listaCita = mCitas.consultarCita();
        return listaCita;
    }

    public void setListaCita(List<Citas> listaCita) {
        this.listaCita = listaCita;
    }

    public List<Datos> getListaMedico() {
        listaMedico = new ArrayList<>();
        listaMedico = mDatos.consultaDatosPaci(doc);
        return listaMedico;
    }

    public void setListaMedico(List<Datos> listaMedico) {
        this.listaMedico = listaMedico;
    }

    public List<Datos> getListaPaciente() {
        listaPaciente = new ArrayList<>();
        listaPaciente = mDatos.consultaDatosPaci(paci);
        return listaPaciente;
    }

    public void setListaPaciente(List<Datos> listaPaciente) {
        this.listaPaciente = listaPaciente;
    }

    public List<Consultorio> getListaConsult() {
        this.listaConsult = new ArrayList<>();
        listaConsult = mConsult.consultarTodo();
        return listaConsult;
    }

    public void setListaConsult(List<Consultorio> listaConsult) {
        this.listaConsult = listaConsult;
    }

    public int getId_citas() {
        return id_citas;
    }

    public void setId_citas(int id_citas) {
        this.id_citas = id_citas;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(int id_consultorio) {
        this.id_consultorio = id_consultorio;
    }

    public String getTipo_cita() {
        return tipo_cita;
    }

    public void setTipo_cita(String tipo_cita) {
        this.tipo_cita = tipo_cita;
    }

    public String getN_medico() {
        return n_medico;
    }

    public void setN_medico(String n_medico) {
        this.n_medico = n_medico;
    }

    public String getN_paciente() {
        return n_paciente;
    }

    public void setN_paciente(String n_paciente) {
        this.n_paciente = n_paciente;
    }

    public String getN_consult() {
        return n_consult;
    }

    public void setN_consult(String n_consult) {
        this.n_consult = n_consult;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getEstado_cita() {
        return estado_cita;
    }

    public void setEstado_cita(String estado_cita) {
        this.estado_cita = estado_cita;
    }

    public String getInconveniente() {
        return inconveniente;
    }

    public void setInconveniente(String inconveniente) {
        this.inconveniente = inconveniente;
    }

    public Expediente getCodigoExpe() {
        return codigoExpe;
    }

    public void setCodigoExpe(Expediente codigoExpe) {
        this.codigoExpe = codigoExpe;
    }

    public Citas getSelectedCitas() {
        return selectedCitas;
    }

    public void setSelectedCitas(Citas selectedCitas) {
        this.selectedCitas = selectedCitas;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Date getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Date hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_enfermedad() {
        return id_enfermedad;
    }

    public void setId_enfermedad(int id_enfermedad) {
        this.id_enfermedad = id_enfermedad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public int getPaci() {
        return paci;
    }

    public void setPaci(int paci) {
        this.paci = paci;
    }

    public String getN_enfermedad() {
        return n_enfermedad;
    }

    public void setN_enfermedad(String n_enfermedad) {
        this.n_enfermedad = n_enfermedad;
    }

    public String getN_medicamento() {
        return n_medicamento;
    }

    public void setN_medicamento(String n_medicamento) {
        this.n_medicamento = n_medicamento;
    }
        

    /**
     * ******
     * metodo para eliminar
     *
     * @param id_citas
     * @return
     */
    public String eliminarCita(int id_citas) {

        int r = mCitas.eliminarCita(id_citas);

        if (r > 0) {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Exito al borrar la cita"));
        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "No se elimino la cita"));
        }
        return null;
    }

    /**
     * ******************
     * mostrar todo en el calendario
     *
     * @param **************
     */
    @PostConstruct
    public void init() {
        Eventos = new DefaultScheduleModel();
        List<Citas> c = mCitas.consultarCita();
        Iterator i = c.iterator();

        while (i.hasNext()) {
            Citas cita = (Citas) i.next();

            Date a3 = null;

            try {
                /**
                 * *********
                 * Se hace para concatenar la fecha y la hora para mostrar en la
                 * vista la hora ya que solo permite un parametro como tal s
                 * ehizo esto
                 */
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                Date a = cita.getFechaInicio();
                String as = f.format(a);
                Date fec = f.parse(as);

                SimpleDateFormat f1 = new SimpleDateFormat("HH:mm:ss");
                Date a1 = cita.getHoraInicio();
                String as1 = f1.format(a1);
                Date fec1 = f1.parse(as1);

                SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                a3 = f3.parse(as + " " + as1);

            } catch (Exception e) {
                e.printStackTrace();
            }

            List<Citas> con = mCitas.consultarCita();

            Eventos.addEvent(new DefaultScheduleEvent(cita.getTipoCita(), a3, cita.getHoraInicio(), cita.getIdCitas()));
        }

    }

    public ScheduleModel getEventModel() {
        return Eventos;
    }

    public ScheduleModel getLazyEventModel() {
        return LazyEventos;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void guardarCita() {
        Citas citas = new Citas();
        Datos pac = new Datos();
        Datos doc = new Datos();
        Consultorio consul = new Consultorio();

        doc.setIdDatos(id_medico);
        pac.setIdDatos(id_paciente);
        consul.setIdConsultorio(id_consultorio);
        estado_cita = "Pendiente";
        citas.setIdMedico(doc);
        citas.setIdPaciente(pac);
        citas.setIdConsultorio(consul);
        citas.setTipoCita(tipo_cita);
        citas.setFechaInicio(fecha_inicio);
        citas.setHoraInicio(hora_inicio);
        citas.setEstadoCita(estado_cita);
        citas.setInconveniente(inconveniente);

        int r = mCitas.insertarCita(citas);

        if (r > 0) {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Cita guardada Exitosamente"));

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Cita No guardada Exitosamente"));

        }

    }

    public String actualizarCita(int id_citas) {
        Datos doc = new Datos();
        Datos pac = new Datos();
        Citas c = new Citas();
        Consultorio con = new Consultorio();

        doc.setIdDatos(id_medico);
        pac.setIdDatos(id_paciente);
        con.setIdConsultorio(id_consultorio);
        c.setIdMedico(doc);
        c.setIdPaciente(pac);
        c.setIdConsultorio(con);
        c.setTipoCita(tipo_cita);
        c.setFechaInicio(fecha_inicio);
        c.setHoraInicio(hora_inicio);
        c.setEstadoCita(estado_cita);
        c.setInconveniente(inconveniente);

        int r = mCitas.actualizarCita(id_citas, c);

        if (r > 0) {

            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Evento Movido", "Se movio al" + c.getFechaInicio()));

            return "prueba.xhtml?faces-redirect=true";
        } else {

            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Evento no Movido", "El evento no se actualizo"));

        }
        return null;
    }

    public void onEventSelect(SelectEvent selectEvent) {

        event = (ScheduleEvent) selectEvent.getObject();
        event.getDescription();

        Object obj = event.getData();
        id_citas = (int) obj;

        //System.out.println("El id es " + id_citas);
        if (id_citas != 0) {

            Citas listaCitas = mCitas.consultarIdC(id_citas);

            if (listaCitas != null) {

                this.id_citas = listaCitas.getIdCitas();
                this.id_medico = listaCitas.getIdMedico().getIdDatos();
                this.n_medico = listaCitas.getIdMedico().getNombres();
                this.id_paciente = listaCitas.getIdPaciente().getIdDatos();
                this.n_paciente = listaCitas.getIdPaciente().getNombres();
                this.id_consultorio = listaCitas.getIdConsultorio().getIdConsultorio();
                this.n_consult = listaCitas.getIdConsultorio().getConsultorio();
                this.tipo_cita = listaCitas.getTipoCita();
                this.fecha_inicio = listaCitas.getFechaInicio();
                this.hora_inicio = listaCitas.getHoraInicio();
                this.estado_cita = listaCitas.getEstadoCita();
                this.inconveniente = listaCitas.getInconveniente();

                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Exito", "Cita por Id mostrando..."));

            } else {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Advertencia", "No hay datos para mostrar"));
            }
        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_WARN, "Advertencia", "NO hay ID seleccionado..."));
        }

    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {

        event = ScheduleEntryMoveEvent.class.cast(event.getScheduleEvent().getData());
        Object obj = event;

    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Exito", ""));
    }

    /**
     * ****************
     * Mostrar la lista de las citas y filtrar las citas realizadas las citas no
     * realizadas etc. etc.
     *
     * @return
     */
    public List<Citas> consult() {
        Date hoy = new Date();

        List<Citas> l = mCitas.consultarTodo();

        List<Citas> consultaC = new ArrayList<>();
        Iterator i = l.iterator();
        while (i.hasNext()) {

            Citas cita = new Citas();
            Datos me = new Datos();
            Expediente e = new Expediente();
            Consultorio con = new Consultorio();
            Areas area = new Areas();
            Hospital h = new Hospital();
            Datos pac = new Datos();
            Datos d = new Datos();

            ArrayRecord ar = (ArrayRecord) i.next();

            me.setNombres((String) ar.get("medico"));
            pac.setNombres((String) ar.get("paciente"));

            con.setConsultorio((String) ar.get("consultorio"));
            area.setTipoAreas((String) ar.get("tipo_areas"));
            h.setHospital((String) ar.get("hospital"));
            e.setCodExpediente((String) ar.get("cod_expediente"));

            area.setIdHospital(h);
            con.setIdAreas(area);
            cita.setIdCitas((Integer) ar.get("id_citas"));
            cita.setFechaInicio((Date) ar.get("fecha_inicio"));
            cita.setHoraInicio((Date) ar.get("hora_inicio"));
            cita.setTipoCita((String) ar.get("tipo_cita"));

            cita.setIdConsultorio(con);
            cita.setIdMedico(me);

            codigoExpe = e;
            String cod = (String) codigoExpe.getCodExpediente();
            cita.setAgregado(cod);

            d.setNombres((String) ar.get("paciente"));
            cita.setIdPaciente(d);

            Date fecha = cita.getFechaInicio();

            /* if (c.getEstadoC() == null) {
                c.setEstadoC("#fff");
            } else {
                
                if(hoy.getTime() > fecha.getTime() && c.getEstadoC().equalsIgnoreCase("Pendiente")){
                     c.setEstadoC("#30a5ff");
                     
                }else if(hoy.getTime() > fecha.getTime() && c.getEstadoC().equalsIgnoreCase("Realizada")){
                    c.setEstadoC("#8ad919");
                    
                }else if(hoy.getTime() > fecha.getTime() && c.getEstadoC().equalsIgnoreCase("Problemas")){
                    c.setEstadoC("#ffb53e");
                    
                }else if(hoy.getTime() > fecha.getTime() && c.getEstadoC().equalsIgnoreCase("Perdida")){
                    c.setEstadoC("#f9243f");
                    
                }else if(hoy.getTime() > fecha.getTime() && c.getEstadoC().equalsIgnoreCase("Reprogramada")){
                    c.setEstadoC("#5bc0de");
                    
                }else if(hoy.getTime() < fecha.getTime() && c.getEstadoC().equalsIgnoreCase("Pendiente")){
                     c.setEstadoC("#30a5ff");
                     
                }else if(hoy.getTime() < fecha.getTime() && c.getEstadoC().equalsIgnoreCase("Realizada")){
                    c.setEstadoC("#8ad919");
                    
                }else if(hoy.getTime() < fecha.getTime() && c.getEstadoC().equalsIgnoreCase("Problemas")){
                    c.setEstadoC("#ffb53e");
                    
                }else if(hoy.getTime() < fecha.getTime() && c.getEstadoC().equalsIgnoreCase("Perdida")){
                    c.setEstadoC("#f9243f");
                    
                }else if(hoy.getTime() < fecha.getTime() && c.getEstadoC().equalsIgnoreCase("Reprogramada")){
                    c.setEstadoC("#5bc0de");
                    
                }
                
            }*/
            consultaC.add(cita);
        }
        return consultaC;
    }

    public String consultarIDCita(int id_citas) {

        Citas listaCitas = mCitas.consultarIdC(id_citas);

        if (listaCitas != null) {

            this.id_citas = listaCitas.getIdCitas();
            this.id_medico = listaCitas.getIdMedico().getIdDatos();
            this.n_medico = listaCitas.getIdMedico().getNombres();
            this.id_paciente = listaCitas.getIdPaciente().getIdDatos();
            this.n_paciente = listaCitas.getIdPaciente().getNombres();
            this.id_consultorio = listaCitas.getIdConsultorio().getIdConsultorio();
            this.n_consult = listaCitas.getIdConsultorio().getConsultorio();
            this.tipo_cita = listaCitas.getTipoCita();
            this.fecha_inicio = listaCitas.getFechaInicio();
            this.hora_inicio = listaCitas.getHoraInicio();
            this.estado_cita = listaCitas.getEstadoCita();
            this.inconveniente = listaCitas.getInconveniente();

            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Exito", "Cita por Id mostrando..."));

        } else {
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Advertencia", "No hay datos para mostrar"));
        }
        return null;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //restablecer en caso de que el usuario vuelva
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
    
     public String insertarC(int id_citas) throws ParseException {

        Consulta bc = new Consulta();
        Citas cita = new Citas();
        SimpleDateFormat h = new SimpleDateFormat("HH:mm");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        Date d = new Date();

        cita.setIdCitas(id_citas);
        
         System.out.println("id es == " + id_citas);

        bc.setIdCitas(cita);
        bc.setFechaFin(f.parse(f.format(d)));
        bc.setHoraFin(h.parse(h.format(d)));
        bc.setEstado(estado);

        System.out.println(bc.getIdCitas());
        System.out.println(bc.getFechaFin());
        System.out.println(bc.getHoraFin());
        System.out.println(bc.getEstado());

        //int r = mConsulta.insertarConsulta(bc);
        int r = 1;
        int id = r;
        System.out.println("El id es " + id);
        if (r > 0) {
            Diagnostico diag = new Diagnostico();
            DiagnosticoPK diagPK = new DiagnosticoPK();

            System.out.println(id_enfermedad);
            System.out.println(id);
            System.out.println(observaciones);

            diagPK.setIdEnfermedad(id_enfermedad);
            diagPK.setIdConsulta(id);
            diag.setDiagnosticoPK(diagPK);
            diag.setObservaciones(observaciones);

            //int r1 = mDiag.insertarDiag(diag);
            int r1 = 1;
            if (r1 > 0) {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Exito", "Se guardo en Diagnostico"));
            } else {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Error", "No Se guardo en Diagnostico"));

            }

        }
        if (r > 0) {
            MedicamentoConsulta mCon = new MedicamentoConsulta();
            MedicamentoTipo mt = new MedicamentoTipo();

            System.out.println(id);
            System.out.println(id_medicamento_tipo);
            System.out.println(cantidad);
            System.out.println(instrucciones);

            bc.setIdConsulta(id);
            mt.setIdMedicamentoTipo(id_medicamento_tipo);

            mCon.setIdConsulta(bc);
            mCon.setIdMedicamentoTipo(mt);
            mCon.setCantidad(cantidad);
            mCon.setInstrucciones(instrucciones);

            //int r2 = mmC.insertarMediConsul(mCon);
            int r2 = 1;
            if (r2 > 0) {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Exito", "Se guardo en Medicamento Consulta"));
            } else {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Error", "No Se guardo en Medicamento Consulta"));
            }
        }

        return null;
    }

}
