package repositorio;

import modelo.Profesor;;

public class ProfesorDao extends RepositorioHibernate<Profesor> {
	
	public ProfesorDao() {
		super(Profesor.class);
	}
	
	public boolean validarCredenciales(String usuario, String pass) {
		//if((null != recuperarUno("usuario", usuario)) && (null != recuperarUno("password", pass))) {
		if(contiene("usuario", usuario) && contiene("password", pass)) {
			return true;
		}else {
			return false;
		}
		
	}
}
