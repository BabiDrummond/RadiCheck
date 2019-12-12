package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private BigInteger historicoId;

    @ManyToOne
    @Getter @Setter
    private Paciente paciente;

    @OneToMany(mappedBy = "historico")
    @Getter @Setter
    private List<Exame> exames = new ArrayList<>();

    @Getter
    private BigDecimal nivelRadiacao;

    @Getter
    private String risco;

    public void setNivelRadiacao(BigDecimal nivelRadiacaoSoma) {
        nivelRadiacao.add(nivelRadiacaoSoma);
    }
}
