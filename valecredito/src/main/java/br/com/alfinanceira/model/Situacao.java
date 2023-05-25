package br.com.alfinanceira.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "situacao")
public class Situacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idsituacao")
	private Integer idsituacao;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "identificador")
	private int identificador;
	@Column(name = "visualizar")
	private boolean visualizar;
	@Column(name = "portabilidade")
	private boolean portabilidade;
	
	
	public Situacao() {
	
	}


	public Integer getIdsituacao() {
		return idsituacao;
	}


	public void setIdsituacao(Integer idsituacao) {
		this.idsituacao = idsituacao;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getIdentificador() {
		return identificador;
	}


	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	
	
	
	public boolean isVisualizar() {
		return visualizar;
	}


	public void setVisualizar(boolean visualizar) {
		this.visualizar = visualizar;
	}


	public boolean isPortabilidade() {
		return portabilidade;
	}


	public void setPortabilidade(boolean portabilidade) {
		this.portabilidade = portabilidade;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idsituacao != null ? idsituacao.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Situacao)) {
			return false;
		}
		Situacao other = (Situacao) object;
		if ((this.idsituacao == null && other.idsituacao != null)
				|| (this.idsituacao != null && !this.idsituacao.equals(other.idsituacao))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.alfinanceira.model.Situacao[ idsituacao=" + idsituacao + " ]";
	}

}
