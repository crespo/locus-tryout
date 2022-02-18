package software.locus.tryout.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
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
    @Operation(
            summary = "Add a city to the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid City Name supplied")
            })
    public Cidade addCidade(@RequestBody Cidade cidade) {
        if (cidadeService.saveCidade(cidade)) {
            throw new ResponseStatusException(HttpStatus.OK, "City created.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

    @GetMapping("/cidades")
    @Operation(summary = "Returns a list of cities")
    @Parameter(in = ParameterIn.QUERY, name = "nome", description = "Search by name", example = "Recif")
    @Parameter(in = ParameterIn.QUERY, name = "populacao_min", description = "Filter by the minimum value of population", example = "500")
    @Parameter(in = ParameterIn.QUERY, name = "populacao_max", description = "Filter by the maximum value of population", example = "1000")
    @Parameter(in = ParameterIn.QUERY, name = "ordenarNome", description = "Sort by name", examples = {
            @ExampleObject(name = "asc", value = "asc", description = "Ascending order"),
            @ExampleObject(name = "desc", value = "desc", description = "Descending order")
    })
    @Parameter(in = ParameterIn.QUERY, name = "ordenarPopulacao", description = "Sort by population", examples = {
            @ExampleObject(name = "asc", value = "asc", description = "Ascending order"),
            @ExampleObject(name = "desc", value = "desc", description = "Descending order")
    })
    @Parameter(in = ParameterIn.QUERY, name = "ordenarEstado", description = "Sort by state", examples = {
            @ExampleObject(name = "asc", value = "asc", description = "Ascending order"),
            @ExampleObject(name = "desc", value = "desc", description = "Descending order")
    })
    public List<Cidade> findAllCidades(@RequestParam(required = false) Map<String, String> params) {
        return cidadeService.getCidades(params);
    }

    @PutMapping("/updateCidade")
    @Operation(
            summary = "Updates a city information",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid City ID supplied")
            })
    public Cidade updateCidade(@RequestBody Cidade cidade) {
        if (cidadeService.updateCidade(cidade)) {
            throw new ResponseStatusException(HttpStatus.OK, "City updated");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name supplied");
        }
    }

    @DeleteMapping("/deleteCidade/{id}")
    @Operation(
            summary = "Deletes a city",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid City ID supplied")
            })
    public String deleteCidade(@PathVariable int id) {
        if (cidadeService.deleteCidade(id)) {
            throw new ResponseStatusException(HttpStatus.OK, "City deleted created.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }
}
