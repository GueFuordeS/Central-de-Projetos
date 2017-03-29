package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

public class Despesa { //classe sem especificacao ainda, ate o user story chegado
	private double montanteBolsas;
	private double montanteCusteio;
	private double montanteCapital;

	public Despesa() {
		
	}
	
	public Despesa(double montanteBolsas, double montanteCusteio, double montanteCapital) {
		this.montanteBolsas = montanteBolsas;
		this.montanteCusteio = montanteCusteio;
		this.montanteCapital = montanteCapital;
	}


	public double getMontanteBolsas() {
		return montanteBolsas;
	}


	public double getMontanteCusteio() {
		return montanteCusteio;
	}


	public double getMontanteCapital() {
		return montanteCapital;
	}
	
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital) throws ValidacaoException {
		validaDouble(montanteBolsas);
		validaDouble(montanteCusteio);
		validaDouble(montanteCapital);
		
		this.montanteBolsas = montanteBolsas;
		this.montanteCusteio = montanteCusteio;
		this.montanteCapital = montanteCapital;
	}
}