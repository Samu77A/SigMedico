package mantenimientos;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.EntityType;
import persistencia.Datos;
import persistencia.Datos_;

import persistencia.Expediente;

import persistencia.Muni;
import persistencia.Role;
import persistencia.Role_;
import persistencia.Turno;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimientoDatos {

    EntityManager em;

    public static void main(String[] args) {

        mantenimientoDatos dato = new mantenimientoDatos();

        /*List<Datos> lista = dato.consultarTodoDatos();
           
            Iterator i = lista.iterator();
            
            while(i.hasNext()){
                Datos d = (Datos) i.next();
                System.out.println("--------");
                System.out.println("Nombre: " + d.getNombres());
                System.out.println("Apellido: " + d.getApellidos());
                System.out.println("User: " + d.getUsuario());
            }
 
            Datos d = new Datos();
            Role r = new Role();
            Turno t = new Turno();
            Muni m = new Muni();
            
            int ro = 2;
            r.setIdRole(ro);
            int tu = 2;
            t.setIdTurno(tu);
            int mu = 2;
            m.setIdMuni(mu);
            
            d.setIdDatos(0);
            d.setNombres("adonay");
            d.setApellidos("alvarado");
            d.setTel("7878-1245");
            d.setIdRole(r);
            d.setIdTurno(t);
            d.setIdMuni(m);
            d.setDui("05015494-0");
            d.setNit("0614-290209-012-3");
            d.setEdad(22);
            d.setDireccion("san salvador");
            d.setUsuario("dsamu");
            d.setClave("salvarado");
            d.setCorreo("al@gmail.com");
            d.setPin("nadie2332");
            d.setEstado("A");
            d.setGenero("masculino");
            d.setResponsable("nada");
            d.setTelResponsable("7878-1212");
            d.setCodigoH("Doc12");
            
            int in = dato.insert(d);
            if(in >0){
                System.out.println("Ingreso");
            }else{
                System.out.println("NO ingreso");
            }*/
 /*List<Datos> list = dato.consultaDatosPaci(3);

        Iterator ite = list.iterator();
        while (ite.hasNext()) {
            Datos d = (Datos) ite.next();
            System.out.println("nombre " + d.getNombres());
            System.out.println("Role " + d.getIdRole());
            System.out.println("Usuario " + d.getUsuario());
        }*/

 /* List<Datos> lista = dato.consultaDatosPaci(3);
      for(Datos d: lista){
          
            System.out.println("--------");
                System.out.println("Nombre: " + d.getNombres());
                System.out.println("Apellido: " + d.getApellidos());
                System.out.println("User: " + d.getUsuario());
                System.out.println("Role " + d.getIdRole().getRole());
                System.out.println("Muni " + d.getIdMuni().getMuni());
                 System.out.println("--------");
       }*/
        /**
         * ***************
         * @param consultar Doctores
         *
         **************
         */
        List<Datos> lista = dato.consultarIdMedico(2);
        for (Datos d : lista) {

            System.out.println("Nombre: " + d.getNombres());
            System.out.println("Apellido: " + d.getApellidos());
            System.out.println("User: " + d.getUsuario());
            System.out.println("Role " + d.getIdRole().getRole());
            System.out.println("Muni " + d.getIdMuni().getMuni());
            System.out.println("Codigo " + d.getCodigoH());
            System.out.println("--------");
        }
    }

    public int insert(Datos datos) {
        int ban = 0;
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(datos);
            em.getTransaction().commit();

            ban = 1;
        } catch (Exception e) {
            ban = 0;
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError("Error assa" + e);

        } finally {
            if (em != null) {
                em.close();
            }
        }
        return ban;
    }

    public List consultarTodoDatos() {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<Datos> listaDatos = null;
        em.getTransaction().begin();

        try {
            Query q = em.createNamedQuery("Datos.findAll", Datos.class);
            listaDatos = q.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {

            if (em != null) {

                em.close();
            }
        }
        return listaDatos;
    }

    public Datos consultarIdDato(int id_datos) {
        Datos datosLista = null;
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        try {

            datosLista = em.find(Datos.class, id_datos);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        }
        return datosLista;
    }

    public boolean actualizarDatos(int id_datos, Datos dato) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Datos datos = null;
        try {

            datos = em.find(Datos.class, id_datos);

            datos.setNombres(dato.getNombres());
            datos.setApellidos(dato.getApellidos());
            datos.setTel(dato.getTel());
            datos.setDui(dato.getDui());
            datos.setNit(dato.getNit());
            datos.setEdad(dato.getEdad());
            datos.setDireccion(dato.getDireccion());
            datos.setUsuario(dato.getUsuario());
            datos.setClave(dato.getClave());
            datos.setCorreo(dato.getCorreo());
            datos.setPin(dato.getPin());
            datos.setEstado(dato.getEstado());
            datos.setGenero(dato.getGenero());
            datos.setResponsable(dato.getResponsable());
            datos.setTelResponsable(dato.getTelResponsable());
            datos.setCodigoH(dato.getCodigoH());

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

    public int eliminarDatos(int id_datos) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Datos dato = null;
        int ban = 0;
        try {
            dato = em.find(Datos.class, id_datos);
            em.remove(dato);
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

    public List<Datos> consultaDatosPaci(int id_role) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<Datos> listaPac = null;
        em.getTransaction().begin();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Datos> cq = cb.createQuery(Datos.class);
            if (cq != null) {

                Root<Datos> d = cq.from(Datos.class);
                Join<Datos, Role> datos_role = d.join(Datos_.idRole, JoinType.INNER);

                cq.where(cb.equal(datos_role.get(Role_.idRole), id_role));

                TypedQuery<Datos> q = em.createQuery(cq);
                listaPac = q.getResultList();
                em.getTransaction().commit();
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);

        }
        return listaPac;
    }

    public List<Datos> consultarIdMedico(int id_role) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<Datos> listaDoc = null;
        em.getTransaction().begin();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Datos> cq = cb.createQuery(Datos.class);
            if (cq != null) {
                Root<Datos> d = cq.from(Datos.class);
                Join<Datos, Role> join = d.join(Datos_.idRole, JoinType.INNER);
                cq.where(cb.equal(join.get(Role_.idRole), id_role));
                TypedQuery<Datos> tq = em.createQuery(cq);
                listaDoc = tq.getResultList();

                em.getTransaction().commit();
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ExceptionInInitializerError(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listaDoc;
    }

    public int validarUser(String usuario) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int ban = 0;
        Query qUno = em.createNamedQuery("Datos.findByUsuario", Datos.class).setParameter("usuario", usuario);
        List<Datos> lista = qUno.getResultList();

        if (!lista.isEmpty()) 
        {
            ban = 1;
        } else { ban = 0; }

        return ban;
    }
    
    public int validarDui(String dui) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int ban = 0;
        Query qUno = em.createNamedQuery("Datos.findByDui", Datos.class).setParameter("dui", dui);
        List<Datos> lista = qUno.getResultList();

        if (!lista.isEmpty()) 
        {
            ban = 1;
        } else { ban = 0; }

        return ban;
    }

}
