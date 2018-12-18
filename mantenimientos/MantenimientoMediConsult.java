
package mantenimientos;

import javax.persistence.EntityManager;
import persistencia.MedicamentoConsulta;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class MantenimientoMediConsult {
    
    EntityManager em;
    
    public int insertarMediConsul(MedicamentoConsulta mc){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int ban =0;
        em.getTransaction().begin();
        try {
            
            em.persist(mc);
            ban = 1;
            em.getTransaction().commit();
            
        } catch (Exception e) {
            ban =0;
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }finally{
            if( em != null){
                em.close();
            }
        }
        
        return ban;
    }
    
}
