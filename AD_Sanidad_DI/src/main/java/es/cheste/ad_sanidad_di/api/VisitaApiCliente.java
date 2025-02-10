package es.cheste.ad_sanidad_di.api;
import com.fasterxml.jackson.core.type.TypeReference;

import es.cheste.ad_sanidad_di.model.Visita;

public class VisitaApiCliente extends ApiClientBase<Visita>{
	public VisitaApiCliente() {
		super("http://localhost:8080/api/visitas");
	}
	@Override
	protected TypeReference<Visita> getTypeReference() {
		return new TypeReference<Visita>() {
		};
	}
}
