package br.com.alfinanceira.managebean.contrato;

import br.com.alfinanceira.facade.CoeficienteFacade;
import br.com.alfinanceira.facade.ContratoFacade;
import br.com.alfinanceira.facade.UsuarioFacade;
import br.com.alfinanceira.model.Coeficiente;
import br.com.alfinanceira.model.Contrato;
import br.com.alfinanceira.model.Usuario;
import br.com.alfinanceira.util.Formatacao;
import br.com.alfinanceira.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TrocarTitularMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<Usuario> listaUsuario;
  
  private Usuario usuario;
  
  private Contrato contrato;
  
  private String senha;
  
  private Coeficiente coeficiente;
  
  private Usuario usuarioAtual;
  
  private boolean selecionarTodos;
  
  @PostConstruct
  public void init() {
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
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  public String getSenha() {
    return this.senha;
  }
  
  public void setSenha(String senha) {
    this.senha = senha;
  }
  
  public Coeficiente getCoeficiente() {
	return coeficiente;
}

public void setCoeficiente(Coeficiente coeficiente) {
	this.coeficiente = coeficiente;
}

public Usuario getUsuarioAtual() {
    return this.usuarioAtual;
  }
  
  public void setUsuarioAtual(Usuario usuarioAtual) {
    this.usuarioAtual = usuarioAtual;
  }
  
  public boolean isSelecionarTodos() {
    return this.selecionarTodos;
  }
  
  public void setSelecionarTodos(boolean selecionarTodos) {
    this.selecionarTodos = selecionarTodos;
  }
  
  public void gerarListaUsuario() {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    this.listaUsuario = usuarioFacade.listar("Select u From Usuario u Where u.treinamento=false order by u.nome");
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public String salvar() {
    ContratoFacade contratoFacade = new ContratoFacade();
    
    return "dashboard";
  }
  
  public String cancelar() {
    return "dashboard";
  }
  
  
  
  public void buscarRegrasCoeficiente() {
    CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
    this.coeficiente = coeficienteFacade.consultar(this.contrato.getIdregracoeficiente());
  }
 
  
  
}
