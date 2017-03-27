package participacao;

import excecoes.ValidacaoException;
import pessoa.Pessoa;
import projeto.Projeto;

public class ParticipacaoGraduando extends Participacao{

	public ParticipacaoGraduando(Pessoa pessoa, Projeto projeto, String dataInicio, int duracaoEmMeses,
			double valorDaHora, int qtdeHorasDedicadas) throws ValidacaoException {
		
		super(pessoa, projeto, dataInicio, duracaoEmMeses, valorDaHora, qtdeHorasDedicadas);
	}

	@Override
	public double geraBolsa() {
		double soma = super.getValorDaHora() * super.getQtdeHorasDedicadas();
		if(soma < 350) return 350; return soma;
	}	
}