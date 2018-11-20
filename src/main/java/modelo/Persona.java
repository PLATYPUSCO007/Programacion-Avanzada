package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private int identificacion;
	@Column(unique = true)
	private String usuario;
	@Column
	private String password;
	@Enumerated(EnumType.STRING)
	private Identificacion tipoIdentificacion;
	@Column
	private String nombre;
	@Column
	private String apellidos;
	@Enumerated(EnumType.STRING)
	private Ciudades ciudad;
	@Enumerated(EnumType.STRING)
	private Departamentos departamento;

	public Persona() {
	}

	public Persona(int identificacion, Identificacion tipoIdentificacion, String nombre, String apellido,
			Ciudades ciudad, Departamentos departamento, String usuario, String password) {
		this.identificacion = identificacion;
		this.tipoIdentificacion = tipoIdentificacion;
		this.nombre = nombre;
		this.apellidos = apellido;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.usuario=usuario;
		this.password=password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
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

	public Ciudades getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudades ciudad) {
		this.ciudad = ciudad;
	}

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	public Identificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(Identificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String password) {
		this.password = password;
	}
	
	

}
