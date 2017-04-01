package participacao;

import java.io.Serializable;

import excecoes.ValidacaoException;
import pessoa.Pessoa;
import projeto.Projeto;

public class ParticipacaoGraduando extends Participacao implements Serializable {
	private static final long serialVersionUID = 1L;

	public ParticipacaoGraduando(Pessoa pessoa, Projeto projeto, String dataInicio, int duracaoEmMeses,
			double valorDaHora, int qtdeHorasDedicadas) throws ValidacaoException {

		super(pessoa, projeto, dataInicio, duracaoEmMeses, valorDaHora, qtdeHorasDedicadas);
	}

	@Override
	public double geraBolsa() {
		double soma = super.getValorDaHora() * super.getQtdeHorasDedicadas();
		if (soma < 350)
			return 350;
		return soma;
	}
}