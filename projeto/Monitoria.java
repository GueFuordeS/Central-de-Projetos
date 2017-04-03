package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;
import myUtils.Periodo;

/**
 * Subclasse de Projeto, possui como caracteristicas
 * adicionais a Projeto uma disciplina em que esta associada, um periodo(Uso da classe Periodo)
 * e rendimento da monitoria.
 * 
 * Utiliza da interface Rendimento, na qual permite fazer a ligacao
 * de proetos que possuem essa mesma caracteristica(usada para chamadas polimorficas).
 * 
 * @author Gabriel Fernandes
 */
public class Monitoria extends Projeto implements Rendimento {
	private static final long serialVersionUID = 1L;
	private String disciplina;
	private Periodo periodo;
	private int rendimento;
	
	/**
	 * Constroi objeto a partir de informacoes passadas sobre ele 
	 * como paramentro.
	 * 
	 * @param codigo				identificador unico de projeto
	 * @param nome					nome do projeto
	 * @param disciplina			disciplina associada a monitoria
	 * @param rendimento			rendimento planejado para esta monitoria
	 * @param objetivo				objetivo previo para esta monitoria
	 * @param periodo				periodo na qual a monitoria vai acontecer(Uso de classe especifica Periodo)
	 * @param dataInicio			data inicial, refere-se ao inicio do periodo
	 * @param duracao				normalmente 6 meses
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public Monitoria(int codigo, String nome, String disciplina, int rendimento, String objetivo,
		 String periodo, String dataInicio, int duracao) throws ValidacaoException {
		super(codigo, nome, objetivo, dataInicio, duracao);
		
		validaNomeDisciplina(disciplina);
		validaRendimento(rendimento);
		validaPeriodo(periodo);
		this.disciplina = disciplina;
		this.rendimento = rendimento;
		this.periodo = new Periodo(periodo);
	}
	
	/**
	 * Acessa disciplina associada com monitoria.
	 * 
	 * @return 		nome da disciplina
	 */
	public String getDisciplina() {
		return this.disciplina;
	}
	
	/**
	 * Acessa periodo associado a monitoria.
	 * 
	 * @return		toString() da classe projeto
	 */
	public String getPeriodo() {
		return this.periodo.toString();
	}
	
	/**
	 * Acessa o rendimento previsto.
	 */
	public int getRendimento() {
		return this.rendimento;
	}
	
	/**
	 * Altera a disciplina associada.
	 * 
	 * @param disciplina			nova disciplina
	 * @throws ValidacaoException	em caso de nome de disciplina ser invalido
	 */
	public void setDisciplina(String disciplina) throws ValidacaoException { //Acao nao muito usual diga-se de passagem
		validaNomeDisciplina(disciplina);
		this.disciplina = disciplina;
	}
	
	/**
	 * Modifica periodo da monitoria.
	 * 
	 * @param periodo				novo periodo(recebe uma string, a partir dela inicializa o objeto)
	 * @throws ValidacaoException	em caso de formato de periodo invalido
	 */
	public void setPeriodo(String periodo) throws ValidacaoException {
		validaPeriodo(periodo);
		this.periodo = new Periodo(periodo);
	}
	
	/**
	 * Modifica rendimento planejado para a monitoria.
	 */
	public void setRendimento(int rendimento) throws ValidacaoException {
		validaRendimento(rendimento);
		this.rendimento = rendimento;
	}

	/**
	 * Calculo de colaboracao para a uasc
	 * (toda Monitoria tem isencao).
	 */
	@Override
	public double calculaColaboracao() {
		return 0;
	}

	/**
	 * Sobrescrita com a logica necessaria para
	 * projetos do tipo Monitoria.
	 */
	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ValidacaoException {
		if(montanteCusteio > 0 || montanteCapital > 0) throw new ValidacaoException("Erro na atualizacao de projeto: "
				+ "projeto do tipo monitoria nao permite despesas de custeio ou capital");
		super.getDespesas().atualizaDespesas(montanteBolsas, montanteCusteio, montanteCapital);
	}
}