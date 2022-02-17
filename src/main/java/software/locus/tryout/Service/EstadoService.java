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

    public boolean saveEstado(Estado estado) {
        if (estadoRepository.existsById(estado.getId())) {
            estadoRepository.save(estado);
            return true;
        } else {
            return false;
        }
    }

    public List<Estado> getEstados() {
        return estadoRepository.findAll();
    }

    public boolean updateEstado(Estado estado) {
        Estado existingEstado = estadoRepository
                                    .findById(estado.getId())
                                    .orElse(null);

        if (existingEstado != null) {
            existingEstado.setNome(estado.getNome());
            estadoRepository.save(existingEstado);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteEstado(int id) {
        if (estadoRepository.existsById(id)) {
            estadoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
