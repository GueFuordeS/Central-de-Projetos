package participacao;

import excecoes.ValidacaoException;
import static myUtils.Validacao.*;

import java.io.Serializable;

import pessoa.Pessoa;
import projeto.Projeto;

public class ParticipacaoProfissional extends Participacao implements Serializable {
	private static final long serialVersionUID = 1L;
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