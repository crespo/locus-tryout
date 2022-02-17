package software.locus.tryout.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import software.locus.tryout.Model.Estado;
import software.locus.tryout.Service.EstadoService;

import java.util.List;

@RestController
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @PostMapping("/addEstado")
    public Estado addEstado(@RequestBody Estado estado) {
        if (estadoService.saveEstado(estado)) {
            throw new ResponseStatusException(HttpStatus.OK, "State created.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

    @GetMapping("/estados")
    public List<Estado> findAllEstados() {
        return estadoService.getEstados();
    }

    @PutMapping("/updateEstado")
    public Estado updateEstado(@RequestBody Estado estado) {
        if (estadoService.updateEstado(estado)) {
            throw new ResponseStatusException(HttpStatus.OK, "State updated.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

    @DeleteMapping("/deleteEstado/{id}")
    public String deleteEstado(@PathVariable int id) {
        if (estadoService.deleteEstado(id)) {
            throw new ResponseStatusException(HttpStatus.OK, "State updated.");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something's wrong.");
        }
    }

}
