import dao.ExameDAO;
import dao.HistoricoDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;
import model.Exame;
import model.Historico;
import model.Medico;
import model.Paciente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Service {
    private static final Logger LOGGER = LogManager.getLogger(Service.class.getName());
    private static Scanner scanner = new Scanner(System.in);
    private ExameDAO exameDAO = new ExameDAO();
    private HistoricoDAO historicoDAO = new HistoricoDAO();
    private MedicoDAO medicoDAO = new MedicoDAO();
    private PacienteDAO pacienteDAO = new PacienteDAO();

    public void salvarPaciente(){
        Paciente paciente = new Paciente();

        LOGGER.info("Informe CPF do Paciente: ");
        String cpfPaciente = scanner.next();
        paciente.setCpf(cpfPaciente);

        LOGGER.info("Nome: ");
        String nomePaciente = scanner.next();
        paciente.setNome(nomePaciente);

        LOGGER.info("Dia de nascimento: ");
        int dia = (scanner.nextInt());
        LOGGER.info("Mês de nascimento (em número): ");
        int mes = (scanner.nextInt());
        LOGGER.info("Ano de nascimento: ");
        int ano = (scanner.nextInt());
        Date dataNascimento = new Date(ano, mes, dia);
        paciente.setDataNascimento(dataNascimento);

        LOGGER.info("Telefone: ");
        String telPaciente = scanner.next();
        paciente.setTelefone(telPaciente);

        LOGGER.info("E-mail: ");
        String emailPaciente = scanner.next();
        paciente.setEmail(emailPaciente);

        pacienteDAO.save(paciente);
    }

    public void salvarExame(){
        Exame exame = new Exame();

        LOGGER.info("Informe CPF do paciente: ");
        String cpfPaciente = scanner.next();
        exame.setPaciente(pacienteDAO.findByCpf(cpfPaciente));

        LOGGER.info("Informe CRM do médico: ");
        String crmMedico = scanner.next();
        exame.setMedico(medicoDAO.findByCrm(crmMedico));

        LOGGER.info("Dia do exame: ");
        int dia = (scanner.nextInt());
        LOGGER.info("Mês (em número): ");
        int mes = (scanner.nextInt());
        LOGGER.info("Ano: ");
        int ano = (scanner.nextInt());
        Date dataExame= new Date(ano, mes, dia);
        exame.setDataExame(dataExame);

        LOGGER.info("Nível de radiação: ");
        BigDecimal radiacao = scanner.nextBigDecimal();
        exame.setRadiacao(radiacao);

        LOGGER.info("Região do corpo: ");
        String regiaoCorpo = scanner.next();
        exame.setRegiaoCorpo(regiaoCorpo);

        exameDAO.save(exame);
        atualizaHistorico(cpfPaciente, exame, radiacao);
    }

    public void salvarMedico(){
        Medico medico = new Medico();

        LOGGER.info("Informe CRM do Medico: ");
        String crmMedico = scanner.next();
        medico.setCrm(crmMedico);

        LOGGER.info("Especialidade: ");
        String especialidade = scanner.next();
        medico.setEspecialidade(especialidade);

        LOGGER.info("Nome: ");
        String nomeMedico = scanner.next();
        medico.setNome(nomeMedico);

        LOGGER.info("Dia de nascimento: ");
        int dia = (scanner.nextInt());
        LOGGER.info("Mês de nascimento (em número): ");
        int mes = (scanner.nextInt());
        LOGGER.info("Ano de nascimento: ");
        int ano = (scanner.nextInt());
        Date dataNascimento = new Date(ano, mes, dia);
        medico.setDataNascimento(dataNascimento);

        LOGGER.info("Telefone: ");
        String telMedico = scanner.next();
        medico.setTelefone(telMedico);

        LOGGER.info("E-mail: ");
        String emailMedico = scanner.next();
        medico.setEmail(emailMedico);

        medicoDAO.save(medico);
    }

    public void atualizaHistorico(String cpfPaciente, Exame exame, BigDecimal radiacao) {
        Historico historico = historicoDAO.getHistoricoByPaciente(pacienteDAO.findByCpf(cpfPaciente));
        historico.getExames().add(exame);
        historico.setPaciente(pacienteDAO.findByCpf(cpfPaciente));
        historico.setNivelRadiacao(radiacao);

        BigDecimal radiacaoTotal = historico.getNivelRadiacao();
//        if(radiacaoTotal > new BigDecimal("2.0") ){
//        }

        historicoDAO.save(historico);
    }

    public Historico consultarHistorico() {
        LOGGER.info("Informe CPF do paciente para o qual deseja consultar o histórico: ");
        String cpfPaciente = scanner.next();

        return historicoDAO.getHistoricoByPaciente(pacienteDAO.findByCpf(cpfPaciente));
    }
}
