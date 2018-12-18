/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Tipo;

/**
 *
 * @author marvin.guzmanUSAM
 */
public class mantenimientoTipo {
  
    
    EntityManager em;
    public int guardarTipo(Tipo tip){
        
    em=JpaUtil.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();
    int ban=0;
        try {
            em.persist(tip);
            em.getTransaction().commit();
        ban=1;
        } catch (Exception e) {
            ban=0;
            em.getTransaction().rollback();
        }
        return ban;
        
        }
    
    public List<Tipo> consultarTodo(){
    
    em=JpaUtil.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();
    List<Tipo> listatipo=null;
        try {
            Query q = em.createNamedQuery("Tipo.findAll", Tipo.class);
            listatipo= q.getResultList();
            em.getTransaction().commit();
            
                    
        } catch (Exception e) {
            em.getTransaction().rollback();
        throw new ExceptionInInitializerError(e);
        }
    return listatipo;
            }
    
    public Tipo consultarByID(int id_tipo){
      em=JpaUtil.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();
    Tipo listatip= null;
        try {
            listatip=em.find(Tipo.class, id_tipo);
            em.getTransaction().commit();
        } catch (Exception e) {
        em.getTransaction().rollback();
        throw new ExceptionInInitializerError(e);
        
        }
        return listatip;
    }
    public boolean actualizarTip(int id_tipo, Tipo t){
     em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Tipo tipo= null;
        try {
            tipo=em.find(Tipo.class, id_tipo);
            tipo.setTipoMedicamento(t.getTipoMedicamento());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
            
        }finally{
        if(em!=null){
        em.close();
        }
        }
        return true;
    }
public int eliminar(int id_tipo){
  em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        int ban=0;
        Tipo t=null;
        try {
        t=em.find(Tipo.class, id_tipo);
        em.remove(t);
        em.getTransaction().commit();
        ban=1;
                
    } catch (Exception e) {
    em.close();
    }
        return ban;
}    
}
