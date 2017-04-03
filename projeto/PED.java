package projeto;

import static myUtils.Validacao.*;

import java.util.HashMap;
import java.util.Map;

import excecoes.ValidacaoException;

/**
 * Subclasse de Projeto, possui como caracteristicas
 * adicionais a Projeto uma categoria em que subclassifica os projetos do tipo PED
 * e uma produtividade em 3 aspectos: producao tecnica, producao academica, patentes.
 * 
 * @author Gabriel Fernandes
 */
public class PED extends Projeto {
	private static final long serialVersionUID = 1L;
	private String categoria;
	private Map<Produtividade,Integer> produtividade;
	
	/**
	 * Construtor responsavel por inicializar objetos dessa classe.
	 * 
	 * @param codigo 				recebe o codigo que vai diferenciar cada projeto
	 * @param nome 					nome referente ao projeto
	 * @param categoria 			a que categoria o projeto esta associado
	 * @param prodTecnica 			valor que no projeto sera produzido de producao tecnica
	 * @param prodAcademica 		valor que no projeto sera produzido de producao academica
	 * @param patentes 				valor que no projeto sera produzido de patentes
	 * @param objetivo 				objetivo geral do projeto
	 * @param dataInicio 			data de inicio do projeto
	 * @param duracao 				planejada para o projeto
	 * @throws ValidacaoException 	Lanca excecao caso falhe nas validacoes
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
	
	/**
	 * Acessador de categoria a que projeto esta
	 * ligado.
	 * 
	 * @return 		a categoria em questao
	 */
	public String getCategoria() {
		return this.categoria;
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
	 * Modifica a categoria associada ao PED.
	 * 
	 * @param categoria				nova categoria
	 * @throws ValidacaoException	em caso de categoria invalida
	 */
	public void setCategoria(String categoria) throws ValidacaoException {
		validaCategoria(categoria);
		this.categoria = categoria;
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
	 * para o PED.
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
	 * O projeto do tipo PED pertencer
	 * a determinadas categorias geram algumas limitacoes
	 * que precisam ser verificadas para permitir determinadas
	 * operacoes no sistema.
	 * 
	 * @return
	 */
	public boolean hasPEDLimitacao() {
		if(this.categoria.toLowerCase().equals("pibic") || this.categoria.toLowerCase().equals("pibiti") 
				|| this.categoria.toLowerCase().equals("pivic")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Implementacao do calculo da colaboracao para a uasc,
	 * no qual possui algumas caracteristicas especiais observadas
	 * somente para projetos PED.
	 */
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

	/**
	 * Sobrescrita com a logica necessaria para
	 * projetos do tipo PED
	 * no qual possui diversas caracteristicas singulares dos demais
	 * tipos de projetos.
	 */
	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ValidacaoException {
		if(this.categoria.toLowerCase().equals("pibic") || this.categoria.toLowerCase().equals("pibiti")) {
			if(montanteBolsas == 0) {
				throw new ValidacaoException("Erro na atualizacao de projeto: projeto do tipo "
						+ "P&D - PIBIC ou PIBIT deve permitir despesas de bolsas");
			}
		}
		if(!this.categoria.toLowerCase().equals("coop")) {
			if(montanteCusteio > 0 || montanteCapital > 0) throw new ValidacaoException("Erro na atualizacao de projeto: "
					+ "projeto do tipo P&D - PIBIC ou PIBIT nao permite despesas de custeio ou capital");
		}
		else {
			if(montanteCusteio == 0 || montanteCapital == 0 || montanteBolsas == 0) 
				throw new 
					ValidacaoException("Erro na atualizacao de projeto: projeto do tipo Coop devem possuir todas as despesas");
		}
		if(montanteBolsas == 0) throw new ValidacaoException("Erro na atualizacao de projeto: montante nulo ou vazio");
		super.getDespesas().atualizaDespesas(montanteBolsas, montanteCusteio, montanteCapital);
	}
}