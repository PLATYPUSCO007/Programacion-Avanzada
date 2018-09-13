package gradle.cucumber;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import modelo.Estudiante;
import repositorio.EstudianteDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class EliminarUnCliente {
	
	Session session;
	Transaction tx;
	EstudianteDao estudianteDao;
	Estudiante estudiante;
	
	@Given("inicio sesion")
	public void inicio_sesion() {
		SessionFactoryProvider.destroy();
		
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		
		Runner.addSession(session);
		estudianteDao = new EstudianteDao();
	}

	@When("creo un estudiante")
	public void creo_un_estudiante() {
		estudiante = new Estudiante();
		estudiante.setNombre("Gonzalo");
		estudiante.setApellidos("Higuain");
		estudiante.setCiudad("Quilmes");
		estudiante.setDepartamento("BA");
		estudiante.setIdentificacion(1345876509);
		estudianteDao.guardar(estudiante);
	}

	@And("elimino el estudiante de la BD")
	public void elimino_el_estudiante_de_la_BD() {
		estudianteDao.borrar("id", 1);
		tx.commit();
	}

	@Then("valido la eliminacion")
	public void valido_la_eliminacion() {
		Assert.assertEquals(false, estudianteDao.contiene("nombre", null));
	}


}
