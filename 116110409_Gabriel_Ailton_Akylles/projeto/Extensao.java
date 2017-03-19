package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

public class Extensao extends Projeto {
	int impacto;
	
	public Extensao(String nome, String objetivo, String date, int duracao,int impacto) throws ValidacaoException {
		super(nome, objetivo, date, duracao);
		
		validaImpacto(impacto);
		this.impacto = impacto; 
	}
	
	public int getImpacto() {
		return impacto;
	}
}
