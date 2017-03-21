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
	int codigo;
	String nome;
	String objetivo;
	Date dataInicio;
	int duracao;
	
	/** 
	 * Construtor de classe
	 * 
	 * @param nome - String nome do projeto.
	 * @param objetivo - String objetivo do projeto.
	 * @param dataInicio - String data de inicio do projeto.
	 * @param duracao - int duracao do projeto.
	 * @throws ValidacaoException - Ira validar os "param" nome, objetivo, dataInicio e duracao.
	 */
	public Projeto(int codigo, String nome, String objetivo, String dataInicio, int duracao) throws ValidacaoException {
		validaNomeProjeto(nome);
		validaObjetivo(objetivo);
		validaInt(duracao);
		
		this.codigo = codigo;
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = new Date(dataInicio);
		this.duracao = duracao;
	}
	
	/**
	 * Acessador de codigo
	 * 
	 * @return inteiro com o codigo do projeto
	 */
	public int getCodigo() {
		return this.codigo;
	}
	
	/** Metodo que ira retornar o nome do projeto.
	 * 
	 * @return String - nome do projeto.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/** Metodo que ira retornar o objetivo do projeto.
	 * 
	 * @return String - objetivo do projeto.
	 */
	public String getObjetivo() {
		return this.objetivo;
	}
	
	/** Metodo que ira retornar a data de inicio do projeto.
	 * 
	 * @return String - data de inicio do projeto.
	 */
	public String getDataInicio() {
		return this.dataInicio.toString();
	}
	
	/** Metodo que ira retornar a duracao do projeto.
	 * 
	 * @return int - duracao do projeto.
	 */
	public int getDuracao() {
		return this.duracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (codigo != other.codigo)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}