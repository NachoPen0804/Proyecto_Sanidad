package es.cheste.ad_sanidad_di.service;

import es.cheste.ad_sanidad_di.model.Paciente;
import es.cheste.ad_sanidad_di.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
	private final PacienteRepository pacienteRepository;

	public PacienteService(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}
	public List<Paciente> findAll() {
		return pacienteRepository.findAll();
	}
	public Paciente save(Paciente paciente){
		return pacienteRepository.save(paciente);
	}
	public Paciente findById(Long id){
		return pacienteRepository.findById(id).orElse(null);
	}
	public void deleteById(Long id){
		pacienteRepository.deleteById(id);
	}
	
}
