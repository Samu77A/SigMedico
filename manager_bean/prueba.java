package manager_bean;

import java.io.Serializable;
import static java.lang.Math.log;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.ws.Response;
import mantenimientos.mantenimientoCitas;
import mantenimientos.mantenimientoConsult;
import mantenimientos.mantenimientoDatos;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import persistencia.Citas;
import persistencia.Consultorio;
import persistencia.Datos;
import static sun.util.logging.LoggingSupport.log;

/**
 *
 * @author samuel.alvaradoUSAM
 */
@ManagedBean
@ViewScoped
public class prueba implements Serializable {

    private int id_citas = 0;
    private int id_medico;
    private int id_paciente;
    private int id_consultorio;
    private String tipo_cita;
    private Date fecha_hora;
    private Date fecha_hora_fin;

    private int paci = 3;
    private int doc = 2;

    private String n_medico;
    private String n_paciente;
    private String n_consult;

    private List<Citas> listaCita;
    private List<Datos> listaMedico;
    private List<Datos> listaPaciente;
    private List<Consultorio> listaConsult;

    private final mantenimientoCitas mCitas = new mantenimientoCitas();
    private final mantenimientoDatos mDatos = new mantenimientoDatos();
    private final mantenimientoConsult mConsult = new mantenimientoConsult();

    private ScheduleModel Eventos;
    private ScheduleModel LazyEventos;
    private ScheduleEvent event = new DefaultScheduleEvent();

    public prueba() {
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

    public Date getFecha_hora_fin() {
        return fecha_hora_fin;
    }

    public void setFecha_hora_fin(Date fecha_hora_fin) {
        this.fecha_hora_fin = fecha_hora_fin;
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

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
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

    @PostConstruct
    public void init() {
        Eventos = new DefaultScheduleModel();
        List<Citas> c = mCitas.consultarCita();
        Iterator i = c.iterator();
        while (i.hasNext()) {
            Citas cita = (Citas) i.next();
            List<Citas> con = mCitas.consultarCita();
            Eventos.addEvent(new DefaultScheduleEvent(cita.getTipoCita() + " " + cita.getIdCitas()
                    + " ", cita.getFechaHora(), cita.getFechaHoraFin(), cita.getIdCitas()));
        }
        LazyEventos = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));

                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }
        };
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return Eventos;
    }

    public ScheduleModel getLazyEventModel() {
        return LazyEventos;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void guardarCita() {

        if (event.getId() == null) {
            Eventos.addEvent(event);

            Citas citas = new Citas();
            Datos pac = new Datos();
            Datos doc = new Datos();
            Consultorio consul = new Consultorio();

            doc.setIdDatos(id_medico);
            pac.setIdDatos(id_paciente);
            consul.setIdConsultorio(id_consultorio);

            System.out.println("id doctor es " + doc);
            System.out.println("id paciente es " + pac);

            citas.setIdMedico(doc);
            citas.setIdPaciente(pac);
            citas.setIdConsultorio(consul);
            citas.setTipoCita(tipo_cita);
            citas.setFechaHora(fecha_hora);
            citas.setFechaHoraFin(fecha_hora_fin);

            int r = mCitas.insertarCita(citas);

            if (r > 0) {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Exito", "Cita Guardada Exitosamente"));

                Eventos.addEvent(event);
            } else {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Error", "Cita No guardada Exitosamente"));

            }
        } else {
            Eventos.updateEvent(event);

            event = new DefaultScheduleEvent();
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
        c.setFechaHora(fecha_hora);
        c.setFechaHoraFin(fecha_hora_fin);

        int r = mCitas.actualizarCita(id_citas, c);

        if (r > 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Evento movido", "Se movio el evento " + c.getIdCitas());
            addMessage(message);

            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Evento Movido", "Se movio al" + c.getFechaHora() + " a " + c.getFechaHoraFin()));
            return "prueba.xhtml?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Event No movido", "No");
            addMessage(message);

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

        System.out.println("El id es  ????  " + id_citas);
        if (id_citas != 0) {

            Citas listaCitas = mCitas.consultarIdC(id_citas);

            if (listaCitas != null) {
                /*
                this.id_citas = listaCitas.getIdCitas();
                this.id_medico = listaCitas.getIdMedico().getIdDatos();
                this.n_medico = listaCitas.getIdMedico().getNombres();
                this.id_paciente = listaCitas.getIdPaciente().getIdDatos();
                this.n_paciente = listaCitas.getIdPaciente().getNombres();
                this.id_consultorio = listaCitas.getIdConsultorio().getIdConsultorio();
                this.n_consult = listaCitas.getIdConsultorio().getConsultorio();
                this.tipo_cita = listaCitas.getTipoCita();
                this.fecha_hora = listaCitas.getFechaHora();
                this.fecha_hora_fin = listaCitas.getFechaHoraFin();
*/
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Exito", "Cita por Id mostrando..."));

            } else {
                RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Advertencia", "No hay datos para mostrar"));
            }
        }else{
            RequestContext.getCurrentInstance().update("grow1");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN, "Advertencia", "NO hay ID seleccionado..."));
        }

    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {

    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Exito", ""));
    }

}
