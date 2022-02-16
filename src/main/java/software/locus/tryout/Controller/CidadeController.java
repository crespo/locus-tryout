package software.locus.tryout.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.locus.tryout.Model.Cidade;
import software.locus.tryout.Service.CidadeService;

import java.util.List;

@RestController
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping("/addCidade")
    public Cidade addCidade(@RequestBody Cidade cidade) {
        return cidadeService.saveCidade(cidade);
    }

    @GetMapping("/cidades")
    public List<Cidade> findAllCidades(@RequestParam(required = false) String nome) {
        if (nome != null) {
            return cidadeService.getCidadesByName(nome);
        }
        return cidadeService.getCidades();
    }

    @PutMapping("/updateCidade")
    public Cidade updateCidade(@RequestBody Cidade cidade) {
        return cidadeService.updateCidade(cidade);
    }

    @DeleteMapping("/deleteCidade/{id}")
    public String deleteCidade(@PathVariable int id) {
        return cidadeService.deleteCidade(id);
    }
}
