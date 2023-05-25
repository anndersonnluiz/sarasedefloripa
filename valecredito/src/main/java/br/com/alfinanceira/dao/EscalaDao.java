package br.com.alfinanceira.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.alfinanceira.connection.ConectionFactory;
import br.com.alfinanceira.model.Escala;

public class EscalaDao {

	public Escala salvar(Escala escala) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		escala = manager.merge(escala);
		tx.commit();

		return escala;
	}

	public Escala consultar(int idEscala) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Escala escala = manager.find(Escala.class, idEscala);

		return escala;
	}

	@SuppressWarnings("unchecked")
	public List<Escala> listaEscala() throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery("select u from Escala u order by u.nome");
		List<Escala> lista = q.getResultList();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Escala> listar(String sql) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery(sql);
		List<Escala> lista = q.getResultList();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Escala> listaEscala(String nome) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery("select u from Escala u where u.nome like '%" + nome + "%' order by u.nome");
		List<Escala> lista = q.getResultList();

		return lista;
	}

	public void excluir(int idEscala) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select u from Escala u where u.idEscala=" + idEscala);
		if (q.getResultList().size() > 0) {
			Escala escala = (Escala) q.getResultList().get(0);
			manager.remove(escala);
		}
		tx.commit();

	}
}
