package mantenimientos;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mantenimientos.JpaUtil;
import persistencia.Areas;

public class mantenimientoAreas {

//    public static void main(String[] args) {
//       mantenimiento_depto md = new mantenimiento_depto();
//       Depto d = new Depto();
//       d.setIdDepto(Integer.SIZE);
//       d.setDepto("prro");
//       if(md.insertar(d)!=0){
//            System.out.println("si funciona prro :v");
//        }else{
//            System.err.println("nel prro ya fue, es por que programa en negro :v");
//        }
//    }
    public int insertar(Areas areas) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        try {
            em.persist(areas);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return flag;
    }

    public List<Areas> consultar() {
        List<Areas> listareas = null;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT d FROM Areas d");
            em.getTransaction().commit();
            listareas = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
        return listareas;
    }

    public Areas porID(int id_areas) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Areas are = null;
        em.getTransaction().begin();
        try {
            are = em.find(Areas.class, id_areas);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return are;
    }

    public static void main(String[] args) {
        mantenimientoAreas a = new mantenimientoAreas();
        System.out.println(a.porID(1));
    }
    
//      public static void main(String[] args) {
//          mantenimiento_depto m =new mantenimiento_depto();
//          Depto c = new Depto();
//          
//          if(m.porID(30)!=null){
//              System.err.println("si funciona prrro :v ");
//          }else{
//                System.err.println("nel prrrro mejor psicologia >:v");
//          }
//        
//    }
    public Areas eliminar(int id_areas) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int flag = 0;
        em.getTransaction().begin();
        Areas are = null;
        try {
            are = em.find(Areas.class, id_areas);
            em.remove(are);
            em.getTransaction().commit();
            flag = 1;
        } catch (Exception e) {
            em.getTransaction().rollback();
            flag = 0;
        } finally {
            em.close();
        }
        return are;
    }
//
//   public int ActualizarDatos(int id_depto) {
//        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
//        Depto dep = null;
//        int flag = 0;
//        em.getTransaction().begin();
//        try {
//           
//            dep = em.find(Depto.class, id_depto);
//            dep.setDepto();              
//            em.getTransaction().commit();
//            flag = 1;
//        } catch (Exception e) {
//            System.out.println("error: update: "+e.getMessage());
//            em.getTransaction().rollback();
//            throw new ExceptionInInitializerError(e);
//        } finally {
//            em.close();
//        }
//        return flag;
//    }
    
    
    public boolean actualizar(int id_areas, Areas ar) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Areas areas = null;
        em.getTransaction().begin();

        try {
   
            areas = em.find(Areas.class, id_areas);
            areas.setIdHospital(ar.getIdHospital());
            areas.setTipoAreas(ar.getTipoAreas());
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {
            em.close();
        }
        return true;
    }
   
// public Areas consultarIdDato(int id_areas) {
//
//        Areas areaslista = null;
//        em = JpaUtil.getEntityManagerFactory().createEntityManager();
//        em.getTransaction().begin();
//        try {
//            munilista = em.find(Muni.class, id_muni);
//            em.getTransaction().rollback();
//
//        } catch (Exception e) {
//            throw new ExceptionInInitializerError(e);
//
//        }
//        return munilista;
//
//    }
    
    
}
