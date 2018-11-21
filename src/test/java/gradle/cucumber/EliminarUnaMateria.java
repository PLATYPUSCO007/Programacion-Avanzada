package gradle.cucumber;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import modelo.Materia;
import repositorio.MateriaDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class EliminarUnaMateria {
	
	Session session;
	Transaction tx;
	Materia materia = new Materia();
	MateriaDao materiadao = new MateriaDao();
	
	@Given("Get a session")
	public void get_a_session() {
		SessionFactoryProvider.destroy();
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		Runner.addSession(session);
	}

	@When("creo la materia")
	public void creo_la_materia() {
		materia.setCodigo(100);
		materia.setHorario(new Date());
		materia.setNombre("Redes de datos");
		materia.setNota1(4);
		materia.setNota2(5);
		materia.setNota3(4);
		materia.setNota4(5);
		materia.setNota5(3);
		materiadao.guardar(materia);
	}

	@And("elimino la materia")
	public void elimino_la_materia() {
		materiadao.borrar("codigo", 100);
		tx.commit();
	}

	@Then("verifico la bd")
	public void verifico_la_bd() {
		Assert.assertFalse(materiadao.contiene("codigo", 100));
		session.close();
	}

}
