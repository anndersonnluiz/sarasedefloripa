package br.com.alfinanceira.managebean.rankingvendas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.alfinanceira.facade.HistoricoComissaoFacade;
import br.com.alfinanceira.facade.RankingVendasFacade;
import br.com.alfinanceira.facade.TipoOperacaoFacade;
import br.com.alfinanceira.facade.UsuarioFacade;
import br.com.alfinanceira.model.Historicocomissao;
import br.com.alfinanceira.model.Rankingvendas;
import br.com.alfinanceira.model.Tipooperacao;
import br.com.alfinanceira.model.Usuario;

@Named
@ViewScoped
public class CadRankingVendasMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rankingvendas rankingvendas;
	private Tipooperacao tipooperacao;
	private List<Tipooperacao> listaTipoOperacao;
	private Usuario usuario;
	private List<Usuario> listaUsuario;
	private List<Historicocomissao> listaHistoricoComissao;
	private float valorProducao;
	private float valorQuitar;
	private float valorLiberado;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		rankingvendas = (Rankingvendas) session.getAttribute("rankingvendas");
		session.removeAttribute("rankingvendas");
		if (rankingvendas == null) {
			rankingvendas = new Rankingvendas();
		}else {
			usuario = rankingvendas.getUsuario();
			tipooperacao = rankingvendas.getTipooperacao();
		}
		gerarListaTipoOperacao();
		gerarListaUsuario();
	}



	/**
	 * @return the rankingvendas
	 */
	public Rankingvendas getRankingvendas() {
		return rankingvendas;
	}



	/**
	 * @param rankingvendas the rankingvendas to set
	 */
	public void setRankingvendas(Rankingvendas rankingvendas) {
		this.rankingvendas = rankingvendas;
	}



	/**
	 * @return the tipooperacao
	 */
	public Tipooperacao getTipooperacao() {
		return tipooperacao;
	}



	/**
	 * @param tipooperacao the tipooperacao to set
	 */
	public void setTipooperacao(Tipooperacao tipooperacao) {
		this.tipooperacao = tipooperacao;
	}



	/**
	 * @return the listaTipoOperacao
	 */
	public List<Tipooperacao> getListaTipoOperacao() {
		return listaTipoOperacao;
	}



	/**
	 * @param listaTipoOperacao the listaTipoOperacao to set
	 */
	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}



	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}



	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	/**
	 * @return the listaUsuario
	 */
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}



	/**
	 * @param listaUsuario the listaUsuario to set
	 */
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}



	/**
	 * @return the listaHistoricoComissao
	 */
	public List<Historicocomissao> getListaHistoricoComissao() {
		return listaHistoricoComissao;
	}



	/**
	 * @param listaHistoricoComissao the listaHistoricoComissao to set
	 */
	public void setListaHistoricoComissao(List<Historicocomissao> listaHistoricoComissao) {
		this.listaHistoricoComissao = listaHistoricoComissao;
	}
	
	
	
	
	/**
	 * @return the valorProducao
	 */
	public float getValorProducao() {
		return valorProducao;
	}



	/**
	 * @param valorProducao the valorProducao to set
	 */
	public void setValorProducao(float valorProducao) {
		this.valorProducao = valorProducao;
	}



	/**
	 * @return the valorQuitar
	 */
	public float getValorQuitar() {
		return valorQuitar;
	}



	/**
	 * @param valorQuitar the valorQuitar to set
	 */
	public void setValorQuitar(float valorQuitar) {
		this.valorQuitar = valorQuitar;
	}



	/**
	 * @return the valorLiberado
	 */
	public float getValorLiberado() {
		return valorLiberado;
	}



	/**
	 * @param valorLiberado the valorLiberado to set
	 */
	public void setValorLiberado(float valorLiberado) {
		this.valorLiberado = valorLiberado;
	}



	public String cancelar() {
		return "consRankingVendas";
	}
	
	
	public String salvar() {
		rankingvendas.setTipooperacao(tipooperacao);
		rankingvendas.setUsuario(usuario);
		if (tipooperacao.getIdtipooperacao() == 1 
				|| tipooperacao.getIdtipooperacao() == 4
				|| tipooperacao.getIdtipooperacao() == 5) {
			rankingvendas.setPortabilidade(true);
			rankingvendas.setDescricaoportabilidade("PORTABILIDADE");
		}else {
			rankingvendas.setPortabilidade(false);
			rankingvendas.setDescricaoportabilidade("DEMAIS OPERAÇÕES");
		}
		RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
		rankingVendasFacade.salvar(rankingvendas);
		return "consRankingVendas";
	}
	
	
	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Where t.ativo=true");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}
	
	
	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		this.listaUsuario = usuarioFacade.listar("Select u From Usuario u Where u.ativo=true order by u.nome");
		if (this.listaUsuario == null)
			this.listaUsuario = new ArrayList<>();
	}
	
	
	public void gerarListaHistoricoComissao() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		listaHistoricoComissao = historicoComissaoFacade.lista("Select h From Historicocomissao h Where h.contrato.situacao.idsituacao<>2 AND h.contrato.situacao.idsituacao<>6 AND h.mes="
				+ rankingvendas.getMes() + " AND h.ano=" + rankingvendas.getAno() + " AND h.usuario.idusuario=" + usuario.getIdusuario());
		
		if (listaHistoricoComissao == null) {
			listaHistoricoComissao = new ArrayList<Historicocomissao>();
		}
		valorLiberado = 0.0f;
		valorProducao = 0.0f;
		valorQuitar = 0.0f;
		for (int i = 0; i < listaHistoricoComissao.size(); i++) {
			valorLiberado = valorLiberado + listaHistoricoComissao.get(i).getContrato().getValorliberado();
			valorProducao = valorProducao + listaHistoricoComissao.get(i).getProdliq();
			valorQuitar = valorQuitar + listaHistoricoComissao.get(i).getContrato().getValorquitar();
		}
	}
	
	
	
	

}
