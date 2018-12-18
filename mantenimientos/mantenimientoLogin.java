package mantenimientos;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Datos;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimientoLogin {

    EntityManager em;

    public static void main(String[] args) {
        mantenimientoLogin ingre = new mantenimientoLogin();

        
            /* Datos d = ingre.validarD("samu", "samu");
            if (d != null) {
                System.out.println("usuario correcto " + d.getUsuario() + " " + d.getIdRole());
            } else {
                System.out.println("Error");
            }*/

 /*boolean r = ingre.actualizarcontra(1, "sam");
           if(r){
               System.out.println("si");
           }else{
               System.out.println("no");
           }*/

    }

    public Datos validarD(String usuario, String clave) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();

        Query qUno = em.createNamedQuery("Datos.todo", Datos.class).setParameter("usuario", usuario).setParameter("clave", clave);
        List<Datos> lista = qUno.getResultList();

        if (!lista.isEmpty()) {

            Query qTres = em.createNamedQuery("Datos.finByTipo", Datos.class).setParameter("usuario", usuario);
            List<Datos> list = qTres.getResultList();

            if (!list.isEmpty()) {

                return lista.get(0);
            }
        } else {

            System.out.println("Error contraseña");
        }

        return null;
    }

    public Datos validarPin(String usuario, String pin) {

        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            Query qUno = em.createNamedQuery("Datos.todoClave", Datos.class).setParameter("usuario", usuario).setParameter("pin", pin);
            List<Datos> listaUser = qUno.getResultList();

            if (!listaUser.isEmpty()) {
                return listaUser.get(0);
            } else {
                System.out.println("Error pin");
            }

            System.out.println("Error usuario");

        } catch (Exception e) {

            System.out.println("Error en metodo pin");
            throw new ExceptionInInitializerError(e);
        } finally {

            if (em != null) {

                em.close();
            }
        }

        return null;
    }

    public boolean actualizarcontra(int id_datos, String pass) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Datos uc = em.find(Datos.class, id_datos);
            uc.setClave(pass);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                em.getTransaction().rollback();

            }
            throw new ExceptionInInitializerError(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


}
