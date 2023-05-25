package br.com.alfinanceira.managebean.contrato;

import br.com.alfinanceira.facade.CoeficienteFacade;
import br.com.alfinanceira.model.Coeficiente;
import br.com.alfinanceira.model.Contrato;
import br.com.alfinanceira.model.OrgaoBanco;
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
public class SelecioneCoeficienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Contrato contrato;
  
  private List<Coeficiente> listaRegrasValores;
  
  private OrgaoBanco orgaobanco;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato = (Contrato)session.getAttribute("contrato");
    this.orgaobanco = (OrgaoBanco)session.getAttribute("orgaobanco");
    session.removeAttribute("contrato");
    session.removeAttribute("orgaobanco");
    gerarListaValores();
  }
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  
  
  public List<Coeficiente> getListaRegrasValores() {
	return listaRegrasValores;
}

public void setListaRegrasValores(List<Coeficiente> listaRegrasValores) {
	this.listaRegrasValores = listaRegrasValores;
}

public OrgaoBanco getOrgaobanco() {
	return orgaobanco;
}

public void setOrgaobanco(OrgaoBanco orgaobanco) {
	this.orgaobanco = orgaobanco;
}

public void gerarListaValores() {
    CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
    this.listaRegrasValores = coeficienteFacade
      .lista("Select v From Coeficiente v WHERE v.orgaoBanco.idorgaobanco=" + 
        this.orgaobanco.getIdorgaobanco() + " AND v.tipooperacao.idtipooperacao=" + 
        this.contrato.getTipooperacao().getIdtipooperacao());
    if (this.listaRegrasValores == null)
      this.listaRegrasValores = new ArrayList<>(); 
  }
  
  public String selecionarCoeficiente(Coeficiente coeficiente) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", this.contrato);
    session.setAttribute("coeficiente", coeficiente);
    session.setAttribute("orgaobanco", this.orgaobanco);
    return "cadContrato";
  }
  
  public String voltar() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", this.contrato);
    session.setAttribute("orgaobanco", this.orgaobanco);
    return "cadContrato";
  }
}
