package es.cheste.ad_sanidad_di.api;

import com.fasterxml.jackson.core.type.TypeReference;
import es.cheste.ad_sanidad_di.model.Medico;

public class MedicoApiClient extends ApiClientBase<Medico>{
	public MedicoApiClient() {
		super("http://localhost:8080/api/medicos");
	}
	@Override
	protected TypeReference<Medico> getTypeReference() {
		return new TypeReference<Medico>() {
		};
	}
}
