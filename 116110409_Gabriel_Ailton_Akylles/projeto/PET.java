package projeto;

import static myUtils.Validacao.*;

import java.util.HashMap;

import excecoes.ValidacaoException;

public class PET extends Projeto {
	int impacto;
	int rendimento;
	private HashMap<Produtividade,Integer> produtividade;
	
	
	public PET(String nome, String objetivo, int impacto, int rendimento, 
			int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) throws ValidacaoException {
		super(nome, objetivo, dataInicio, duracao);
		
		validaImpacto(impacto);
		validaRendimento(rendimento);
		validaProdutividade(prodTecnica);
		validaProdutividade(prodAcademica);
		validaProdutividade(patentes);
		
		this.impacto = impacto;
		this.rendimento = rendimento;
		produtividade = new HashMap<>();
		iniciaMap(prodTecnica, prodAcademica, patentes);
	}
	
	public int getImpacto() {
		return this.impacto;
	}
	
	public int getRendimento() {
		return this.rendimento;
	}
	
	private void iniciaMap(int prodTecnica, int prodAcademica, int patentes) {
		produtividade.put(Produtividade.prodTecnica, prodTecnica);
		produtividade.put(Produtividade.prodAcademica, prodAcademica);
		produtividade.put(Produtividade.patentes, patentes);
	}
}