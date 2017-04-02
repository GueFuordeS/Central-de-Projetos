package projeto;

import static myUtils.Validacao.*;

import java.util.HashMap;
import java.util.Map;

import excecoes.ValidacaoException;

/**
 * Subclasse de Projeto, possui como caracteristicas
 * adicionais a Projeto um impacto social, um rendimento
 * e uma produtividade em 3 aspectos: producao tecnica, producao academica, patentes.
 * 
 * Utiliza da interface Impacto, possibilitando assim a referenciacao
 * por meio da interface.
 * 
 * Utiliza da interface Rendimento, na qual permite fazer a ligacao
 * de proetos que possuem essa mesma caracteristica(usada para chamadas polimorficas).
 * 
 * @author Gabriel Fernandes
 */
public class PET extends Projeto implements Impacto, Rendimento {
	private static final long serialVersionUID = 1L;
	int impacto;
	int rendimento;
	private Map<Produtividade,Integer> produtividade;
	
	/**
	 * Constroi objeto a partir de informacoes passadas sobre ele 
	 * como paramentro.
	 * 
	 * @param codigo 				codigo do novo projeto
	 * @param nome					nome dado ao projeto
	 * @param objetivo				objetivo de criacao do projeto
	 * @param impacto				impacto na comunidade em que atuara o projeto
	 * @param rendimento			rendimento a qual o projeto esta planejado ter
	 * @param prodTecnica			quantidade de producoes tecnicas do projeto
	 * @param prodAcademica			quantidade de producoes academicas do projeto
	 * @param patentes				quantidade de patentes do projeto
	 * @param dataInicio			data de inicio do projeto
	 * @param duracao				duracao prevista
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
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
	
	/**
	 * Retorna o impacto social do projeto PET em questao
	 * (sobrescrita de metodo da interface Impacto).
	 */
	public int getImpacto() {
		return this.impacto;
	}
	
	/**
	 * Retorna o rendimento.
	 */
	public int getRendimento() {
		return this.rendimento;
	}
	
	/**
	 * Nos permite acessar o valor de producoes academicas/tecnicas ou
	 * patentes previstas para o projeto.
	 * 
	 * @param chave		Um dos tres valores permitidos pelo enum, chave para dicionario
	 * @return			valor referente a chave escolhida
	 */
	public int getValor(Produtividade chave) {
		return produtividade.get(chave);
	}
	
	/**
	 * Possibilita alterar o impacto do projeto
	 * (sobrescreve interface).
	 */
	public void setImpacto(int impacto) throws ValidacaoException {
		validaImpacto(impacto);
		this.impacto = impacto;
	}
	
	/**
	 * Altera rendimento do projeto.
	 */
	public void setRendimento(int rendimento) throws ValidacaoException {
		validaRendimento(rendimento);
		this.rendimento = rendimento;
	}
	
	/**
	 * Altera valor referente a producoes academicas/tecnicas ou
	 * patentes.
	 * 
	 * @param chave					chave para o valor que deseja alterar
	 * @param valor					novo valor
	 * @throws ValidacaoException	em caso de valor ser invalido(necessita ser um inteiro valido)
	 */
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
	
	/**
	 * Inicia o armazenamento com valor/chave para as produtividades possiveis
	 * para o PET.
	 * 
	 * @param prodTecnica		quantidade de producoes tecnicas
	 * @param prodAcademica		quantidade de producoes academicas
	 * @param patentes			quantidade de patentes
	 */
	private void iniciaMap(int prodTecnica, int prodAcademica, int patentes) {
		produtividade.put(Produtividade.PRODTECNICA, prodTecnica);
		produtividade.put(Produtividade.PRODACADEMICA, prodAcademica);
		produtividade.put(Produtividade.PATENTES, patentes);
	}

	/**
	 * Calculo de colaboracao para a uasc
	 * (todo PET tem isencao).
	 */
	@Override
	public double calculaColaboracao() {
		return 0;
	}

	/**
	 * Sobrescrita com a logica necessaria para
	 * projetos do tipo PET.
	 */
	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ValidacaoException {
		if(montanteCapital > 0) throw new ValidacaoException("Erro na atualizacao de projeto: "
				+ "projeto do tipo PET nao permite despesas de capital");
		super.getDespesas().atualizaDespesas(montanteBolsas, montanteCusteio, montanteCapital);
	}
}