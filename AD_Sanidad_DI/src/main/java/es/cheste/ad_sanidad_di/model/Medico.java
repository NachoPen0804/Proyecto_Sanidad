package es.cheste.ad_sanidad_di.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.Objects;


@Entity
@Table(name = "medico")
public class Medico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		
	long id;
	
	@Column(name = "nombre", nullable = false)
	String nombre;
	
	@Column(name = "apellidos", nullable = false)
	String apellidos;

	@ManyToOne
	@JoinColumn(name = "id_hospital", referencedColumnName = "id")
	Hospital hospital;

	@Column(name = "password", nullable = false)
	String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Medico{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", apellidos='" + apellidos + '\'' +
				", hospital=" + hospital +
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

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Medico medico = (Medico) o;
		return id == medico.id && Objects.equals(nombre, medico.nombre) && Objects.equals(apellidos, medico.apellidos) && Objects.equals(hospital, medico.hospital);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, apellidos, hospital);
	}

	public Medico(long id, String nombre, String apellidos, Hospital hospital) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.hospital = hospital;
	}

	public Medico() {
	}
}
