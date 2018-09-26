package repositorio;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import modelo.Estudiante;

public class EstudianteDao extends RepositorioHibernate<Estudiante> {
	
	public EstudianteDao() {
		super(Estudiante.class);
	}
	
	public List<Estudiante> buscarEstudiantesSimilares(String campo, String valor) {
		String valorBuscado = "%" + valor + "%";
		Session session = Runner.getCurrentSession();
		String hql = "FROM Estudiante a WHERE a. " + campo + " LIKE '" + valorBuscado + "' ";
		Query query = session.createQuery(hql);
		List<Estudiante> estudiantes = (List<Estudiante>) query.getResultList();
		return estudiantes;
	}

}
