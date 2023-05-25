package br.com.alfinanceira.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-07-07T13:12:38.437-0300")
@StaticMetamodel(Contasreceber.class)
public class Contasreceber_ {
	public static volatile SingularAttribute<Contasreceber, Integer> idcontasreceber;
	public static volatile SingularAttribute<Contasreceber, String> descricao;
	public static volatile SingularAttribute<Contasreceber, Date> datavencimento;
	public static volatile SingularAttribute<Contasreceber, Date> datapagamento;
	public static volatile SingularAttribute<Contasreceber, Tipodespesa> tipodespesa;
	public static volatile SingularAttribute<Contasreceber, Float> valor;
}
