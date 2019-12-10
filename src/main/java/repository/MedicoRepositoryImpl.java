package repository;

import connection.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.Medico;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that implements methods of interface for Medico class.
 *
 * @author barbaradrummond
 */
public class MedicoRepositoryImpl implements MedicoRepository {
    private EntityManager entityManager = Connection.getConnection();
    private static final Logger LOGGER = LogManager.getLogger(MedicoRepositoryImpl.class.getName());

    /**
     * Method that saves a new medico.
     *
     * @param medico Type: Medico
     */
    @Override
    public void save(Medico medico) {
        try {
            if (entityManager.contains(medico)) {
                entityManager.getTransaction().begin();
                entityManager.merge(medico);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().begin();
                entityManager.persist(medico);
                entityManager.getTransaction().commit();
            }
            LOGGER.info("Medico saved: {}", medico.getCrm());
        } catch (Exception exception) {
            LOGGER.error("Fail to save new medico: {}. {}", medico.getCrm(), exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Method that removes a medico.
     *
     * @param medico Type: Medico
     */
    @Override
    public void remove(Medico medico) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(medico);
            entityManager.getTransaction().commit();
            LOGGER.info("Medico removed: {}", medico.getCrm());
        } catch (Exception exception) {
            LOGGER.error("Fail to remove medico {}: {}", medico, exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /** Method that removes all medicos. */
    @Override
    public void removeAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from Medico").executeUpdate();
            entityManager.getTransaction().commit();
            LOGGER.info("Removed all medicos.");
        } catch (Exception exception) {
            LOGGER.error("Fail to remove all medicos: {}", exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Method to get a medico by its Id.
     *
     * @param crm Type: String
     * @return Medico
     */
    @Override
    public Medico findByCrm(String crm) {
        Medico medico = null;

        try {
            medico = entityManager.find(Medico.class, crm);
            LOGGER.info("Found medico: {}", medico.getCrm());
        } catch (Exception exception) {
            LOGGER.error("Fail to find medico by id: {}. {}", crm, exception.getMessage());
        }
        return medico;
    }

    /**
     * Method to get all medicos.
     *
     * @return List of Medico
     */
    @Override
    public List<Medico> findAll() {
        List<Medico> medicos = new ArrayList<>();

        try {
            medicos = entityManager.createQuery("from Medico m").getResultList();
            LOGGER.info("Found Medicos: {}", medicos);
        } catch (Exception exception) {
            LOGGER.error("Fail to get all medicos: {}", exception.getMessage());
        }
        return medicos;
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
