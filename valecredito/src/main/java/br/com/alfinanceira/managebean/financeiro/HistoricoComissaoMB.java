package br.com.alfinanceira.managebean.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.alfinanceira.facade.BancoFacade;
import br.com.alfinanceira.facade.HistoricoComissaoFacade;
import br.com.alfinanceira.facade.SituacaoFacade;
import br.com.alfinanceira.facade.TipoOperacaoFacade;
import br.com.alfinanceira.facade.UsuarioFacade;
import br.com.alfinanceira.model.Banco;
import br.com.alfinanceira.model.Historicocomissao;
import br.com.alfinanceira.model.Situacao;
import br.com.alfinanceira.model.Tipooperacao;
import br.com.alfinanceira.model.Usuario;
import br.com.alfinanceira.util.Formatacao;
import br.com.alfinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class HistoricoComissaoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Historicocomissao> listaComissao;
	private String nome;
	private int cdinterno;
	private int dataLancamento;
	private List<Usuario> listaUsuario;
	private Usuario usuario;
	private List<Tipooperacao> listaTipoOperacao;
	private Tipooperacao tipooiperacao;
	private boolean unicoUsuario = false;
	private int nPortabilidade;
	private int nMargem;
	private int nCartao;
	private int nRefin;
	private float valorRepassada;
	private float valorRecebida;
	private float valorProducao;
	private Date dataini;
	private Date datafin;
	private String tipoFiltro;
	private String cpf;
	private String statusTipo;
	private boolean selecionarTodos;
	private int nBaixa;
	private Date dataCadastroIni;
	private Date dataCadastroFinal;
	private float valortotal;
	private List<Banco> listaBanco;
	private Banco banco;
	private String nomeSituacao;
	private boolean baixarTodos = false;
	private List<Situacao> listaSituacao;
	private Situacao situacao;
 
	@PostConstruct
	public void init() {
		gerarListaInicial();
		gerarListaUsuario();
		gerarListaTipoOperacao();
		gerarListaBanco();
		gerarListaSituacao();
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()) {
			unicoUsuario = true;
			usuario = usuarioLogadoMB.getUsuario();
		}
	}

	public List<Historicocomissao> getListaComissao() {
		return listaComissao;
	}

	public void setListaComissao(List<Historicocomissao> listaComissao) {
		this.listaComissao = listaComissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCdinterno() {
		return cdinterno;
	}

	public void setCdinterno(int cdinterno) {
		this.cdinterno = cdinterno;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(int dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public List<Tipooperacao> getListaTipoOperacao() {
		return listaTipoOperacao;
	}

	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public Tipooperacao getTipooiperacao() {
		return tipooiperacao;
	}

	public void setTipooiperacao(Tipooperacao tipooiperacao) {
		this.tipooiperacao = tipooiperacao;
	}

	public boolean isUnicoUsuario() {
		return unicoUsuario;
	}

	public void setUnicoUsuario(boolean unicoUsuario) {
		this.unicoUsuario = unicoUsuario;
	}

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public int getnPortabilidade() {
		return nPortabilidade;
	}

	public void setnPortabilidade(int nPortabilidade) {
		this.nPortabilidade = nPortabilidade;
	}

	public int getnMargem() {
		return nMargem;
	}

	public void setnMargem(int nMargem) {
		this.nMargem = nMargem;
	}

	public int getnCartao() {
		return nCartao;
	}

	public void setnCartao(int nCartao) {
		this.nCartao = nCartao;
	}

	public int getnRefin() {
		return nRefin;
	}

	public void setnRefin(int nRefin) {
		this.nRefin = nRefin;
	}

	public float getValorRepassada() {
		return valorRepassada;
	}

	public void setValorRepassada(float valorRepassada) {
		this.valorRepassada = valorRepassada;
	}

	public float getValorRecebida() {
		return valorRecebida;
	}

	public void setValorRecebida(float valorRecebida) {
		this.valorRecebida = valorRecebida;
	}

	public float getValorProducao() {
		return valorProducao;
	}

	public void setValorProducao(float valorProducao) {
		this.valorProducao = valorProducao;
	}

	public Date getDataini() {
		return dataini;
	}

	public void setDataini(Date dataini) {
		this.dataini = dataini;
	}

	public Date getDatafin() {
		return datafin;
	}

	public void setDatafin(Date datafin) {
		this.datafin = datafin;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getStatusTipo() {
		return statusTipo;
	}

	public void setStatusTipo(String statusTipo) {
		this.statusTipo = statusTipo;
	}

	public String getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public boolean isSelecionarTodos() {
		return selecionarTodos;
	}

	public void setSelecionarTodos(boolean selecionarTodos) {
		this.selecionarTodos = selecionarTodos;
	}

	public int getnBaixa() {
		return nBaixa;
	}

	public void setnBaixa(int nBaixa) {
		this.nBaixa = nBaixa;
	}

	public Date getDataCadastroIni() {
		return dataCadastroIni;
	}

	public void setDataCadastroIni(Date dataCadastroIni) {
		this.dataCadastroIni = dataCadastroIni;
	}

	public Date getDataCadastroFinal() {
		return dataCadastroFinal;
	}

	public void setDataCadastroFinal(Date dataCadastroFinal) {
		this.dataCadastroFinal = dataCadastroFinal;
	}

	public float getValortotal() {
		return valortotal;
	}

	public void setValortotal(float valortotal) {
		this.valortotal = valortotal;
	}

	public List<Banco> getListaBanco() {
		return listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getNomeSituacao() {
		return nomeSituacao;
	}

	public void setNomeSituacao(String nomeSituacao) {
		this.nomeSituacao = nomeSituacao;
	}

	public boolean isBaixarTodos() {
		return baixarTodos;
	}

	public void setBaixarTodos(boolean baixarTodos) {
		this.baixarTodos = baixarTodos;
	}

	/**
	 * @return the listaSituacao
	 */
	public List<Situacao> getListaSituacao() {
		return listaSituacao;
	}

	/**
	 * @param listaSituacao the listaSituacao to set
	 */
	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}

	

	/**
	 * @return the situacao
	 */
	public Situacao getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao the situacao to set
	 */
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public void gerarListaInicial() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		String sql = "Select h From Historicocomissao h WHERE h.contrato.situacao.idsituacao<>2 and h.baixa=false and h.tipo='PENDENTE'";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()) {
			sql = sql + " and h.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		
		sql = sql + " order by h.datalancamento DESC";
		listaComissao = historicoComissaoFacade.lista(sql);
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		}
		valorRecebida = 0.0f;
		valorRepassada = 0.0f;
		nCartao = 0;
		nMargem = 0;
		nPortabilidade = 0;
		nRefin = 0;
		valorProducao = 0.0f;
		valortotal = 0.0f;
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 5) {
				nPortabilidade = nPortabilidade + 1;
			} else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2) {
				nMargem = nMargem + 1;
			} else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 4) {
				nRefin = nRefin + 1;
			} else {
				nCartao = nCartao + 1;
			}
			valorRecebida = valorRecebida + listaComissao.get(i).getCmsliq();
			valorRepassada = valorRepassada + listaComissao.get(i).getCmdbruta();
			valorProducao = valorProducao + listaComissao.get(i).getProdliq();
			valortotal = valortotal + listaComissao.get(i).getComissaototal();
		}
	}

	public void pesquisar() {
		String sql = "Select h From Historicocomissao h Where "
				+ " h.contrato.situacao.idsituacao<>2 and h.contrato.cliente.cpf like '%" + nome + "%'";
		if (tipooiperacao != null && tipooiperacao.getIdtipooperacao() != null) {
			sql = sql + " and h.contrato.tipooperacao.idtipooperacao=" + tipooiperacao.getIdtipooperacao();
		}

		if (dataLancamento > 0) {
			sql = sql + " and h.mes=" + dataLancamento; 
		}

		if (dataini != null && datafin != null) {
			sql = sql + " and h.contrato.datacadastro>='" + Formatacao.ConvercaoDataNfe(dataini)
					+ "' and h.contrato.datacadastro<='" + Formatacao.ConvercaoDataNfe(datafin) + "'";
		}
		
		if (this.situacao != null && this.situacao.getIdsituacao() != null) {
		      sql = sql + " and h.contrato.situacao.idsituacao=" + this.situacao.getIdsituacao(); 
		}
		
		
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()) {
			sql = sql + " and h.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		} else {
			if (usuario != null && usuario.getIdusuario() != null) {
				sql = sql + " and h.usuario.idusuario=" + usuario.getIdusuario();
			}
		}
		if (statusTipo != null && statusTipo.length() > 0) {
			sql = sql + " and h.tipo='" + statusTipo + "'";
		}

		if (nBaixa > 0) {
			if (nBaixa == 1) {
				sql = sql + " and h.baixa=true";
			} else {
				sql = sql + " and h.baixa=false";
			}
		}
		if (this.banco != null && this.banco.getIdbanco() != null) {
			sql = String.valueOf(sql) + " and h.contrato.orgaoBanco.banco.idbanco=" + this.banco.getIdbanco();
		}
		sql = sql + " order by h.contrato.datapagamento";
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		listaComissao = historicoComissaoFacade.lista(sql);
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		}
		valorRecebida = 0.0f;
		valorRepassada = 0.0f;
		nCartao = 0;
		nMargem = 0;
		nPortabilidade = 0;
		nRefin = 0;
		valorProducao = 0.0f;
		valortotal = 0.0f;
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1) {
				nPortabilidade = nPortabilidade + 1;
			} else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 12) {
				nMargem = nMargem + 1;
			} else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 7
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 8
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 9) {
				nRefin = nRefin + 1;
			} else {
				nCartao = nCartao + 1;
			}
			valorRecebida = valorRecebida + listaComissao.get(i).getCmsliq();
			valorRepassada = valorRepassada + listaComissao.get(i).getCmdbruta();
			valorProducao = valorProducao + listaComissao.get(i).getProdliq();
			valortotal = valortotal + listaComissao.get(i).getComissaototal();
		}
	}

	public void limpar() {
		dataLancamento = 0;
		if (usuarioLogadoMB.getUsuario().isAcessogeral()) {
			usuario = null;
		}
		tipooiperacao = null;
		datafin = null;
		dataini = null;
		cpf = "";
		nBaixa = 0;
		dataCadastroFinal = null;
		dataCadastroIni = null;
		banco = null;
		situacao = null;
		gerarListaInicial();
	}

	public String editar(Historicocomissao historicocomissao) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("historicocomissao", historicocomissao);
		session.setAttribute("voltar", "consPagamentoComissao");
		return "editarComissao";
	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		String sql = "Select u From Usuario u WHERE u.ativo=true";
		sql = sql + " order by u.nome";
		listaUsuario = usuarioFacade.listar(sql);
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public void gerarListaSituacao() {
	    SituacaoFacade situacaoFacade = new SituacaoFacade();
	    String sql = "Select s From Situacao s WHERE s.visualizar=true ";
	    sql = String.valueOf(sql) + " ORDER BY s.descricao";
	    this.listaSituacao = situacaoFacade.lista(sql);
	    if (this.listaSituacao == null)
	      this.listaSituacao = new ArrayList<>(); 
	  }

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Where t.ativo=true");
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<Tipooperacao>();
		}
	}
	
	public void baixarTodos() {
		boolean selecionado = false;
		List<Historicocomissao> listaSelecionado = new ArrayList<Historicocomissao>();
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).isSelecionado()) {
				listaSelecionado.add(listaComissao.get(i));
				selecionado = true;
			}
		}
		if (!selecionado) {
			listaSelecionado = listaComissao;
		}
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		for (int i = 0; i < listaSelecionado.size(); i++) {
			listaSelecionado.get(i).setBaixa(true);
			listaSelecionado.get(i).setDescricaobaixa("thumbs-up");
			listaSelecionado.get(i).setCorbaixa("green");
			historicoComissaoFacade.salvar(listaSelecionado.get(i));
		}
		gerarListaInicial();
	}

	public void selecionarTodosContratos() {
		boolean tipo = true;
		if (selecionarTodos) {
			tipo = false;
		} else {
			tipo = true;
		}
		for (int i = 0; i < listaComissao.size(); i++) {
			listaComissao.get(i).setSelecionado(tipo);
		}
	}

	public void pagou(Historicocomissao historicocomissao) {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		if (historicocomissao.getDescricaobaixa().equalsIgnoreCase("thumbs-down")) {
			historicocomissao.setDescricaobaixa("thumbs-up");
			historicocomissao.setCorbaixa("green");
			historicocomissao.setBaixa(true);
		} else {
			historicocomissao.setDescricaobaixa("thumbs-down");
			historicocomissao.setCorbaixa("red");
			historicocomissao.setBaixa(false);
		}
		historicoComissaoFacade.salvar(historicocomissao);
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBanco = bancoFacade.lista("Select b From Banco b Where b.visualizar=true ORDER BY b.nome");
		if (this.listaBanco == null)
			this.listaBanco = new ArrayList<>();
	}

}
