package software.locus.tryout.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.locus.tryout.Model.Cidade;
import software.locus.tryout.Repository.CidadeRepository;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade saveCidade(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> getCidades() {
        return cidadeRepository.findAll();
    }

    public List<Cidade> getCidadesByName(String name) {
        return cidadeRepository.findByNameContains(name);
    }

    public Cidade updateCidade(Cidade cidade) {
        Cidade existingCidade = cidadeRepository.findById(cidade.getId()).orElse(null);
        existingCidade.setName(cidade.getName());
        existingCidade.setPopulation(cidade.getPopulation());
        existingCidade.setEstado(cidade.getEstado());

        return cidadeRepository.save(existingCidade);
    }

    public String deleteCidade(int id) {
        cidadeRepository.deleteById(id);

        return "Estado " + id + " deleted.";
    }
}
