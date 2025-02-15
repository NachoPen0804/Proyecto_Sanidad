package es.cheste.ad_sanidad_di.controllerSpring;

import es.cheste.ad_sanidad_di.model.Hospital;
import es.cheste.ad_sanidad_di.repository.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hospitales")
public class HospitalController {
    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping
    public List<Hospital> obtenerHospitales() {
        return hospitalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> obtenerHospitalPorId(@PathVariable Long id) {
        return hospitalRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Hospital crearHospital(@RequestBody Hospital hospital) {
        return hospitalRepository.save(hospital);
    }
} 