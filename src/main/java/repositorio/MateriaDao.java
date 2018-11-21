package repositorio;

import org.hibernate.Session;

import modelo.Estudiante;
import modelo.Materia;

public class MateriaDao extends RepositorioHibernate<Materia>{
	
	public MateriaDao() {
		super(Materia.class);
	}



}
