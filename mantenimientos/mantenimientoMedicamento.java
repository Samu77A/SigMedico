/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import persistencia.Medicamento;

/**
 *
 * @author marvin.guzmanUSAM
 */
public class mantenimientoMedicamento {

    EntityManager em;

    public int guardar(Medicamento medicamento) {

        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        int ban = 0;
        try {
            em.persist(medicamento);
            em.getTransaction().commit();
            ban = 1;
        } catch (Exception e) {
            ban = 0;
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        }
        return ban;
    }

    public List<Medicamento> consultarTodo() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Medicamento> listamed = null;

        try {
            Query q = em.createNamedQuery("Medicamento.findAll", Medicamento.class);
            listamed = q.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        }
        return listamed;
    }

    public Medicamento consultarById(int id_medicamento) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Medicamento listamed = null;

        try {
            listamed = em.find(Medicamento.class, id_medicamento);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        }
        return listamed;
    }

    public boolean actualizarMedi(int id_medicamento, Medicamento m) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Medicamento medicamento = null;

        try {
            medicamento = em.find(Medicamento.class, id_medicamento);
            medicamento.setMedicamento(m.getMedicamento());
            medicamento.setFechaVencimiento(m.getFechaVencimiento());

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return true;

    }

    public int eliminar(int id_medicamento) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        int ban = 0;
        Medicamento m = null;
        try {
            m = em.find(Medicamento.class, id_medicamento);
            em.remove(m);
            em.getTransaction().commit();
            ban =1;
        } catch (Exception e) {
            ban =0;
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
