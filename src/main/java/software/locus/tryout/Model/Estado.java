package software.locus.tryout.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue
    @Column(name = "estado_id")
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

}
