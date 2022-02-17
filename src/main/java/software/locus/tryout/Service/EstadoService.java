package software.locus.tryout.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.locus.tryout.Model.Estado;
import software.locus.tryout.Repository.EstadoRepository;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado saveEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    public List<Estado> getEstados() {
        return estadoRepository.findAll();
    }

}
