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
import javax.persistence.Transient;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idcliente")
	private Integer idcliente;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "matriculabeneficio")
	private String matriculabeneficio;
	  
	  @Column(name = "telefone")
	  private String telefone;
	  
	  @Column(name = "email")
	  private String email;

	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;

	@Transient
	private boolean selecionado;

	public Integer getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMatriculabeneficio() {
		return matriculabeneficio;
	}

	public void setMatriculabeneficio(String matriculabeneficio) {
		this.matriculabeneficio = matriculabeneficio;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the selecionado
	 */
	public boolean isSelecionado() {
		return selecionado;
	}

	/**
	 * @param selecionado the selecionado to set
	 */
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public int hashCode() {
		int hash = 0;
		hash += (this.idcliente != null) ? this.idcliente.hashCode() : 0;
		return hash;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Cliente))
			return false;
		Cliente other = (Cliente) object;
		if ((this.idcliente == null && other.idcliente != null)
				|| (this.idcliente != null && !this.idcliente.equals(other.idcliente)))
			return false;
		return true;
	}

	public String toString() {
		return "br.com.alfinanceira.model.Cliente[ idcliente=" + this.idcliente + " ]";
	}
}
