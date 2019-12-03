package repository;

import model.Paciente;
import java.util.List;

/**
 * Interface created to be used for PacienteRepositoryImpl class.
 *
 * @author barbaradrummond
 */
public interface PacienteRepository {
    /**
     * Method that saves an instance to the database.
     *
     * @param paciente Type: Paciente
     */
    void save(Paciente paciente);

    /**
     * Method that removes an instance of database.
     *
     * @param paciente Type: Paciente
     */
    void remove(Paciente paciente);

    /** Method that removes all instances of database. */
    void removeAll();

    /**
     * Method that gets an instance by id.
     *
     * @param cpf Type: String
     * @return Paciente
     */
    Paciente findByCpf(String cpf);

    /**
     * Method that gets all instances.
     *
     * @return List of Paciente
     */
    List<Paciente> findAll();
}
