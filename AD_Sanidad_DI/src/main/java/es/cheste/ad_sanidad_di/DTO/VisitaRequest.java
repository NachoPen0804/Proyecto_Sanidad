package es.cheste.ad_sanidad_di.DTO;

import java.time.LocalDate;

public class VisitaRequest {
	private Long pacienteId;
	private Long medicoId;
	private LocalDate fecha;
	

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Long getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(Long medicoId) {
		this.medicoId = medicoId;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
