package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

public class Extensao extends Projeto {
	int impacto;
	
	public Extensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) throws ValidacaoException {
		super(nome, objetivo, dataInicio, duracao);
		
		validaImpacto(impacto);
		this.impacto = impacto; 
	}
	
	public int getImpacto() {
		return this.impacto;
	}
}