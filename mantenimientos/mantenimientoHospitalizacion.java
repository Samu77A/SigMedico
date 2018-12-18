/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Hospitalizacion;

/**
 *
 * @author marvin.guzmanUSAM
 */
public class mantenimientoHospitalizacion {
   EntityManager em;
   
   
    public int guardarHosp(Hospitalizacion h){
    em= JpaUtil.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();
    int ban=0;
        try {
            em.persist(h);
            em.getTransaction().commit();
        ban=1;
        } catch (Exception e) {
              ban=0;
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
          
            }
        return ban;
    }
    public List<Hospitalizacion> consultarTodo(){
      em= JpaUtil.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();
    List<Hospitalizacion> listhosp= null;
        try {
            Query q=em.createNamedQuery("Hospitalizacion.findAll", Hospitalizacion.class);
            listhosp=q.getResultList();
            em.getTransaction().commit();
            
        } catch (Exception e) {
         em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);   
        }
        return listhosp;
        
               
}
    public Hospitalizacion consultarByID(int id_hospitalizacion){
          em= JpaUtil.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();
    Hospitalizacion hospi= null;
        try {
            hospi=em.find(Hospitalizacion.class, id_hospitalizacion);
                    em.getTransaction().commit();
                    
        } catch (Exception e) {
        em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
        return hospi;
    }
    public boolean ActualizarHospi(int id_hospitalizacion, Hospitalizacion hp){
    em= JpaUtil.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();
    Hospitalizacion hospp= null;
        try {
            hospp=em.find(Hospitalizacion.class, id_hospitalizacion);
            hospp.setIdHospitalizacion(hp.getIdHospitalizacion());
            hospp.setIdConsulta(hp.getIdConsulta());
            hospp.setFechaHoraEntrada(hp.getFechaHoraEntrada());
            hospp.setFechaHoraSalida(hp.getFechaHoraSalida());
            hospp.setIdHospitalizacion(hp.getIdHospitalizacion());
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
        return true;
    }
    public int eliminarhossp(int id_hospitalizacion){
    em= JpaUtil.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();
    int ban=0;
    Hospitalizacion hp= null;
        try {
            hp=em.find(Hospitalizacion.class, id_hospitalizacion);
            em.getTransaction().commit();
            ban=1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
    return ban;
    }
}
