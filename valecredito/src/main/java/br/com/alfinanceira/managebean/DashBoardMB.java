package br.com.alfinanceira.managebean;

import br.com.alfinanceira.facade.ContratoFacade;
import br.com.alfinanceira.facade.HistoricoComissaoFacade;
import br.com.alfinanceira.facade.UsuarioFacade;
import br.com.alfinanceira.model.Contrato;
import br.com.alfinanceira.model.Historicocomissao;
import br.com.alfinanceira.model.Usuario;
import br.com.alfinanceira.util.Formatacao;
import br.com.alfinanceira.util.UsuarioLogadoMB;
import br.com.alfinanceira.facade.NotificacaoFacade;
import br.com.alfinanceira.dao.RankingVendasDao;
import br.com.alfinanceira.model.Rankingvendas;
import br.com.alfinanceira.dao.NotificacaoDao;
import br.com.alfinanceira.model.Notificacao;

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

@Named
@ViewScoped
public class DashBoardMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;

	private String mesAtual;

	private float valorPagar;

	private float valorReceber;

	private float fatutamento;

	private int mesatual;

	private int nNotificacao;

	private int nProducao;

	private int nAguardandoPagamento;

	private int nAguardandoAssinatura;

	private int nPendenciaAverbacao;

	private float valorAverbacao;

	private float valorComissaoRecebida;

	private int nComissaoRecebida;

	private boolean viewPagoCliente;

	private int nPendenciaDocumento;

	private float valorProducao;

	private int nTotalProducao;

	private int nFormalizacaoPendencia;

	private int convenio = 0;

	private int nAvisos;

	private boolean verificarAvisos;

	private boolean verificarNotificacoes;

	private int nFormalizacao;

	private int nPendencia;

	private int nSemPendencia;

	private int nCancelados;

	private float valorPendencia;

	private float valorSemPendencia;

	private float valorCancelados;

	private int nPortabilidade;

	private float valorPortabilidade;

	private int nMargem;

	private float valorMargem;

	private int nCartao;

	private float valorCartao;

	private int nRefin;

	private float valorRefin;

	private int nRefinPort;

	private float valorRefinPort;

	private List<Usuario> listaUsuario;

	private Usuario usuario;

	private Date dataini;

	private Date datafin;

	private int nFGTS;

	private float valorFGTS;
	
	private List<Contrato> listaContrato;
	
	private List<Notificacao> listaNotificacao;

	private Rankingvendas primeiroPortabilidade;

	private Rankingvendas segundoPortabilidade;

	private Rankingvendas terceiroPortabilidade;

	private Rankingvendas primeiroDemais;

	private Rankingvendas segundoDemais;

	private Rankingvendas terceiroDemais;
	
	private int nSAQUE;
	
	private float valorSAQUE;

	@PostConstruct
	public void init() {
		faturamentoMensal();
		listarNotificacao();
		// listaAvisos();
		gerarListaUsuario();
		gerarRankingPortabilidade();
		gerarRankingDemais();
		// gerarListaFormalizacao();
		gerarListaContrato();
		int mes = Formatacao.getMesData(new Date()) + 1;
		this.mesAtual = Formatacao.nomeMes(mes);
		if (this.usuarioLogadoMB.getUsuario().isAcessogeral()) {
			this.viewPagoCliente = true;
		} else {
			this.viewPagoCliente = false;
		}
	}

	public String getMesAtual() {
		return this.mesAtual;
	}

	public void setMesAtual(String mesAtual) {
		this.mesAtual = mesAtual;
	}

	public float getFatutamento() {
		return this.fatutamento;
	}

	public void setFatutamento(float fatutamento) {
		this.fatutamento = fatutamento;
	}

	public int getMesatual() {
		return this.mesatual;
	}

	public void setMesatual(int mesatual) {
		this.mesatual = mesatual;
	}

	public float getValorPagar() {
		return this.valorPagar;
	}

	public void setValorPagar(float valorPagar) {
		this.valorPagar = valorPagar;
	}

	public float getValorReceber() {
		return this.valorReceber;
	}

	public void setValorReceber(float valorReceber) {
		this.valorReceber = valorReceber;
	}

	public int getnNotificacao() {
		return this.nNotificacao;
	}

	public void setnNotificacao(int nNotificacao) {
		this.nNotificacao = nNotificacao;
	}

	public int getnProducao() {
		return this.nProducao;
	}

	public void setnProducao(int nProducao) {
		this.nProducao = nProducao;
	}

	public int getnAguardandoPagamento() {
		return this.nAguardandoPagamento;
	}

	public void setnAguardandoPagamento(int nAguardandoPagamento) {
		this.nAguardandoPagamento = nAguardandoPagamento;
	}

	public int getnAguardandoAssinatura() {
		return this.nAguardandoAssinatura;
	}

	public void setnAguardandoAssinatura(int nAguardandoAssinatura) {
		this.nAguardandoAssinatura = nAguardandoAssinatura;
	}

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return this.usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public int getnPendenciaAverbacao() {
		return this.nPendenciaAverbacao;
	}

	public void setnPendenciaAverbacao(int nPendenciaAverbacao) {
		this.nPendenciaAverbacao = nPendenciaAverbacao;
	}

	public float getValorAverbacao() {
		return this.valorAverbacao;
	}

	public void setValorAverbacao(float valorAverbacao) {
		this.valorAverbacao = valorAverbacao;
	}

	public float getValorComissaoRecebida() {
		return this.valorComissaoRecebida;
	}

	public void setValorComissaoRecebida(float valorComissaoRecebida) {
		this.valorComissaoRecebida = valorComissaoRecebida;
	}

	public int getnComissaoRecebida() {
		return this.nComissaoRecebida;
	}

	public void setnComissaoRecebida(int nComissaoRecebida) {
		this.nComissaoRecebida = nComissaoRecebida;
	}

	public boolean isViewPagoCliente() {
		return this.viewPagoCliente;
	}

	public void setViewPagoCliente(boolean viewPagoCliente) {
		this.viewPagoCliente = viewPagoCliente;
	}

	public int getnPendenciaDocumento() {
		return this.nPendenciaDocumento;
	}

	public void setnPendenciaDocumento(int nPendenciaDocumento) {
		this.nPendenciaDocumento = nPendenciaDocumento;
	}

	public float getValorProducao() {
		return this.valorProducao;
	}

	public void setValorProducao(float valorProducao) {
		this.valorProducao = valorProducao;
	}

	public int getnTotalProducao() {
		return this.nTotalProducao;
	}

	public void setnTotalProducao(int nTotalProducao) {
		this.nTotalProducao = nTotalProducao;
	}

	public int getnFormalizacaoPendencia() {
		return this.nFormalizacaoPendencia;
	}

	public void setnFormalizacaoPendencia(int nFormalizacaoPendencia) {
		this.nFormalizacaoPendencia = nFormalizacaoPendencia;
	}

	public int getConvenio() {
		return convenio;
	}

	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}

	public int getnAvisos() {
		return nAvisos;
	}

	public void setnAvisos(int nAvisos) {
		this.nAvisos = nAvisos;
	}

	public boolean isVerificarAvisos() {
		return verificarAvisos;
	}

	public void setVerificarAvisos(boolean verificarAvisos) {
		this.verificarAvisos = verificarAvisos;
	}

	public boolean isVerificarNotificacoes() {
		return verificarNotificacoes;
	}

	public void setVerificarNotificacoes(boolean verificarNotificacoes) {
		this.verificarNotificacoes = verificarNotificacoes;
	}

	public int getnFormalizacao() {
		return nFormalizacao;
	}

	public void setnFormalizacao(int nFormalizacao) {
		this.nFormalizacao = nFormalizacao;
	}

	public int getnPendencia() {
		return nPendencia;
	}

	public void setnPendencia(int nPendencia) {
		this.nPendencia = nPendencia;
	}

	public int getnSemPendencia() {
		return nSemPendencia;
	}

	public void setnSemPendencia(int nSemPendencia) {
		this.nSemPendencia = nSemPendencia;
	}

	public int getnCancelados() {
		return nCancelados;
	}

	public void setnCancelados(int nCancelados) {
		this.nCancelados = nCancelados;
	}

	/**
	 * @return the valorPendencia
	 */
	public float getValorPendencia() {
		return valorPendencia;
	}

	/**
	 * @param valorPendencia the valorPendencia to set
	 */
	public void setValorPendencia(float valorPendencia) {
		this.valorPendencia = valorPendencia;
	}

	/**
	 * @return the valorSemPendencia
	 */
	public float getValorSemPendencia() {
		return valorSemPendencia;
	}

	/**
	 * @param valorSemPendencia the valorSemPendencia to set
	 */
	public void setValorSemPendencia(float valorSemPendencia) {
		this.valorSemPendencia = valorSemPendencia;
	}

	/**
	 * @return the valorCancelados
	 */
	public float getValorCancelados() {
		return valorCancelados;
	}

	/**
	 * @param valorCancelados the valorCancelados to set
	 */
	public void setValorCancelados(float valorCancelados) {
		this.valorCancelados = valorCancelados;
	}

	/**
	 * @return the nPortabilidade
	 */
	public int getnPortabilidade() {
		return nPortabilidade;
	}

	/**
	 * @param nPortabilidade the nPortabilidade to set
	 */
	public void setnPortabilidade(int nPortabilidade) {
		this.nPortabilidade = nPortabilidade;
	}

	/**
	 * @return the valorPortabilidade
	 */
	public float getValorPortabilidade() {
		return valorPortabilidade;
	}

	/**
	 * @param valorPortabilidade the valorPortabilidade to set
	 */
	public void setValorPortabilidade(float valorPortabilidade) {
		this.valorPortabilidade = valorPortabilidade;
	}

	/**
	 * @return the nMargem
	 */
	public int getnMargem() {
		return nMargem;
	}

	/**
	 * @param nMargem the nMargem to set
	 */
	public void setnMargem(int nMargem) {
		this.nMargem = nMargem;
	}

	/**
	 * @return the valorMargem
	 */
	public float getValorMargem() {
		return valorMargem;
	}

	/**
	 * @param valorMargem the valorMargem to set
	 */
	public void setValorMargem(float valorMargem) {
		this.valorMargem = valorMargem;
	}

	/**
	 * @return the nCartao
	 */
	public int getnCartao() {
		return nCartao;
	}

	/**
	 * @param nCartao the nCartao to set
	 */
	public void setnCartao(int nCartao) {
		this.nCartao = nCartao;
	}

	/**
	 * @return the valorCartao
	 */
	public float getValorCartao() {
		return valorCartao;
	}

	/**
	 * @param valorCartao the valorCartao to set
	 */
	public void setValorCartao(float valorCartao) {
		this.valorCartao = valorCartao;
	}

	/**
	 * @return the nRefin
	 */
	public int getnRefin() {
		return nRefin;
	}

	/**
	 * @param nRefin the nRefin to set
	 */
	public void setnRefin(int nRefin) {
		this.nRefin = nRefin;
	}

	/**
	 * @return the valorRefin
	 */
	public float getValorRefin() {
		return valorRefin;
	}

	/**
	 * @param valorRefin the valorRefin to set
	 */
	public void setValorRefin(float valorRefin) {
		this.valorRefin = valorRefin;
	}

	/**
	 * @return the nRefinPort
	 */
	public int getnRefinPort() {
		return nRefinPort;
	}

	/**
	 * @param nRefinPort the nRefinPort to set
	 */
	public void setnRefinPort(int nRefinPort) {
		this.nRefinPort = nRefinPort;
	}

	/**
	 * @return the valorRefinPort
	 */
	public float getValorRefinPort() {
		return valorRefinPort;
	}

	/**
	 * @param valorRefinPort the valorRefinPort to set
	 */
	public void setValorRefinPort(float valorRefinPort) {
		this.valorRefinPort = valorRefinPort;
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
	 * @return the dataini
	 */
	public Date getDataini() {
		return dataini;
	}

	/**
	 * @param dataini the dataini to set
	 */
	public void setDataini(Date dataini) {
		this.dataini = dataini;
	}

	/**
	 * @return the datafin
	 */
	public Date getDatafin() {
		return datafin;
	}

	/**
	 * @param datafin the datafin to set
	 */
	public void setDatafin(Date datafin) {
		this.datafin = datafin;
	}

	/**
	 * @return the valorFGTS
	 */
	public float getValorFGTS() {
		return valorFGTS;
	}

	/**
	 * @param valorFGTS the valorFGTS to set
	 */
	public void setValorFGTS(float valorFGTS) {
		this.valorFGTS = valorFGTS;
	}

	/**
	 * @return the nFGTS
	 */
	public int getnFGTS() {
		return nFGTS;
	}

	/**
	 * @param nFGTS the nFGTS to set
	 */
	public void setnFGTS(int nFGTS) {
		this.nFGTS = nFGTS;
	}

	/**
	 * @return the listaNotificacao
	 */
	public List<Notificacao> getListaNotificacao() {
		return listaNotificacao;
	}

	/**
	 * @param listaNotificacao the listaNotificacao to set
	 */
	public void setListaNotificacao(List<Notificacao> listaNotificacao) {
		this.listaNotificacao = listaNotificacao;
	}

	/**
	 * @return the listaContrato
	 */
	public List<Contrato> getListaContrato() {
		return listaContrato;
	}

	/**
	 * @param listaContrato the listaContrato to set
	 */
	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}

	/**
	 * @return the primeiroPortabilidade
	 */
	public Rankingvendas getPrimeiroPortabilidade() {
		return primeiroPortabilidade;
	}

	/**
	 * @param primeiroPortabilidade the primeiroPortabilidade to set
	 */
	public void setPrimeiroPortabilidade(Rankingvendas primeiroPortabilidade) {
		this.primeiroPortabilidade = primeiroPortabilidade;
	}

	/**
	 * @return the segundoPortabilidade
	 */
	public Rankingvendas getSegundoPortabilidade() {
		return segundoPortabilidade;
	}

	/**
	 * @param segundoPortabilidade the segundoPortabilidade to set
	 */
	public void setSegundoPortabilidade(Rankingvendas segundoPortabilidade) {
		this.segundoPortabilidade = segundoPortabilidade;
	}

	/**
	 * @return the terceiroPortabilidade
	 */
	public Rankingvendas getTerceiroPortabilidade() {
		return terceiroPortabilidade;
	}

	/**
	 * @param terceiroPortabilidade the terceiroPortabilidade to set
	 */
	public void setTerceiroPortabilidade(Rankingvendas terceiroPortabilidade) {
		this.terceiroPortabilidade = terceiroPortabilidade;
	}

	/**
	 * @return the nSAQUE
	 */
	public int getnSAQUE() {
		return nSAQUE;
	}

	/**
	 * @param nSAQUE the nSAQUE to set
	 */
	public void setnSAQUE(int nSAQUE) {
		this.nSAQUE = nSAQUE;
	}

	/**
	 * @return the valorSAQUE
	 */
	public float getValorSAQUE() {
		return valorSAQUE;
	}

	/**
	 * @param valorSAQUE the valorSAQUE to set
	 */
	public void setValorSAQUE(float valorSAQUE) {
		this.valorSAQUE = valorSAQUE;
	}

	/**
	 * @return the primeiroDemais
	 */
	public Rankingvendas getPrimeiroDemais() {
		return primeiroDemais;
	}

	/**
	 * @param primeiroDemais the primeiroDemais to set
	 */
	public void setPrimeiroDemais(Rankingvendas primeiroDemais) {
		this.primeiroDemais = primeiroDemais;
	}

	/**
	 * @return the segundoDemais
	 */
	public Rankingvendas getSegundoDemais() {
		return segundoDemais;
	}

	/**
	 * @param segundoDemais the segundoDemais to set
	 */
	public void setSegundoDemais(Rankingvendas segundoDemais) {
		this.segundoDemais = segundoDemais;
	}

	/**
	 * @return the terceiroDemais
	 */
	public Rankingvendas getTerceiroDemais() {
		return terceiroDemais;
	}

	/**
	 * @param terceiroDemais the terceiroDemais to set
	 */
	public void setTerceiroDemais(Rankingvendas terceiroDemais) {
		this.terceiroDemais = terceiroDemais;
	}

	public void faturamentoMensal() {
		this.mesatual = Formatacao.getMesData(new Date()) + 1;
		Date dataInicio = Formatacao.ConvercaoStringData("2021-" + mesatual + "-01");
		this.dataini = dataInicio;
		String dataSqlInicio = Formatacao.ConvercaoDataNfe(dataInicio);
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		String sql = "Select h From Historicocomissao h Where h.baixa=false" + " and h.contrato.datacadastro>='"
				+ dataSqlInicio + "' AND h.contrato.situacao.idsituacao<>2";
		if (!this.usuarioLogadoMB.getUsuario().isAcessogeral())
			sql = String.valueOf(sql) + " and h.contrato.usuario.idusuario="
					+ this.usuarioLogadoMB.getUsuario().getIdusuario();

		List<Historicocomissao> lista = historicoComissaoFacade.lista(sql);
		if (lista == null)
			lista = new ArrayList<>();
		this.nAguardandoAssinatura = 0;
		this.nAguardandoPagamento = 0;
		this.nProducao = 0;
		this.fatutamento = 0.0F;
		this.valorPagar = 0.0F;
		this.valorReceber = 0.0F;
		this.valorComissaoRecebida = 0.0F;
		this.valorAverbacao = 0.0F;
		this.valorProducao = 0.0F;
		this.nPendenciaDocumento = 0;
		this.valorProducao = 0.0F;
		this.nTotalProducao = 0;
		this.nFormalizacaoPendencia = 0;
		this.nPendencia = 0;
		this.nSemPendencia = 0;
		this.nCancelados = 0;
		this.valorCancelados = 0.0f;
		this.valorSemPendencia = 0.0f;
		this.valorPendencia = 0.0f;
		this.valorPendencia = 0.0f;
		this.valorMargem = 0.0f;
		this.valorCartao = 0.0f;
		this.valorRefin = 0.0f;
		this.valorRefinPort = 0.0f;
		this.nPortabilidade = 0;
		this.nMargem = 0;
		this.nCartao = 0;
		this.nRefin = 0;
		this.nRefinPort = 0;
		this.nFGTS = 0;
		this.valorFGTS = 0.0f;
		this.nSAQUE = 0;
		this.valorSAQUE = 0.0f;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getContrato().getSituacao().getIdsituacao() != 2) {
				this.valorProducao += ((Historicocomissao) lista.get(i)).getProdliq();
				this.nTotalProducao = nTotalProducao + 1;
				if (lista.get(i).getContrato().getSituacao().getIdsituacao() != 6) {
					this.nSemPendencia = nSemPendencia + 1;
					this.valorSemPendencia = valorSemPendencia + lista.get(i).getProdliq();
				} else {
					this.nPendencia = nPendencia + 1;
					this.valorPendencia = valorPendencia + lista.get(i).getProdliq();
				}

				if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1
						|| lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 5) {
					nPortabilidade = nPortabilidade + 1;
					valorPortabilidade = valorPortabilidade + lista.get(i).getProdliq();
				} else if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2) {
					nMargem = nMargem + 1;
					valorMargem = valorMargem + lista.get(i).getProdliq();
				} else if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 3) {
					nCartao = nCartao + 1;
					valorCartao = valorCartao + lista.get(i).getProdliq();
				}else if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 7) {
					nSAQUE = nSAQUE + 1;
					valorSAQUE = valorSAQUE + lista.get(i).getContrato().getValorliberado();
				} else if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 4) {
					nRefin = nRefin + 1;
					valorRefin = valorRefin + lista.get(i).getProdliq();
				} else if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 8) {
					nFGTS = nFGTS + 1;
					valorFGTS = valorFGTS + lista.get(i).getProdliq();
				}
			}
		}
	}

	public void pesquisar() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		String sql = "Select h From Historicocomissao h Where h.baixa=false and h.contrato.simulacao=false"
				+ " and h.contrato.usuario.treinamento=false";
		if (!this.usuarioLogadoMB.getUsuario().isAcessogeral())
			sql = String.valueOf(sql) + " and h.contrato.usuario.idusuario="
					+ this.usuarioLogadoMB.getUsuario().getIdusuario();
		if (this.convenio > 0) {
			if (this.convenio == 1) {
				sql = String.valueOf(sql) + " and h.contrato.operacaoinss=true";
			} else if (this.convenio == 2) {
				sql = String.valueOf(sql) + " and h.contrato.operacaoinss=false";
			}
		}
		if (dataini != null && datafin != null) {
			sql = sql + " and h.contrato.datacadastro>='" + Formatacao.ConvercaoDataNfe(dataini)
					+ "' and h.contrato.datacadastro<='" + Formatacao.ConvercaoDataNfe(datafin) + "'";
		}
		if (usuario != null && usuario.getIdusuario() != null) {
			sql = sql + " and h.contrato.usuario.idusuario=" + usuario.getIdusuario();
		}
		List<Historicocomissao> lista = historicoComissaoFacade.lista(sql);
		if (lista == null)
			lista = new ArrayList<>();
		this.nAguardandoAssinatura = 0;
		this.nAguardandoPagamento = 0;
		this.nProducao = 0;
		this.fatutamento = 0.0F;
		this.valorPagar = 0.0F;
		this.valorReceber = 0.0F;
		this.valorComissaoRecebida = 0.0F;
		this.valorAverbacao = 0.0F;
		this.valorProducao = 0.0F;
		this.nPendenciaDocumento = 0;
		this.valorProducao = 0.0F;
		this.nTotalProducao = 0;
		this.nFormalizacaoPendencia = 0;
		this.nPendencia = 0;
		this.nSemPendencia = 0;
		this.nCancelados = 0;
		this.valorCancelados = 0.0f;
		this.valorSemPendencia = 0.0f;
		this.valorPendencia = 0.0f;
		this.valorPendencia = 0.0f;
		this.valorMargem = 0.0f;
		this.valorCartao = 0.0f;
		this.valorRefin = 0.0f;
		this.valorRefinPort = 0.0f;
		this.nPortabilidade = 0;
		this.nMargem = 0;
		this.nCartao = 0;
		this.nRefin = 0;
		this.nRefinPort = 0;
		this.nFGTS = 0;
		this.valorFGTS = 0.0f;
		this.valorPortabilidade = 0.0f;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getContrato().getSituacao().getIdsituacao() != 2) {
				this.valorProducao += ((Historicocomissao) lista.get(i)).getProdliq();
				this.nTotalProducao++;
				if (lista.get(i).getContrato().getSituacao().getIdsituacao() != 5
						&& lista.get(i).getContrato().getSituacao().getIdsituacao() != 37
						&& lista.get(i).getContrato().getSituacao().getIdsituacao() != 36) {
					this.nSemPendencia = nSemPendencia + 1;
					this.valorSemPendencia = valorSemPendencia + lista.get(i).getProdliq();
				}

				if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1) {
					nPortabilidade = nPortabilidade + 1;
					valorPortabilidade = valorPortabilidade + lista.get(i).getProdliq();
				} else if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2) {
					nMargem = nMargem + 1;
					valorMargem = valorMargem + lista.get(i).getProdliq();
				} else if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 3
						|| lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 4
						|| lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 5) {
					nCartao = nCartao + 1;
					valorCartao = valorCartao + lista.get(i).getProdliq();
				} else if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 7) {
					nRefin = nRefin + 1;
					valorRefin = valorRefin + lista.get(i).getProdliq();
				} else if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 8) {
					nRefinPort = nRefinPort + 1;
					valorRefinPort = valorRefinPort + lista.get(i).getProdliq();
				} else if (lista.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 17) {
					nFGTS = nFGTS + 1;
					valorFGTS = valorFGTS + lista.get(i).getProdliq();
				}
			}
		}
	}

	public String pagoCliente() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "16");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public String receitaNaoPaga() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "19");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public String receitaPaga() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "28");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public String receitaPendencia() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "36");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public String comissaoRecebida() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "comissao");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public String producaoGeral() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("convenio", convenio);
		session.setAttribute("tipoFiltro", "0");
		return "consProducao";
	}

	public String pendencias() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("voltar", "dashboard");
		return "consPendencias";
	}

	public String formalizacao() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("voltar", "dashboard");
		return "consFormalizacao";
	}

	public String receitaPendenciaDocs() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "5");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		String sql = "Select u From Usuario u WHERE u.ativo=true";
		sql = sql + " and u.departamento.iddepartamento=7 order by u.nome";
		listaUsuario = usuarioFacade.listar(sql);
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}

	public void gerarListaContrato() {
		ContratoFacade contratoFacade = new ContratoFacade();
		String sql = "Select c From Contrato c WHERE c.situacao.idsituacao<>2 ";
		if (!this.usuarioLogadoMB.getUsuario().isAcessogeral())
			sql = String.valueOf(sql) + " AND c.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario();
		sql = String.valueOf(sql) + " ORDER BY c.idcontrato DESC";
		listaContrato = contratoFacade.lista(sql);
		if (listaContrato == null) {
			listaContrato = new ArrayList<>();
		}
	}
	
	
	public void listarNotificacao() {
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		List<Notificacao> listaNotificacao = notificacaoDao
				.lista("Select n From Notificacao n WHERE n.visto=false AND n.usuario.idusuario="
						+ this.usuarioLogadoMB.getUsuario().getIdusuario() + " ORDER BY n.datalancamento DESC");
		if (listaNotificacao == null)
			listaNotificacao = new ArrayList<>();
		this.listaNotificacao = new ArrayList<Notificacao>();
		for (int i = 0; i < listaNotificacao.size(); i++) {
			if (this.listaNotificacao.size() < 3) {
				this.listaNotificacao.add(listaNotificacao.get(i));
			} else {
				i = listaNotificacao.size();
			}
		}
		this.nNotificacao = listaNotificacao.size();
		if (nNotificacao > 0) {
			verificarNotificacoes = true;
		} else {
			verificarNotificacoes = false;
		}
	}
	
	
	public void vistoTodos() {
		NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
		if (listaNotificacao == null) {
			listaNotificacao = new ArrayList<Notificacao>();
		}
		for (int i = 0; i < listaNotificacao.size(); i++) {
			listaNotificacao.get(i).setVisto(true);
			notificacaoFacade.salvar(listaNotificacao.get(i));
		}
		listaNotificacao = new ArrayList<Notificacao>();
		listarNotificacao();
	}
	
	
	public void gerarRankingPortabilidade() {
		RankingVendasDao rankingVendasDao = new RankingVendasDao();
		List<Rankingvendas> listaRanking = rankingVendasDao
				.lista("Select r From Rankingvendas r WHERE r.mes=" + (Formatacao.getMesData(new Date()) + 1)
						+ " AND r.ano=" + Formatacao.getAnoData(new Date()) 
						+ " AND r.portabilidade=true ORDER BY r.valorvenda DESC");
		if (listaRanking == null)
			listaRanking = new ArrayList<>();
		primeiroPortabilidade = new Rankingvendas();
		segundoPortabilidade = new Rankingvendas();
		terceiroPortabilidade = new Rankingvendas();
		for (int i = 0; i < listaRanking.size(); i++) {
			if (i == 0) {
				this.primeiroPortabilidade = listaRanking.get(i);
				if (this.segundoPortabilidade == null)
					this.terceiroPortabilidade = new Rankingvendas();
			} else if (i == 1) {
				this.segundoPortabilidade = listaRanking.get(i);
				if (this.segundoPortabilidade == null)
					this.segundoPortabilidade = new Rankingvendas();
			} else if (i == 2) {
				this.terceiroPortabilidade = listaRanking.get(i);
				if (this.terceiroPortabilidade == null)
					this.terceiroPortabilidade = new Rankingvendas();
			}
		}
	}
	
	
	public void gerarRankingDemais() {
		RankingVendasDao rankingVendasDao = new RankingVendasDao();
		List<Rankingvendas> listaRanking = rankingVendasDao
				.lista("Select r From Rankingvendas r WHERE r.mes=" + (Formatacao.getMesData(new Date()) + 1)
						+ " AND r.ano=" + Formatacao.getAnoData(new Date()) 
						+ " AND r.portabilidade=false ORDER BY r.valorvenda DESC");
		if (listaRanking == null)
			listaRanking = new ArrayList<>();
		primeiroDemais = new Rankingvendas();
		segundoDemais = new Rankingvendas();
		terceiroDemais = new Rankingvendas();
		for (int i = 0; i < listaRanking.size(); i++) {
			if (i == 0) {
				this.primeiroDemais = listaRanking.get(i);
				if (this.segundoDemais == null)
					this.terceiroDemais = new Rankingvendas();
			} else if (i == 1) {
				this.segundoDemais = listaRanking.get(i);
				if (this.segundoDemais == null)
					this.segundoDemais = new Rankingvendas();
			} else if (i == 2) {
				this.terceiroDemais = listaRanking.get(i);
				if (this.terceiroDemais == null)
					this.terceiroDemais = new Rankingvendas();
			}
		}
	}

}
