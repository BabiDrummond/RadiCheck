package dao;

import connection.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import model.Historico;
import model.Paciente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that implements methods of interface for Historico class.
 *
 * @author barbaradrummond
 */
public class HistoricoDAO {
    private EntityManager entityManager = Connection.getConnection();
    private static final Logger LOGGER = LogManager.getLogger(HistoricoDAO.class.getName());

    /**
     * Method that saves a new historico.
     *
     * @param historico Type: Historico
     */
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
    public Historico findById(Long historicoId) {
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
//            Query query = entityManager.createQuery("SELECT h FROM Historico h WHERE h.paciente=:paciente");
//            query.setParameter("paciente", paciente);
//            historico = (Historico) query.getSingleResult();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Historico> criteriaQuery = criteriaBuilder.createQuery(Historico.class);
            Root<Historico> root = criteriaQuery.from(Historico.class);
            ParameterExpression<Paciente> parameterExpression = criteriaBuilder.parameter(Paciente.class);
            criteriaQuery.where(criteriaBuilder.equal(root.get("paciente"), parameterExpression));

            TypedQuery<Historico> query = entityManager.createQuery(criteriaQuery);
            query.setParameter(parameterExpression, paciente);

            historico = (Historico) query.getSingleResult();

            LOGGER.info("Found historico from paciente: {}", historico.getPaciente());
        } catch (Exception exception) {
            LOGGER.error("Fail to find historico by paciente: {}. {}", historico.getPaciente(), exception.getMessage());
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
