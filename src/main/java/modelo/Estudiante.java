package modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Estudiante extends Persona{
	
	@Column
	private Date fechaDeIngreso;
	
	public Estudiante() {
	}
	
	public Estudiante(int identificacion, Identificacion tipoIdentificacion, 
			String nombre, String apellido, Ciudades ciudad, Departamentos departamento, Date fechaDeIngreso) {
		
		super(identificacion, tipoIdentificacion, nombre, apellido, ciudad, departamento);
		this.fechaDeIngreso = fechaDeIngreso;
	}
	
	public Date getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	public void setFechaDeIngreso(Date fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}

}
