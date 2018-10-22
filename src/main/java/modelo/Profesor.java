package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Profesor extends Persona {
	
	@Column
	private String area;

	public Profesor(int identificacion, Identificacion tipoIdentificacion, String nombre, String apellido,
			Ciudades ciudad, Departamentos departamento, String area) {

		super(identificacion, tipoIdentificacion, nombre, apellido, ciudad, departamento);
		this.area = area;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
