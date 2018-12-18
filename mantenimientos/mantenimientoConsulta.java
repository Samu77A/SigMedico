package mantenimientos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import static jdk.nashorn.internal.objects.NativeFunction.call;
import org.eclipse.persistence.internal.sessions.ArrayRecord;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.DataReadQuery;
import org.eclipse.persistence.queries.StoredFunctionCall;
import org.eclipse.persistence.queries.StoredProcedureCall;
import persistencia.Consulta;

/**
 *
 * @author samuel.alvaradoUSAM
 */
public class mantenimientoConsulta {

    EntityManager em;

    public int insertarConsulta(Consulta c) {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Consulta con = null;
        em.getTransaction().begin();
        int ban = 0;
        try {
            em.persist(c);
            em.getTransaction().commit();
            ban = c.getIdConsulta();
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
    
    
    
}
