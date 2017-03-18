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
	
	/** M�todo que ir� verificar se o padr�o para cada par�metro est� correto.
	 * 
	 * @param nome - String nome do projeto.
	 * @param objetivo - String objetivo do projeto.
	 * @param date - String data de in�cio do projeto.
	 * @param duracao - int duracao do projeto.
	 * @throws ValidacaoException - Ir� validar os "param" nome, objetivo, dataInicio e duracao.
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
	
	/** M�todo que ir� retornar o nome do projeto.
	 * 
	 * @return String - nome do projeto.
	 */
	
	public String getNome() {
		return nome;
	}
	
	/** M�todo que ir� retornar o objetivo do projeto.
	 * 
	 * @return String - objetivo do projeto.
	 */
	
	public String getObjetivo() {
		return objetivo;
	}
	
	/** M�todo que ir� retornar a data de in�cio do projeto.
	 * 
	 * @return String - data de in�cio do projeto.
	 */
	
	public Date getDataInicio() {
		return dataInicio;
	}
	
	/** M�todo que ir� retornar a dura��o do projeto.
	 * 
	 * @return int - dura��o do projeto.
	 */
	
	public int getDuracao() {
		return duracao;
	}
}
