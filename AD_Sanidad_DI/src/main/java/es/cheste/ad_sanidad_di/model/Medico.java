package es.cheste.ad_sanidad_di.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "medico")
@AllArgsConstructor
@NoArgsConstructor
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

}
