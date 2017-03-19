package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;
import myUtils.Periodo;

public class Monitoria extends Projeto {
	String disciplina;
	Periodo periodo;
	int rendimento;
	
	public Monitoria(String nome, String disciplina, int rendimento, String objetivo,
		 String periodo, String dataInicio, int duracao) throws ValidacaoException {
		super(nome, objetivo, dataInicio, duracao);
		
		validaNomeDisciplina(disciplina);
		validaRendimento(rendimento);
		validaPeriodo(periodo);
		this.disciplina = disciplina;
		this.rendimento = rendimento;
		this.periodo = new Periodo(periodo);
	}
	public String getDisciplina() {
		return this.disciplina;
	}
	
	public String getPeriodo() {
		return this.periodo.toString();
	}
	
	public int getRendimento() {
		return this.rendimento;
	}
}