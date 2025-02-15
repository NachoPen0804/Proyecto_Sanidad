package es.cheste.ad_sanidad_di.controllerSpring;

import es.cheste.ad_sanidad_di.model.Paciente;
import es.cheste.ad_sanidad_di.repository.PacienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente nuevo) {
        return pacienteRepository.save(nuevo);
    }
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> getPaciente(@PathVariable Long id) {
		return pacienteRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

}