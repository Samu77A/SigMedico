package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.TipoEnfermedad;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimieto_tipo_enfermedad {

    EntityManager em;

    public List<TipoEnfermedad> consultar() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<TipoEnfermedad> listatipo = null;
        try {
            Query q = em.createNamedQuery("TipoEnfermedad.findAll", TipoEnfermedad.class);
            listatipo = q.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
        return listatipo;
    }

    public int insertar(TipoEnfermedad tipoenfermedad) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(tipoenfermedad);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }

    public TipoEnfermedad porID(int id_tipo_enfermedad) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TipoEnfermedad departamento = null;
        em.getTransaction().begin();
        try {
            departamento = em.find(TipoEnfermedad.class, id_tipo_enfermedad);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return departamento;
    }
    
    public int eliminar(int id_tipo_enfermedad) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        TipoEnfermedad dep = null;
        try {
            dep = em.find(TipoEnfermedad.class, id_tipo_enfermedad);
            em.remove(dep);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        return flag;
    }
    
    public boolean Actualizar(int id_tipo_enfermedad, TipoEnfermedad tip) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        TipoEnfermedad tipo = null;
        em.getTransaction().begin();
        try {

            tipo = em.find(TipoEnfermedad.class, id_tipo_enfermedad);
            tipo.setTipoEnfermedad(tip.getTipoEnfermedad());

            em.getTransaction().commit();
           
        } catch (Exception e) {
            em.getTransaction().rollback(); 
           
            throw new ExceptionInInitializerError(e); 
        } finally {
            em.close();
        }
       return true;
    }

}
