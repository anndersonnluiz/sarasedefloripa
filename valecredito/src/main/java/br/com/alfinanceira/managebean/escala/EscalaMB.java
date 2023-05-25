package br.com.alfinanceira.managebean.escala;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import br.com.alfinanceira.model.Escala;

public class EscalaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Escala> listaEscala;
	private String mes;
	private String ano;
	
	
	@PostConstruct
	public void init() {
		
	}


	/**
	 * @return the listaEscala
	 */
	public List<Escala> getListaEscala() {
		return listaEscala;
	}


	/**
	 * @param listaEscala the listaEscala to set
	 */
	public void setListaEscala(List<Escala> listaEscala) {
		this.listaEscala = listaEscala;
	}


	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}


	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}


	/**
	 * @return the ano
	 */
	public String getAno() {
		return ano;
	}


	/**
	 * @param ano the ano to set
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	
	public String cadEscala() {
		return "cadEscala";
	}

}
