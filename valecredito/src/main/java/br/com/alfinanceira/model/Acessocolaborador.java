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
@Table(name = "acessocolaborador")
public class Acessocolaborador implements Serializable {
	@Column(name = "cadastrar")
	private boolean cadastrar = true;

	@Column(name = "financeiro")
	private boolean financeiro = true;

	@Column(name = "cadastrarbanco")
	private boolean cadastrarbanco = true;

	@Column(name = "cadastrarcoeficiente")
	private boolean cadastrarcoeficiente = true;

	@Column(name = "cadastrarorgao")
	private boolean cadastrarorgao = true;

	@Column(name = "notificacaooperacional")
	private boolean notificacaooperacional = true;

	@Column(name = "metas")
	private boolean metas = true;

	@Column(name = "cadastrarmetas")
	private boolean cadastrarmetas = true;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idacessocolaborador")
	private Integer idacessocolaborador;
	
	@Column(name = "emissaocontrato")
	private boolean emissaocontrato;

	public Integer getIdacessocolaborador() {
		return this.idacessocolaborador;
	}

	public void setIdacessocolaborador(Integer idacessocolaborador) {
		this.idacessocolaborador = idacessocolaborador;
	}

	public boolean isCadastrarbanco() {
		return this.cadastrarbanco;
	}

	public void setCadastrarbanco(boolean cadastrarbanco) {
		this.cadastrarbanco = cadastrarbanco;
	}

	public boolean isCadastrarorgao() {
		return this.cadastrarorgao;
	}

	public void setCadastrarorgao(boolean cadastrarorgao) {
		this.cadastrarorgao = cadastrarorgao;
	}

	public boolean isCadastrarcoeficiente() {
		return this.cadastrarcoeficiente;
	}

	public void setCadastrarcoeficiente(boolean cadastrarcoeficiente) {
		this.cadastrarcoeficiente = cadastrarcoeficiente;
	}

	public boolean isFinanceiro() {
		return this.financeiro;
	}

	public void setFinanceiro(boolean financeiro) {
		this.financeiro = financeiro;
	}

	public boolean isNotificacaooperacional() {
		return this.notificacaooperacional;
	}

	public void setNotificacaooperacional(boolean notificacaooperacional) {
		this.notificacaooperacional = notificacaooperacional;
	}

	public boolean isCadastrar() {
		return this.cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public boolean isMetas() {
		return this.metas;
	}

	public void setMetas(boolean metas) {
		this.metas = metas;
	}

	public boolean isCadastrarmetas() {
		return this.cadastrarmetas;
	}

	public void setCadastrarmetas(boolean cadastrarmetas) {
		this.cadastrarmetas = cadastrarmetas;
	}

	public boolean isEmissaocontrato() {
		return emissaocontrato;
	}

	public void setEmissaocontrato(boolean emissaocontrato) {
		this.emissaocontrato = emissaocontrato;
	}

	public int hashCode() {
		int hash = 0;
		hash += (this.idacessocolaborador != null) ? this.idacessocolaborador.hashCode() : 0;
		return hash;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Acessocolaborador))
			return false;
		Acessocolaborador other = (Acessocolaborador) object;
		if ((this.idacessocolaborador == null && other.idacessocolaborador != null)
				|| (this.idacessocolaborador != null && !this.idacessocolaborador.equals(other.idacessocolaborador)))
			return false;
		return true;
	}

	public String toString() {
		return "br.com.alfinanceira.model.Acessocolaborador[ idacessocolaborador=" + this.idacessocolaborador + " ]";
	}
}
