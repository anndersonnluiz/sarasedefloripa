package br.com.alfinanceira.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.alfinanceira.dao.EscalaUsuarioDao;
import br.com.alfinanceira.model.Escalausuario;

public class EscalaUsuarioFacade {

	
	EscalaUsuarioDao escalaUsuarioDao;

	public Escalausuario salvar(Escalausuario escala) {
		escalaUsuarioDao = new EscalaUsuarioDao();
		try {
			return escalaUsuarioDao.salvar(escala);
		} catch (SQLException ex) {
			Logger.getLogger(EscalaFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public Escalausuario consultar(int idEscala) {
		escalaUsuarioDao = new EscalaUsuarioDao();
		try {
			return escalaUsuarioDao.consultar(idEscala);
		} catch (SQLException ex) {
			Logger.getLogger(EscalaFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public List<Escalausuario> listaEscala() throws SQLException {
		escalaUsuarioDao = new EscalaUsuarioDao();
		return escalaUsuarioDao.listaEscala();
	}

	public List<Escalausuario> listar(String sql) {
		escalaUsuarioDao = new EscalaUsuarioDao();
		try {
			return escalaUsuarioDao.listar(sql);
		} catch (SQLException ex) {
			Logger.getLogger(EscalaFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public void excluir(int idEscala) {
		escalaUsuarioDao = new EscalaUsuarioDao();
		escalaUsuarioDao.excluir(idEscala);
	}
}
