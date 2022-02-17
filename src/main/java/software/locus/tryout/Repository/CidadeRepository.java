package software.locus.tryout.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.locus.tryout.Model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    boolean existsByNome(String nome);
}
