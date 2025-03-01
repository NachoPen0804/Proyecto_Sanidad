package es.cheste.ad_sanidad_di.service;

import es.cheste.ad_sanidad_di.model.Medico;
import es.cheste.ad_sanidad_di.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
	@Autowired
	private MedicoRepository medicoRepository;

	
	public List<Medico> findAll() {
		return medicoRepository.findAll();
	}

	public Medico save(Medico medico) {
		return medicoRepository.save(medico);
	}

	public Medico findById(Long id) {
		return medicoRepository.findById(id).orElse(null);
	}

	public void deleteById(Long id) {
		medicoRepository.deleteById(id);
	}
}
