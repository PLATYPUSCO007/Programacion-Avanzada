package gradle.cucumber;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import modelo.Ciudades;
import modelo.Departamentos;
import modelo.Estudiante;
import modelo.Materia;
import repositorio.EstudianteDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class ValidarUnInicioDeSesion {
	
	Session session;
	Transaction tx;
	Materia materia = new Materia();
	String usuario="", pass="";
	EstudianteDao estudiantedao = new EstudianteDao();
	Estudiante estudiante = new Estudiante();
	
	
	@Given("obtenemos una nueva sesion")
	public void obtenemos_una_nueva_sesion() {
		SessionFactoryProvider.destroy();
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		Runner.addSession(session);
	}

	@When("registro un nuevo usuario")
	public void registro_un_nuevo_usuario() {
		estudiante.setNombre("William");
		estudiante.setApellidos("Enciso");
		estudiante.setCiudad(Ciudades.Florencia);
		estudiante.setDepartamento(Departamentos.Caqueta);
		estudiante.setIdentificacion(1022408475);
		estudiante.setUsuario("platypus");
		estudiante.setPass("holamundo");
		estudiante.setFechaDeIngreso(new Date());
		estudiantedao.guardar(estudiante);
		tx.commit();
	}

	@When("inicio sesion en el sistema")
	public void inicio_sesion_en_el_sistema() {
		usuario = estudiante.getUsuario();
		pass = estudiante.getPass();
	}

	@Then("valido las credenciales en la bd")
	public void valido_las_credenciales_en_la_bd() {
		Assert.assertTrue(estudiantedao.validarCredenciales(usuario,pass));
	}

}
