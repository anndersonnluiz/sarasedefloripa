package br.com.alfinanceira.managebean.contrato;

import br.com.alfinanceira.facade.BancoFacade;
import br.com.alfinanceira.facade.CoeficienteFacade;
import br.com.alfinanceira.facade.OrgaoBancoFacade;
import br.com.alfinanceira.facade.SituacaoFacade;
import br.com.alfinanceira.facade.UsuarioFacade;
import br.com.alfinanceira.model.Banco;
import br.com.alfinanceira.model.Coeficiente;
import br.com.alfinanceira.model.Contrato;
import br.com.alfinanceira.model.Historicocomissao;
import br.com.alfinanceira.model.OrgaoBanco;
import br.com.alfinanceira.model.Situacao;
import br.com.alfinanceira.model.Usuario;
import br.com.alfinanceira.util.Formatacao;
import br.com.alfinanceira.util.Mensagem;
import br.com.alfinanceira.util.UsuarioLogadoMB;
import br.com.alfinanceira.dao.NotificacaoDao;
import br.com.alfinanceira.model.Notificacao;
import br.com.alfinanceira.facade.ContratoFacade;
import br.com.alfinanceira.facade.HistoricoComissaoFacade;

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
public class AlterarSituacaoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;

	private Contrato contrato;

	private Situacao situacao;

	private List<Situacao> listaSituacao;

	private String voltar;

	private boolean valorliberado;

	private List<Banco> listaBanco;

	private List<OrgaoBanco> listaOrgao;

	private Banco banco;

	private OrgaoBanco orgaoBanco;

	private List<Coeficiente> listaCoefiente;

	private Coeficiente coeficiente;

	private List<Usuario> listaUsuario;

	private Usuario operador;
	
	private boolean habilitarPortabilidade = false;
	
	private float valorComissaoTotal;
	
	private float valorComissaoCorretor;
	
	private float valorComissaoLoja;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
		this.voltar = (String) session.getAttribute("voltar");
		session.removeAttribute("voltar");
		this.situacao = this.contrato.getSituacao();
		gerarListaSituacao();
		this.banco = this.contrato.getOrgaoBanco().getBanco();
		this.orgaoBanco = this.contrato.getOrgaoBanco();
		gerarListaBanco();
		gerarListaOrgao();
		buscarCoeficiente();
		gerarListaUsuario();
		if (this.usuarioLogadoMB.getUsuario().isAcessogeral()) {
			this.valorliberado = false;
		} else {
			this.valorliberado = true;
		}
		
		if (contrato.getTipooperacao().getIdtipooperacao() == 1) {
			habilitarPortabilidade = true;
		}
		gerarListaValores();
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Situacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Situacao> getListaSituacao() {
		return this.listaSituacao;
	}

	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}

	public String getVoltar() {
		return this.voltar;
	}

	public void setVoltar(String voltar) {
		this.voltar = voltar;
	}

	public boolean isValorliberado() {
		return this.valorliberado;
	}

	public void setValorliberado(boolean valorliberado) {
		this.valorliberado = valorliberado;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public OrgaoBanco getOrgaoBanco() {
		return this.orgaoBanco;
	}

	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}

	public List<Coeficiente> getListaCoefiente() {
		return listaCoefiente;
	}

	public void setListaCoefiente(List<Coeficiente> listaCoefiente) {
		this.listaCoefiente = listaCoefiente;
	}

	public Coeficiente getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Coeficiente coeficiente) {
		this.coeficiente = coeficiente;
	}

	/**
	 * @return the habilitarPortabilidade
	 */
	public boolean isHabilitarPortabilidade() {
		return habilitarPortabilidade;
	}

	/**
	 * @param habilitarPortabilidade the habilitarPortabilidade to set
	 */
	public void setHabilitarPortabilidade(boolean habilitarPortabilidade) {
		this.habilitarPortabilidade = habilitarPortabilidade;
	}
	
	

	/**
	 * @return the valorComissaoTotal
	 */
	public float getValorComissaoTotal() {
		return valorComissaoTotal;
	}

	/**
	 * @param valorComissaoTotal the valorComissaoTotal to set
	 */
	public void setValorComissaoTotal(float valorComissaoTotal) {
		this.valorComissaoTotal = valorComissaoTotal;
	}

	/**
	 * @return the valorComissaoCorretor
	 */
	public float getValorComissaoCorretor() {
		return valorComissaoCorretor;
	}

	/**
	 * @param valorComissaoCorretor the valorComissaoCorretor to set
	 */
	public void setValorComissaoCorretor(float valorComissaoCorretor) {
		this.valorComissaoCorretor = valorComissaoCorretor;
	}

	/**
	 * @return the valorComissaoLoja
	 */
	public float getValorComissaoLoja() {
		return valorComissaoLoja;
	}

	/**
	 * @param valorComissaoLoja the valorComissaoLoja to set
	 */
	public void setValorComissaoLoja(float valorComissaoLoja) {
		this.valorComissaoLoja = valorComissaoLoja;
	}

	public void gerarListaSituacao() {
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		String sql = "Select s From Situacao s WHERE s.visualizar=true ";
		if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() != 1)
			sql = String.valueOf(sql) + " AND s.portabilidade=false ";
		sql = String.valueOf(sql) + " ORDER BY s.descricao";
		this.listaSituacao = situacaoFacade.lista(sql);
		if (this.listaSituacao == null)
			this.listaSituacao = new ArrayList<>();
	}

	public String cancelar() {
		return this.voltar;
	}

	public List<Banco> getListaBanco() {
		return this.listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public List<OrgaoBanco> getListaOrgao() {
		return this.listaOrgao;
	}

	public void setListaOrgao(List<OrgaoBanco> listaOrgao) {
		this.listaOrgao = listaOrgao;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getOperador() {
		return operador;
	}

	public void setOperador(Usuario operador) {
		this.operador = operador;
	}

	public String salvar() { 
		if (validarDados()) {
			ContratoFacade contratoFacade = new ContratoFacade();
			this.contrato.setSituacao(this.situacao);
			this.contrato.setOrgaoBanco(this.orgaoBanco);
			
			//gerarProducao();
			if (this.coeficiente != null && this.coeficiente.getIdcoeficiente() != null) {
				this.contrato.setIdregracoeficiente(this.coeficiente.getIdcoeficiente().intValue());
				gerarComissao();
			} else {
				this.contrato.setIdregracoeficiente(0);
			}
			if (this.contrato.getSituacao().getIdsituacao() == 5) {
				if (contrato.getDatapagamento() == null) {
					this.contrato.setDatapagamento(new Date());
				}
			}
			gerarNotificacao();
			
			this.contrato = contratoFacade.salvar(this.contrato);
			
			return this.voltar;
		}
		return "";
	}

	

	public boolean validarDados() {
		if (situacao == null || situacao.getIdsituacao() == null) {
			Mensagem.lancarMensagemInfo("Selecione a nova situação do contrato", "");
			return false;
		}
		if (orgaoBanco == null || orgaoBanco.getIdorgaobanco() == null) {
			Mensagem.lancarMensagemInfo("Selecione um novo Orgão", "");
			return false;
		}
		if (banco == null || banco.getIdbanco() == null) {
			Mensagem.lancarMensagemInfo("Selecione um novo Banco", "");
			return false;
		}
		return true;
	}

	public void gerarNotificacao() {
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		Notificacao notificacao = new Notificacao();
		notificacao.setDatalancamento(new Date());
		notificacao.setVisto(false);
		notificacao.setUsuario(this.contrato.getUsuario());
		notificacao.setIdcontrato(this.contrato.getIdcontrato().intValue());
		notificacao.setTitulo("Contrato: " + this.contrato.getCodigocontrato());
		notificacao.setDescricao("Seu contrato do(a) cliente: " + this.contrato.getCliente().getNome()
				+ " mudou seu status para: " + this.situacao.getDescricao());
		notificacao.setTipooperacao(contrato.getTipooperacao().getDescricao());
		notificacaoDao.salvar(notificacao);
	}

	public void buscarCoeficiente() {
		if (this.contrato.getIdregracoeficiente() > 0) {
			CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
			this.coeficiente = coeficienteFacade.consultar(this.contrato.getIdregracoeficiente());
		}
	}

//	public void salvarHistorico(Historicousuario historicousuario) {
//		HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
//		historicousuario.setDatacadastro(new Date());
//		historicousuario.setTitulo("Alteração");
//		historicousuario.setIcone("mudanca.png");
//		historicousuario.setIdcontrato(this.contrato.getIdcontrato().intValue());
//		historicousuario.setHora(Formatacao.foramtarHoraString());
//		historicousuario.setUsuario(this.usuarioLogadoMB.getUsuario());
//		String convenio = "";
//		if (contrato.isOperacaoinss()) {
//			convenio = "INSS";
//		} else {
//			convenio = "SIAPE";
//		}
//		historicousuario.setDescricao("Situação alterada de " + this.alteracaoBean.getDescricao() + " para "
//				+ this.contrato.getSituacao().getDescricao() + ", Tipo do contrato: "
//				+ this.contrato.getTipooperacao().getDescricao() + " - " + convenio + ", Cliente: "
//				+ contrato.getCliente().getNome());
//		historicoUsuarioFacade.salvar(historicousuario);
//	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBanco = bancoFacade.lista("Select b From Banco b ORDER BY b.nome");
		if (this.listaBanco == null)
			this.listaBanco = new ArrayList<>();
	}

	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		if (this.banco != null && this.banco.getIdbanco() != null)
			this.listaOrgao = orgaoBancoFacade
					.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" + this.banco.getIdbanco());
		if (this.listaOrgao == null)
			this.listaOrgao = new ArrayList<>();
	}

	public void gerarListaValores() {
		CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
		if (orgaoBanco != null && orgaoBanco.getIdorgaobanco() != null) {
			this.listaCoefiente = coeficienteFacade.lista("Select c From Coeficiente c WHERE c.orgaoBanco.idorgaobanco="
					+ this.orgaoBanco.getIdorgaobanco() + " AND c.ativo=true AND c.tipooperacao.idtipooperacao="
					+ this.contrato.getTipooperacao().getIdtipooperacao());
		}
		if (this.listaCoefiente == null)
			this.listaCoefiente = new ArrayList<>();
	}

