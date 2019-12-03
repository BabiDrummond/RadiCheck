package repository;

import java.math.BigInteger;
import model.Usuario;
import java.util.List;

/**
 * Interface created to be used for UsuarioRepositoryImpl class.
 *
 * @author barbaradrummond
 */
public interface UsuarioRepository {
    /**
     * Method that saves an instance to the database.
     *
     * @param usuario Type: Usuario
     */
    void save(Usuario usuario);

    /**
     * Method that removes an instance of database.
     *
     * @param usuario Type: Usuario
     */
    void remove(Usuario usuario);

    /** Method that removes all instances of database. */
    void removeAll();

    /**
     * Method that gets an instance by id.
     *
     * @param id Type: BigInteger
     * @return Usuario
     */
    Usuario findById(BigInteger id);

    /**
     * Method that gets all instances.
     *
     * @return List of Usuario
     */
    List<Usuario> findAll();
}
