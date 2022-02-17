package software.locus.tryout.Model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cidade")
public class Cidade {

    @Getter
    @Id
    @GeneratedValue
    @Column(name = "cidade_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "nome")
    private String nome;

    @Getter
    @Setter
    @Column(name = "populacao")
    private int populacao;

    @Getter
    @Setter
    @ManyToOne(targetEntity = Estado.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_id", insertable = false, updatable = false)
    private Estado estado;

    @Getter
    @Setter
    @Column(name = "estado_id")
    private int estadoId;

}
