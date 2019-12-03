package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger historicoId;
    @ManyToOne
    private Paciente paciente;
    @OneToMany(mappedBy = "historico", cascade = CascadeType.ALL)
    private List<Exame> exames;

    private BigDecimal nivelRadiacao;
    private String risco;
}
