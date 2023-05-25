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
@Table(name="escala")
public class Escala implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idescala")
	private Integer idEscala;
	
	
	@Column(name = "mes")
	private String mes;
	
	@Column(name = "ano")
	private String ano;
	
	@Column(name = "finalizou")
	private boolean finalizou;
	
	@JoinColumn(name = "departamento_iddepartamento", referencedColumnName = "iddepartamento")
	@ManyToOne(optional = false)
	private Departamento departamento;

	/**
	 * @return the idEscala
	 */
	public Integer getIdEscala() {
		return idEscala;
	}

	/**
	 * @param idEscala the idEscala to set
	 */
	public void setIdEscala(Integer idEscala) {
		this.idEscala = idEscala;
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return the ano
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * @param ano the ano to set
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/**
	 * @return the departamento
	 */
	public Departamento getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the finalizou
	 */
	public boolean isFinalizou() {
		return finalizou;
	}

	/**
	 * @param finalizou the finalizou to set
	 */
	public void setFinalizou(boolean finalizou) {
		this.finalizou = finalizou;
	}
	
	
	
	
	

}
