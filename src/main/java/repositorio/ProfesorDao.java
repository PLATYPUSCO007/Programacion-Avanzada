package repositorio;

import modelo.Profesor;;

public class ProfesorDao extends RepositorioHibernate<Profesor> {
	
	public ProfesorDao() {
		super(Profesor.class);
	}
}
