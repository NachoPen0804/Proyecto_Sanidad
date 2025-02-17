package es.cheste.ad_sanidad_di.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Entity

@Table(name = "visita")
public class Visita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@ManyToOne
	@JoinColumn(name = "id_paciente", referencedColumnName = "id")
	Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "id_medico", referencedColumnName = "id")
	Medico medico;
	
	@Column(name = "fecha")
	LocalDate fecha;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Visita(long id, Paciente paciente, Medico medico, LocalDate fecha) {
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.fecha = fecha;
	}

	public Visita() {
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Visita visita = (Visita) o;
		return id == visita.id && Objects.equals(paciente, visita.paciente) && Objects.equals(medico, visita.medico) && Objects.equals(fecha, visita.fecha);
	}

	@Override
	public String toString() {
		return "Visita{" +
				"id=" + id +
				", paciente=" + paciente +
				", medico=" + medico+
				", fecha=" + fecha +
				'}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, paciente, medico, fecha);
	}
}
