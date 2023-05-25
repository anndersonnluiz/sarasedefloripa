package br.com.alfinanceira.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-07-07T13:12:38.446-0300")
@StaticMetamodel(OrgaoBanco.class)
public class OrgaoBanco_ {
	public static volatile SingularAttribute<OrgaoBanco, Integer> idorgaobanco;
	public static volatile SingularAttribute<OrgaoBanco, String> nome;
	public static volatile SingularAttribute<OrgaoBanco, Banco> banco;
	public static volatile SingularAttribute<OrgaoBanco, Boolean> demaisopeinss;
}
