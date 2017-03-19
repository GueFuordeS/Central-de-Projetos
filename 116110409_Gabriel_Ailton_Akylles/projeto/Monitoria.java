package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

public class Monitoria extends Projeto {
	String disciplina;
	String periodo;
	int rendimento;
	
	public Monitoria(String nome, String disciplina, int rendimento, String objetivo, 
			String periodo, String dataInicio, int duracao) throws ValidacaoException {
		super(nome, objetivo, dataInicio, duracao);
		
		validaNomeDisciplina(disciplina);
		validaRendimento(rendimento);
		validaPeriodo(periodo);
		this.disciplina = disciplina;
		this.rendimento = rendimento;
		this.periodo = periodo;
	}
	
	public int getRendimento() {
		return this.rendimento;
	}
}
