package participacao;

import excecoes.ValidacaoException;
import static myUtils.Validacao.*;
import pessoa.Pessoa;
import projeto.Projeto;

public class ParticipacaoPosGraduando extends Participacao {

	private String titulacao;
	
	public ParticipacaoPosGraduando(Pessoa pessoa, Projeto projeto, String titulacao,String dataInicio, int duracaoEmMeses,
			double valorDaHora, int qtdeHorasDedicadas) throws ValidacaoException {
		
		super(pessoa, projeto, dataInicio, duracaoEmMeses, valorDaHora, qtdeHorasDedicadas);
		
		validaTitulacao(titulacao);
		
		this.titulacao = titulacao;
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) throws ValidacaoException {
		validaTitulacao(titulacao);
		this.titulacao = titulacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((titulacao == null) ? 0 : titulacao.hashCode());
		return result;
	}

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