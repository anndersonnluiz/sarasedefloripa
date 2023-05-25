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
@Table(name = "coeficiente")
public class Coeficiente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idcoeficiente")
	private Integer idcoeficiente;

	@Column(name = "nometabela")
	private String nometabela;

	@JoinColumn(name = "orgaobanco_idorgaobanco", referencedColumnName = "idorgaobanco")
	@ManyToOne(optional = false)
	private OrgaoBanco orgaoBanco;

	@Column(name = "prazo")
	private int prazo;

	@Column(name = "coeficientevalor")
	private float coeficientevalor;

	@Column(name = "comissaoloja")
	private float comissaoloja;

	@Column(name = "comissaocorretor")
	private float comissaocorretor;

	@Column(name = "comissaototal")
	private float comissaototal;

	@Column(name = "ativo") 
	private boolean ativo;

	@JoinColumn(name = "tipoioperacao_idtipooperacao", referencedColumnName = "idtipooperacao")
	@ManyToOne(optional = false)
	private Tipooperacao tipooperacao;

	public Integer getIdcoeficiente() {
		return this.idcoeficiente;
	}

	public void setIdcoeficiente(Integer idcoeficiente) {
		this.idcoeficiente = idcoeficiente;
	}

	public String getNometabela() {
		return this.nometabela;
	}

	public void setNometabela(String nometabela) {
		this.nometabela = nometabela;
	}

	

	public Tipooperacao getTipooperacao() {
		return this.tipooperacao;
	}

	public void setTipooperacao(Tipooperacao tipooperacao) {
		this.tipooperacao = tipooperacao;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public float getCoeficientevalor() {
		return coeficientevalor;
	}

	public void setCoeficientevalor(float coeficientevalor) {
		this.coeficientevalor = coeficientevalor;
	}

	public float getComissaoloja() {
		return comissaoloja;
	}

	public void setComissaoloja(float comissaoloja) {
		this.comissaoloja = comissaoloja;
	}

	public float getComissaocorretor() {
		return comissaocorretor;
	}

	public void setComissaocorretor(float comissaocorretor) {
		this.comissaocorretor = comissaocorretor;
	}

	public float getComissaototal() {
		return comissaototal;
	}

	public void setComissaototal(float comissaototal) {
		this.comissaototal = comissaototal;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public OrgaoBanco getOrgaoBanco() {
		return orgaoBanco;
	}

	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}

	public int hashCode() {
		int hash = 0;
		hash += (this.idcoeficiente != null) ? this.idcoeficiente.hashCode() : 0;
		return hash;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Coeficiente))
			return false;
		Coeficiente other = (Coeficiente) object;
		if ((this.idcoeficiente == null && other.idcoeficiente != null)
				|| (this.idcoeficiente != null && !this.idcoeficiente.equals(other.idcoeficiente)))
			return false;
		return true;
	}

	public String toString() {
		return "br.com.alfinanceira.model.Coeficiente[ idcoeficiente=" + this.idcoeficiente + " ]";
	}
}
