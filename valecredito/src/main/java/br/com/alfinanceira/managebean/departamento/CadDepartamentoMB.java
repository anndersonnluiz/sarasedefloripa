package br.com.alfinanceira.managebean.departamento;

import br.com.alfinanceira.facade.DepartamentoFacade;
import br.com.alfinanceira.model.Departamento;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadDepartamentoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Departamento departamento;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.departamento = (Departamento) session.getAttribute("departamento");
		session.removeAttribute("departamento");
		if (this.departamento == null) {
			this.departamento = new Departamento();
			this.departamento.setNusuario(0);
		}
	}

	public synchronized Departamento getDepartamento() {
		return this.departamento;
	}

	public synchronized void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String cancelar() {
		return "consDepartamento";
	}

	public String salvar() {
		DepartamentoFacade departamentoFacade = new DepartamentoFacade();
		departamentoFacade.salvar(this.departamento);
		return "consDepartamento";
	}
}
