package repositorio;

import modelo.Estudiante;

public class EstudianteDao extends RepositorioHibernate<Estudiante> {
	
	public EstudianteDao() {
		super(Estudiante.class);
	}

}
