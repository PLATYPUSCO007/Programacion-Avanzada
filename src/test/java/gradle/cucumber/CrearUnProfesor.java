package gradle.cucumber;

import org.hibernate.Session;
import org.hibernate.Transaction;

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


public class CrearUnProfesor {
	
	ProfesorDao profesordao;
	Profesor profesor;
	Transaction tx;
	Session session;
	Profesor profesorTraido;
	
	@Given("inicia sesion")
	public void inicia_sesion() {
	    SessionFactoryProvider.destroy();
	    session = SessionFactoryProvider.getInstance().createSession();
	    tx = session.beginTransaction();
	    
	    Runner.addSession(session);
	    profesordao = new ProfesorDao();
	}

	@When("registro un profesor")
	public void registro_un_profesor() {
		profesor = new Profesor();
		profesor.setNombre("Pedro");
		profesor.setApellidos("Rozo");
		profesor.setCiudad(Ciudades.Barranquilla);
		profesor.setDepartamento(Departamentos.Atlantico);
		profesor.setTipoIdentificacion(Identificacion.CC);
		profesor.setIdentificacion(1231231);
		profesor.setUsuario("pedro");
		profesor.setPass("holamundo");
		profesor.setArea("Sistemas");
		
		profesordao.guardar(profesor);
		tx.commit();
	}

	@Then("valido su registro")
	public void valido_su_registro() {
		profesorTraido = new Profesor();
		profesorTraido = profesordao.recuperarUno("nombre", "Pedro");
		Assert.assertEquals("Pedro", profesorTraido.getNombre());
		session.close();
	}


}
