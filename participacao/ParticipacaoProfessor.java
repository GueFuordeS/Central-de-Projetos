package participacao;

import java.io.Serializable;

import excecoes.ValidacaoException;
import pessoa.Pessoa;
import projeto.Projeto;

/**
 * Subclasse de Participacao, possui como caracteristicas
 * adicionais a Participacao a possibilidade
 * de ser coordenador, na qual gera comportamentos
 * diferentes para algumas acoes.
 * 
 * @author Gabriel Fernandes
 */
public class ParticipacaoProfessor extends Participacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean isCoordenador;
	
	/**
	 * Inicializa objeto desta subclasse,
	 * passando caracteristicas comuns de participacoes
	 * para o construtor de Participacao.
	 * 
	 * @param pessoa				Pessoa na qual vai ser associada
	 * @param projeto				Projeto na qual a Pessoa vai ser associada
	 * @param isCoordenador			se o professor vai entrar no projeto na funcao de coordenador, true para sim, false para nao
	 * @param dataInicio			data inicio da associacao
	 * @param duracaoEmMeses		duracao planejada, em meses
	 * @param valorDaHora			valor por hora que esta pessoa demanda ao participar deste Projeto
	 * @param qtdeHorasDedicadas	horas dedicadas a cumprir as funcoes do projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public ParticipacaoProfessor(Pessoa pessoa, Projeto projeto, boolean isCoordenador, String dataInicio, int duracaoEmMeses, 
			double valorDaHora, int qtdeHorasDedicadas) throws ValidacaoException {
		
		super(pessoa, projeto, dataInicio, duracaoEmMeses, valorDaHora, qtdeHorasDedicadas);
		this.isCoordenador = isCoordenador;
	}

	/**
	 * Acessa o estado a qual Professor
	 * esta associado nesta Participacao possui
	 * (como coordenador ou nao).
	 * 
	 * @return		estado do Professor, sendo coordenador ou nao
	 */
	public boolean isCoordenador() {
		return isCoordenador;
	}

	/**
	 * Altera situacao do professor
	 * quanto a ser coordenador ou nao
	 * (caso professor ja seja coordenador, altera para nao sendo mais,
	 * e vice-versa).
	 */
	public void setCoordenador() {
		if(this.isCoordenador == true)
			this.isCoordenador = false;
		else
			this.isCoordenador = true;
	}

	/**
	 * Sobrescrita do hashCode de
	 * Participacao, usando alem das caracteristicas
	 * escolhidas para gerar o hash da superclasse:
	 * ser ou nao coordenador.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isCoordenador ? 1231 : 1237);
		return result;
	}

	/**
	 * Sobrescrita do equals.
	 * Alem das caracteristicas de Participacao
	 * usa: estado de ser ou nao coordenador no projeto em questao
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
		ParticipacaoProfessor other = (ParticipacaoProfessor) obj;
		if (isCoordenador != other.isCoordenador)
			return false;
		return true;
	}

	/**
	 * Implementacao de metodo abstrato da superclasse,
	 * usando logica especifica para esta subclasse.
	 */
	@Override
	public double geraBolsa() {
		if(this.isCoordenador) {
			return (super.getValorDaHora()*(7.0/5)) * super.getQtdeHorasDedicadas();
		}
		else {
			return super.getValorDaHora() * super.getQtdeHorasDedicadas();
		}
	}	
}