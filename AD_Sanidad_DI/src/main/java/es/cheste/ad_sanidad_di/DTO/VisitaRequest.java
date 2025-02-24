package es.cheste.ad_sanidad_di.DTO;

import java.time.LocalDate;

public class VisitaRequest {
	private Long pacienteId;  // Cambiado de String paciente a Long pacienteId
	private Long medicoId;    // Cambiado de String medico a Long medicoId
	private LocalDate fecha;
	private int hora;
	private int minuto;

	// Getters y setters
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

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
}