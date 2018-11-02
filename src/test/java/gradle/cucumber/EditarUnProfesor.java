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
import modelo.Identificacion;
import modelo.Profesor;
import repositorio.ProfesorDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class EditarUnProfesor {
	
	ProfesorDao profesordao;
	Profesor profesor;
	Transaction tx;
	Session session;
	Profesor profesorTraido;
	
	@Given("iniciamos sesion")
	public void iniciamos_sesion() {
		SessionFactoryProvider.destroy();
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		
		Runner.addSession(session);
		profesordao = new ProfesorDao();

	}

	@When("registro a un profesor")
	public void registro_a_un_profesor() {
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

	@Then("editamos su nombre")
	public void editamos_su_nombre() {
		profesor.setNombre("Raul");
		profesordao.actualizar(profesor);
		tx.commit();
	}

	@And("valido la modificacion")
	public void valido_la_modificacion() {
		profesorTraido = new Profesor();
		profesorTraido = profesordao.recuperarUno("nombre", "Raul");
		Assert.assertNotNull(profesorTraido);
		session.close();
	}


}
