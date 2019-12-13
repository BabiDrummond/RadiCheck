package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long historicoId;

    @ManyToOne
    private Paciente paciente;

    @OneToMany(mappedBy = "historico")
    private List<Exame> exames = new ArrayList<>();

    private double nivelRadiacao;

    private String risco;

    private final double NIVEL_NORMAL = 2.4;

    public void setNivelRadiacao(double radiacao) {
        nivelRadiacao += radiacao;

    }
    public String setRisco(double nivelRadiacao){
        double idade = (Calendar.getInstance().get(Calendar.YEAR)) - (paciente.getDataNascimento().getYear());
        if (nivelRadiacao*idade <= NIVEL_NORMAL*idade) {
            risco = "Normal";
        } else {
            risco = "Acima do normal";
        }
        return risco;
    }
}
