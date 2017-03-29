package projeto;

import static myUtils.Validacao.*;

import java.util.HashMap;
import java.util.Map;

import excecoes.ValidacaoException;

/**
 * Subclasse de projeto responsavel  por moldar projetos do tipo
 * PeD
 *
 * @author Gabriel Fernandes
 *
 */
public class PED extends Projeto {
	String categoria;
	private Map<Produtividade,Integer> produtividade;
	
	/**
	 * 
	 * 
	 * @param codigo recebe o codigo que vai diferenciar cada projeto
	 * @param nome nome referente ao projeto
	 * @param categoria á que categoria o projeto está associado
	 * @param prodTecnica Valor que no projeto sera produzido de producao tecnica
	 * @param prodAcademica Valor que no projeto sera produzido de producao academica
	 * @param patentes Valor que no projeto sera produzido de patentes
	 * @param objetivo Objetivo geral do projeto
	 * @param dataInicio data de inicio do projeto
	 * @param duracao planejada para o projeto
	 * @throws ValidacaoException Lanca excecao caso falhe nas validacoes
	 */
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
	
	public void setProdutividade(Produtividade chave, String valor) throws ValidacaoException {
		switch (chave) {
		
		case PRODTECNICA:
			try {
				validaProdTecnica(Integer.parseInt(valor));
			}
			catch (NumberFormatException e) {
				throw new ValidacaoException("Producao tecnica precisa ter um valor inteiro valido");
			}
			
		case PRODACADEMICA:
			try {
				validaProdAcademica(Integer.parseInt(valor));
			}
			catch (NumberFormatException e) {
				throw new ValidacaoException("Producao tecnica precisa ter um valor inteiro valido");
			}
			
		case PATENTES: 
			try {
				validaPatentes(Integer.parseInt(valor));
			}
			catch (NumberFormatException e) {
				throw new ValidacaoException("Producao tecnica precisa ter um valor inteiro valido");
			}
		}
		produtividade.put(chave, Integer.parseInt(valor));
	}
	
	private void iniciaMap(int prodTecnica, int prodAcademica, int patentes) {
		produtividade.put(Produtividade.PRODTECNICA, prodTecnica);
		produtividade.put(Produtividade.PRODACADEMICA, prodAcademica);
		produtividade.put(Produtividade.PATENTES, patentes);
	}

	public boolean hasPEDLimitacao() {
		if(this.categoria.toLowerCase().equals("pibic") || this.categoria.toLowerCase().equals("pibiti") 
				|| this.categoria.toLowerCase().equals("pivic")) {
			return true;
		}
		return false;
	}
	
	@Override
	public double calculaColaboracao() {
		if(super.getMontanteCusteio() <= 10000 && super.getMontanteCapital() <= 10000) return 0;
		
		double percentual = 10;
		if(this.produtividade.get(Produtividade.PATENTES) > 0) percentual += 3;
		percentual += this.produtividade.get(Produtividade.PRODTECNICA) * 0.3;
		percentual -= this.produtividade.get(Produtividade.PRODACADEMICA) * 0.2;
		percentual += Math.floor(this.getMontanteCapital()/100000);
		
		return super.getDespesasTotais() * (percentual/100);
	}
}