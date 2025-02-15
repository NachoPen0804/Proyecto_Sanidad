package es.cheste.ad_sanidad_di.controllerSpring;

import es.cheste.ad_sanidad_di.model.Medico;
import es.cheste.ad_sanidad_di.repository.MedicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @GetMapping
    public List<Medico> obtenerMedicos() {
        return medicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedicoPorId(@PathVariable Long id) {
        return medicoRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medico crearMedico(@RequestBody Medico medico) {
        return medicoRepository.save(medico);
    }
}