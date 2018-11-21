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

public class BuscarUnaMateria {
	
	Session session;
	Transaction tx;
	Materia materia;
	MateriaDao materiadao;
	Materia materiabuscada;
	
	@Given("iniciamos session")
	public void iniciamos_session() {
		SessionFactoryProvider.destroy();
		session = SessionFactoryProvider.getInstance().createSession();
		tx = session.beginTransaction();
		
		Runner.addSession(session);
		materiadao = new MateriaDao();
	}

	@When("creo una materia")
	public void creo_una_materia() {
		materia = new Materia();
		materia.setCodigo(122);
		materia.setHorario(new Date());
		materia.setNombre("Algebra");
		materia.setNota1(4);
		materia.setNota2(5);
		materia.setNota3(4);
		materia.setNota4(5);
		materia.setNota5(3);
		materiadao.guardar(materia);
	}

	@And("busco la materia")
	public void busco_la_materia() {
		materiabuscada = new Materia();
		materiabuscada = materiadao.recuperarUno("codigo", 122);
		tx.commit();
	}

	@Then("obtengo sus datos")
	public void obtengo_sus_datos() {
		Assert.assertEquals(materia, materiabuscada);
		session.close();
	}

}
