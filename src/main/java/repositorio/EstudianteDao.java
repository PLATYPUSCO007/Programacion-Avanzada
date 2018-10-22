package repositorio;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import modelo.Estudiante;


public class EstudianteDao extends RepositorioHibernate<Estudiante> {
	
	public EstudianteDao() {
		super(Estudiante.class);
	}
	
	public List<Estudiante> buscarEstudiantesSimilares(String campo, Serializable valor) {
		Serializable valorBuscado = "%" + valor + "%";
		Session session = Runner.getCurrentSession();
		String hql = "FROM Estudiante e INNER JOIN Persona p ON e.id = p.id WHERE e." + campo + " LIKE '" + valorBuscado + "' ";
		Query query = session.createQuery(hql);
		List<Estudiante> estudiantes = (List<Estudiante>) query.getResultList();
		return estudiantes;
	}

}
