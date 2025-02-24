package es.cheste.ad_sanidad_di.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "visita")
public class Visita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "id_paciente", referencedColumnName = "id")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "id_medico", referencedColumnName = "id")
	private Medico medico;

	@Column(name = "fecha")
	private LocalDate fecha;

	@Column(name = "hora")
	private int hora;

	@Column(name = "minuto")
	private int minuto;

	// Constructores
	public Visita() {}

	public Visita(Paciente paciente, Medico medico, LocalDate fecha, int hora, int minuto) {
		this.paciente = paciente;
		this.medico = medico;
		this.fecha = fecha;
		setHora(hora); // Usa setter para validación
		setMinuto(minuto); // Usa setter para validación
	}

	// Getters y setters
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }

	public Paciente getPaciente() { return paciente; }
	public void setPaciente(Paciente paciente) { this.paciente = paciente; }

	public Medico getMedico() { return medico; }
	public void setMedico(Medico medico) { this.medico = medico; }

	public LocalDate getFecha() { return fecha; }
	public void setFecha(LocalDate fecha) { this.fecha = fecha; }

	public int getHora() { return hora; }
	public void setHora(int hora) {
		if (hora < 0 || hora > 23) {
			throw new IllegalArgumentException("La hora debe estar entre 0 y 23.");
		}
		this.hora = hora;
	}

	public int getMinuto() { return minuto; }
	public void setMinuto(int minuto) {
		if (minuto < 0 || minuto > 59 || (minuto % 15 != 0)) { // Solo 00, 15, 30, 45
			throw new IllegalArgumentException("Los minutos deben ser 00, 15, 30 o 45.");
		}
		this.minuto = minuto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Visita visita = (Visita) o;
		return id == visita.id && hora == visita.hora && minuto == visita.minuto &&
				Objects.equals(paciente, visita.paciente) &&
				Objects.equals(medico, visita.medico) &&
				Objects.equals(fecha, visita.fecha);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, paciente, medico, fecha, hora, minuto);
	}
	

	@Override
	public String toString() {
		return "Visita{" +
				"id=" + id +
				", paciente=" + paciente +
				", medico=" + medico +
				", fecha=" + fecha +
				", hora=" + String.format("%02d", hora) +
				", minuto=" + String.format("%02d", minuto) +
				'}';
	}
}