package participacao;

import pessoa.Pessoa;
import projeto.Projeto;

public class ParticipacaoPosGraduando extends Participacao {

	private TipoPosGraduando tipo;
	
	public ParticipacaoPosGraduando(Pessoa pessoa, Projeto projeto, String dataInicio, int duracaoEmMeses,
			int qtdeHorasDedicadas, double valorDaHora, TipoPosGraduando tipo) {
		
		super(pessoa, projeto, dataInicio, duracaoEmMeses, qtdeHorasDedicadas, valorDaHora);
		this.tipo = tipo;
	}

	public TipoPosGraduando getTipo() {
		return tipo;
	}

	public void setTipo(TipoPosGraduando tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		if (tipo != other.tipo)
			return false;
		return true;
	}
}
