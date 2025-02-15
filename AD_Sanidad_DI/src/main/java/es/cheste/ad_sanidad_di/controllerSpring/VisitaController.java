package es.cheste.ad_sanidad_di.controllerSpring;

import es.cheste.ad_sanidad_di.model.Visita;
import es.cheste.ad_sanidad_di.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visitas")
public class VisitaController {
    @Autowired
    private final VisitaRepository visitaRepository;

    public VisitaController(VisitaRepository visitaRepository) {
        this.visitaRepository = visitaRepository;
    }

    @GetMapping
    public List<Visita> obtenerVisitas() {
        return visitaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visita> obtenerVisitaPorId(@PathVariable Long id) {
        return visitaRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Visita crearVisita(@RequestBody Visita visita) {
        return visitaRepository.save(visita);
    }
}