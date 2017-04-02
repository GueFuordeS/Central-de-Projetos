package participacao;

import java.io.Serializable;

import excecoes.ValidacaoException;
import pessoa.Pessoa;
import projeto.Projeto;

/**
 * Subclasse de Participacao, possui como caracteristicas
 * adicionais a Participacao somente uma forma de calcular
 * a bolsa diferente.
 * 
 * @author Gabriel Fernandes
 */
public class ParticipacaoGraduando extends Participacao implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Inicializa objeto desta subclasse,
	 * passando caracteristicas comuns de participacoes
	 * para o construtor de Participacao.
	 *
	 * @param pessoa				Pessoa na qual vai ser associada
	 * @param projeto				Projeto na qual a Pessoa vai ser associada
	 * @param dataInicio			data inicio da associacao
	 * @param duracaoEmMeses		duracao planejada, em meses
	 * @param valorDaHora			valor por hora que esta pessoa demanda ao participar deste Projeto
	 * @param qtdeHorasDedicadas	horas dedicadas a cumprir as funcoes do projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public ParticipacaoGraduando(Pessoa pessoa, Projeto projeto, String dataInicio, int duracaoEmMeses,
			double valorDaHora, int qtdeHorasDedicadas) throws ValidacaoException {

		super(pessoa, projeto, dataInicio, duracaoEmMeses, valorDaHora, qtdeHorasDedicadas);
	}

	/**
	 * Implementacao de metodo abstrato da superclasse,
	 * usando logica especifica para esta subclasse.
	 */
	@Override
	public double geraBolsa() {
		double soma = super.getValorDaHora() * super.getQtdeHorasDedicadas();
		if (soma < 350)
			return 350;
		return soma;
	}
}