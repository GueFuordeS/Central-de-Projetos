package projeto;

import static excecoes.Validacao.*;

import java.util.HashMap;

import excecoes.ValidacaoException;

public class PeD extends Projeto {
	private HashMap<Produtividade,Integer> produtividade;
	String categoria;
	
	public PeD(String nome, String objetivo, String date, int duracao, String categoria,
			int prodTecnica, int prodAcademica, int patentes) throws ValidacaoException {
		super(nome, objetivo, date, duracao);
		
		validaCategoria(categoria);
		validaProdutividade(prodTecnica);
		validaProdutividade(prodAcademica);
		validaProdutividade(patentes);
		
		produtividade = new HashMap<>();
		this.categoria = categoria;
		iniciaMap(prodTecnica, prodAcademica, patentes);
	}
	
	private void iniciaMap(int prodTecnica, int prodAcademica, int patentes) {
		produtividade.put(Produtividade.prodTecnica, prodTecnica);
		produtividade.put(Produtividade.prodAcademica, prodAcademica);
		produtividade.put(Produtividade.patentes, patentes);
	}
}
