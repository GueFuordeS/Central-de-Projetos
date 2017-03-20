package projeto;

import static myUtils.Validacao.*;

import java.util.HashMap;
import java.util.Map;

import excecoes.ValidacaoException;

public class PED extends Projeto {
	String categoria;
	private Map<Produtividade,Integer> produtividade;
	
	public PED(int codigo, String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, 
			String objetivo, String dataInicio, int duracao) throws ValidacaoException {
		super(codigo, nome, objetivo, dataInicio, duracao);
		
		validaCategoria(categoria);
		validaProdTecnica(prodTecnica);
		validaProdAcademica(prodAcademica);
		validaPatentes(patentes);
		
		this.categoria = categoria;
		produtividade = new HashMap<>();
		iniciaMap(prodTecnica, prodAcademica, patentes);
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public int getValor(Produtividade chave) {
		return produtividade.get(chave);
	}
	
	public void setCategoria(String categoria) throws ValidacaoException {
		validaCategoria(categoria);
		this.categoria = categoria;
	}
	
	public void setProdutividade(Produtividade chave, String valor) throws NumberFormatException, ValidacaoException {
		switch (chave) {
		case PRODTECNICA: 
			validaProdTecnica(Integer.parseInt(valor));
		case PRODACADEMICA:
			validaProdAcademica(Integer.parseInt(valor));
		case PATENTES: 
			validaPatentes(Integer.parseInt(valor));
		}
		produtividade.put(chave, Integer.parseInt(valor));
	}
	
	private void iniciaMap(int prodTecnica, int prodAcademica, int patentes) {
		produtividade.put(Produtividade.PRODTECNICA, prodTecnica);
		produtividade.put(Produtividade.PRODACADEMICA, prodAcademica);
		produtividade.put(Produtividade.PATENTES, patentes);
	}
}