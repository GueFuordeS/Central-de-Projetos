package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class ParticipacaoProfessor extends Participacao {

	private boolean isCoordenador;
	
	public ParticipacaoProfessor(Pessoa pessoa, Projeto projeto, boolean isCoordenador, String dataInicio, int duracaoEmMeses, 
			double valorDaHora, int qtdeHorasDedicadas) {
		
		super(pessoa, projeto, dataInicio, duracaoEmMeses, valorDaHora, qtdeHorasDedicadas);
		this.isCoordenador = isCoordenador;
	}

	public boolean isCoordenador() {
		return isCoordenador;
	}

	public void setCoordenador(boolean isCoordenador) {
		this.isCoordenador = isCoordenador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isCoordenador ? 1231 : 1237);
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
		ParticipacaoProfessor other = (ParticipacaoProfessor) obj;
		if (isCoordenador != other.isCoordenador)
			return false;
		return true;
	}
	
	
	
}
