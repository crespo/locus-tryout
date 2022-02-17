package software.locus.tryout.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue
    @Column(name = "cidade_id")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "populacao")
    private int populacao;

    @ManyToOne(targetEntity = Estado.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_id", insertable = false, updatable = false)
    private Estado estado;

    @Column(name = "estado_id")
    private int estadoId;

}
