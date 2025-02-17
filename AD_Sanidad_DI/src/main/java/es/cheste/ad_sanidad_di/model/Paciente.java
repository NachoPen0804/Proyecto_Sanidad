package es.cheste.ad_sanidad_di.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "paciente")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(name = "nombre", nullable = false)
	String nombre;
	
	@Column(name = "apellidos", nullable = false)
	String apellidos;
	
	@Column(name = "pueblo_residencia")
	String pueblo_residencia;

	@Override
	public String toString() {
		return nombre.toUpperCase() + " " + apellidos.toUpperCase();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPueblo_residencia() {
		return pueblo_residencia;
	}

	public void setPueblo_residencia(String pueblo_residencia) {
		this.pueblo_residencia = pueblo_residencia;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Paciente paciente = (Paciente) o;
		return id == paciente.id && Objects.equals(nombre, paciente.nombre) && Objects.equals(apellidos, paciente.apellidos) && Objects.equals(pueblo_residencia, paciente.pueblo_residencia);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, apellidos, pueblo_residencia);
	}

	public Paciente(long id, String nombre, String apellidos, String pueblo_residencia) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.pueblo_residencia = pueblo_residencia;
	}

	public Paciente() {
	}
}
