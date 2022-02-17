package software.locus.tryout.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.locus.tryout.Model.Cidade;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    List<Cidade> findByNomeContains(String nome);
}
