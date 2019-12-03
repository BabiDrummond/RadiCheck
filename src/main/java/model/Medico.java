package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Usuario {
    @Id
    @Column(nullable = false)
    private String crm;
    @Column(nullable = false)
    private String especialidade;
}
