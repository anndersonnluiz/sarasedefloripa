package br.com.alfinanceira.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.alfinanceira.connection.ConectionFactory;
import br.com.alfinanceira.model.Escalausuario;

public class EscalaUsuarioDao {

	public Escalausuario salvar(Escalausuario escala) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		escala = manager.merge(escala);
		tx.commit();

		return escala;
	}

	public Escalausuario consultar(int idEscala) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Escalausuario escala = manager.find(Escalausuario.class, idEscala);

		return escala;
	}

	@SuppressWarnings("unchecked")
	public List<Escalausuario> listaEscala() throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery("select u from Escalausuario u");
		List<Escalausuario> lista = q.getResultList();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Escalausuario> listar(String sql) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery(sql);
		List<Escalausuario> lista = q.getResultList();

		return lista;
	}
	public void excluir(int idEscala) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select u from Escalausuario u where u.idEscala=" + idEscala);
		if (q.getResultList().size() > 0) {
			Escalausuario escala = (Escalausuario) q.getResultList().get(0);
			manager.remove(escala);
		}
		tx.commit();

	}
}
