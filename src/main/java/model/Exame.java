package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger exameId;

    @ManyToOne
    @Column(nullable = false)
    private Paciente paciente;

    @ManyToOne
    @Column(nullable = false)
    private Medico medico;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataExame;

    @Column(nullable = false)
    private BigDecimal radiacao;

    @Column(nullable = false)
    private String regiaoCorpo;
}
