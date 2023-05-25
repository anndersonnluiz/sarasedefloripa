package br.com.alfinanceira.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-07-13T11:08:00.473-0300")
@StaticMetamodel(Contrato.class)
public class Contrato_ {
	public static volatile SingularAttribute<Contrato, Integer> idcontrato;
	public static volatile SingularAttribute<Contrato, Integer> totalparcelas;
	public static volatile SingularAttribute<Contrato, Float> valorparcela;
	public static volatile SingularAttribute<Contrato, String> observacao;
	public static volatile SingularAttribute<Contrato, Float> valorliberado;
	public static volatile SingularAttribute<Contrato, Situacao> situacao;
	public static volatile SingularAttribute<Contrato, Date> datacadastro;
	public static volatile SingularAttribute<Contrato, Date> datapagamento;
	public static volatile SingularAttribute<Contrato, String> codigocontrato;
	public static volatile SingularAttribute<Contrato, String> detalhesituacao;
	public static volatile SingularAttribute<Contrato, OrgaoBanco> orgaoBanco;
	public static volatile SingularAttribute<Contrato, Cliente> cliente;
	public static volatile SingularAttribute<Contrato, Usuario> usuario;
	public static volatile SingularAttribute<Contrato, Tipooperacao> tipooperacao;
	public static volatile SingularAttribute<Contrato, Integer> idregracoeficiente;
	public static volatile SingularAttribute<Contrato, Boolean> operacaoinss;
	public static volatile SingularAttribute<Contrato, Integer> prazo;
	public static volatile SingularAttribute<Contrato, Integer> nparcela;
	public static volatile SingularAttribute<Contrato, String> nomeBanco;
	public static volatile SingularAttribute<Contrato, Integer> parcelaspagas;
	public static volatile SingularAttribute<Contrato, Float> valorquitar;
	public static volatile SingularAttribute<Contrato, Float> margemultilizado;
	public static volatile SingularAttribute<Contrato, Integer> codigobanco;
	public static volatile SingularAttribute<Contrato, String> numerocontrato;
}
