package br.com.alfinanceira.managebean.contrato;

import br.com.alfinanceira.dao.TipoArquivoDao;
import br.com.alfinanceira.dao.UsuarioDao;
import br.com.alfinanceira.facade.BancoFacade;
import br.com.alfinanceira.facade.ClienteFacade;
import br.com.alfinanceira.facade.CoeficienteFacade;
import br.com.alfinanceira.facade.ContratoArquivoFacade;
import br.com.alfinanceira.facade.ContratoFacade;
import br.com.alfinanceira.facade.HistoricoComissaoFacade;
import br.com.alfinanceira.facade.NotificacaoFacade;
import br.com.alfinanceira.facade.OrgaoBancoFacade;
import br.com.alfinanceira.facade.TipoOperacaoFacade;
import br.com.alfinanceira.facade.UsuarioFacade;
import br.com.alfinanceira.model.Banco;
import br.com.alfinanceira.model.Cliente;
import br.com.alfinanceira.model.Coeficiente;
import br.com.alfinanceira.model.Contrato;
import br.com.alfinanceira.model.Contratoarquivo;
import br.com.alfinanceira.model.Historicocomissao;
import br.com.alfinanceira.model.Notificacao;
import br.com.alfinanceira.model.OrgaoBanco;
import br.com.alfinanceira.model.Tipoarquivo;
import br.com.alfinanceira.model.Tipooperacao;
import br.com.alfinanceira.model.Usuario;
import br.com.alfinanceira.util.Formatacao;
import br.com.alfinanceira.util.Ftp;
import br.com.alfinanceira.util.Mensagem;
import br.com.alfinanceira.util.UsuarioLogadoMB;
import br.com.alfinanceira.facade.RankingVendasFacade;
import br.com.alfinanceira.model.Rankingvendas;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class CadContratoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Banco> listaBanco;

	private List<Banco> listaBancoInicio;

	private Banco banco;

	private List<OrgaoBanco> listaOrgaoBanco;

	private OrgaoBanco orgaoBanco;

	private Contrato contrato;

	private Cliente cliente;

	private String cpf;

	private List<Banco> listaBancoOperacao;

	private List<Tipooperacao> listaTipoOperacao;

	private Tipooperacao tipooiperacao;

	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;

	private Banco bancoDadosBancario;

	private int mes;

	private int ano;

	private Coeficiente coeficiente;

	private boolean novo = true;

	private List<Contratoarquivo> listaContratoArquivo;

	private Tipoarquivo tipoarquivo;

	private StreamedContent fileDownload;

	private List<String> listaNomeArquivo;

	private UploadedFile file;

	private boolean arquivoEnviado;

	private String tipoDocumento;

	private List<Tipoarquivo> listaTipoArquivo;

	private Contratoarquivo contratoarquivo;

	private List<Usuario> listaUsuario;

	private Usuario usuario;

	private boolean habilitarUsuario = true;
	private String voltarTela;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.contrato = (Contrato) session.getAttribute("contrato");
		this.orgaoBanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		this.banco = (Banco) session.getAttribute("banco");
		this.coeficiente = (Coeficiente) session.getAttribute("coeficiente");
		this.voltarTela = (String) session.getAttribute("voltarTela");
		session.removeAttribute("orgaobanco");
		session.removeAttribute("contrato");
		session.removeAttribute("coeficiente");
		session.removeAttribute("voltarTela");
		this.cliente = this.contrato.getCliente();
		gerarListaBanco();
		gerarListaTipoArquivo();
		gerarListaOrgao();
		gerarListaUsuario();
		if (this.contrato == null) {
			this.contrato = new Contrato();
			this.mes = Formatacao.getMesData(new Date()) + 1;
			this.ano = Formatacao.getAnoData(new Date());
			this.usuario = this.usuarioLogadoMB.getUsuario();
		} else if (this.contrato.getIdcontrato() != null) {
			this.novo = false;
			gerarListaContratoAquivo();
			this.usuario = this.contrato.getUsuario();
			buscarBanco();
			gerarListaOrgaoEdicao();
			this.orgaoBanco = this.contrato.getOrgaoBanco();
		} else {

			this.usuario = this.usuarioLogadoMB.getUsuario();
			BancoFacade bancoFacade = new BancoFacade();
			this.banco = bancoFacade.consultar(1);
			gerarListaOrgao();
		}
		if (this.usuarioLogadoMB.getUsuario().isAcessogeral())
			this.habilitarUsuario = false;
		if (this.cliente != null && this.cliente.getIdcliente() != null) {
			if (cliente.getCpf() != null && cliente.getCpf().length() > 0) {
				this.cpf = this.cliente.getCpf();
			}
		} else {
			this.cliente = new Cliente();
			this.cliente.setUsuario(this.usuario);
		}
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

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Banco> getListaBancoOperacao() {
		return this.listaBancoOperacao;
	}

	public void setListaBancoOperacao(List<Banco> listaBancoOperacao) {
		this.listaBancoOperacao = listaBancoOperacao;
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

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return this.usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public Banco getBancoDadosBancario() {
		return this.bancoDadosBancario;
	}

	public void setBancoDadosBancario(Banco bancoDadosBancario) {
		this.bancoDadosBancario = bancoDadosBancario;
	}

	public int getMes() {
		return this.mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Coeficiente getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Coeficiente coeficiente) {
		this.coeficiente = coeficiente;
	}

	public boolean isNovo() {
		return this.novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}

	public List<Contratoarquivo> getListaContratoArquivo() {
		return this.listaContratoArquivo;
	}

	public void setListaContratoArquivo(List<Contratoarquivo> listaContratoArquivo) {
		this.listaContratoArquivo = listaContratoArquivo;
	}

	public Tipoarquivo getTipoarquivo() {
		return this.tipoarquivo;
	}

	public void setTipoarquivo(Tipoarquivo tipoarquivo) {
		this.tipoarquivo = tipoarquivo;
	}

	public StreamedContent getFileDownload() {
		return this.fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public List<String> getListaNomeArquivo() {
		return this.listaNomeArquivo;
	}

	public void setListaNomeArquivo(List<String> listaNomeArquivo) {
		this.listaNomeArquivo = listaNomeArquivo;
	}

	public UploadedFile getFile() {
		return this.file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public boolean isArquivoEnviado() {
		return this.arquivoEnviado;
	}

	public void setArquivoEnviado(boolean arquivoEnviado) {
		this.arquivoEnviado = arquivoEnviado;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<Tipoarquivo> getListaTipoArquivo() {
		return this.listaTipoArquivo;
	}

	public void setListaTipoArquivo(List<Tipoarquivo> listaTipoArquivo) {
		this.listaTipoArquivo = listaTipoArquivo;
	}

	public Contratoarquivo getContratoarquivo() {
		return this.contratoarquivo;
	}

	public void setContratoarquivo(Contratoarquivo contratoarquivo) {
		this.contratoarquivo = contratoarquivo;
	}

	public List<Banco> getListaBancoInicio() {
		return this.listaBancoInicio;
	}

	public void setListaBancoInicio(List<Banco> listaBancoInicio) {
		this.listaBancoInicio = listaBancoInicio;
	}

	public List<Usuario> getListaUsuario() {
		return this.listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isHabilitarUsuario() {
		return this.habilitarUsuario;
	}

	public void setHabilitarUsuario(boolean habilitarUsuario) {
		this.habilitarUsuario = habilitarUsuario;
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBancoOperacao = bancoFacade.lista("Select b From Banco b WHERE b.visualizar=true ORDER BY b.codigo");
		if (this.listaBancoOperacao == null)
			this.listaBancoOperacao = new ArrayList<>();
		this.listaBanco = this.listaBancoOperacao;
		this.listaBancoInicio = this.listaBancoOperacao;
	}

	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		if (this.banco != null && this.banco.getIdbanco() != null)
			this.listaOrgaoBanco = orgaoBancoFacade
					.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" + this.banco.getIdbanco());
		if (this.listaOrgaoBanco == null)
			this.listaOrgaoBanco = new ArrayList<>();
	}

	public void gerarListaOrgaoEdicao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		this.listaOrgaoBanco = orgaoBancoFacade.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco="
				+ this.contrato.getOrgaoBanco().getBanco().getIdbanco());
		if (this.listaOrgaoBanco == null)
			this.listaOrgaoBanco = new ArrayList<>();
	}

	public void buscarCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		this.cliente = clienteFacade.consultarCpf(this.cpf);
		this.contrato.setCliente(this.cliente);
		if (this.cliente == null) {
			this.cliente = new Cliente();
			this.cliente.setUsuario(this.usuario);
		} else if (this.cliente != null && this.cliente.getUsuario().getIdusuario() != usuario.getIdusuario()
				&& this.cliente.getUsuario().getIdusuario() != 13) {
			Mensagem.lancarMensagemWarn("Este cliente pertence ao corretor(a): ", this.cliente.getUsuario().getNome());
			this.cliente = new Cliente();
			this.cliente.setUsuario(this.usuario);
		}
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Where t.ativo=true");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}

	public String salvar() {
		this.contrato.setCliente(salvarCliente());
		this.contrato.setUsuario(this.usuario);
		if (banco != null) {
			this.contrato.setNomeBanco(banco.getNome());
			this.contrato.setCodigobanco(banco.getIdbanco());
		}
		ContratoFacade contratoFacade = new ContratoFacade();
		if (this.contrato == null || this.contrato.getIdcontrato() == null) {
			this.contrato.setIdregracoeficiente(0);
			if (this.contrato.getOrgaoBanco() == null || this.contrato.getOrgaoBanco().getIdorgaobanco() == null) {
				this.contrato.setOrgaoBanco(this.orgaoBanco);
			}
		}
		this.contrato.setOrgaoBanco(this.orgaoBanco);
		if (this.contrato == null || this.contrato.getIdcontrato() == null)
			this.contrato.setCodigocontrato(gerarCodigo(0));
		this.contrato = contratoFacade.salvar(this.contrato);
		if (this.novo) {
			gerarComissao(this.contrato);
			if (this.contrato.getTipooperacao().getIdtipooperacao() == 1 || this.contrato.getTipooperacao().getIdtipooperacao() == 4
					|| this.contrato.getTipooperacao().getIdtipooperacao() == 5) {
				gerarRankingPortabilidade();
			}else {
				gerarRankingDemaisOperacoes();
			}
			gerarNotificacao(this.contrato);
		}
		verificarUpload(this.contrato);
		if (this.voltarTela != null && this.voltarTela.length() > 0)
			return this.voltarTela;
		return "consContrato";
	}

	public void gerarComissao(Contrato contrato) {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		Historicocomissao historicocomissao = new Historicocomissao();
		historicocomissao.setDatalancamento(new Date());
		historicocomissao.setContrato(contrato);
		historicocomissao.setUsuario(contrato.getUsuario());
		historicocomissao.setTipo("PENDENTE");
		int mes = Formatacao.getMesData(new Date()) + 1;
		int ano = Formatacao.getAnoData(new Date());
		historicocomissao.setAno(ano);
		historicocomissao.setMes(mes);
		if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
			historicocomissao.setCmdbruta(0.0F);
			historicocomissao.setCmsliq(0.0F);
			historicocomissao.setProdliq(contrato.getValorquitar());
			historicocomissao.setComissaototal(0.0F);
		} else if (contrato.getTipooperacao().getIdtipooperacao().intValue() != 1) {
			historicocomissao.setCmdbruta(0.0F);
			historicocomissao.setCmsliq(0.0F);
			historicocomissao.setProdliq(contrato.getValorliberado());
			historicocomissao.setComissaototal(0.0F);
		} else {
			historicocomissao.setCmdbruta(0.0F);
			historicocomissao.setCmsliq(0.0F);
			historicocomissao.setProdliq(0.0F);
			historicocomissao.setComissaototal(0.0F);
		}
		historicoComissaoFacade.salvar(historicocomissao);
	}

	public void gerarNotificacao(Contrato contrato) {
		UsuarioDao usuarioDao = new UsuarioDao();
		NotificacaoFacade notificacaoDao = new NotificacaoFacade();
		try {
			List<Usuario> listaUsuario = usuarioDao
					.listar("Select u From Usuario u WHERE  u.acessogeral=true");
			for (int i = 0; i < listaUsuario.size(); i++) {
				Notificacao notificacao = new Notificacao();
				notificacao.setDatalancamento(new Date());
				notificacao.setVisto(false);
				notificacao.setUsuario(listaUsuario.get(i));
				notificacao.setIdcontrato(contrato.getIdcontrato().intValue());
				notificacao.setTipooperacao(contrato.getTipooperacao().getDescricao());
				notificacao.setTitulo("Novo Contrato: " + contrato.getCodigocontrato());
				notificacao.setDescricao(String.valueOf(contrato.getTipooperacao().getDescricao())
						+ " emitido pelo corretor(a) " + this.usuario.getNome());
				notificacaoDao.salvar(notificacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void verificarUpload(Contrato contrato) {
		if (this.listaContratoArquivo == null)
			this.listaContratoArquivo = new ArrayList<>();
		List<Contratoarquivo> listaArquivos = this.listaContratoArquivo;
		for (int i = 0; i < listaArquivos.size(); i++) {
			ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
			if (listaArquivos.get(i).isNovoupload()) {
				((Contratoarquivo) listaArquivos.get(i)).setContrato(contrato);
				contratoArquivoFacade.salvar(listaArquivos.get(i));
			}
		}
	}

	public Cliente salvarCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		if (this.bancoDadosBancario == null || this.bancoDadosBancario.getIdbanco() == null) {
			BancoFacade bancoFacade = new BancoFacade();
			List<Banco> listaBanco = bancoFacade.lista("Select b From Banco b");
			if (listaBanco == null)
				listaBanco = new ArrayList<>();
			this.bancoDadosBancario = listaBanco.get(0);
		}
		if (this.cpf != null && this.cpf.length() > 0)
			this.cliente.setCpf(this.cpf);
		return clienteFacade.salvar(this.cliente);
	}

	public String cancelar() {
		if (this.voltarTela != null && this.voltarTela.length() > 0)
			return this.voltarTela;
		return "consContrato";
	}

	public String gerarCodigo(int nContratos) {
		String codigo = "DF " + Formatacao.ConvercaoDataPadrao(new Date()) + " - ";
		ContratoFacade contratoFacade = new ContratoFacade();
		List<Contrato> lisContratos = contratoFacade.lista(
				"Select c From Contrato c Where c.datacadastro='" + Formatacao.ConvercaoDataSql(new Date()) + "'");
		if (lisContratos == null)
			lisContratos = new ArrayList<>();
		codigo = String.valueOf(codigo) + (lisContratos.size() + 1 + nContratos);
		return codigo;
	}

	public void fileUploadListener(FileUploadEvent e) {
		this.file = e.getFile();
		salvarArquivoFTP();
		if (this.arquivoEnviado) {
			String nome = e.getFile().getFileName();
			try {
				nome = new String(nome.getBytes(Charset.defaultCharset()), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (this.listaNomeArquivo == null)
				this.listaNomeArquivo = new ArrayList<>();
			this.listaNomeArquivo.add(String.valueOf(this.cpf) + "_" + nome);
			salvarUpload();
		}
	}

	public boolean salvarArquivoFTP() {
		String msg = "";
		Ftp ftp = new Ftp("ag-br1-5.hospedagemelastica.com.br", "rnzzud_userftp", "asdDQ@#E!@");
		try {
			if (!ftp.conectar()) {
				Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
				return false;
			}
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
			Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
		}
		try {
			String nomeArquivoFTP = this.cpf;
			this.arquivoEnviado = ftp.enviarArquivoDOCS(this.file, nomeArquivoFTP, "");
			if (this.arquivoEnviado) {
				msg = "Arquivo: " + nomeArquivoFTP + " enviado com sucesso";
				salvarUpload();
			} else {
				msg = " Erro no nome do arquivo";
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(msg, ""));
			ftp.desconectar();
			return true;
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
			Mensagem.lancarMensagemInfo("Erro Salvar Arquivo", "" + ex);
			try {
				ftp.desconectar();
			} catch (IOException iOException) {
				Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, iOException);
				Mensagem.lancarMensagemInfo("Erro", "desconectar FTP");
			}
			return false;
		}
	}

	public void gerarListaTipoArquivo() {
		TipoArquivoDao tipoArquivoDao = new TipoArquivoDao();
		this.listaTipoArquivo = tipoArquivoDao.listar("Select t From Tipoarquivo t order by t.descricao");
		if (this.listaTipoArquivo == null)
			this.listaTipoArquivo = new ArrayList<>();
	}

	public void salvarUpload() {
		if (this.arquivoEnviado)
			try {
				if (this.tipoarquivo != null && this.tipoarquivo.getIdtipoarquivo() != null) {
					this.contratoarquivo = new Contratoarquivo();
					this.contratoarquivo.setDataupload(new Date());
					this.contratoarquivo.setNovoupload(true);
					this.contratoarquivo.setNomearquivo(String.valueOf(this.cpf) + "_"
							+ new String(this.file.getFileName().trim().getBytes("ISO-8859-1"), "UTF-8"));
					this.contratoarquivo.setTipoarquivo(this.tipoarquivo);
					if (this.listaContratoArquivo == null)
						this.listaContratoArquivo = new ArrayList<>();
					this.listaContratoArquivo.add(this.contratoarquivo);
					this.contratoarquivo = new Contratoarquivo();
					this.listaNomeArquivo = new ArrayList<>();
					this.file = null;
					this.arquivoEnviado = false;
					Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
				} else {
					Mensagem.lancarMensagemFatal("Erro", "Favor escolher o tipo de arquivo");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	}

	public void gerarListaContratoAquivo() {
		ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
		if (this.contrato != null)
			this.listaContratoArquivo = contratoArquivoFacade.lista(
					"Select c From Contratoarquivo c WHERE c.contrato.idcontrato=" + this.contrato.getIdcontrato());
		if (this.listaContratoArquivo == null)
			this.listaContratoArquivo = new ArrayList<>();
	}

	public void excluirArquivo(String ilinha) {
		int linha = Integer.parseInt(ilinha);
		if (listaContratoArquivo.get(linha).getIdcontratoarquivo() != null) {
			ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
			contratoArquivoFacade.excluir(
					((Contratoarquivo) this.listaContratoArquivo.get(linha)).getIdcontratoarquivo().intValue());
		}
		if (linha >= 0)
			this.listaContratoArquivo.remove(linha);
	}

	public void baixarArquivoFTP(Contratoarquivo contratoarquivo) {
		Ftp ftp = new Ftp("ag-br1-5.hospedagemelastica.com.br", "rnzzud_userftp", "asdDQ@#E!@");
		try {
			if (!ftp.conectar())
				Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
			Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
		}
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			InputStream is = ftp.receberArquivo(contratoarquivo.getNomearquivo(), contratoarquivo.getNomearquivo(), "");
			ExternalContext externalContext = context.getExternalContext();
			externalContext.responseReset();
			externalContext.setResponseHeader("Content-Disposition",
					"attachment;filename=" + contratoarquivo.getNomearquivo());
			OutputStream outputStream = externalContext.getResponseOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0)
				outputStream.write(buffer, 0, length);
			is.close();
			context.responseComplete();
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
			Mensagem.lancarMensagemInfo("Erro Salvar Arquivo", "" + ex);
		}
		try {
			ftp.desconectar();
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
			Mensagem.lancarMensagemInfo("Erro", "desconectar FTP");
		}
	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		this.listaUsuario = usuarioFacade.listar("Select u From Usuario u Where u.ativo=true order by u.nome");
		if (this.listaUsuario == null)
			this.listaUsuario = new ArrayList<>();
	}

	public boolean gerarListaValores() {
		CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
		List<Coeficiente> listaRegrasValores = coeficienteFacade.lista(
				"Select v From Coeficiente v WHERE v.orgaoBanco.idorgaobanco=" + this.orgaoBanco.getIdorgaobanco()
						+ " AND v.tipooperacao.idtipooperacao=" + this.contrato.getTipooperacao().getIdtipooperacao());
		if (listaRegrasValores == null || listaRegrasValores.isEmpty())
			return false;
		return true;
	}

	public void buscarBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		banco = bancoFacade.consultar(contrato.getCodigobanco());
	}
	
	
	public void gerarRankingPortabilidade() {
		Rankingvendas rankingvendas;
		RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
		int mes = Formatacao.getMesData(new Date()) + 1;
		int ano = Formatacao.getAnoData(new Date());
		List<Rankingvendas> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendas r WHERE r.mes=" + mes
				+ " AND r.ano=" + ano + " AND r.usuario.idusuario=" + this.contrato.getUsuario().getIdusuario()
				 + " AND r.portabilidade=true");
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
		} else {
			rankingvendas = new Rankingvendas();
			rankingvendas.setPortabilidade(true);
			rankingvendas.setAno(ano);
			rankingvendas.setMes(mes);
			rankingvendas.setDescricaoportabilidade("PORTABILIDADE");
			rankingvendas.setUsuario(contrato.getUsuario());
		}

		rankingvendas.setComissaovenda(0.0f);
		rankingvendas.setTipooperacao(this.contrato.getTipooperacao());
		if (this.contrato.getTipooperacao().getIdtipooperacao() == 4) {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda() + this.contrato.getValorliberado());
		}else {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda() + this.contrato.getValorquitar());
		}
		rankingVendasFacade.salvar(rankingvendas);
	}
	
	
	public void gerarRankingDemaisOperacoes() {
		Rankingvendas rankingvendas;
		RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
		int mes = Formatacao.getMesData(new Date()) + 1;
		int ano = Formatacao.getAnoData(new Date());
		List<Rankingvendas> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendas r WHERE r.mes=" + mes
				+ " AND r.ano=" + ano + " AND r.usuario.idusuario=" + this.contrato.getUsuario().getIdusuario()
				 + " AND r.portabilidade=false");
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
		} else {
			rankingvendas = new Rankingvendas();
			rankingvendas.setPortabilidade(false);
			rankingvendas.setAno(ano);
			rankingvendas.setMes(mes);
			rankingvendas.setDescricaoportabilidade("DEMAIS OPERAÇÕES");
			rankingvendas.setUsuario(contrato.getUsuario());
		}

		rankingvendas.setComissaovenda(0.0f);
		rankingvendas.setTipooperacao(this.contrato.getTipooperacao());
		if (this.contrato.getTipooperacao().getIdtipooperacao() == 4) {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda() + this.contrato.getValorliberado());
		}else {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda() + this.contrato.getValorquitar());
		}
		rankingVendasFacade.salvar(rankingvendas);
	}

}
