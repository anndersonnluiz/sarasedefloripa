package br.com.alfinanceira.facade;

import java.util.List;

import br.com.alfinanceira.dao.TipoDespesaDao;
import br.com.alfinanceira.model.Tipodespesa;

public class TipoDespesaFacade {

	TipoDespesaDao tipoDespesaDao;
	
	public Tipodespesa salvar(Tipodespesa tipodespesa) {
		tipoDespesaDao = new TipoDespesaDao();
		return tipoDespesaDao.salvar(tipodespesa);
	}

	public Tipodespesa consultar(int idtipodespesa) {
		tipoDespesaDao = new TipoDespesaDao();
		return tipoDespesaDao.consultar(idtipodespesa);
	}

	public List<Tipodespesa> lista(String sql) {
		tipoDespesaDao = new TipoDespesaDao();
		return tipoDespesaDao.lista(sql);
	}
}
