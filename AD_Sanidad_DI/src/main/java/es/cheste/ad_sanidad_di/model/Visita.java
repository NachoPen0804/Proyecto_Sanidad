package es.cheste.ad_sanidad_di.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	@Column(name = "id_hospital")
	private Long idHospital;


}
