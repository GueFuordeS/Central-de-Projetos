package projeto;

import static myUtils.Validacao.*;

import java.util.HashMap;

import excecoes.ValidacaoException;

public class PED extends Projeto {
	String categoria;
	private HashMap<Produtividade,Integer> produtividade;
	
	public PED(int codigo, String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, 
			String objetivo, String dataInicio, int duracao) throws ValidacaoException {
		super(codigo, nome, objetivo, dataInicio, duracao);
		
		validaCategoria(categoria);
		validaProdutividade(prodTecnica);
		validaProdutividade(prodAcademica);
		validaProdutividade(patentes);
		
		this.categoria = categoria;
		produtividade = new HashMap<>();
		iniciaMap(prodTecnica, prodAcademica, patentes);
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	private void iniciaMap(int prodTecnica, int prodAcademica, int patentes) {
		produtividade.put(Produtividade.prodTecnica, prodTecnica);
		produtividade.put(Produtividade.prodAcademica, prodAcademica);
		produtividade.put(Produtividade.patentes, patentes);
	}
}