package software.locus.tryout.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import software.locus.tryout.Model.Estado;
import software.locus.tryout.Service.EstadoService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "API REST States")
@CrossOrigin(value = "*")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @PostMapping("/addEstado")
    @Operation(summary = "Add a state to the database")
    public Estado addEstado(@RequestBody Estado estado) {
        if (estadoService.saveEstado(estado)) {
            throw new ResponseStatusException(HttpStatus.OK, "State created.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

    @GetMapping("/estados")
    @Operation(summary = "Returns a list of states")
    public List<Estado> findAllEstados() {
        return estadoService.getEstados();
    }

    @PutMapping("/updateEstado")
    @Operation(summary = "Updates a state information")
    public Estado updateEstado(@RequestBody Estado estado) {
        if (estadoService.updateEstado(estado)) {
            throw new ResponseStatusException(HttpStatus.OK, "State updated.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

    @DeleteMapping("/deleteEstado/{id}")
    @Operation(summary = "Deletes a state")
    public String deleteEstado(@PathVariable int id) {
        if (estadoService.deleteEstado(id)) {
            throw new ResponseStatusException(HttpStatus.OK, "State updated.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

}
