package es.cheste.ad_sanidad_di.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "hospital")

public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(name = "nombre", nullable = false)
	String nombre;

	@Column(name = "localidad", nullable = true)
	String localidad;

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

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Hospital(long id, String nombre, String localidad) {
		this.id = id;
		this.nombre = nombre;
		this.localidad = localidad;
	}

	public Hospital() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hospital hospital = (Hospital) o;
		return id == hospital.id && Objects.equals(nombre, hospital.nombre) && Objects.equals(localidad, hospital.localidad);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, localidad);
	}

	@Override
	public String toString() {
		return "Hospital{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", localidad='" + localidad + '\'' +
				'}';
	}
}
