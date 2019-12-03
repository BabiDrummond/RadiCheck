package repository;

import java.math.BigInteger;
import model.Historico;
import java.util.List;

/**
 * Interface created to be used for HistoricoRepositoryImpl class.
 *
 * @author barbaradrummond
 */
public interface HistoricoRepository {
    /**
     * Method that saves an instance to the database.
     *
     * @param historico Type: Historico
     */
    void save(Historico historico);

    /**
     * Method that removes an instance of database.
     *
     * @param historico Type: Historico
     */
    void remove(Historico historico);

    /** Method that removes all instances of database. */
    void removeAll();

    /**
     * Method that gets an instance by id.
     *
     * @param id Type: String
     * @return Historico
     */
    Historico findById(BigInteger id);

    /**
     * Method that gets all instances.
     *
     * @return List of Historico
     */
    List<Historico> findAll();
}
