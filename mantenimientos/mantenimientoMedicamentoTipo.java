/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.MedicamentoTipo;

/**
 *
 * @author marvin.guzmanUSAM
 */
public class mantenimientoMedicamentoTipo {

    EntityManager em;
    
    public static void main(String[] args) {
        
    }

    public int guardarMT(MedicamentoTipo m) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        int ban = 0;
        try {
            em.persist(m);
            em.getTransaction().commit();
            ban = 1;
        } catch (Exception e) {
            ban = 0;
            em.getTransaction().rollback();

        }
        return ban;

    }

    public List<MedicamentoTipo> consultarTodo() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<MedicamentoTipo> listamt = null;
        try {
            Query q = em.createNamedQuery("MedicamentoTipo.findAll", MedicamentoTipo.class);
            listamt = q.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        }
        return listamt;
    }

    public MedicamentoTipo consultaByID(int id_medicamento_tipo) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        MedicamentoTipo listamt = null;

        try {
            listamt = em.find(MedicamentoTipo.class, id_medicamento_tipo);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        }
        return listamt;
    }

    public boolean actualizarMedTipo(int id_medicamento_tipo, MedicamentoTipo mt) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        MedicamentoTipo medtipo = null;
        
        try {
            medtipo = em.find(MedicamentoTipo.class, id_medicamento_tipo);
            medtipo.setIdMedicamento(mt.getIdMedicamento());
            medtipo.setIdTipo(mt.getIdTipo());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
        return true;
    }

    public int eliminar(int id_medicamento_tipo) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        int ban = 0;
        MedicamentoTipo m = null;
        try {
            m = em.find(MedicamentoTipo.class, id_medicamento_tipo);
            em.remove(m);

            em.getTransaction().commit();
            ban = 1;
        } catch (Exception e) {
            em.close();

        }
        return ban;
    }

}
