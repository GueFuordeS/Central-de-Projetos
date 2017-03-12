package projeto;

import static excecoes.Validacao.*;

import excecoes.ValidacaoException;

public class Monitoria extends Projeto {
	String disciplina;
	
	public Monitoria(String nome, String objetivo, String date, int duracao, String disciplina) throws ValidacaoException {
		super(nome, objetivo, date, duracao);
		
		validaString(disciplina);
		this.disciplina = disciplina;
	}

}
