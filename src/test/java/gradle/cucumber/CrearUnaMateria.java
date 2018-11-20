package gradle.cucumber;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import modelo.Materia;
import repositorio.MateriaDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class CrearUnaMateria {
	
	Materia materia;
	MateriaDao materiadao;
	Session session;
	Transaction tx;
	
	@Given("iniciamos la sesion {int}")
	public void iniciamos_la_sesion(Integer int1) {
		SessionFactoryProvider.destroy();
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		
		Runner.addSession(session);
		materiadao = new MateriaDao();
	}

	@When("creo una materia con sus datos")
	public void creo_una_materia_con_sus_datos() {
		materia = new Materia();
		materia.setCodigo(123);
		materia.setNombre("Conexiones Inalambricas");
		materia.setHorario(new Date());
		materia.setNota1(5);
		materia.setNota2(4);
		materia.setNota3(3);
		materia.setNota4(3);
		materia.setNota5(2);
		materiadao.guardar(materia);
		tx.commit();
	}

	@Then("verificamos su almacenamiento")
	public void verificamos_su_almacenamiento() {
		Assert.assertNotNull(materiadao.recuperarUno("codigo", 123));
	}

}
