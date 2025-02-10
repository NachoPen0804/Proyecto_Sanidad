package es.cheste.ad_sanidad_di.api;
import com.fasterxml.jackson.core.type.TypeReference;

import es.cheste.ad_sanidad_di.model.Paciente;

public class PacienteApiClient extends ApiClientBase<Paciente>{
	public PacienteApiClient() {
		super("http://localhost:8080/api/pacientes");
	}
	@Override
	protected TypeReference<Paciente> getTypeReference() {
		return new TypeReference<Paciente>() {
		};
	}
}
