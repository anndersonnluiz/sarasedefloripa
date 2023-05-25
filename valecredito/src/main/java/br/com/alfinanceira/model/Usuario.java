package br.com.alfinanceira.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idusuario")
	private Integer idusuario;

	@Column(name = "cdinterno")
	private String cdinterno;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;

	@Column(name = "ativo")
	private boolean ativo = true;

	@Column(name = "descricaoativo")
	private String descricaoativo;

	@Column(name = "acessogeral")
	private boolean acessogeral;
	
	@Column(name = "chavepix")
	private String chavepix;

	@Column(name = "trabalhando")
	private boolean trabalhando;
	
	@JoinColumn(name = "tipocolaborador_idtipocolaborador", referencedColumnName = "idtipocolaborador")
	@ManyToOne(optional = false)
	private Tipocolaborador tipocolaborador;

	@JoinColumn(name = "departamento_iddepartamento", referencedColumnName = "iddepartamento")
	@ManyToOne(optional = false)
	private Departamento departamento;

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getCdinterno() {
		return this.cdinterno;
	}

	public void setCdinterno(String cdinterno) {
		this.cdinterno = cdinterno;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Tipocolaborador getTipocolaborador() {
		return this.tipocolaborador;
	}

	public void setTipocolaborador(Tipocolaborador tipocolaborador) {
		this.tipocolaborador = tipocolaborador;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricaoativo() {
		return this.descricaoativo;
	}

	public void setDescricaoativo(String descricaoativo) {
		this.descricaoativo = descricaoativo;
	}

	public boolean isAcessogeral() {
		return this.acessogeral;
	}

	public void setAcessogeral(boolean acessogeral) {
		this.acessogeral = acessogeral;
	}

	public synchronized Departamento getDepartamento() {
		return this.departamento;
	}

	public synchronized void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the chavepix
	 */
	public String getChavepix() {
		return chavepix;
	}

	/**
	 * @param chavepix the chavepix to set
	 */
	public void setChavepix(String chavepix) {
		this.chavepix = chavepix;
	}

	/**
	 * @return the trabalhando
	 */
	public boolean isTrabalhando() {
		return trabalhando;
	}

	/**
	 * @param trabalhando the trabalhando to set
	 */
	public void setTrabalhando(boolean trabalhando) {
		this.trabalhando = trabalhando;
	}

	public int hashCode() {
		int hash = 0;
		hash += (this.idusuario != null) ? this.idusuario.hashCode() : 0;
		return hash;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Usuario))
			return false;
		Usuario other = (Usuario) object;
		if ((this.idusuario == null && other.idusuario != null)
				|| (this.idusuario != null && !this.idusuario.equals(other.idusuario)))
			return false;
		return true;
	}

	public String toString() {
		return "br.com.alfinanceira.model.Usuario[ idusuario=" + this.idusuario + " ]";
	}
}
