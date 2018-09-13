package gradle.cucumber;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import modelo.Ciudades;
import modelo.Departamentos;
import modelo.Estudiante;
import repositorio.EstudianteDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class EditarUnEstudiante {
	
	Estudiante estudiante;
	EstudianteDao estudianteDao;
	Session session;
	Transaction tx; 
	
	@Given("iniciar de sesion")
	public void iniciar_de_sesion() {
		SessionFactoryProvider.destroy();
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		
		Runner.addSession(session);
		estudianteDao = new EstudianteDao();
	}

	@When("edito un estudiante")
	public void edito_un_estudiante() {
		estudiante = new Estudiante();
		estudiante.setNombre("Alejandra");
		estudiante.setApellidos("Vergara");
		estudiante.setCiudad(Ciudades.Tunja);
		estudiante.setDepartamento(Departamentos.Boyaca);
		estudiante.setIdentificacion(1012413642);
		estudianteDao.guardar(estudiante);
		
		estudiante.setNombre("Maria");
		estudianteDao.actualizar(estudiante);
		tx.commit();
	}

	@Then("valido su edicion")
	public void valido_su_edicion() {
		Assert.assertTrue(estudianteDao.contiene("nombre", "Maria"));

	}

}
