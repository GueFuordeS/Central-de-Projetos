package projeto;

import static excecoes.Validacao.*;

import excecoes.ValidacaoException;

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
	
	/** Método que irá verificar se o padrão para cada parâmetro está correto.
	 * 
	 * @param nome - String nome do projeto.
	 * @param objetivo - String objetivo do projeto.
	 * @param date - String data de início do projeto.
	 * @param duracao - int duracao do projeto.
	 * @throws ValidacaoException - Irá validar os "param" nome, objetivo, dataInicio e duracao.
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
	
	/** Método que irá retornar o nome do projeto.
	 * 
	 * @return String - nome do projeto.
	 */
	
	public String getNome() {
		return nome;
	}
	
	/** Método que irá retornar o objetivo do projeto.
	 * 
	 * @return String - objetivo do projeto.
	 */
	
	public String getObjetivo() {
		return objetivo;
	}
	
	/** Método que irá retornar a data de início do projeto.
	 * 
	 * @return String - data de início do projeto.
	 */
	
	public Date getDataInicio() {
		return dataInicio;
	}
	
	/** Método que irá retornar a duração do projeto.
	 * 
	 * @return int - duração do projeto.
	 */
	
	public int getDuracao() {
		return duracao;
	}
}
