package br.com.alfinanceira.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-07-16T16:06:35.845-0300")
@StaticMetamodel(Rankingvendas.class)
public class Rankingvendas_ {
	public static volatile SingularAttribute<Rankingvendas, Integer> idrankingvendas;
	public static volatile SingularAttribute<Rankingvendas, Integer> mes;
	public static volatile SingularAttribute<Rankingvendas, Integer> ano;
	public static volatile SingularAttribute<Rankingvendas, Float> valorvenda;
	public static volatile SingularAttribute<Rankingvendas, Float> comissaovenda;
	public static volatile SingularAttribute<Rankingvendas, Usuario> usuario;
	public static volatile SingularAttribute<Rankingvendas, Tipooperacao> tipooperacao;
	public static volatile SingularAttribute<Rankingvendas, Boolean> portabilidade;
	public static volatile SingularAttribute<Rankingvendas, String> descricaoportabilidade;
}
