package software.locus.tryout.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.locus.tryout.Model.Estado;
import software.locus.tryout.Service.EstadoService;

import java.util.List;

@RestController
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @PostMapping("/addEstado")
    public Estado addEstado(@RequestBody Estado estado) {
        return estadoService.saveEstado(estado);
    }

    @GetMapping("/estados")
    public List<Estado> findAllEstados() {
        return estadoService.getEstados();
    }

    @GetMapping("/estado/{id}")
    public Estado findEstadoById(@PathVariable int id) {
        return estadoService.getEstadoById(id);
    }

    @GetMapping("/estado/{name}")
    public Estado findEstadoByName(@PathVariable String name) {
        return estadoService.getEstadoByName(name);
    }

//    @PutMapping("/updateEstado")
//    public Estado updateEstado(@RequestBody Estado estado) {
//        return estadoService.updateEstado(estado);
//    }

//    @DeleteMapping("/deleteEstado/{id}")
//    public String deleteEstado(@PathVariable int id) {
//        return estadoService.deleteEstado(id)
//    }
}
