package br.com.alfinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.alfinanceira.connection.ConectionFactory;
import br.com.alfinanceira.model.Tipoarquivo;

public class TipoArquivoDao {

	
	public Tipoarquivo salvar(Tipoarquivo Tipoarquivo) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Tipoarquivo = manager.merge(Tipoarquivo);
		tx.commit();

		return Tipoarquivo;
	}

	@SuppressWarnings("unchecked")
	public List<Tipoarquivo> listar(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery(sql);
		List<Tipoarquivo> lista = q.getResultList();

		return lista;
	}

	public void excluir(int idTipoarquivo) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select t from Tipoarquivo t where t.idtipoarquivo=" + idTipoarquivo);
		if (q.getResultList().size() > 0) {
			Tipoarquivo Tipoarquivo = (Tipoarquivo) q.getResultList().get(0);
			manager.remove(Tipoarquivo);
		}
		tx.commit();

	}
}
