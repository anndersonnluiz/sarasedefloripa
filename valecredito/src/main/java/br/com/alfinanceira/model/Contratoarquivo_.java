package br.com.alfinanceira.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-07-07T13:12:38.441-0300")
@StaticMetamodel(Contratoarquivo.class)
public class Contratoarquivo_ {
	public static volatile SingularAttribute<Contratoarquivo, Integer> idcontratoarquivo;
	public static volatile SingularAttribute<Contratoarquivo, String> nomearquivo;
	public static volatile SingularAttribute<Contratoarquivo, Date> dataupload;
	public static volatile SingularAttribute<Contratoarquivo, Contrato> contrato;
	public static volatile SingularAttribute<Contratoarquivo, Tipoarquivo> tipoarquivo;
}
