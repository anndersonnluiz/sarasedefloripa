package br.com.alfinanceira.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-07-12T16:07:13.805-0300")
@StaticMetamodel(Historicocomissao.class)
public class Historicocomissao_ {
	public static volatile SingularAttribute<Historicocomissao, Integer> idhistoricocomissao;
	public static volatile SingularAttribute<Historicocomissao, Float> prodliq;
	public static volatile SingularAttribute<Historicocomissao, Float> cmdbruta;
	public static volatile SingularAttribute<Historicocomissao, Float> proddesc;
	public static volatile SingularAttribute<Historicocomissao, Float> cmsliq;
	public static volatile SingularAttribute<Historicocomissao, String> tipo;
	public static volatile SingularAttribute<Historicocomissao, Date> datalancamento;
	public static volatile SingularAttribute<Historicocomissao, Usuario> usuario;
	public static volatile SingularAttribute<Historicocomissao, Contrato> contrato;
	public static volatile SingularAttribute<Historicocomissao, Integer> mes;
	public static volatile SingularAttribute<Historicocomissao, Integer> ano;
	public static volatile SingularAttribute<Historicocomissao, Boolean> baixa;
	public static volatile SingularAttribute<Historicocomissao, String> descricaobaixa;
	public static volatile SingularAttribute<Historicocomissao, String> corbaixa;
	public static volatile SingularAttribute<Historicocomissao, String> dataentrada;
	public static volatile SingularAttribute<Historicocomissao, Float> comissaototal;
	public static volatile SingularAttribute<Historicocomissao, Float> percentualpago;
}
