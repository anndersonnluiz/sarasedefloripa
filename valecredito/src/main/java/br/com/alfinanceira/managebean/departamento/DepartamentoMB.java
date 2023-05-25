package br.com.alfinanceira.managebean.departamento;

import br.com.alfinanceira.facade.DepartamentoFacade;
import br.com.alfinanceira.facade.UsuarioFacade;
import br.com.alfinanceira.model.Departamento;
import br.com.alfinanceira.model.Usuario;
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
public class DepartamentoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Departamento> listaDepartamento;

	@PostConstruct
	public void init() {
		gerarListaDepartamento();
	}

	public synchronized List<Departamento> getListaDepartamento() {
		return this.listaDepartamento;
	}

	public synchronized void setListaDepartamento(List<Departamento> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}

	public void gerarListaDepartamento() {
		DepartamentoFacade departamentoFacade = new DepartamentoFacade();
		this.listaDepartamento = departamentoFacade.lista("Select d From Departamento d");
		if (this.listaDepartamento == null)
			this.listaDepartamento = new ArrayList<>();
	}

	public String novoDepartamento() {
		return "cadDepartamento";
	}

	public String editar(Departamento departamento) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("departamento", departamento);
		return "cadDepartamento";
	}

	public String acessarUsuarios(Departamento departamento) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("departamento", departamento);
		return "consUsuario";
	}

	public int gerarNumeroUsuarios(Departamento departamento) {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		List<Usuario> listaUsuario = usuarioFacade.listar(
				"Select u From Usuario u Where u.departamento.iddepartamento=" + departamento.getIddepartamento());
		if (listaUsuario == null)
			listaUsuario = new ArrayList<>();
		return listaUsuario.size();
	}
}
