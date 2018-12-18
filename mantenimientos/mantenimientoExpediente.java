package mantenimientos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import persistencia.Datos;
import persistencia.Datos_;
import persistencia.Expediente;
import persistencia.Expediente_;


/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimientoExpediente {

    EntityManager em;

    public static void main(String[] args) {

        mantenimientoExpediente mE = new mantenimientoExpediente();

        /*Expediente ex = mE.consultarIdExpe(1);

        System.out.println("-------------------");
        System.out.println("Id " + ex.getIdExpediente());
        System.out.println("NOmbre " + ex.getIdDatos().getNombres());
        System.out.println("Expe " + ex.getCodExpediente());
        System.out.println("FEcha " + ex.getFechaCreacion());
        System.out.println("estado " + ex.getEstado());*/
        
    

    }

    /**
     * *****************
     * @param metodoIsertar Expediente ****************
     */
    public int insertarExpe(Expediente expe) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int ban = 0;
        em.getTransaction().begin();

        try {
            em.persist(expe);
            em.getTransaction().commit();

            ban = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return ban;
    }

    /**
     * ***************
     * @param metodoConsult Expediente ***************
     */
    public List consultarTodoExpe() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Expediente> listExpe = null;

        try {
            Query q = em.createNamedQuery("Expediente.findAll", Expediente.class);
            listExpe = q.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return listExpe;
    }

    public Expediente consultarIdExpe(int id_expediente) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Expediente expe = null;
        em.getTransaction().begin();

        try {
            expe = em.find(Expediente.class, id_expediente);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error" + e);
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        } finally {
            if (em != null) {
                em.close();
            }
        }
        return expe;
    }

    public boolean actualizarExpe(int id_expediente, Expediente e) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Expediente expe = null;
        em.getTransaction().begin();

        try {
            expe = em.find(Expediente.class, id_expediente);

            expe.setIdDatos(e.getIdDatos());
            expe.setCodExpediente(e.getCodExpediente());
            expe.setFechaCreacion(e.getFechaCreacion());
            expe.setEstado(e.getEstado());
            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(ex);

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return true;
    }

    public int EliminarExpe(int id_expediente) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Expediente expe = null;
        em.getTransaction().begin();
        int ban = 0;

        try {
            expe = em.find(Expediente.class, id_expediente);
            em.remove(expe);
            em.getTransaction().commit();
            ban = 1;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return ban;
    }

}
