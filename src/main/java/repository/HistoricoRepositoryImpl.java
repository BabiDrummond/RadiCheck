package repository;

import connection.Connection;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.Exame;
import model.Historico;
import model.Paciente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that implements methods of interface for Historico class.
 *
 * @author barbaradrummond
 */
public class HistoricoRepositoryImpl implements HistoricoRepository {
    private EntityManager entityManager = Connection.getConnection();
    private static final Logger LOGGER = LogManager.getLogger(HistoricoRepositoryImpl.class.getName());

    /**
     * Method that saves a new historico.
     *
     * @param historico Type: Historico
     */
    @Override
    public void save(Historico historico) {
        try {
            if (entityManager.contains(historico)) {
                entityManager.getTransaction().begin();
                entityManager.merge(historico);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().begin();
                entityManager.persist(historico);
                entityManager.getTransaction().commit();
            }
            LOGGER.info("Historico saved: {}", historico.getHistoricoId());
        } catch (Exception exception) {
            LOGGER.error("Fail to save new historico: {}. {}", historico.getHistoricoId(), exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Method that removes a historico.
     *
     * @param historico Type: Historico
     */
    @Override
    public void remove(Historico historico) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(historico);
            entityManager.getTransaction().commit();
            LOGGER.info("Historico removed: {}", historico.getHistoricoId());
        } catch (Exception exception) {
            LOGGER.error("Fail to remove historico {}: {}", historico, exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /** Method that removes all historicos. */
    @Override
    public void removeAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from Historico").executeUpdate();
            entityManager.getTransaction().commit();
            LOGGER.info("Removed all historicos.");
        } catch (Exception exception) {
            LOGGER.error("Fail to remove all historicos: {}", exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Method to get a historico by its Id.
     *
     * @param historicoId Type: Long
     * @return Historico
     */
    @Override
    public Historico findById(BigInteger historicoId) {
        Historico historico = null;

        try {
            historico = entityManager.find(Historico.class, historicoId);
            LOGGER.info("Found historico: {}", historico.getHistoricoId());
        } catch (Exception exception) {
            LOGGER.error("Fail to find historico by id: {}. {}", historicoId, exception.getMessage());
        }
        return historico;
    }

    /**
     * Method to get all historicos.
     *
     * @return List of Historico
     */
    @Override
    public List<Historico> findAll() {
        List<Historico> historicos = new ArrayList<>();

        try {
            historicos = entityManager.createQuery("from Historico h").getResultList();
            LOGGER.info("Found Historicos: {}", historicos);
        } catch (Exception exception) {
            LOGGER.error("Fail to get all historicos: {}", exception.getMessage());
        }
        return historicos;
    }

    /**
     * Searches an order for orderLines.
     *
     * @param paciente Type: Paciente
     * @return Historico
     */
    public Historico getHistoricoByPaciente(Paciente paciente) {
        Historico historico = null;

        try {
            historico = entityManager.find(Historico.class, paciente.getCpf());
            LOGGER.info("Found historico from paciente: {}", historico.getPaciente().getCpf());
        } catch (Exception exception) {
            LOGGER.error("Fail to find historico by paciente: {}. {}", historico.getPaciente().getCpf(), exception.getMessage());
        }
        return historico;
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
