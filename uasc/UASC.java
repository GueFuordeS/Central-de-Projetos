package uasc;

import projeto.ProjetoController;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

public class UASC {
	private double receita;
	private ProjetoController projController;
	
	public UASC(ProjetoController projetoController) {
		this.receita = projetoController.calculaColaboracaoTotalUASC();
		this.projController = projetoController;
	}
	
	public double getReceita() {
		return this.receita;
	}
	
	public void diminuiReceita(double valor) throws ValidacaoException {
		validaDouble(valor);
		receita += projController.calculaColaboracaoTotalUASC();
		if(this.receita - valor < 0) throw new ValidacaoException("Erro na atualizacao da receita da unidade: "
				+ "a unidade nao possui fundos suficientes");
		this.receita -= valor;
	}
	
	public double calculaColaboracaoTotalUASC() {
		receita += projController.calculaColaboracaoTotalUASC();
		return receita;
	}
}