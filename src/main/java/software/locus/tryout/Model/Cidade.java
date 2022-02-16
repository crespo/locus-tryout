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
    private String name;

    @Column(name = "populacao")
    private int population;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

}
