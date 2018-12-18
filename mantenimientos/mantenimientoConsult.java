
package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Consultorio;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimientoConsult {
    
    EntityManager em;
    
    public static void main(String[] args) {
        
    }
    
    public int insertarC(Consultorio c){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Consultorio con = null;
        em.getTransaction().begin();
        int ban =0;
        try {
            em.persist(c);
            em.getTransaction().commit();
            
            ban = 1;
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
    
    public List consultarTodo(){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<Consultorio> listaC = null;
        em.getTransaction().begin();
        try {
            Query q = em.createNamedQuery("Consultorio.findAll", Consultorio.class);
            listaC = q.getResultList();
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
    
    public Consultorio consultarId(int id_consultorio){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Consultorio con = null;
        em.getTransaction().begin();
        try {
            con = em.find(Consultorio.class, id_consultorio);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }finally{
            if(em != null){
                em.close();
            }
        }
        return con;
    }
    
    public int borrarC(int id_consultorio){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Consultorio con = null;
        em.getTransaction().begin();
        int ban =0;
        try {
            con = em.find(Consultorio.class, id_consultorio);
            em.remove(con);
            em.getTransaction().commit();
            
            ban  = 1;
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
    
    public boolean actualizarC(int id_consultorio, Consultorio c){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Consultorio con = null;
        em.getTransaction().begin();
        try {
            con = em.find(Consultorio.class, id_consultorio);
            con.setIdAreas(c.getIdAreas());
            con.setConsultorio(c.getConsultorio());
            em.getTransaction().commit();
            
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
    }   
}
