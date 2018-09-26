package gradle.cucumber;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import modelo.Ciudades;
import modelo.Departamentos;
import modelo.Estudiante;
import repositorio.EstudianteDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class BuscarUnEstudiante {
	
	Session session;
	Transaction tx;
	Estudiante estudiante;
	EstudianteDao estudianteDao;
	Estudiante idEstudiante;
	
	@Given("iniciar sesion")
	public void iniciar_sesion() {
		SessionFactoryProvider.destroy();
		
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		
		Runner.addSession(session);
		estudianteDao = new EstudianteDao();
	}

	@When("creo el estudiante")
	public void creo_el_estudiante() {
		estudiante = new Estudiante();
		estudiante.setNombre("Sara");
		estudiante.setApellidos("Guzman");
		estudiante.setCiudad(Ciudades.Bogota);
		estudiante.setDepartamento(Departamentos.Cundinamarca);
		estudiante.setIdentificacion(291435612);
		estudiante.setFechaDeIngreso(new Date());
		estudianteDao.guardar(estudiante);
	}

	@And("busco el estudiante en la BD")
	public void busco_el_estudiante_de_la_BD() {
		idEstudiante = new Estudiante();
		idEstudiante = estudianteDao.recuperar("identificacion", 291435612);
	}

	@Then("imprimo la busqueda")
	public void imprimo_la_busqueda() {
		Assert.assertEquals(291435612, idEstudiante.getIdentificacion());
		session.close();
	}


}
