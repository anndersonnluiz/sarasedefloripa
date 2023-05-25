package br.com.alfinanceira.managebean.contrato;

import br.com.alfinanceira.model.Contrato;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class FichaContratoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Contrato contrato;
  
  private String voltar;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato = (Contrato)session.getAttribute("contrato");
    this.voltar = (String)session.getAttribute("voltar");
    session.removeAttribute("voltar");
    session.removeAttribute("contrato");
    if (this.contrato == null)
      this.contrato = new Contrato(); 
  }
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  public String getVoltar() {
    return this.voltar;
  }
  
  public void setVoltar(String voltar) {
    this.voltar = voltar;
  }
  
  public String voltar() {
    return this.voltar;
  }
}
