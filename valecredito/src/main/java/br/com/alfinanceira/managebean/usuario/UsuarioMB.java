package br.com.alfinanceira.managebean.usuario;

import br.com.alfinanceira.dao.UsuarioDao;
import br.com.alfinanceira.facade.UsuarioFacade;
import br.com.alfinanceira.model.Departamento;
import br.com.alfinanceira.model.Usuario;
import br.com.alfinanceira.util.Mensagem;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class UsuarioMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<Usuario> listaUsuario;
  
  private Usuario usuario;
  
  private Departamento departamento;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.departamento = (Departamento)session.getAttribute("departamento");
    session.removeAttribute("departamento");
    gerarListaUsuario();
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
  
  public Departamento getDepartamento() {
    return this.departamento;
  }
  
  public void setDepartamento(Departamento departamento) {
    this.departamento = departamento;
  }
  
  public void gerarListaUsuario() {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    this.listaUsuario = usuarioFacade.listar("Select u From Usuario u Where u.departamento.iddepartamento=" + 
        this.departamento.getIddepartamento() + " order by u.nome");
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public String novoUsuario() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("departamento", this.departamento);
    return "cadUsuario";
  }
  
  public String editar(Usuario usuario) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("usuario", usuario);
    session.setAttribute("departamento", this.departamento);
    return "cadUsuario";
  }
  
  public void resetarSenhaUsuario(Usuario usuario) {}
  
  public void ativarDesativarUsuario(Usuario usuario) {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    if (usuario.isAtivo()) {
      usuario.setAtivo(false);
      usuario.setDescricaoativo("x-circle");
    } else {
      usuario.setAtivo(true);
      usuario.setDescricaoativo("check");
    } 
    usuarioFacade.salvar(usuario);
  }
  
  public void resetSenha(Usuario usuario) {
    usuario.setSenha("t+lL5RPpboxFzSPRYideWhLr3pEApCXE683X+k3NiXw=");
    UsuarioDao usuarioDao = new UsuarioDao();
    try {
      usuarioDao.salvar(usuario);
      Mensagem.lancarMensagemInfo("Reset de Senha", "com sucesso!!");
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public void excluirUsuario(Usuario usuario) {
    if (usuario.getIdusuario().intValue() != 13) {
      UsuarioFacade usuarioFacade = new UsuarioFacade();
      usuarioFacade.excluir(usuario.getIdusuario().intValue());
      this.listaUsuario.remove(usuario);
      Mensagem.lancarMensagemInfo("Usuario excluido com sucesso", "");
    } else {
      Mensagem.lancarMensagemFatal("Atenção", "Este usunpode ser excluido");
    } 
  }
  
  public String voltar() {
    return "consDepartamento";
  }
}
