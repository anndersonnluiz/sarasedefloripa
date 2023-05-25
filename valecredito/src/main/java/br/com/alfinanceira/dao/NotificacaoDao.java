package br.com.alfinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.alfinanceira.connection.ConectionFactory;
import br.com.alfinanceira.model.Notificacao;

public class NotificacaoDao {

	public void salvar(Notificacao notificacao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		notificacao = manager.merge(notificacao);
		tx.commit();
		manager.close();
	}

	public Notificacao consultar(int idnotificacao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select n from Notificacao n where n.idnotificacao=" + idnotificacao);
		Notificacao notificacao = null;
		if (q.getResultList().size() > 0) {
			notificacao = (Notificacao) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return notificacao;
	}

	@SuppressWarnings("unchecked")
	public List<Notificacao> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Notificacao> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
