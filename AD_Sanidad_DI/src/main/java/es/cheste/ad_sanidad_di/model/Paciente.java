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

	@Column(name = "telefono", length = 9) // Longitud fija de 9 dígitos
	String telefono; // Usamos String para facilitar la validación y presentación

	@Column(name = "password", nullable = false)
	private String password;

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		// Validación básica: debe ser un número de 9 dígitos
		if (telefono != null && !telefono.matches("\\d{9}")) {
			throw new IllegalArgumentException("El teléfono debe ser un número de 9 dígitos.");
		}
		this.telefono = telefono;
	}

	// Resto de getters y setters
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Paciente{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", apellidos='" + apellidos + '\'' +
				", pueblo_residencia='" + pueblo_residencia + '\'' +
				", telefono='" + telefono + '\'' +
				'}';
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
		return id == paciente.id && Objects.equals(nombre, paciente.nombre) &&
				Objects.equals(apellidos, paciente.apellidos) &&
				Objects.equals(pueblo_residencia, paciente.pueblo_residencia) &&
				Objects.equals(telefono, paciente.telefono);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, apellidos, pueblo_residencia, telefono);
	}

	// Constructores actualizados
	public Paciente(String nombre, String apellidos, String pueblo_residencia, String telefono, String password) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.pueblo_residencia = pueblo_residencia;
		setTelefono(telefono); // Usar el setter para validación
		this.password = password;
	}

	public Paciente(long id, String nombre, String apellidos, String pueblo_residencia, String telefono, String password) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.pueblo_residencia = pueblo_residencia;
		setTelefono(telefono); // Usar el setter para validación
		this.password = password;
	}

	public Paciente() {
	}
}