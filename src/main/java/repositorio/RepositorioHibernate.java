package repositorio;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import modelo.Departamentos;

public class RepositorioHibernate<T> implements Repositorio<T> {

	private Class<T> tipo;

	public RepositorioHibernate(Class<T> tipo) {
		this.tipo = tipo;
	}

	@Override
	public void guardar(T obj) {
		Runner.runInSession(() -> {
			Session session = Runner.getCurrentSession();
			session.save(obj);
			return null;
		});
	}

	@Override
	public T recuperar(Serializable key) {
		Session session = Runner.getCurrentSession();
		T resultado = (T) session.get(tipo, key);
		return (T) resultado;
	}

	@Override
	public void actualizar(T obj) {
		Session session = Runner.getCurrentSession();
		session.update(obj);
	}

	@Override
	public void borrar(String campo, Serializable key) {
		Session session = Runner.getCurrentSession();
		session.delete(this.recuperarUno(campo, key));
	}

	@Override
	public List<T> traerTodo() {
		Session session = Runner.getCurrentSession();
		Query<T> query = session.createQuery("from " + tipo.getSimpleName() + " t ", tipo);
		return query.getResultList();
	}

	@Override
	public void borrarTodo() {
		Session session = Runner.getCurrentSession();
		session.createQuery("delete from " + tipo.getSimpleName()).executeUpdate();
	}

	public List<T> recuperarVarios(String campo, Serializable valor) {
		Session session = Runner.getCurrentSession();
		String hql = "from " + tipo.getSimpleName() + " t " + "where t." + campo + " = :valor ";
		Query<T> query = session.createQuery(hql, tipo);
		query.setParameter("valor", valor);
		return (List<T>) query.getResultList();
	}
	
	public T recuperarUno(String campo, Serializable valor) {
		Session session = Runner.getCurrentSession();
		String hql = "from " + tipo.getSimpleName() + " t " + "where t." + campo + " = :valor ";
		Query<T> query = session.createQuery(hql, tipo);
		query.setParameter("valor", valor);
		query.setMaxResults(1);
		return (T) query.getSingleResult();
	}

	@Override
	public boolean contiene(String campo, Serializable key) {
		boolean res = false;
		try {
			res = (null != this.recuperarUno(campo, key));
		} catch (Exception e) {

			System.out.println("no se encuentra en la base de datos");

		}
		return res;
	}
}
