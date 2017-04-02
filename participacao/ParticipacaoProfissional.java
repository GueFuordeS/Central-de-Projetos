package participacao;

import excecoes.ValidacaoException;
import static myUtils.Validacao.*;

import java.io.Serializable;

import pessoa.Pessoa;
import projeto.Projeto;

/**
 * Subclasse de Participacao, possui como caracteristicas
 * adicionais a Participacao um cargo na qual
 * todo profissional possui, podendo ser ele:
 * "desenvolvedor", "pesquisador", ou "gerente".
 * 
 * @author Gabriel Fernandes
 */
public class ParticipacaoProfissional extends Participacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cargo;
	
	/**
	 * Inicializa objeto desta subclasse,
	 * passando caracteristicas comuns de participacoes
	 * para o construtor de Participacao.
	 * 
	 * @param pessoa				Pessoa na qual vai ser associada
	 * @param projeto				Projeto na qual a Pessoa vai ser associada
	 * @param cargo					funcao na qual profissional desempenha
	 * @param dataInicio			data inicio da associacao
	 * @param duracaoEmMeses		duracao planejada, em meses
	 * @param valorDaHora			valor por hora que esta pessoa demanda ao participar deste Projeto
	 * @param qtdeHorasDedicadas	horas dedicadas a cumprir as funcoes do projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public ParticipacaoProfissional(Pessoa pessoa, Projeto projeto, String cargo, String dataInicio, int duracaoEmMeses,
			double valorDaHora, int qtdeHorasDedicadas) throws ValidacaoException {
	
		super(pessoa, projeto, dataInicio, duracaoEmMeses, valorDaHora, qtdeHorasDedicadas);
		
		validaCargo(cargo);
		
		this.cargo = cargo;
	}
	
	/**
	 * Acessa o cargo a qual Pessoa
	 * desta Participacao possui.
	 * 
	 * @return		cargo do profissional
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * Altera cargo em que profissional
	 * desempenha.
	 * 
	 * @param cargo					novo cargo do profissional
	 * @throws ValidacaoException	em caso de cargo invalido
	 */
	public void setCargo(String cargo) throws ValidacaoException {
		validaCargo(cargo);
		this.cargo = cargo;
	}

	/**
	 * Sobrescrita do hashCode de
	 * Participacao, usando alem das caracteristicas
	 * escolhidas para gerar o hash da superclasse:
	 * cargo de profissional.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		return result;
	}

	/**
	 * Sobrescrita do equals.
	 * Alem das caracteristicas de Participacao
	 * usa: cargo do profissional
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
		ParticipacaoProfissional other = (ParticipacaoProfissional) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		return true;
	}

	/**
	 * Implementacao de metodo abstrato da superclasse,
	 * usando logica especifica para esta subclasse.
	 */
	@Override
	public double geraBolsa() {
		if(this.cargo.toLowerCase().equals("pesquisador")) {
			double soma = super.getValorDaHora() * super.getQtdeHorasDedicadas() + 100;
			if(soma < 350) return 350; return soma;
		}
		else if(this.cargo.toLowerCase().equals("gerente")) {
			int multiplicador = this.getProjeto().getNumParticipantes() * 20;
			if(multiplicador > 100) multiplicador = 100;
			double soma = super.getValorDaHora() * super.getQtdeHorasDedicadas() + multiplicador;
			if(soma < 350) return 350; return soma;
		}
		double soma = super.getValorDaHora() * super.getQtdeHorasDedicadas();
		if(soma < 350) return 350; return soma;
	}
}