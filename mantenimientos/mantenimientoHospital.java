/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Hospital;

/**
 *
 * @author marvin.guzmanUSAM
 */
public class mantenimientoHospital {

    EntityManager em;

    public int guardarH(Hospital hospital) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        int ban = 0;
        try {
            em.persist(hospital);
            em.getTransaction().commit();
            ban = 1;
        } catch (Exception e) {
            ban = 0;
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        }
        return ban;
    }

    public List<Hospital> consultarTodo() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Hospital> listahosp = null;
        try {
            Query q = em.createNamedQuery("Hospital.findAll", Hospital.class);
            listahosp = q.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        }
        return listahosp;

    }

    public Hospital consultarByID(int id_hospital) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Hospital listahosp = null;

        try {
            listahosp = em.find(Hospital.class, id_hospital);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        }
        return listahosp;
    }

    public boolean actualizarH(int id_hospital, Hospital h) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Hospital hospital = null;

        try {
            hospital = em.find(Hospital.class, id_hospital);
            hospital.setHospital(h.getHospital());
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

    public int eliminar(int id_hospital) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int ban = 0;
        Hospital h = null;
        em.getTransaction().begin();
        try {
            h = em.find(Hospital.class, id_hospital);
            em.remove(h);
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
