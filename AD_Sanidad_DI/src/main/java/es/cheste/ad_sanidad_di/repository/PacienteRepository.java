package es.cheste.ad_sanidad_di.repository;

import es.cheste.ad_sanidad_di.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	/*
	save(S entity): Guarda una entidad.
	
	saveAll(Iterable<S> entities): Guarda todas las entidades dadas.
	
	findById(ID id): Encuentra una entidad por su ID.
	
	existsById(ID id): Verifica si una entidad existe por su ID.
	
	findAll(): Encuentra todas las entidades.
	
	findAllById(Iterable<ID> ids): Encuentra todas las entidades por sus IDs.
	
	count(): Cuenta el número de entidades.
	
	deleteById(ID id): Elimina una entidad por su ID.
	
	delete(T entity): Elimina una entidad.
	
	deleteAll(Iterable<? extends T> entities): Elimina todas las entidades dadas.
	
	deleteAll(): Elimina todas las entidades
	 */
}
