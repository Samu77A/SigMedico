package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Depto;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimietoDepto {

    EntityManager em;

    public List<Depto> consultar() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Depto> listaDepto = null;
        try {
            Query q = em.createNamedQuery("Depto.findAll", Depto.class);
            listaDepto = q.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
        return listaDepto;
    }

    public int insertar(Depto Depto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(Depto);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }

    public Depto porID(int id_depto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Depto departamento = null;
        em.getTransaction().begin();
        try {
            departamento = em.find(Depto.class, id_depto);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return departamento;
    }
    
    public Depto eliminar(int id_depto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        Depto dep = null;
        try {
            dep = em.find(Depto.class, id_depto);
            em.remove(dep);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        return dep;
    }
    
    public boolean ActualizarDepto(int id_depto, Depto dep) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        Depto depto = null;
        em.getTransaction().begin();
        try {

            depto = em.find(Depto.class, id_depto);
            depto.setDepto(dep.getDepto());

            em.getTransaction().commit();
           
        } catch (Exception e) {
            em.getTransaction().rollback(); 
           
            throw new ExceptionInInitializerError(e); 
        } finally {
            em.close();
        }
       return true;
    }
    
    public static void main(String[] args) {
        
        int num = (int) Math.round(Math.random()*3);
        
        System.out.println(num);
        
        
    }

}
