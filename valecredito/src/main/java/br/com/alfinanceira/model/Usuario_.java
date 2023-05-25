package br.com.alfinanceira.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-07-22T16:28:29.880-0300")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, Integer> idusuario;
	public static volatile SingularAttribute<Usuario, String> cdinterno;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, String> cpf;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Boolean> ativo;
	public static volatile SingularAttribute<Usuario, String> descricaoativo;
	public static volatile SingularAttribute<Usuario, Boolean> acessogeral;
	public static volatile SingularAttribute<Usuario, String> chavepix;
	public static volatile SingularAttribute<Usuario, Boolean> trabalhando;
	public static volatile SingularAttribute<Usuario, Tipocolaborador> tipocolaborador;
	public static volatile SingularAttribute<Usuario, Departamento> departamento;
}
