package mantenimientos;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.DataReadQuery;
import org.eclipse.persistence.queries.StoredProcedureCall;
import persistencia.Citas;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimientoCitas {

    EntityManager em;

    public static void main(String[] args) {

    }

    public int insertarCita(Citas c) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        int ban = 0;
        try {
            em.persist(c);
            em.getTransaction().commit();
        
            //ban = c.getIdCitas();
            ban =1;

        } catch (Exception e) {
            ban = 0;
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return ban;
    }

    public List consultarCita() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<Citas> lista = null;
        em.getTransaction().begin();
        try {
            Query q = em.createNamedQuery("Citas.findAll");
            lista = q.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return lista;
    }
    
    public int actualizarCita(int id_citas, Citas c){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Citas citas = null;
        em.getTransaction().begin();
        int ban =0;
        try {
            citas = em.find(Citas.class, id_citas);
            citas.setIdMedico(c.getIdMedico());
            citas.setIdPaciente(c.getIdPaciente());
            citas.setIdConsultorio(c.getIdConsultorio());
            citas.setTipoCita(c.getTipoCita());
            citas.setFechaInicio(c.getFechaInicio());
            citas.setHoraInicio(c.getHoraInicio());
            citas.setEstadoCita(c.getEstadoCita());
            citas.setInconveniente(c.getInconveniente());
            
            em.getTransaction().commit();
            ban =1;
        } catch (Exception e) {
            ban =0;
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }finally{
            if(em != null){
                em.close();
            }
        }
        return ban;
    }
    
    public Citas consultarIdC(int id_citas){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Citas listaC = null;
        em.getTransaction().begin();
        
        try {
           listaC = em.find(Citas.class, id_citas);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }finally{
            if(em != null){
                em.close();
            }
        }
        return listaC;
    }
    
    public int eliminarCita(int id_citas){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Citas cita = null;
        int ban =0;
        em.getTransaction().begin();
        try {
            cita = em.find(Citas.class, id_citas);
            em.remove(cita);
            em.getTransaction().commit();
            ban =1;
        } catch (Exception e) {
            ban =0;
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }finally{
            if(em != null){
                em.close();
            }
        }
        
        return ban;
    }
    
    public List vFecha(){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<Citas> fecha= null;
        em.getTransaction().begin();
        try{
            Query q = em.createNamedQuery("Citas.fecha", Citas.class);
            fecha = q.getResultList();
            em.getTransaction().commit();
            
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }finally{
            if(em != null){
                em.close();
            }
        }
            return fecha;
    }
    
    public List consultarTodo() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Citas> lista = new ArrayList<>();
        try {
            StoredProcedureCall spC = new StoredProcedureCall();
            spC.setProcedureName("verCitas");

            DataReadQuery drQ = new DataReadQuery();
            drQ.setCall(spC);

            Query q = ((JpaEntityManager) em.getDelegate()).createQuery(drQ);
            lista = q.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {
            if (em != null) {

            }
        }

        return lista;
    }
}
