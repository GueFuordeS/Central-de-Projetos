package participacao;

import excecoes.ValidacaoException;
import pessoa.Pessoa;
import projeto.Projeto;

public class ParticipacaoGraduando extends Participacao{

	public ParticipacaoGraduando(Pessoa pessoa, Projeto projeto, String dataInicio, int duracaoEmMeses,
			double valorDaHora, int qtdeHorasDedicadas) throws ValidacaoException {
		
		super(pessoa, projeto, dataInicio, duracaoEmMeses, valorDaHora, qtdeHorasDedicadas);
	}	
}