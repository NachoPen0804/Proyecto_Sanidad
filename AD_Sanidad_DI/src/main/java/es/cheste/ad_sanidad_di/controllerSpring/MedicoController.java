package es.cheste.ad_sanidad_di.controllerSpring;

import es.cheste.ad_sanidad_di.DTO.MedicoUpdateDTO;
import es.cheste.ad_sanidad_di.model.Hospital;
import es.cheste.ad_sanidad_di.model.Medico;
import es.cheste.ad_sanidad_di.repository.HospitalRepository;
import es.cheste.ad_sanidad_di.repository.MedicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    private final MedicoRepository medicoRepository;
    private final HospitalRepository hospitalRepository; // Inyectar HospitalRepository

    public MedicoController(MedicoRepository medicoRepository, HospitalRepository hospitalRepository) {
        this.medicoRepository = medicoRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping
    public List<Medico> obtenerMedicos() {
        return medicoRepository.findAll();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable Long id, @RequestBody Medico medico) {
        return medicoRepository.findById(id)
                .map(medicoExistente -> {
                    medicoExistente.setNombre(medico.getNombre());
                    medicoExistente.setApellidos(medico.getApellidos());
                    if (medico.getHospital() != null && medico.getHospital().getId() != 0) { // Verificar que hospital no sea null
                        Hospital hospital = hospitalRepository.findById(medico.getHospital().getId())
                                .orElseThrow(() -> new IllegalArgumentException("Hospital no encontrado con ID: " + medico.getHospital().getId()));
                        medicoExistente.setHospital(hospital);
                    } else {
                        // Si hospital es null o tiene ID 0, mantener el hospital existente
                        // O lanzar una excepción si hospital es obligatorio
                        if (medicoExistente.getHospital() == null) {
                            throw new IllegalArgumentException("El hospital es obligatorio para el médico.");
                        }
                    }
                    medicoExistente.setPassword(medico.getPassword());
                    Medico medicoActualizado = medicoRepository.save(medicoExistente);
                    return ResponseEntity.ok(medicoActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
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

    @GetMapping("/{id}/password")
    public ResponseEntity<Medico> obtenerMedicoPorIdYPassword(@PathVariable Long id, @RequestParam String password) {
        Optional<Medico> medico = medicoRepository.findByIdAndPassword(id, password);
        return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }
}