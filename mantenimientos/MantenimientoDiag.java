
package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Diagnostico;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class MantenimientoDiag {
    
    EntityManager em;
    
    public List consultaTodoD(){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<Diagnostico> listaDiag = null;
        em.getTransaction().begin();
        
        try {
            Query q = em.createNamedQuery("Diagnostico.findAll");
            listaDiag = q.getResultList();
            
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
            
        }finally{
            if(em != null){
                em.close();
            }
        }
        return listaDiag;
    }
    
    public int insertarDiag(Diagnostico d){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int ban =0;
        em.getTransaction().begin();
        try{
            em.persist(d);
            ban =1;
            em.getTransaction().commit();
        }catch(Exception e){
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
    
}
