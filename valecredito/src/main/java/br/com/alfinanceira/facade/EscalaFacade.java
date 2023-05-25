package br.com.alfinanceira.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.alfinanceira.dao.EscalaDao;
import br.com.alfinanceira.model.Escala;

public class EscalaFacade {
	
	EscalaDao escalaDao;

	public Escala salvar(Escala escala) {
		escalaDao = new EscalaDao();
		try {
			return escalaDao.salvar(escala);
		} catch (SQLException ex) {
			Logger.getLogger(EscalaFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public Escala consultar(int idEscala) {
		escalaDao = new EscalaDao();
		try {
			return escalaDao.consultar(idEscala);
		} catch (SQLException ex) {
			Logger.getLogger(EscalaFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public List<Escala> listaEscala() throws SQLException {
		escalaDao = new EscalaDao();
		return escalaDao.listaEscala();
	}

	public List<Escala> listar(String sql) {
		escalaDao = new EscalaDao();
		try {
			return escalaDao.listar(sql);
		} catch (SQLException ex) {
			Logger.getLogger(EscalaFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public List<Escala> listaEscala(String nome) {
		escalaDao = new EscalaDao();
		try {
			return escalaDao.listaEscala(nome);
		} catch (SQLException ex) {
			Logger.getLogger(EscalaFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public void excluir(int idEscala) {
		escalaDao = new EscalaDao();
		escalaDao.excluir(idEscala);
	}
}
