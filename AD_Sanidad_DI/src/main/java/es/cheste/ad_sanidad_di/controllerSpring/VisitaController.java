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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/visitas")
public class VisitaController {
	private final VisitaRepository visitaRepository;
	private final PacienteRepository pacienteRepository;
	private final MedicoRepository medicoRepository;

	@Autowired
	public VisitaController(VisitaRepository visitaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
		this.visitaRepository = visitaRepository;
		this.pacienteRepository = pacienteRepository;
		this.medicoRepository = medicoRepository;
	}

	@PostMapping
	public ResponseEntity<Visita> crearVisita(@RequestBody VisitaRequest visitaRequest) {
		try {
			// Validar fecha no anterior a hoy
			if (visitaRequest.getFecha().isBefore(LocalDate.now())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha no puede ser anterior a hoy.");
			}

			Paciente paciente = pacienteRepository.findById(visitaRequest.getPacienteId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paciente no encontrado"));

			Medico medico = medicoRepository.findById(visitaRequest.getMedicoId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Médico no encontrado"));

			// Validar si ya existe una cita para el médico en esa fecha, hora y minuto
			List<Visita> citasExistentes = visitaRepository.findByMedicoIdAndFechaAndHoraAndMinuto(
					medico.getId(), visitaRequest.getFecha(), visitaRequest.getHora(), visitaRequest.getMinuto());
			if (!citasExistentes.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "El médico ya tiene una cita a esa hora.");
			}

			Visita visita = new Visita();
			visita.setPaciente(paciente);
			visita.setMedico(medico);
			visita.setFecha(visitaRequest.getFecha());
			visita.setHora(visitaRequest.getHora());
			visita.setMinuto(visitaRequest.getMinuto());

			return ResponseEntity.ok(visitaRepository.save(visita));
		} catch (ResponseStatusException e) {
			throw e;
		}
	}

	// Otros métodos (sin cambios relevantes para esta query)
	@GetMapping
	public List<Visita> obtenerVisitas() {
		return visitaRepository.findAll();
	}
}
