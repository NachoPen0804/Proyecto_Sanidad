package es.cheste.ad_sanidad_di.DTO;

import es.cheste.ad_sanidad_di.model.Visita;

import java.util.List;

public class VisitaResponseDTO {
	private List<Visita> visitas;

	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}
}
