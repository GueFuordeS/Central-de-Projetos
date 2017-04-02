package participacao;

import excecoes.ValidacaoException;
import myUtils.Date;
import myUtils.Validacao;

import static myUtils.Validacao.*;

import java.io.Serializable;

import pessoa.Pessoa;
import projeto.Projeto;

/**
 * Classe que faz a associacao entre uma pessoa
 * e um projeto, mantem um uma copia do objeto
 * pessoa e uma do projeto na qual estao sendo
 * relacionados.
 * 
 * @author Gabriel Fernandes
 */
public abstract class Participacao implements Comparable<Participacao>, Serializable {
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private Projeto projeto;
	private Date dataInicio;
	private int duracao;
	private int qntHoras;
	private double valorHora;

	/**
	 * Contrutor usado sobretudo
	 * pelas subclasses, recebe como parametros
	 * atributos comum a todo tipo de participacao.
	 * 
	 * @param pessoa				objeto Pessoa no qual vai ser envolvido na Participacao
	 * @param projeto				objeto Projeto no qual vai ser envolvido na Participacao
	 * @param dataInicio			data do inicio da Participacao
	 * @param duracaoEmMeses		duracao prevista para a Participacao desta Pessoa nesse Projeto
	 * @param valorDaHora			em que a Pessoa recebera ao participar deste Projeto
	 * @param qtdeHorasDedicadas	em que a Pessoa se dedicara ao Projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public Participacao(Pessoa pessoa, Projeto projeto, String dataInicio, int duracaoEmMeses, double valorDaHora,
			int qtdeHorasDedicadas) throws ValidacaoException {

		Validacao.validaPessoa(pessoa);
		Validacao.validaProjeto(projeto);
		try {
			validaDuracao(duracaoEmMeses);
		} catch (ValidacaoException e) {
			throw new ValidacaoException("Erro na associacao de pessoa a projeto: Duracao invalida");
		}
		validaQntHoras(qtdeHorasDedicadas);

		this.pessoa = pessoa;
		this.projeto = projeto;
		this.dataInicio = new Date(dataInicio);
		this.duracao = duracaoEmMeses;
		this.qntHoras = qtdeHorasDedicadas;
		this.valorHora = valorDaHora;
	}

	/**
	 * Acessa a Pessoa desta
	 * associacao.
	 * 
	 * @return		a Pessoa em questao
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * Altera a Pessoa desta
	 * associacao.
	 * 
	 * @param pessoa	nova Pessoa
	 */
	public void setPessoa(Pessoa pessoa) {
		if(pessoa != null)
			this.pessoa = pessoa; //apesar de nao fazer muito sentido fazer essa troca, melhor opcao eh criar uma nova associacao
	}								//ao mesmo projeto com a nova pessoa, mas ta ai

	/**
	 * Acessa o Projeto desta
	 * associacao.
	 * 
	 * @return		o Projeto em questao
	 */
	public Projeto getProjeto() {
		return projeto;
	}

	/**
	 * Altera o Projeto desta
	 * associacao.
	 * 
	 * @param pessoa	novo Projeto
	 */
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	/**
	 * Acessa a data de inicio desta
	 * associacao.
	 * 
	 * @return		a data de inicio em questao
	 */
	public String getDataInicio() {
		return dataInicio.toString();
	}

	/**
	 * Altera a data de inicio desta
	 * associacao.
	 * 
	 * @param dataInicio			a data de inicio em questao
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public void setDataInicio(String dataInicio) throws ValidacaoException {
		this.dataInicio = new Date(dataInicio);
	}

	/**
	 * Acessa a duracao planejada desta
	 * associacao.
	 * 
	 * @return		a duracao da associacao
	 */
	public int getDuracaoEmMeses() {
		return duracao;
	}

	/**
	 * Altera a duracao desta
	 * associacao.
	 * 
	 * @param dataInicio			a nova duracao da associacao
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public void setDuracaoEmMeses(int duracaoEmMeses) throws ValidacaoException {
		validaDuracao(duracaoEmMeses);
		this.duracao = duracaoEmMeses;
	}

	/**
	 * Acessa a quantidade de horas dedicadas planejada desta
	 * Pessoa a este projeto.
	 * 
	 * @return		a quantidade de horas dedicadas
	 */
	public int getQtdeHorasDedicadas() {
		return qntHoras;
	}

	/**
	 * Altera a quantidade de horas dedicadas desta
	 * Pessoa a este projeto.
	 * 
	 * @param dataInicio			a nova quantidade de horas
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public void setQtdeHorasDedicadas(int qtdeHorasDedicadas) throws ValidacaoException {
		validaQntHoras(qtdeHorasDedicadas);
		this.qntHoras = qtdeHorasDedicadas;
	}

	/**
	 * Acessa o valor por hora ganho desta
	 * Pessoa por esse projeto.
	 * 
	 * @return			o valor da hora
	 */
	public double getValorDaHora() {
		return valorHora;
	}

	/**
	 * Acessa o valor por hora ganho desta
	 * Pessoa por esse projeto.
	 * 
	 * @param dataInicio			o novo valor da hora
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public void setValorDaHora(double valorDaHora) throws ValidacaoException {
		validaValorHora(valorDaHora);
		this.valorHora = valorDaHora;
	}

	/**
	 * Implementacao de metodo da
	 * interface Comparable
	 * (Utilizado na necessidade de impressao ordenada das pessoas
	 * que tem Participacao em cada Projeto, por isso a comparacao eh feita
	 * pelo nome da Pessoa associada ao Projeto).
	 */
	public int compareTo(Participacao part) {
		return this.getPessoa().getNome().compareTo(part.getPessoa().getNome());
	}

	/**
	 * Sobrescrita do hashCode padrao de Object.
	 * Gera o hashCode a partir dos atributos Pessoa
	 * e Projeto de cada Participacao.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
		return result;
	}

	/**
	 * Sobrescrita do equals padrao de Object.
	 * Realiza a comparacao a partir dos atributos Pessoa
	 * e Projeto de cada Participacao.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participacao other = (Participacao) obj;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (projeto == null) {
			if (other.projeto != null)
				return false;
		} else if (!projeto.equals(other.projeto))
			return false;
		return true;
	}

	/**
	 * Metodo abstrato,
	 * no qual cada subclasse de Participacao
	 * implementa com uma logica diferente.
	 * 
	 * @return		valor da bolsa gerada pela Participacao de Pessoa com Projeto
	 */
	public abstract double geraBolsa();
}