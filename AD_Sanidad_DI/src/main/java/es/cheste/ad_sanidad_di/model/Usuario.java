package es.cheste.ad_sanidad_di.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Usuario {
	@Id
	private String nombre;
	@Column(name = "contraseña")
	private String contraseña;

	public Usuario(String nombre, String contraseña) {
		this.nombre = nombre;
		this.contraseña = contraseña;
	}

	public Usuario() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(nombre, usuario.nombre) && Objects.equals(contraseña, usuario.contraseña);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, contraseña);
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"nombre='" + nombre + '\'' +
				", contraseña='" + contraseña + '\'' +
				'}';
	}
}
