package br.com.alfinanceira.facade;

import java.util.List;

import br.com.alfinanceira.dao.TipoColaboradorDao;
import br.com.alfinanceira.model.Tipocolaborador;

public class TipoColaboradorFacade {

	TipoColaboradorDao tipoColaboradorDao;
	
	public Tipocolaborador salvar(Tipocolaborador tipocolaborador) {
		tipoColaboradorDao = new TipoColaboradorDao();
			return tipoColaboradorDao.salvar(tipocolaborador);
	}

	public List<Tipocolaborador> listar(String sql) {
		tipoColaboradorDao = new TipoColaboradorDao();
			return tipoColaboradorDao.listar(sql);
	}
}
