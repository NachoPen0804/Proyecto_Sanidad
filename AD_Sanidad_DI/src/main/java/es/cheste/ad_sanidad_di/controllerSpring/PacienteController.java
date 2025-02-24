package es.cheste.ad_sanidad_di.controllerSpring;

import es.cheste.ad_sanidad_di.model.Paciente;
import es.cheste.ad_sanidad_di.repository.PacienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

	@PutMapping("/{id}")
	public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
		return pacienteRepository.findById(id)
				.map(pacienteExistente -> {
					pacienteExistente.setNombre(paciente.getNombre());
					pacienteExistente.setApellidos(paciente.getApellidos());
					pacienteExistente.setPueblo_residencia(paciente.getPueblo_residencia());
					pacienteExistente.setTelefono(paciente.getTelefono()); // Nuevo campo
					pacienteExistente.setPassword(paciente.getPassword());
					Paciente pacienteActualizado = pacienteRepository.save(pacienteExistente);
					return ResponseEntity.ok(pacienteActualizado);
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePaciente(@PathVariable Long id) {
		return pacienteRepository.findById(id)
				.map(paciente -> {
					pacienteRepository.delete(paciente);
					return ResponseEntity.noContent().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}/password")
	public ResponseEntity<Paciente> obtenerPacientePorIdYPassword(@PathVariable Long id, @RequestParam String password) {
		Optional<Paciente> paciente = pacienteRepository.findByIdAndPassword(id, password);
		return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
	}
}