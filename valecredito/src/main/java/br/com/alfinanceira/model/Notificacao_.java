package br.com.alfinanceira.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-07-13T14:01:26.578-0300")
@StaticMetamodel(Notificacao.class)
public class Notificacao_ {
	public static volatile SingularAttribute<Notificacao, Integer> idnotificacao;
	public static volatile SingularAttribute<Notificacao, String> titulo;
	public static volatile SingularAttribute<Notificacao, String> descricao;
	public static volatile SingularAttribute<Notificacao, Boolean> visto;
	public static volatile SingularAttribute<Notificacao, Date> datalancamento;
	public static volatile SingularAttribute<Notificacao, String> tipooperacao;
	public static volatile SingularAttribute<Notificacao, Usuario> usuario;
	public static volatile SingularAttribute<Notificacao, Integer> idcontrato;
}
