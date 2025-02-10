package es.cheste.ad_sanidad_di.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
