import connection.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import model.Paciente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.PacienteRepositoryImpl;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    private static final int[] VALIDOPTIONS = {1,2,3,9};
    private static Scanner scanner = new Scanner(System.in);
    private static int chooseNumber = 0;

    public static void main(String[] args) {

        do{
            LOGGER.info("Seja bem vindo! Escolha uma das opções: \n" +
                    "1. Cadastro paciente\n" +
                    "2. Cadastro exame\n" +
                    "3. Consultar historico\n" +
                    "9. Sair");
            chooseNumber = scanner.nextInt();

            switch(chooseNumber) {
                case 1:
                    LOGGER.info("Nome do Paciente: ");
                    Paciente paciente = new Paciente();
                    String nomePaciente = scanner.next();
                    paciente.setNome(nomePaciente);
                    LOGGER.info("Dia de nascimento: ");
                    Date dataNascimento = new Date();
                    dataNascimento.setDate(scanner.nextInt());
                    LOGGER.info("Mês de nascimento (em número): ");
                    dataNascimento.setMonth(scanner.nextInt());
                    LOGGER.info("Ano de nascimento: ");
                    dataNascimento.setYear(scanner.nextInt());
                    LOGGER.info("CPF: ");
                    String cpfPaciente = scanner.next();
                    paciente.setCpf(cpfPaciente);
                    LOGGER.info("Telefone: ");
                    String telPaciente = scanner.next();
                    paciente.setTelefone(telPaciente);
                    LOGGER.info("E-mail: ");
                    String emailPaciente = scanner.next();
                    paciente.setEmail(emailPaciente);

                    System.out.println("Paciente {} salvo." + paciente);
                    //PacienteRepositoryImpl pacienteRepositoryImpl = new PacienteRepositoryImpl();
                    //pacienteRepositoryImpl.save(paciente);
                    break;
      }
        } while (chooseNumber != 9);

        Connection.closeConnection();
    }
}
