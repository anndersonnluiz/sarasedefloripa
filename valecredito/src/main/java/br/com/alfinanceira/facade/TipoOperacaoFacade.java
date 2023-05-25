package br.com.alfinanceira.facade;

import java.util.List;

import br.com.alfinanceira.dao.TipoOperacaoDao;
import br.com.alfinanceira.model.Tipooperacao;


public class TipoOperacaoFacade {
	
	TipoOperacaoDao tipoOperacaoDao;

	public Tipooperacao salvar(Tipooperacao tipooperacao) {
		tipoOperacaoDao = new TipoOperacaoDao();
		return tipoOperacaoDao.salvar(tipooperacao);
	}

	public List<Tipooperacao> lista(String sql) {
		tipoOperacaoDao = new TipoOperacaoDao();
		return tipoOperacaoDao.listar(sql);
	}
	
	
	public Tipooperacao consultar(int idtipooperacao) {
		tipoOperacaoDao = new TipoOperacaoDao();
		return tipoOperacaoDao.consultar(idtipooperacao);
	}
}
