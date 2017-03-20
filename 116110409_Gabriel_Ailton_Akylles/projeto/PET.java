package projeto;

import static myUtils.Validacao.*;

import java.util.HashMap;
import java.util.Map;

import excecoes.ValidacaoException;

public class PET extends Projeto {
	int impacto;
	int rendimento;
	private Map<Produtividade,Integer> produtividade;
	
	
	public PET(int codigo, String nome, String objetivo, int impacto, int rendimento, 
			int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) throws ValidacaoException {
		super(codigo, nome, objetivo, dataInicio, duracao);
		
		validaImpacto(impacto);
		validaRendimento(rendimento);
		validaProdTecnica(prodTecnica);
		validaProdAcademica(prodAcademica);
		validaPatentes(patentes);
		
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
	
	public int getValor(Produtividade chave) {
		return produtividade.get(chave);
	}
	
	public void setImpacto(int impacto) throws ValidacaoException {
		validaImpacto(impacto);
		this.impacto = impacto;
	}
	
	public void setRendimento(int rendimento) throws ValidacaoException {
		validaRendimento(rendimento);
		this.rendimento = rendimento;
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