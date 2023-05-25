package br.com.alfinanceira.managebean.contrato;

import br.com.alfinanceira.facade.BancoFacade;
import br.com.alfinanceira.facade.OrgaoBancoFacade;
import br.com.alfinanceira.facade.SituacaoFacade;
import br.com.alfinanceira.facade.TipoOperacaoFacade;
import br.com.alfinanceira.model.Banco;
import br.com.alfinanceira.model.Contrato;
import br.com.alfinanceira.model.OrgaoBanco;
import br.com.alfinanceira.model.Tipooperacao;
import br.com.alfinanceira.util.Mensagem;
import br.com.alfinanceira.util.UsuarioLogadoMB;

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
public class ConfDadosContratoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;

	private List<Banco> listaBanco;

	private Banco banco;

	private List<OrgaoBanco> listaOrgaoBanco;

	private OrgaoBanco orgaoBanco;

	private Contrato contrato;

	private List<Tipooperacao> listaTipoOperacao;

	private Tipooperacao tipooiperacao;

	private String nomeCliente;

	private String cpf;

	private boolean operacaoinss;

	private int tipoOpcoes;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.contrato = new Contrato();

		gerarListaBanco();
		gerarListaTipoOperacao();
		this.contrato.setDatacadastro(new Date());
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		this.contrato.setSituacao(situacaoFacade.consultar(1));
		this.tipoOpcoes = 1;
		this.contrato.setTipooperacao(tipooiperacao);
	}

	public List<Banco> getListaBanco() {
		return this.listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public List<OrgaoBanco> getListaOrgaoBanco() {
		return this.listaOrgaoBanco;
	}

	public void setListaOrgaoBanco(List<OrgaoBanco> listaOrgaoBanco) {
		this.listaOrgaoBanco = listaOrgaoBanco;
	}

	public OrgaoBanco getOrgaoBanco() {
		return this.orgaoBanco;
	}

	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Tipooperacao> getListaTipoOperacao() {
		return this.listaTipoOperacao;
	}

	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public Tipooperacao getTipooiperacao() {
		return this.tipooiperacao;
	}

	public void setTipooiperacao(Tipooperacao tipooiperacao) {
		this.tipooiperacao = tipooiperacao;
	}

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isOperacaoinss() {
		return this.operacaoinss;
	}

	public int getTipoOpcoes() {
		return this.tipoOpcoes;
	}

	public void setTipoOpcoes(int tipoOpcoes) {
		this.tipoOpcoes = tipoOpcoes;
	}

	public void setOperacaoinss(boolean operacaoinss) {
		this.operacaoinss = operacaoinss;
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBanco = bancoFacade.lista("Select b From Banco b Where b.visualizar=true ORDER BY b.nome");
		if (this.listaBanco == null)
			this.listaBanco = new ArrayList<>();
	}

	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		this.listaOrgaoBanco = orgaoBancoFacade
				.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" + this.banco.getIdbanco());
		if (this.listaOrgaoBanco == null)
			this.listaOrgaoBanco = new ArrayList<>();
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Where t.ativo=true Order By t.descricao");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}

	public String cancelar() {
		return "consContrato";
	}

	public String cadContrato() {
		if (this.contrato.getTipooperacao() != null && this.contrato.getTipooperacao().getIdtipooperacao() != null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.setAttribute("banco", this.banco);
			session.setAttribute("contrato", this.contrato);
			session.setAttribute("orgaobanco", this.orgaoBanco);
			if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
				this.contrato.setVoltarTela("consPortabilidade");
			} else if (this.contrato.isOperacaoinss()) {
				this.contrato.setVoltarTela("consDemaisOperacoesINSS");
			} else if (!this.contrato.isOperacaoinss()) {
				this.contrato.setVoltarTela("consDemaisOperacoes");
			}else {
				this.contrato.setVoltarTela("consDemaisFGTS");
			}
			if (contrato.getTipooperacao().getIdtipooperacao() == 17) {
				return "cadFGTS";
			}
		} else {
			Mensagem.lancarMensagemInfo("Selecione o Tipo de Operação", "");
		}
		
		return "cadContrato";
	}


}
