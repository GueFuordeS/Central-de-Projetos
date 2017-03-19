package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

public class Extensao extends Projeto {
	int impacto;
	
	public Extensao(int codigo, String nome, String objetivo, int impacto, String dataInicio, int duracao) 
			throws ValidacaoException {
		super(codigo, nome, objetivo, dataInicio, duracao);
		
		validaImpacto(impacto);
		this.impacto = impacto; 
	}
	
	public int getImpacto() {
		return this.impacto;
	}
}