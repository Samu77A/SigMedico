package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Turno;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimientoTurno {

    EntityManager em;

    public List<Turno> consultTodoTurno() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Turno> listaTurno = null;
        try {

            Query q = em.createNamedQuery("Turno.findAll", Turno.class);
            listaTurno = q.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {

            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
        return listaTurno;
    }

    public int insertarTurno(Turno t) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int ban = 0;
        em.getTransaction().begin();
        try {

            em.persist(t);
            em.getTransaction().commit();
            ban = 1;
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

    public Turno consultarIdTurno(int id_turno) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Turno turno = null;
        em.getTransaction().begin();
        try {
            turno = em.find(Turno.class, id_turno);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return turno;
    }

    public boolean actualizarTurno(int id_turno, Turno t) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Turno turno = null;
        em.getTransaction().begin();

        try {
            turno = em.find(Turno.class, id_turno);

            turno.setTipoTurno(t.getTipoTurno());
            turno.setDesde(t.getDesde());
            turno.setHasta(t.getHasta());

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public int eliminarTurno(int id_turno) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Turno t = null;
        em.getTransaction().begin();
        int ban = 0;
        try {
            t = em.find(Turno.class, id_turno);
            em.remove(t);
            em.getTransaction().commit();

            ban = 1;
        } catch (Exception e) {
            ban = 0;
            throw new ExceptionInInitializerError(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return ban;
    }

}
