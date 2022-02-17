package software.locus.tryout.Model;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estado")
public class Estado {

    @Getter
    @Id
    @GeneratedValue
    @Column(name = "estado_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "nome")
    private String name;

}
