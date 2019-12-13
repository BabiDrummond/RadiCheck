import connection.Connection;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    private static final int[] VALIDOPTIONS = {1,2,3,9};
    private static Scanner scanner = new Scanner(System.in);
    private static int chooseNumber = 0;
    private static Service service = new Service();

    public static void main(String[] args) {

        do {
            LOGGER.info("Seja bem vindo! Escolha uma das opções: \n" +
                    "1. Cadastro paciente\n" +
                    "2. Cadastro medico\n" +
                    "3. Cadastro exame\n" +
                    "4. Consultar historico\n" +
                    "9. Sair");
            chooseNumber = scanner.nextInt();

            switch(chooseNumber) {
                case 1:
                    service.salvarPaciente();
                    break;
                case 2:
                    service.salvarMedico();
                    break;
                case 3:
                    service.salvarExame();
                    break;
                case 4:
                    service.consultarHistorico();
                    break;
                case 9:
                    LOGGER.info("Encerrando programa.");
      }
        } while (chooseNumber != 9);

        Connection.closeConnection();
    }
}
