package gradle.cucumber;

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
import modelo.Identificacion;
import modelo.Profesor;
import repositorio.EstudianteDao;
import repositorio.ProfesorDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class EliminarUnProfesor {
	
	ProfesorDao profesordao;
	Profesor profesor;
	Transaction tx;
	Session session;
	Profesor profesorTraido; 
	
	
	@Given("iniciamos la sesion")
	public void iniciamos_la_sesion() {
		SessionFactoryProvider.destroy();
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		
		Runner.addSession(session);
		profesordao = new ProfesorDao();
	}

	@When("registramos a un profesor")
	public void registramos_a_un_profesor() {
		profesor =  new Profesor();
		profesor.setNombre("Pedro");
		profesor.setApellidos("Rozo");
		profesor.setCiudad(Ciudades.Barranquilla);
		profesor.setDepartamento(Departamentos.Atlantico);
		profesor.setTipoIdentificacion(Identificacion.CC);
		profesor.setIdentificacion(1231231);
		profesor.setArea("Sistemas");
		profesordao.guardar(profesor);
	}

	@And("lo eliminamos del sistema")
	public void lo_eliminamos_del_sistema() {
		profesordao.borrar("id", 1);
		tx.commit();
	}

	@Then("valido eliminacion")
	public void valido_eliminacion() {
		Assert.assertEquals(false, profesordao.contiene("id", 1));
		session.close();
	}


}
