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

public class EditarUnaMateria {
	
	Session session;
	Transaction tx;
	Materia materia = new Materia();
	MateriaDao materiadao = new MateriaDao();
	
	@Given("Ready a session")
	public void ready_a_session() {
		SessionFactoryProvider.destroy();
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		
		Runner.addSession(session);
	}

	@When("creo una materia nueva")
	public void creo_una_materia_nueva() {
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

	@And("edito el codigo de la materia")
	public void edito_el_codigo_de_la_materia() {
		materia.setNombre("Programacion");
		materiadao.actualizar(materia);
		tx.commit();
	}

	@Then("valido la edicion")
	public void valido_la_edicion() {
		Assert.assertTrue(materiadao.contiene("nombre", "Programacion"));
		session.close();
	}

}
