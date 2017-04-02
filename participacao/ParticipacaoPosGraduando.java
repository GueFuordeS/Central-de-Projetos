package participacao;

import excecoes.ValidacaoException;
import static myUtils.Validacao.*;

import java.io.Serializable;

import pessoa.Pessoa;
import projeto.Projeto;

/**
 * Subclasse de Participacao, possui como caracteristicas
 * adicionais a Participacao uma titulacao,
 * na qual todo pos-graduando deve possuir,
 * sendo elas mestrado ou doutorado.
 * 
 * @author Gabriel Fernandes
 */
public class ParticipacaoPosGraduando extends Participacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private String titulacao;
	
	/**
	 * Inicializa objeto desta subclasse,
	 * passando caracteristicas comuns de participacoes
	 * para o construtor de Participacao.
	 * 
	 * @param pessoa				Pessoa na qual vai ser associada
	 * @param projeto				Projeto na qual a Pessoa vai ser associada
	 * @param titulacao				titulo de pos-graduacao na qual Pessoa esta cursando
	 * @param dataInicio			data inicio da associacao
	 * @param duracaoEmMeses		duracao planejada, em meses
	 * @param valorDaHora			valor por hora que esta pessoa demanda ao participar deste Projeto
	 * @param qtdeHorasDedicadas	horas dedicadas a cumprir as funcoes do projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public ParticipacaoPosGraduando(Pessoa pessoa, Projeto projeto, String titulacao,String dataInicio, int duracaoEmMeses,
			double valorDaHora, int qtdeHorasDedicadas) throws ValidacaoException {
		
		super(pessoa, projeto, dataInicio, duracaoEmMeses, valorDaHora, qtdeHorasDedicadas);
		
		validaTitulacao(titulacao);
		
		this.titulacao = titulacao;
	}
	
	/**
	 * Acessa o titulo a qual Pessoa
	 * desta Participacao possui.
	 * 
	 * @return		titulo do pos-graduando
	 */
	public String getTitulacao() {
		return titulacao;
	}

	/**
	 * Altera titulo de pos-graduando
	 *
	 * @param cargo					novo cargo do profissional
	 * @throws ValidacaoException	em caso de cargo invalido
	 */
	public void setTitulacao(String titulacao) throws ValidacaoException {
		validaTitulacao(titulacao);
		this.titulacao = titulacao;
	}
	
	/**
	 * Sobrescrita do hashCode de
	 * Participacao, usando alem das caracteristicas
	 * escolhidas para gerar o hash da superclasse:
	 * titulacao de pos-graduando.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((titulacao == null) ? 0 : titulacao.hashCode());
		return result;
	}

	/**
	 * Sobrescrita do equals.
	 * Alem das caracteristicas de Participacao
	 * usa: titulacao do pos-graduando
	 * para diferenciar dois objetos deste tipo.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipacaoPosGraduando other = (ParticipacaoPosGraduando) obj;
		if (titulacao != other.titulacao)
			return false;
		return true;
	}

	/**
	 * Implementacao de metodo abstrato da superclasse,
	 * usando logica especifica para esta subclasse.
	 */
	@Override
	public double geraBolsa() {
		if(this.getTitulacao().toLowerCase().equals("doutorado")) {
			double soma = super.getValorDaHora() * super.getQtdeHorasDedicadas()*(4.0/3);
			if(soma < 350) return 350; return soma;
		}
		double soma = super.getValorDaHora() * super.getQtdeHorasDedicadas();
		if(soma < 350) return 350; return soma;
	}
}