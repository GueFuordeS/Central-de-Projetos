package projeto;

import static myUtils.Validacao.*;

import java.io.Serializable;

import excecoes.ValidacaoException;

/**
 * Classe responsavel por gerenciar as dispesas que um projeto
 * possui.
 * 
 * @author Gabriel Fernandes
 */
public class Despesa implements Serializable {
	private static final long serialVersionUID = 1L;
	private double montanteBolsas;
	private double montanteCusteio;
	private double montanteCapital;

	/**
	 * Construtor de classe
	 * (vazio, a permitir instanciacao somente).
	 * 
	 */
	public Despesa() {
		
	}
	
	/**
	 * Construtor opcional da classe
	 * quando se possui as informacoes necessarias durante a criacao
	 * do objeto.
	 * 
	 * @param montanteBolsas	valor das despesas com bolsas
	 * @param montanteCusteio	valor das despesas com custeio
	 * @param montanteCapital	valor das despesas com capital
	 */
	public Despesa(double montanteBolsas, double montanteCusteio, double montanteCapital) {
		this.montanteBolsas = montanteBolsas;
		this.montanteCusteio = montanteCusteio;
		this.montanteCapital = montanteCapital;
	}

	/**
	 * Acessador public do valor referente a
	 * despesas com bolsas.
	 * 
	 * @return	valor da despesa
	 */
	public double getMontanteBolsas() {
		return montanteBolsas;
	}

	/**
	 * Acessador public do valor referente a
	 * despesas com custeio.
	 * 
	 * @return	valor da despesa
	 */
	public double getMontanteCusteio() {
		return montanteCusteio;
	}

	/**
	 * Acessador public do valor referente a
	 * despesas com capital.
	 * 
	 * @return	valor da despesa
	 */
	public double getMontanteCapital() {
		return montanteCapital;
	}
	
	/**
	 * Metodo chave da classe
	 * permite apos o projeto ja possuir uma instancia da classe
	 * Despesa, definir a partir dai os gastos que ele vai ter.
	 * 
	 * @param montanteBolsas		valor referente a bolsas
	 * @param montanteCusteio		valor referente a custeio
	 * @param montanteCapital		valor referente a capital
	 * @throws ValidacaoException	em caso de valor ser invalido
	 */
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital) throws ValidacaoException {
		validaDouble(montanteBolsas);
		validaDouble(montanteCusteio);
		validaDouble(montanteCapital);
		
		this.montanteBolsas = montanteBolsas;
		this.montanteCusteio = montanteCusteio;
		this.montanteCapital = montanteCapital;
	}
}