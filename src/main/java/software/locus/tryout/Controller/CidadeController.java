package software.locus.tryout.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import software.locus.tryout.Model.Cidade;
import software.locus.tryout.Service.CidadeService;

import java.util.List;
import java.util.Map;

@RestController
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping("/addCidade")
    public Cidade addCidade(@RequestBody Cidade cidade) {
        if (cidadeService.saveCidade(cidade)) {
            throw new ResponseStatusException(HttpStatus.OK, "City created.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

    @GetMapping("/cidades")
    public List<Cidade> findAllCidades(@RequestParam(required = false) Map<String, String> params) {
        return cidadeService.getCidades(params);
    }

    @PutMapping("/updateCidade")
    public Cidade updateCidade(@RequestBody Cidade cidade) {
        if (cidadeService.updateCidade(cidade)) {
            throw new ResponseStatusException(HttpStatus.OK, "City updated.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

    @DeleteMapping("/deleteCidade/{id}")
    public String deleteCidade(@PathVariable int id) {
        if (cidadeService.deleteCidade(id)) {
            throw new ResponseStatusException(HttpStatus.OK, "City deleted created.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }
}
