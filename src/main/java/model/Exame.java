package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long exameId;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataExame;

    @Column(nullable = false)
    private double radiacao;

    @Column(nullable = false)
    private String regiaoCorpo;

    @ManyToOne (optional = true)
    private Historico historico;
}
