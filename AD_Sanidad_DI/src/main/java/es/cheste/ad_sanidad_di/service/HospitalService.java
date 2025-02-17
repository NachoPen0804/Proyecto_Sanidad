package es.cheste.ad_sanidad_di.service;

import es.cheste.ad_sanidad_di.model.Hospital;
import es.cheste.ad_sanidad_di.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
	@Autowired
	private  HospitalRepository hospitalRepository;
	
	public List<Hospital> findAll() {
		return hospitalRepository.findAll();
	}
	
	public Hospital save(Hospital hospital){
		return hospitalRepository.save(hospital);
	}
	
	public Hospital findById(Long id){
		return hospitalRepository.findById(id).orElse(null);
	}
	
	public void deleteById(Long id){
		hospitalRepository.deleteById(id);
	}
}
