
package mantenimientos;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author samuel.alvaradoUSAM
 */
public class JpaUtil {
    
    public static final EntityManagerFactory emf;
    
    static{
        try {
            emf = Persistence.createEntityManagerFactory("hospitalPU");
        } catch (Exception ex) {
            System.err.println("Error en crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    
}
