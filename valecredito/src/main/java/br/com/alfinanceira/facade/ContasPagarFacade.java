package br.com.alfinanceira.facade;

import java.util.List;

import br.com.alfinanceira.dao.ContasPagarDao;
import br.com.alfinanceira.model.Contaspagar;

public class ContasPagarFacade {
	
	ContasPagarDao contasPagarDao;
	
	public Contaspagar salvar(Contaspagar contaspagar) {
		contasPagarDao = new ContasPagarDao();
		return contasPagarDao.salvar(contaspagar);
	}

	public Contaspagar consultar(int idcontaspagar) {
		contasPagarDao = new ContasPagarDao();
		return contasPagarDao.consultar(idcontaspagar);
	}

	public List<Contaspagar> lista(String sql) {
		contasPagarDao = new ContasPagarDao();
		return contasPagarDao.lista(sql);
	}
	
	
	public void excluir(int idcontaspagar) {
		contasPagarDao = new ContasPagarDao();
		contasPagarDao.excluir(idcontaspagar);
	}

}
