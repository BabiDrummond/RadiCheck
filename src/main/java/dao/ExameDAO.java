package dao;

import connection.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.Exame;
import model.Historico;
import model.Paciente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that implements methods of interface for Exame class.
 *
 * @author barbaradrummond
 */
public class ExameDAO {
    private EntityManager entityManager = Connection.getConnection();
    private static final Logger LOGGER = LogManager.getLogger(ExameDAO.class.getName());

    /**
     * Method that saves a new exame.
     *
     * @param exame Type: Exame
     */
    public void save(Exame exame) {
        try {
            if (entityManager.contains(exame)) {
                entityManager.getTransaction().begin();
                entityManager.merge(exame);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().begin();
                entityManager.persist(exame);
                entityManager.getTransaction().commit();
            }
            LOGGER.info("Exame saved: {}", exame.getExameId());
        } catch (Exception exception) {
            LOGGER.error("Fail to save new exame: {}. {}", exame.getExameId(), exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Method that removes a exame.
     *
     * @param exame Type: Exame
     */
    public void remove(Exame exame) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(exame);
            entityManager.getTransaction().commit();
            LOGGER.info("Exame removed: {}", exame.getExameId());
        } catch (Exception exception) {
            LOGGER.error("Fail to remove exame {}: {}", exame, exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /** Method that removes all exames. */
    public void removeAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from Exame").executeUpdate();
            entityManager.getTransaction().commit();
            LOGGER.info("Removed all exames.");
        } catch (Exception exception) {
            LOGGER.error("Fail to remove all exames: {}", exception.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * Method to get a exame by its Id.
     *
     * @param exameId Type: Long
     * @return Exame
     */
    public Exame findById(Long exameId) {
        Exame exame = null;

        try {
            exame = entityManager.find(Exame.class, exameId);
            LOGGER.info("Found exame: {}", exame.getExameId());
        } catch (Exception exception) {
            LOGGER.error("Fail to find exame by id: {}. {}", exameId, exception.getMessage());
        }
        return exame;
    }

    /**
     * Method to get all exames.
     *
     * @return List of Exame
     */
    public List<Exame> findAll() {
        List<Exame> exames = new ArrayList<>();

        try {
            exames = entityManager.createQuery("from Exame e").getResultList();
            LOGGER.info("Found Exames: {}", exames);
        } catch (Exception exception) {
            LOGGER.error("Fail to get all exames: {}", exception.getMessage());
        }
        return exames;
    }

    /**
     * Searches an order for orderLines.
     *
     * @param paciente Type: Paciente
     * @return List of Exames
     */
    public List<Exame> getExamesByPaciente(Paciente paciente) {
        List<Exame> exames = new ArrayList<>();

        HistoricoDAO historicoDAO = new HistoricoDAO();
        Historico historico = historicoDAO.getHistoricoByPaciente(paciente);
        for (Exame exame : historico.getExames()) {
            LOGGER.info("Found OrderLine: {}", findById(exame.getExameId()));
            exames.add(exame);
        }
        return exames;
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
