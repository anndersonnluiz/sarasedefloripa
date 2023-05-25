package br.com.alfinanceira.facade;

import java.util.List;

import br.com.alfinanceira.dao.BancoDao;
import br.com.alfinanceira.model.Banco;


public class BancoFacade {
	
	BancoDao bancoDao;
	
	public Banco salvar(Banco banco) {
		bancoDao = new BancoDao();
		return bancoDao.salvar(banco);
	}

	public Banco consultar(int idusuario) {
		bancoDao = new BancoDao();
		return bancoDao.consultar(idusuario);
	}

	public List<Banco> lista(String sql) {
		bancoDao = new BancoDao();
		return bancoDao.lista(sql);
	}

}
