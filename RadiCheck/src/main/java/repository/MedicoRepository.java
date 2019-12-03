package repository;

import model.Medico;
import java.util.List;

/**
 * Interface created to be used for MedicoRepositoryImpl class.
 *
 * @author barbaradrummond
 */
public interface MedicoRepository {
    /**
     * Method that saves an instance to the database.
     *
     * @param medico Type: Medico
     */
    void save(Medico medico);

    /**
     * Method that removes an instance of database.
     *
     * @param medico Type: Medico
     */
    void remove(Medico medico);

    /** Method that removes all instances of database. */
    void removeAll();

    /**
     * Method that gets an instance by id.
     *
     * @param crm Type: String
     * @return Medico
     */
    Medico findByCrm(String crm);

    /**
     * Method that gets all instances.
     *
     * @return List of Medico
     */
    List<Medico> findAll();
}
