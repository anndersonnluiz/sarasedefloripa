package br.com.alfinanceira.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-07-07T13:12:38.435-0300")
@StaticMetamodel(Contaspagar.class)
public class Contaspagar_ {
	public static volatile SingularAttribute<Contaspagar, Integer> idcontaspagar;
	public static volatile SingularAttribute<Contaspagar, String> descricao;
	public static volatile SingularAttribute<Contaspagar, Date> datavencimento;
	public static volatile SingularAttribute<Contaspagar, Date> datapagamento;
	public static volatile SingularAttribute<Contaspagar, Tipodespesa> tipodespesa;
	public static volatile SingularAttribute<Contaspagar, Float> valor;
	public static volatile SingularAttribute<Contaspagar, Integer> totalparcela;
	public static volatile SingularAttribute<Contaspagar, Integer> nparcela;
	public static volatile SingularAttribute<Contaspagar, Integer> mesreferente;
}
