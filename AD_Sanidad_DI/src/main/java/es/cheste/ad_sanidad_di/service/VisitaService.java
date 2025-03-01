package es.cheste.ad_sanidad_di.service;

import es.cheste.ad_sanidad_di.model.Visita;
import es.cheste.ad_sanidad_di.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitaService {
	@Autowired
	private VisitaRepository visitaRepository;
	
	public List<Visita> findAll() {
		return visitaRepository.findAll();
	}
	public Visita save(Visita visita){
		return visitaRepository.save(visita);
	}
	public Visita findById(Long id){
		return visitaRepository.findById(id).orElse(null);
	}
	public void deleteById(Long id){
		visitaRepository.deleteById(id);
	}
	
}
