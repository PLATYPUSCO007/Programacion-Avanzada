package gradle.cucumber;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;
import repositorio.EstudianteDao;
import modelo.Ciudades;
import modelo.Departamentos;
import modelo.Estudiante;
import modelo.Persona;


public class CrearUnEstudiante {
	
	EstudianteDao estudianteDao;
	Estudiante estudiante;
	Session session;
	Transaction tx;
	
	@Given("un inicio de sesion")
	public void un_inicio_de_sesion() {
		SessionFactoryProvider.destroy();
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		
		Runner.addSession(session);
		estudianteDao = new EstudianteDao();
	}

	@When("creo un estudiante con sus datos personales")
	public void creo_un_estudiante_con_sus_datos_personales() {
		estudiante = new Estudiante();
		estudiante.setNombre("William");
		estudiante.setApellidos("Enciso");
		estudiante.setCiudad(Ciudades.Florencia);
		estudiante.setDepartamento(Departamentos.Caqueta);
		estudiante.setIdentificacion(1022408475);
		estudiante.setUsuario("platypus");
		estudiante.setPass("holamundo");
		estudiante.setFechaDeIngreso(new Date());
		estudianteDao.guardar(estudiante);
		
		tx.commit();
	}

	@Then("valido su creacion")
	public void valido_su_creacion() {
		List<Estudiante> estu = estudianteDao.traerTodo();
		Assert.assertNotEquals(null, estu);
		session.close();
	}

}
