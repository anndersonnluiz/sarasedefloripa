package br.com.alfinanceira.facade;

import java.util.List;

import br.com.alfinanceira.dao.CoeficienteDao;
import br.com.alfinanceira.model.Coeficiente;

public class CoeficienteFacade {

	CoeficienteDao coeficienteDao;
	
	public Coeficiente salvar(Coeficiente coeficiente) {
		coeficienteDao = new CoeficienteDao();
		return coeficienteDao.salvar(coeficiente);
	}

	public Coeficiente consultar(int idcoeficiente) {
		coeficienteDao = new CoeficienteDao();
		return coeficienteDao.consultar(idcoeficiente);
	}

	public List<Coeficiente> lista(String sql) {
		coeficienteDao = new CoeficienteDao();
		return coeficienteDao.lista(sql);
	}
}