//	public void gerarProducao() {
//		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
//		Historicocomissao historicocomissao = historicoComissaoFacade
//				.consultarPorContrato(this.contrato.getIdcontrato().intValue());
//		if (historicocomissao == null) {
//			historicocomissao = new Historicocomissao();
//			historicocomissao.setDatalancamento(new Date());
//			historicocomissao.setContrato(this.contrato);
//			historicocomissao.setUsuario(this.contrato.getUsuario());
//			historicocomissao.setTipo("PENDENTE");
//			int mes = Formatacao.getMesData(new Date()) + 1;
//			int ano = Formatacao.getAnoData(new Date());
//			historicocomissao.setAno(ano);
//			historicocomissao.setMes(mes);
//		}
//		if (this.contrato.getParcelaspagas() > 12
//				&& this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
//			historicocomissao.setProdliq(this.contrato.getValorquitar());
//		} else if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() != 1) {
//			historicocomissao.setProdliq(this.contrato.getValorcliente());
//		} else {
//			historicocomissao.setProdliq(0.0F);
//		}
//		historicoComissaoFacade.salvar(historicocomissao);
//	}
//
	public void gerarComissao() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		Historicocomissao historicocomissao = historicoComissaoFacade
				.consultarPorContrato(this.contrato.getIdcontrato().intValue());
		if (historicocomissao == null) {
			historicocomissao = new Historicocomissao();
			historicocomissao.setDatalancamento(new Date());
			historicocomissao.setContrato(this.contrato);
			historicocomissao.setUsuario(this.contrato.getUsuario());
			historicocomissao.setTipo("PENDENTE");
			int mes = Formatacao.getMesData(new Date()) + 1;
			int ano = Formatacao.getAnoData(new Date());
			historicocomissao.setAno(ano);
			historicocomissao.setMes(mes);historicocomissao.setCmdbruta(0.0F);
			historicocomissao.setCmsliq(0.0F);
			historicocomissao.setComissaototal(0.0F); 
		}
		if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1
				|| this.contrato.getTipooperacao().getIdtipooperacao() == 5) {
			historicocomissao
					.setCmsliq(this.contrato.getValorquitar() * 10f / 100.0F);
			historicocomissao.setCmdbruta((this.contrato.getValorquitar()
					* (this.coeficiente.getComissaototal()/ 100.0F)) * (10f / 100.0F));

			historicocomissao.setComissaototal(historicocomissao.getCmdbruta() + historicocomissao.getCmsliq());
		} else  {
			historicocomissao
					.setCmsliq(this.contrato.getValorliberado() * 10f / 100.0F);
			historicocomissao.setCmdbruta(this.contrato.getValorliberado()
					* (this.coeficiente.getComissaototal() - 10f) / 100.0F);
			historicocomissao.setComissaototal(historicocomissao.getCmdbruta() + historicocomissao.getCmsliq());
		} 
		historicoComissaoFacade.salvar(historicocomissao);
	}

	

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		String sql = "Select u From Usuario u WHERE u.ativo=true order by u.nome";
		this.listaUsuario = usuarioFacade.listar(sql);
		if (this.listaUsuario == null)
			this.listaUsuario = new ArrayList<>();
	}


	
	
	
}
