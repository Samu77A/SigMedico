package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Role;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimientoRole {

    EntityManager em;

    public int guardar(Role role) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        int bandera = 0;
        try {
            em.persist(role);
            em.getTransaction().commit();
            bandera = 1;

        } catch (Exception e) {
            bandera = 0;
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        }
        return bandera;
    }
    public List<Role> consultTodo(){
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Role> listaRol=null;
        try {
            Query q = em.createNamedQuery("Role.findAll", Role.class);
            listaRol = q.getResultList();
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
        return listaRol;
    }

}
