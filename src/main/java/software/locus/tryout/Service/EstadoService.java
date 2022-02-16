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

    public Estado getEstadoById(int id) {
        return estadoRepository.findById(id).orElse(null);
    }

    public Estado getEstadoByName(String name) {
        return estadoRepository.findByName(name);
    }

//    public String deleteEstado(int id) {
//        estadoRepository.deleteById(id);
//        return "Estado deleted" + id;
//    }

//    public Estado updateEstado(Estado estado) {
//        Estado existingEstado = estadoRepository.findById(estado.getId()).orElse(null);
//        existingEstado.setName(estado.getName());
//
//        return estadoRepository.save(existingEstado);
//    }


}
