package repositorio;



import modelo.Estudiante;


public class EstudianteDao extends RepositorioHibernate<Estudiante> {
	
	public EstudianteDao() {
		super(Estudiante.class);
	}
	
	public boolean validarCredenciales(String usuario, String pass) {
		if(contiene("usuario", usuario) && contiene("password", pass)) {
			return true;
		}else {
			return false;
		}
		
	}
}
