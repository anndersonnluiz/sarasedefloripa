package br.com.alfinanceira.managebean.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.alfinanceira.facade.CoeficienteFacade;
import br.com.alfinanceira.facade.ContratoFacade;
import br.com.alfinanceira.facade.HistoricoComissaoFacade;
import br.com.alfinanceira.facade.SituacaoFacade;
import br.com.alfinanceira.model.Coeficiente;
import br.com.alfinanceira.model.Historicocomissao;
import br.com.alfinanceira.model.Situacao;
import br.com.alfinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class EditarComissaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB; 
	private Historicocomissao historicocomissao;
	private List<Situacao> listaSituacao;
	private String tipoFiltro;
	private String tipoAntigo;
	private Situacao situacao;
	private Integer convenio;
	private String voltar;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		historicocomissao = (Historicocomissao) session.getAttribute("historicocomissao");
		session.removeAttribute("historicocomissao");
		voltar = (String) session.getAttribute("voltar");
		session.removeAttribute("voltar");
		tipoAntigo = historicocomissao.getTipo();
		situacao = historicocomissao.getContrato().getSituacao();
		gerarListaSituacao();
	}


	public Historicocomissao getHistoricocomissao() {
		return historicocomissao;
	}


	public void setHistoricocomissao(Historicocomissao historicocomissao) {
		this.historicocomissao = historicocomissao;
	}
	
	
	public List<Situacao> getListaSituacao() {
		return listaSituacao;
	}


	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}


	public String getTipoFiltro() {
		return tipoFiltro;
	}


	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}


	public String getTipoAntigo() {
		return tipoAntigo;
	}


	public void setTipoAntigo(String tipoAntigo) {
		this.tipoAntigo = tipoAntigo;
	}


	public Situacao getSituacao() {
		return situacao;
	}


	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}


	public String voltar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("convenio", convenio);
		return voltar;
	}
	
	public String salvar() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		historicocomissao.getContrato().setSituacao(situacao);
		historicocomissao.setCmsliq(historicocomissao.getComissaototal() - historicocomissao.getCmdbruta());
		
		ContratoFacade contratoFacade = new ContratoFacade();
		contratoFacade.salvar(historicocomissao.getContrato());
		historicoComissaoFacade.salvar(historicocomissao);
		return voltar;
	}
	
	
	public void gerarListaSituacao() {
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		String sql = "Select s From Situacao s WHERE s.visualizar=true ";
		if (historicocomissao.getContrato().getTipooperacao().getIdtipooperacao() != 1) {
			sql = sql + " AND s.portabilidade=false ";
		}
		sql = sql + " ORDER BY s.descricao";
		listaSituacao = situacaoFacade.lista(sql);
		if (listaSituacao == null) {
			listaSituacao = new ArrayList<Situacao>();
		}
	}
	
	
	public void calcularValores() {
		CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
		Coeficiente coeficiente = coeficienteFacade.consultar(historicocomissao.getContrato().getIdregracoeficiente());
		historicocomissao.setCmdbruta(historicocomissao.getProdliq() 
				* (coeficiente.getComissaoloja() / 100));
		historicocomissao.setCmsliq(historicocomissao.getProdliq() 
				* (coeficiente.getComissaocorretor() / 100));
		historicocomissao.setComissaototal(historicocomissao.getCmdbruta() + historicocomissao.getCmsliq());
	}
	
	
	
	
	
	
	

}
