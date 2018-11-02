package gradle.cucumber;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import modelo.Ciudades;
import modelo.Departamentos;
import modelo.Identificacion;
import modelo.Profesor;
import repositorio.ProfesorDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class BuscarUnProfesor {
	
	ProfesorDao profesordao;
	Profesor profesor;
	Transaction tx;
	Session session;
	Profesor profesorTraido;
	
	@Given("iniciar una session")
	public void iniciar_una_session() {
		SessionFactoryProvider.destroy();
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		
		Runner.addSession(session);
		profesordao = new ProfesorDao();
	}

	@When("registro el Profesor")
	public void registro_el_Profesor() {
		profesor =  new Profesor();
		profesor.setNombre("Pedro");
		profesor.setApellidos("Rozo");
		profesor.setCiudad(Ciudades.Barranquilla);
		profesor.setDepartamento(Departamentos.Atlantico);
		profesor.setTipoIdentificacion(Identificacion.CC);
		profesor.setIdentificacion(1231231);
		profesor.setArea("Sistemas");
		
		profesordao.guardar(profesor);
		tx.commit();
	}

	@And("busco el Profesor en la BD")
	public void busco_el_Profesor_en_la_BD() {
		profesorTraido = new Profesor();
		profesorTraido = profesordao.recuperarUno("departamento", Departamentos.valueOf("Atlantico"));
	}

	@Then("valido la busqueda")
	public void valido_la_busqueda() {
		Assert.assertEquals("Rozo", profesorTraido.getApellidos());
		session.close();
	}

}
