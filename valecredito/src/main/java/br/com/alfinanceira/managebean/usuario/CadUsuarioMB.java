package br.com.alfinanceira.managebean.usuario;

import br.com.alfinanceira.facade.BancoFacade;
import br.com.alfinanceira.facade.DepartamentoFacade;
import br.com.alfinanceira.facade.TipoColaboradorFacade;
import br.com.alfinanceira.facade.TipoOperacaoFacade;
import br.com.alfinanceira.facade.UsuarioFacade;
import br.com.alfinanceira.model.Banco;
import br.com.alfinanceira.model.Departamento;
import br.com.alfinanceira.model.Tipocolaborador;
import br.com.alfinanceira.model.Tipooperacao;
import br.com.alfinanceira.model.Usuario;
import br.com.alfinanceira.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadUsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private Tipocolaborador tipocolaborador;

	private List<Tipocolaborador> listaTipoColaborador;

	private Banco bancoDadosBancario;

	private List<Banco> listaBanco;

	private List<Departamento> listaDepartamento;

	private Departamento departamento;

	private List<Tipooperacao> listaTipoOperacao;

	private Tipooperacao tipooperacao;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.usuario = (Usuario) session.getAttribute("usuario");
		this.departamento = (Departamento) session.getAttribute("departamento");
		session.removeAttribute("usuario");
		session.removeAttribute("departamento");
		gerarListaBanco();
		if (this.usuario == null) {
			this.usuario = new Usuario();
			this.usuario.setDescricaoativo("check");
		} else {
			this.tipocolaborador = this.usuario.getTipocolaborador();
		}
		gerarListaTipoOperacao();
		gerarListaTipoColaborador();
		gerarListaDepartamento();
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tipocolaborador getTipocolaborador() {
		return this.tipocolaborador;
	}

	public void setTipocolaborador(Tipocolaborador tipocolaborador) {
		this.tipocolaborador = tipocolaborador;
	}

	public List<Tipocolaborador> getListaTipoColaborador() {
		return this.listaTipoColaborador;
	}

	public void setListaTipoColaborador(List<Tipocolaborador> listaTipoColaborador) {
		this.listaTipoColaborador = listaTipoColaborador;
	}

	public Banco getBancoDadosBancario() {
		return this.bancoDadosBancario;
	}

	public void setBancoDadosBancario(Banco bancoDadosBancario) {
		this.bancoDadosBancario = bancoDadosBancario;
	}

	public List<Banco> getListaBanco() {
		return this.listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public synchronized List<Departamento> getListaDepartamento() {
		return this.listaDepartamento;
	}

	public synchronized void setListaDepartamento(List<Departamento> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}

	public synchronized Departamento getDepartamento() {
		return this.departamento;
	}

	public synchronized void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Tipooperacao> getListaTipoOperacao() {
		return listaTipoOperacao;
	}

	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public Tipooperacao getTipooperacao() {
		return tipooperacao;
	}

	public void setTipooperacao(Tipooperacao tipooperacao) {
		this.tipooperacao = tipooperacao;
	}

	public void gerarListaTipoColaborador() {
		TipoColaboradorFacade tipoColaboradorFacade = new TipoColaboradorFacade();
		this.listaTipoColaborador = tipoColaboradorFacade.listar("Select t From Tipocolaborador t");
		if (this.listaTipoColaborador == null)
			this.listaTipoColaborador = new ArrayList<>();
	}

	public String salvar() {
		if (validarDados()) {
			UsuarioFacade usuarioFacade = new UsuarioFacade();
			this.usuario.setTipocolaborador(this.tipocolaborador);
			this.usuario.setDepartamento(this.departamento);
			if (this.usuario.getIdusuario() == null) {
				this.usuario.setAtivo(true);
				this.usuario.setSenha("t+lL5RPpboxFzSPRYideWhLr3pEApCXE683X+k3NiXw=");
				this.usuario = usuarioFacade.salvar(this.usuario);
			} else {
				this.usuario = usuarioFacade.salvar(this.usuario);
			}
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.setAttribute("departamento", this.departamento);
			return "consUsuario";
		}
		return "";
	}

	public boolean validarDados() {
		if (this.departamento == null || this.departamento.getIddepartamento() == null) {
			Mensagem.lancarMensagemInfo("Selecione o Departamento", "");
			return false;
		}
		if (this.tipocolaborador == null || this.tipocolaborador.getIdtipocolaborador() == null) {
			Mensagem.lancarMensagemInfo("Selecione o Tipo de Colaborador", "");
			return false;
		}
		return true;
	}

	

	public String cancelar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("departamento", this.departamento);
		return "consUsuario";
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBanco = bancoFacade.lista("Select b From Banco b WHERE b.visualizar=true ORDER BY b.codigo");
		if (this.listaBanco == null)
			this.listaBanco = new ArrayList<>();
	}

	public void gerarListaDepartamento() {
		DepartamentoFacade departamentoFacade = new DepartamentoFacade();
		this.listaDepartamento = departamentoFacade.lista(
				"Select d From Departamento d");
		if (this.listaDepartamento == null)
			this.listaDepartamento = new ArrayList<>();
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Where t.ativo=true Order By t.descricao");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}

}
