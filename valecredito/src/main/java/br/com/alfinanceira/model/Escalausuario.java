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
@Table(name = "escalausuario")
public class Escalausuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idescalausuario")
	private Integer idEscalaUsuario;
	
	
	@Column(name = "dataescala")
	private String dataEscala;
	
	@Column(name = "cumpriu")
	private boolean cumpriu;
	
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	
	@JoinColumn(name = "escala_idescala", referencedColumnName = "idescala")
	@ManyToOne(optional = false)
	private Escala escala;


	/**
	 * @return the idEscalaUsuario
	 */
	public Integer getIdEscalaUsuario() {
		return idEscalaUsuario;
	}


	/**
	 * @param idEscalaUsuario the idEscalaUsuario to set
	 */
	public void setIdEscalaUsuario(Integer idEscalaUsuario) {
		this.idEscalaUsuario = idEscalaUsuario;
	}


	/**
	 * @return the dataEscala
	 */
	public String getDataEscala() {
		return dataEscala;
	}


	/**
	 * @param dataEscala the dataEscala to set
	 */
	public void setDataEscala(String dataEscala) {
		this.dataEscala = dataEscala;
	}


	/**
	 * @return the cumpriu
	 */
	public boolean isCumpriu() {
		return cumpriu;
	}


	/**
	 * @param cumpriu the cumpriu to set
	 */
	public void setCumpriu(boolean cumpriu) {
		this.cumpriu = cumpriu;
	}


	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}


	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	/**
	 * @return the escala
	 */
	public Escala getEscala() {
		return escala;
	}


	/**
	 * @param escala the escala to set
	 */
	public void setEscala(Escala escala) {
		this.escala = escala;
	}
	
	
	

}
