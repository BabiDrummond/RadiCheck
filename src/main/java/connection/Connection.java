package connection;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Class defined to create an entity manager connection.
 *
 * @author barbaradrummond
 */
public class Connection {
    private static EntityManager entityManager;

    /**
     * Method that creates an entity manager connection.
     *
     * @return EntityManager
     */
    public static EntityManager getConnection() {
        entityManager =
                Persistence.createEntityManagerFactory("br.com.radicheck")
                        .createEntityManager();
        return entityManager;
    }

    /** Method to close entity manager connection. */
    public static void closeConnection() {
        entityManager.close();
    }
}
