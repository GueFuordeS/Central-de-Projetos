package participacao;

import excecoes.ValidacaoException;
import static myUtils.Validacao.*;
import pessoa.Pessoa;
import projeto.Projeto;

public class ParticipacaoProfissional extends Participacao {

	private String cargo;
	
	public ParticipacaoProfissional(Pessoa pessoa, Projeto projeto, String cargo, String dataInicio, int duracaoEmMeses,
			double valorDaHora, int qtdeHorasDedicadas) throws ValidacaoException {
	
		super(pessoa, projeto, dataInicio, duracaoEmMeses, valorDaHora, qtdeHorasDedicadas);
		
		validaCargo(cargo);
		
		this.cargo = cargo;
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) throws ValidacaoException {
		validaCargo(cargo);
		this.cargo = cargo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
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
		ParticipacaoProfissional other = (ParticipacaoProfissional) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		return true;
	}
}
