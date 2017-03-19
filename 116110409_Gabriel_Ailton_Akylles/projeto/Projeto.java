package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;
import myUtils.Date;

/**
 * 
 * Classe responsavel por criar uma forma geral para projetos, uma abstracao para suas subclasses.
 * 
 * @author Gabriel Fernandes
 *
 */

public abstract class Projeto {
	String nome;
	String objetivo;
	Date dataInicio;
	int duracao;
	
	/** 
	 * Construtor de classe
	 * 
	 * @param nome - String nome do projeto.
	 * @param objetivo - String objetivo do projeto.
	 * @param date - String data de inicio do projeto.
	 * @param duracao - int duracao do projeto.
	 * @throws ValidacaoException - Ira validar os "param" nome, objetivo, dataInicio e duracao.
	 */
	
	public Projeto(String nome, String objetivo, String date, int duracao) throws ValidacaoException {
		validaNomeProjeto(nome);
		validaObjetivo(objetivo);
		validaInt(duracao);
		
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = new Date(date);
		this.duracao = duracao;
	}
	
	/** Metodo que ira retornar o nome do projeto.
	 * 
	 * @return String - nome do projeto.
	 */
	
	public String getNome() {
		return nome;
	}
	
	/** Metodo que ira retornar o objetivo do projeto.
	 * 
	 * @return String - objetivo do projeto.
	 */
	
	public String getObjetivo() {
		return objetivo;
	}
	
	/** Metodo que ira retornar a data de inicio do projeto.
	 * 
	 * @return String - data de inicio do projeto.
	 */
	
	public Date getDataInicio() {
		return dataInicio;
	}
	
	/** Metodo que ira retornar a duracao do projeto.
	 * 
	 * @return int - duracao do projeto.
	 */
	
	public int getDuracao() {
		return duracao;
	}
}