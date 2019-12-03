package repository;

import java.math.BigInteger;
import model.Exame;
import java.util.List;

/**
 * Interface created to be used for ExameRepositoryImpl class.
 *
 * @author barbaradrummond
 */
public interface ExameRepository {
    /**
     * Method that saves an instance to the database.
     *
     * @param exame Type: Exame
     */
    void save(Exame exame);

    /**
     * Method that removes an instance of database.
     *
     * @param exame Type: Exame
     */
    void remove(Exame exame);

    /** Method that removes all instances of database. */
    void removeAll();

    /**
     * Method that gets an instance by id.
     *
     * @param id Type: String
     * @return Exame
     */
    Exame findById(BigInteger id);

    /**
     * Method that gets all instances.
     *
     * @return List of Exame
     */
    List<Exame> findAll();
}
