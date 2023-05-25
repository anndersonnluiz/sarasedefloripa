package br.com.alfinanceira.managebean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@SessionScoped
public class MenuMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @PostConstruct
  public void init() {}
  
  public String consUsuario() {
    return "consUsuario";
  }
  
  public String notificacoes() {
    return "consNotificacao";
  }
}
