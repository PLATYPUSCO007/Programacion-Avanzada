package repositorio;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import modelo.Estudiante;


public class EstudianteDao extends RepositorioHibernate<Estudiante> {
	
	public EstudianteDao() {
		super(Estudiante.class);
	}
	
}
