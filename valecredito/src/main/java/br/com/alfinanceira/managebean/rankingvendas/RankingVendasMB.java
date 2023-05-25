package br.com.alfinanceira.managebean.rankingvendas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.alfinanceira.facade.RankingVendasFacade;
import br.com.alfinanceira.model.Rankingvendas;

@Named
@ViewScoped
public class RankingVendasMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Rankingvendas> listaRanking;
	private int mes;
	private int ano;
	private int ranking;

	@PostConstruct
	public void init() {
		
	}

	/**
	 * @return the listaRanking
	 */
	public List<Rankingvendas> getListaRanking() {
		return listaRanking;
	}

	/**
	 * @param listaRanking the listaRanking to set
	 */
	public void setListaRanking(List<Rankingvendas> listaRanking) {
		this.listaRanking = listaRanking;
	}

	/**
	 * @return the mes
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * @return the ano
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * @param ano the ano to set
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}

	/**
	 * @return the ranking
	 */
	public int getRanking() {
		return ranking;
	}

	/**
	 * @param ranking the ranking to set
	 */
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public void pesquisar() {
		RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
		String sql = "Select r From Rankingvendas r WHERE r.usuario.nome like '%%'";

		if (mes > 0) {
			sql = sql + " AND r.mes=" + mes;
		}

		if (ano > 0) {
			sql = sql + " AND r.ano=" + ano;
		}

		if (ranking > 0) {
			if (ranking == 1) {
				sql = sql + " AND r.portabilidade=true";
			}else {
				sql = sql + " AND r.portabilidade=false";
			}
		}
		sql = sql + " ORDER BY r.valorvenda DESC";
		listaRanking = rankingVendasFacade.lista(sql);

		if (listaRanking == null) {
			listaRanking = new ArrayList<Rankingvendas>();
		}
		for (int i = 0; i < listaRanking.size(); i++) {
			
		}
	}

	public String editar(Rankingvendas rankingvendas) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("rankingvendas", rankingvendas);
		return "cadRankingVendas";
	}
	
	
	public String novo() {
		return "cadRankingVendas";
	}

}
