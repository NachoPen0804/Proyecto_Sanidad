package es.cheste.ad_sanidad_di.controllerSpring;

import es.cheste.ad_sanidad_di.DTO.VisitaRequest;
import es.cheste.ad_sanidad_di.model.Medico;
import es.cheste.ad_sanidad_di.model.Paciente;
import es.cheste.ad_sanidad_di.model.Visita;
import es.cheste.ad_sanidad_di.repository.MedicoRepository;
import es.cheste.ad_sanidad_di.repository.PacienteRepository;
import es.cheste.ad_sanidad_di.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
public class VisitaController {
	@Autowired
	private final VisitaRepository visitaRepository;
	private final PacienteRepository pacienteRepository; // Añade estas dependencias
	private final MedicoRepository medicoRepository;

	@Autowired
	public VisitaController(
			VisitaRepository visitaRepository,
			PacienteRepository pacienteRepository,
			MedicoRepository medicoRepository
	)	 {
		this.visitaRepository = visitaRepository;
		this.pacienteRepository = pacienteRepository;
		this.medicoRepository = medicoRepository;
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
	public ResponseEntity<Visita> crearVisita(@RequestBody VisitaRequest visitaRequest) {
		try {
			Paciente paciente = pacienteRepository.findById(visitaRequest.getPacienteId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paciente no encontrado"));

			Medico medico = medicoRepository.findById(visitaRequest.getMedicoId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Médico no encontrado"));

			Visita visita = new Visita();
			visita.setPaciente(paciente);
			visita.setMedico(medico);
			visita.setFecha(visitaRequest.getFecha());

			return ResponseEntity.ok(visitaRepository.save(visita));
		} catch (ResponseStatusException e) {
			throw e; // Retorna el error HTTP directamente
		}
	}
}