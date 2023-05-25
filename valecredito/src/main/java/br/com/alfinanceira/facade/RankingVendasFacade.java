package br.com.alfinanceira.facade;

import java.util.List;

import br.com.alfinanceira.dao.RankingVendasDao;
import br.com.alfinanceira.model.Rankingvendas;


public class RankingVendasFacade {

	RankingVendasDao rankingVendasDao;
	
	public Rankingvendas salvar(Rankingvendas rankingvendas) {
		rankingVendasDao = new RankingVendasDao();
		return rankingVendasDao.salvar(rankingvendas);
	}

	public Rankingvendas consultar(int idrankingvendas) {
		rankingVendasDao = new RankingVendasDao();
		return rankingVendasDao.consultar(idrankingvendas);
	}

	public List<Rankingvendas> lista(String sql) {
		rankingVendasDao = new RankingVendasDao();
		return rankingVendasDao.lista(sql);
	}
}
