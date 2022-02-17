package software.locus.tryout.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.locus.tryout.Model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> { }
