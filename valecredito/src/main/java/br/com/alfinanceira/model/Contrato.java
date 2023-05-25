package br.com.alfinanceira.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "contrato")
public class Contrato implements Serializable {
  
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idcontrato")
  private Integer idcontrato;
  
  @Column(name = "totalparcelas")
  private int totalparcelas;
  
  @Column(name = "valorparcela")
  private float valorparcela;
  
  @Column(name = "observacao")
  private String observacao;
  
  @Column(name = "valorliberado")
  private float valorliberado;
  
  @JoinColumn(name = "situacao_idsituacao", referencedColumnName = "idsituacao")
  @ManyToOne(optional = false)
  private Situacao situacao;
 
  
  @Column(name = "datacadastro")
  @Temporal(TemporalType.DATE)
  private Date datacadastro;
  
  @Column(name = "datapagamento")
  @Temporal(TemporalType.DATE)
  private Date datapagamento;
  
  @Column(name = "codigocontrato")
  private String codigocontrato;
  
  @Column(name = "detalhesituacao")
  private String detalhesituacao;
  
  @JoinColumn(name = "orgaobanco_idorgaobanco", referencedColumnName = "idorgaobanco")
  @ManyToOne(optional = false)
  private OrgaoBanco orgaoBanco;
  
  @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
  @ManyToOne(optional = false)
  private Cliente cliente;
  
  @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
  @ManyToOne(optional = false)
  private Usuario usuario;
  
  @JoinColumn(name = "tipooperacao_idtipooperacao", referencedColumnName = "idtipooperacao")
  @ManyToOne(optional = false)
  private Tipooperacao tipooperacao;
  
  @Column(name = "idregrascoeficiente")
  private int idregracoeficiente;
  
  @Column(name = "operacaoinss")
  private boolean operacaoinss;
  
  @Column(name = "prazo")
  private int prazo;
  
  @Column(name = "nparcela")
  private int nparcela;
  
  @Column(name = "nomeBanco")
  private String nomeBanco;
  
  @Column(name = "parcelaspagas")
  private int parcelaspagas;
  
  @Column(name = "valorquitar")
  private float valorquitar;
  
  @Column(name = "margemultilizado")
  private float margemultilizado;
  
  @Column(name = "codigobanco")
  private int codigobanco;
  
  @Column(name = "numerocontrato")
  private String numerocontrato;
  
  @Transient
  private String voltarTela;
  
  
  public Contrato() {
	
}
  
  
  public Integer getIdcontrato() {
    return this.idcontrato;
  }
  
  public void setIdcontrato(Integer idcontrato) {
    this.idcontrato = idcontrato;
  }
  
  public int getTotalparcelas() {
    return this.totalparcelas;
  }
  
  public void setTotalparcelas(int totalparcelas) {
    this.totalparcelas = totalparcelas;
  }
  
  public float getValorparcela() {
    return this.valorparcela;
  }
  
  public void setValorparcela(float valorparcela) {
    this.valorparcela = valorparcela;
  }
  
