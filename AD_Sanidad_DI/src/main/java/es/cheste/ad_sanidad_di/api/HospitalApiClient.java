package es.cheste.ad_sanidad_di.api;
import com.fasterxml.jackson.core.type.TypeReference;

import es.cheste.ad_sanidad_di.model.Hospital;

public class HospitalApiClient extends ApiClientBase<Hospital>{
	public HospitalApiClient() {
		super("http://localhost:8080/api/hospitales");
	}
	@Override
	protected TypeReference<Hospital> getTypeReference() {
		return new TypeReference<Hospital>() {
		};
	}
}
