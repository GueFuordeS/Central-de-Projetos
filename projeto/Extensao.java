package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

/**
 * Subclasse de Projeto, possui como caracteristicas
 * adicionais a Projeto o impacto social.
 * 
 * Utiliza da interface Impacto, possibilitando assim a referenciacao
 * por meio da interface.
 * 
 * @author Gabriel Fernandes
 */
public class Extensao extends Projeto implements Impacto {
	private static final long serialVersionUID = 1L;
	private int impacto;
	
	/**
	 * Constroi objeto a partir de informacoes passadas sobre ele 
	 * como paramentro(tambem conhecido como construtor).
	 * 
	 * @param codigo				identificador unico de cada projeto
	 * @param nome					nome do novo projeto
	 * @param objetivo				objetivo para o projeto
	 * @param impacto				impacto na comunidade em que atuara o projeto
	 * @param dataInicio			data de inicio do projeto
	 * @param duracao				duracao planejada para o projeto
	 * @throws ValidacaoException	em caso de dados invalidos
	 */
	public Extensao(int codigo, String nome, String objetivo, int impacto, String dataInicio, int duracao) 
			throws ValidacaoException {
		super(codigo, nome, objetivo, dataInicio, duracao);
		
		validaImpacto(impacto);
		this.impacto = impacto; 
	}
	
	/**
	 * Retorna o impacto social do projeto de extensao em questao
	 * (sobrescrita de metodo da interface Impacto).
	 */
	public int getImpacto() {
		return this.impacto;
	}

	/**
	 * Possibilita alterar o impacto do projeto
	 * (sobrescreve interface).
	 */
	@Override
	public void setImpacto(int impacto) throws ValidacaoException {
		validaImpacto(impacto);
		this.impacto = impacto;
	}

	/**
	 * Implementacao do calculo de colaboracao
	 * para projetos do tipo Extensao.
	 */
	@Override
	public double calculaColaboracao() {
		if(super.getMontanteCusteio() <= 10000 && super.getMontanteCapital() <= 10000) return 0;
		return super.getDespesasTotais() * ((10.0-(this.impacto/2.0))/100);
	}

	/**
	 * Implementacao do atualiza despesas
	 * de acordo com as caracteristicas necessarias
	 * para projetos do tipo Extensao.
	 */
	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ValidacaoException {
		if(montanteCapital > 0) throw new ValidacaoException("Erro na atualizacao de projeto: "
				+ "projeto do tipo Extensao nao permite despesas de capital");
		super.getDespesas().atualizaDespesas(montanteBolsas, montanteCusteio, montanteCapital);
	}
}