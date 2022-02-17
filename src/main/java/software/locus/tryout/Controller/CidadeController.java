package software.locus.tryout.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import software.locus.tryout.Model.Cidade;
import software.locus.tryout.Service.CidadeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "API REST Cities")
@CrossOrigin(origins = "*")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping("/addCidade")
    @Operation(summary = "Add a city to the database")
    public Cidade addCidade(@RequestBody Cidade cidade) {
        if (cidadeService.saveCidade(cidade)) {
            throw new ResponseStatusException(HttpStatus.OK, "City created.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

    @GetMapping("/cidades")
    @Operation(summary = "Returns a list of cities (Parameters are optional)")
    public List<Cidade> findAllCidades(@RequestParam(required = false) Map<String, String> params) {
        return cidadeService.getCidades(params);
    }

    @PutMapping("/updateCidade")
    @Operation(summary = "Updates a city information")
    public Cidade updateCidade(@RequestBody Cidade cidade) {
        if (cidadeService.updateCidade(cidade)) {
            throw new ResponseStatusException(HttpStatus.OK, "City updated.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

    @DeleteMapping("/deleteCidade/{id}")
    @Operation(summary = "Deletes a city")
    public String deleteCidade(@PathVariable int id) {
        if (cidadeService.deleteCidade(id)) {
            throw new ResponseStatusException(HttpStatus.OK, "City deleted created.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }
}