  public String getObservacao() {
    return this.observacao;
  }
  
  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }
  
  public Cliente getCliente() {
    return this.cliente;
  }
  
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  
  public Usuario getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public Tipooperacao getTipooperacao() {
    return this.tipooperacao;
  }
  
  public void setTipooperacao(Tipooperacao tipooperacao) {
    this.tipooperacao = tipooperacao;
  }
  
  public Situacao getSituacao() {
    return this.situacao;
  }
  
  public void setSituacao(Situacao situacao) {
    this.situacao = situacao;
  }
  
  public Date getDatacadastro() {
    return this.datacadastro;
  }
  
  public void setDatacadastro(Date datacadastro) {
    this.datacadastro = datacadastro;
  }
  
  public Date getDatapagamento() {
    return this.datapagamento;
  }
  
  public void setDatapagamento(Date datapagamento) {
    this.datapagamento = datapagamento;
  }
  
  public String getCodigocontrato() {
    return this.codigocontrato;
  }
  
  public void setCodigocontrato(String codigocontrato) {
    this.codigocontrato = codigocontrato;
  }
  
  public String getDetalhesituacao() {
    return this.detalhesituacao;
  }
  
  public void setDetalhesituacao(String detalhesituacao) {
    this.detalhesituacao = detalhesituacao;
  }
  
  public int getIdregracoeficiente() {
    return this.idregracoeficiente;
  }
  
  public void setIdregracoeficiente(int idregracoeficiente) {
    this.idregracoeficiente = idregracoeficiente;
  }
  
  public boolean isOperacaoinss() {
    return this.operacaoinss;
  }
  
  public void setOperacaoinss(boolean operacaoinss) {
    this.operacaoinss = operacaoinss;
  }
  
  public String getVoltarTela() {
    return this.voltarTela;
  }
  
  public void setVoltarTela(String voltarTela) {
    this.voltarTela = voltarTela;
  }
  
  public OrgaoBanco getOrgaoBanco() {
    return this.orgaoBanco;
  }
  
  public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
    this.orgaoBanco = orgaoBanco;
  }
  
  public int getPrazo() {
    return this.prazo;
  }
  
  public void setPrazo(int prazo) {
    this.prazo = prazo;
  }


public float getValorliberado() {
	return valorliberado;
}


public void setValorliberado(float valorliberado) {
	this.valorliberado = valorliberado;
}


/**
 * @return the nparcela
 */
public int getNparcela() {
	return nparcela;
}


/**
 * @param nparcela the nparcela to set
 */
public void setNparcela(int nparcela) {
	this.nparcela = nparcela;
}


/**
 * @return the nomeBanco
 */
public String getNomeBanco() {
	return nomeBanco;
}


/**
 * @param nomeBanco the nomeBanco to set
 */
public void setNomeBanco(String nomeBanco) {
	this.nomeBanco = nomeBanco;
}


/**
 * @return the parcelaspagas
 */
public int getParcelaspagas() {
	return parcelaspagas;
}


/**
 * @param parcelaspagas the parcelaspagas to set
 */
public void setParcelaspagas(int parcelaspagas) {
	this.parcelaspagas = parcelaspagas;
}


/**
 * @return the valorquitar
 */
public float getValorquitar() {
	return valorquitar;
}


/**
 * @param valorquitar the valorquitar to set
 */
public void setValorquitar(float valorquitar) {
	this.valorquitar = valorquitar;
}


/**
 * @return the margemultilizado
 */
public float getMargemultilizado() {
	return margemultilizado;
}


/**
 * @param margemultilizado the margemultilizado to set
 */
public void setMargemultilizado(float margemultilizado) {
	this.margemultilizado = margemultilizado;
}


/**
 * @return the codigobanco
 */
public int getCodigobanco() {
	return codigobanco;
}


/**
 * @param codigobanco the codigobanco to set
 */
public void setCodigobanco(int codigobanco) {
	this.codigobanco = codigobanco;
}


/**
 * @return the numerocontrato
 */
public String getNumerocontrato() {
	return numerocontrato;
}


/**
 * @param numerocontrato the numerocontrato to set
 */
public void setNumerocontrato(String numerocontrato) {
	this.numerocontrato = numerocontrato;
}


public int hashCode() {
    int hash = 0;
    hash += (this.idcontrato != null) ? this.idcontrato.hashCode() : 0;
    return hash;
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof Contrato))
      return false; 
    Contrato other = (Contrato)object;
    if ((this.idcontrato == null && other.idcontrato != null) || (
      this.idcontrato != null && !this.idcontrato.equals(other.idcontrato)))
      return false; 
    return true;
  }
  
  public String toString() {
    return "br.com.alfinanceira.model.Contrato[ idcontrato=" + this.idcontrato + " ]";
  }
}
