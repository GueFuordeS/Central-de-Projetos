package uasc;

import projeto.ProjetoController;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

public class UASC {
	private double receita;
	private ProjetoController projController;
	
	public UASC(ProjetoController projetoController) {
		this.receita = projetoController.calculaColaboracoesUASC();
		this.projController = projetoController;
	}
	
	public double getReceita() {
		receita += projController.calculaColaboracoesUASC();
		return this.receita;
	}
	
	public void diminuiReceita(double valor) throws ValidacaoException {
		try {
			validaDouble(valor);
		}
		catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na atualizacao da receita da unidade: valor negativo");
		}
			
		receita += projController.calculaColaboracoesUASC();
		if(this.receita - valor < 0) throw new ValidacaoException("Erro na atualizacao da receita da unidade: "
				+ "a unidade nao possui fundos suficientes");
		this.receita -= valor;
	}
	
	public double calculaColaboracaoTotalUASC() {
		return projController.calculaColaboracaoTotalUASC();
	}
}