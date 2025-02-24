package es.cheste.ad_sanidad_di.DTO;

import es.cheste.ad_sanidad_di.model.Medico;

public class MedicoUpdateDTO {
	private long id;
	private String nombre;
	private String apellidos;
	private long hospitalId; // Solo el ID
	private String password;

	// Constructor
	public MedicoUpdateDTO(Medico medico) {
		this.id = medico.getId();
		this.nombre = medico.getNombre();
		this.apellidos = medico.getApellidos();
		this.hospitalId = medico.getHospital().getId();
		this.password = medico.getPassword();
	}

	// Getters y setters
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public String getApellidos() { return apellidos; }
	public void setApellidos(String apellidos) { this.apellidos = apellidos; }
	public long getHospitalId() { return hospitalId; }
	public void setHospitalId(long hospitalId) { this.hospitalId = hospitalId; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
}