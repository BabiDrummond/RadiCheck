package repository;

import connection.Connection;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that implements methods of interface for Usuario class.
 *
 * @author barbaradrummond
 */
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private EntityManager entityManager = Connection.getConnection();
    private static final Logger LOGGER = LogManager.getLogger(UsuarioRepositoryImpl.class.getName());

    /**
     * Method that saves a new usuario.
     *
     * @param usuario Type: Usuario
     */
    @Override
    public void save(Usuario usuario) {
        try {
            if (entityManager.contains(usuario)) {
                entityManager.getTransaction().begin();
                entityManager.merge(usuario);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().begin();
                entityManager.persist(usuario);
                entityManager.getTransaction().commit();
            }
            LOGGER.info("Usuario saved: {}", usuario.getNome());
        } catch (Exception exception) {
            LOGGER.error("Fail to save new usuario: {}. {}", usuario.getNome(), exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Method that removes a usuario.
     *
     * @param usuario Type: Usuario
     */
    @Override
    public void remove(Usuario usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(usuario);
            entityManager.getTransaction().commit();
            LOGGER.info("Usuario removed: {}", usuario.getNome());
        } catch (Exception exception) {
            LOGGER.error("Fail to remove usuario {}: {}", usuario, exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /** Method that removes all usuarios. */
    @Override
    public void removeAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from Usuario").executeUpdate();
            entityManager.getTransaction().commit();
            LOGGER.info("Removed all usuarios.");
        } catch (Exception exception) {
            LOGGER.error("Fail to remove all usuarios: {}", exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Method to get a usuario by its Id.
     *
     * @param usuarioId Type: Long
     * @return Usuario
     */
    @Override
    public Usuario findById(BigInteger usuarioId) {
        Usuario usuario = null;

        try {
            usuario = entityManager.find(Usuario.class, usuarioId);
            LOGGER.info("Found usuario: {}", usuario.getNome());
        } catch (Exception exception) {
            LOGGER.error("Fail to find usuario by id: {}. {}", usuarioId, exception.getMessage());
        }
        return usuario;
    }

    /**
     * Method to get all usuarios.
     *
     * @return List of Usuario
     */
    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            usuarios = entityManager.createQuery("from Usuario u").getResultList();
            LOGGER.info("Found Usuarios: {}", usuarios);
        } catch (Exception exception) {
            LOGGER.error("Fail to get all usuarios: {}", exception.getMessage());
        }
        return usuarios;
    }

    /**
     * Method that sets an entity manager.
     *
     * @param entityManager Type: EntityManager
     */
    void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
