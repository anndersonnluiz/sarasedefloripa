package br.com.alfinanceira.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-07-07T13:12:38.433-0300")
@StaticMetamodel(Coeficiente.class)
public class Coeficiente_ {
	public static volatile SingularAttribute<Coeficiente, Integer> idcoeficiente;
	public static volatile SingularAttribute<Coeficiente, String> nometabela;
	public static volatile SingularAttribute<Coeficiente, OrgaoBanco> orgaoBanco;
	public static volatile SingularAttribute<Coeficiente, Integer> prazo;
	public static volatile SingularAttribute<Coeficiente, Float> coeficientevalor;
	public static volatile SingularAttribute<Coeficiente, Float> comissaoloja;
	public static volatile SingularAttribute<Coeficiente, Float> comissaocorretor;
	public static volatile SingularAttribute<Coeficiente, Float> comissaototal;
	public static volatile SingularAttribute<Coeficiente, Boolean> ativo;
	public static volatile SingularAttribute<Coeficiente, Tipooperacao> tipooperacao;
}
