package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Depto;
import persistencia.Muni;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimientoMuni {
    
    EntityManager em;
    
    public static void main(String[] args) {
        
        Muni m  = new Muni();
        Depto de = new Depto();
        mantenimientoMuni manteMuni = new mantenimientoMuni();
        
        /*int d = Integer.parseInt("1");

        de.setIdDepto(d);
            
        int id_muni = 2;
        m.setIdDepto(de);
        m.setMuni("Soyapango");
      
        boolean r = manteMuni.actualizarMuni(id_muni, m);
        
        if(r){
            System.out.println("si");
        }else{
            System.out.println("no");
        }*/
        
        
    }
    

    public List<Muni> consultTodoMuni() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Muni> listaMuni = null;
        try {
            Query q = em.createNamedQuery("Muni.findAll", Muni.class);
            listaMuni = q.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
        return listaMuni;
    }

    public int guardar(Muni mun) {
        int flag = 0;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(mun);
            em.getTransaction().commit();
            flag = 1;

        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;

        } finally {
            if (em != null) {
                em.close();
            }

        }
        return flag;
    }

    public Muni consultarIdDato(int id_muni) {

        Muni munilista = null;
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            munilista = em.find(Muni.class, id_muni);
            em.getTransaction().rollback();

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);

        }
        return munilista;

    }

    public boolean actualizarMuni(int id_muni, Muni mu) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Muni muni = null;
        em.getTransaction().begin();

        try {
   
            muni = em.find(Muni.class, id_muni);
            muni.setIdDepto(mu.getIdDepto());
            muni.setMuni(mu.getMuni());
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {
            em.close();
        }
        return true;
    }

    public int eliminar(int id_muni) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int ban = 0;
        Muni m = null;
        em.getTransaction().begin();

        try {
            m = em.find(Muni.class, id_muni);
            em.remove(m);

            em.getTransaction().commit();

            ban = 1;
        } catch (Exception e) {

            em.close();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return ban;

    }

}
