package br.com.alfinanceira.facade;

import java.util.List;

import br.com.alfinanceira.dao.DepartamentoDao;
import br.com.alfinanceira.model.Departamento;


public class DepartamentoFacade {

	DepartamentoDao departamentoDao;
	
	public Departamento salvar(Departamento departamento) {
		departamentoDao = new DepartamentoDao();
		return departamentoDao.salvar(departamento);
	}

	public Departamento consultar(int idContasreceber) {
		departamentoDao = new DepartamentoDao();
		return departamentoDao.consultar(idContasreceber);
	}

	public List<Departamento> lista(String sql) {
		departamentoDao = new DepartamentoDao();
		return departamentoDao.lista(sql);
	}
	
	
	public void excluir(int idContasreceber) {
		departamentoDao = new DepartamentoDao();
		departamentoDao.excluir(idContasreceber);
	}
}
