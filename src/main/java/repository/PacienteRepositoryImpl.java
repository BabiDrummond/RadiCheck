package repository;

import connection.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.Paciente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that implements methods of interface for Paciente class.
 *
 * @author barbaradrummond
 */
public class PacienteRepositoryImpl implements PacienteRepository {
    private EntityManager entityManager = Connection.getConnection();
    private static final Logger LOGGER = LogManager.getLogger(PacienteRepositoryImpl.class.getName());

    /**
     * Method that saves a new paciente.
     *
     * @param paciente Type: Paciente
     */
    @Override
    public void save(Paciente paciente) {
        try {
            if (entityManager.contains(paciente)) {
                entityManager.getTransaction().begin();
                entityManager.merge(paciente);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().begin();
                entityManager.persist(paciente);
                entityManager.getTransaction().commit();
            }
            LOGGER.info("Paciente saved: {}", paciente.getCpf());
        } catch (Exception exception) {
            LOGGER.error("Fail to save new paciente: {}. {}", paciente.getCpf(), exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Method that removes a paciente.
     *
     * @param paciente Type: Paciente
     */
    @Override
    public void remove(Paciente paciente) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(paciente);
            entityManager.getTransaction().commit();
            LOGGER.info("Paciente removed: {}", paciente.getCpf());
        } catch (Exception exception) {
            LOGGER.error("Fail to remove paciente {}: {}", paciente, exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /** Method that removes all pacientes. */
    @Override
    public void removeAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from Paciente").executeUpdate();
            entityManager.getTransaction().commit();
            LOGGER.info("Removed all pacientes.");
        } catch (Exception exception) {
            LOGGER.error("Fail to remove all pacientes: {}", exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Method to get a paciente by its Id.
     *
     * @param cpf Type: String
     * @return Paciente
     */
    @Override
    public Paciente findByCpf(String cpf) {
        Paciente paciente = null;

        try {
            paciente = entityManager.find(Paciente.class, cpf);
            LOGGER.info("Found paciente: {}", paciente.getCpf());
        } catch (Exception exception) {
            LOGGER.error("Fail to find paciente by id: {}. {}", cpf, exception.getMessage());
        }
        return paciente;
    }

    /**
     * Method to get all pacientes.
     *
     * @return List of Paciente
     */
    @Override
    public List<Paciente> findAll() {
        List<Paciente> pacientes = new ArrayList<>();

        try {
            pacientes = entityManager.createQuery("from Paciente p").getResultList();
            LOGGER.info("Found Pacientes: {}", pacientes);
        } catch (Exception exception) {
            LOGGER.error("Fail to get all pacientes: {}", exception.getMessage());
        }
        return pacientes;
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
