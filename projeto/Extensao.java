package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;

public class Extensao extends Projeto implements Impacto {
	int impacto;
	
	public Extensao(int codigo, String nome, String objetivo, int impacto, String dataInicio, int duracao) 
			throws ValidacaoException {
		super(codigo, nome, objetivo, dataInicio, duracao);
		
		validaImpacto(impacto);
		this.impacto = impacto; 
	}
	
	public int getImpacto() {
		return this.impacto;
	}

	@Override
	public void setImpacto(int impacto) throws ValidacaoException {
		validaImpacto(impacto);
		this.impacto = impacto;
	}

	@Override
	public double calculaColaboracao() {
		if(super.getMontanteCusteio() <= 10000 && super.getMontanteCapital() <= 10000) return 0;
		return super.getDespesasTotais() * ((10.0-(this.impacto/2.0))/100);
	}

	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ValidacaoException {
		if(montanteCapital > 0) throw new ValidacaoException("Erro na atualizacao de projeto: "
				+ "projeto do tipo Extensao nao permite despesas de capital");
		super.getDespesas().atualizaDespesas(montanteBolsas, montanteCusteio, montanteCapital);
	}
}