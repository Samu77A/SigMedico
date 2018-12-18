package mantenimientos;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mantenimientos.JpaUtil;
import persistencia.Enfermedad;

public class mantenimientoEnfermedad {

    public int insertar(Enfermedad enfermedad) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(enfermedad);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }

    public List<Enfermedad> consultar() {
        List<Enfermedad> listeEnfermedads = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT e FROM Enfermedad e", Enfermedad.class);
            em.getTransaction().commit();
            listeEnfermedads = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listeEnfermedads;
    }

    public Enfermedad porID(int id_enfermedad) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Enfermedad are = null;
        em.getTransaction().begin();
        try {
            are = em.find(Enfermedad.class, id_enfermedad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return are;
    }

    public static void main(String[] args) {
        mantenimientoEnfermedad a = new mantenimientoEnfermedad();
        System.out.println(a.porID(1));
    }
    

    public int eliminar(int id_enfermedad) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        Enfermedad en = null;
        try {
            en = em.find(Enfermedad.class, id_enfermedad);
            em.remove(en);
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

    
    public boolean actualizar(int id_enfermedad, Enfermedad ef) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Enfermedad area = null;
        em.getTransaction().begin();

        try {
   
            area = em.find(Enfermedad.class, id_enfermedad);
            area.setIdTipoEnfermedad(ef.getIdTipoEnfermedad());
            area.setEnfermedad(ef.getEnfermedad());
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } 
        return true;
    }
   

    
}
