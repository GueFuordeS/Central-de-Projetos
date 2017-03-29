package projeto;

import static myUtils.Validacao.*;

import excecoes.ValidacaoException;
import myUtils.Periodo;

public class Monitoria extends Projeto implements Rendimento {
	String disciplina;
	Periodo periodo;
	int rendimento;
	
	public Monitoria(int codigo, String nome, String disciplina, int rendimento, String objetivo,
		 String periodo, String dataInicio, int duracao) throws ValidacaoException {
		super(codigo, nome, objetivo, dataInicio, duracao);
		
		validaNomeDisciplina(disciplina);
		validaRendimento(rendimento);
		validaPeriodo(periodo);
		this.disciplina = disciplina;
		this.rendimento = rendimento;
		this.periodo = new Periodo(periodo);
	}
	
	public String getDisciplina() {
		return this.disciplina;
	}
	
	public String getPeriodo() {
		return this.periodo.toString();
	}
	
	public int getRendimento() {
		return this.rendimento;
	}
	
	public void setDisciplina(String disciplina) throws ValidacaoException {
		validaNomeDisciplina(disciplina);
		this.disciplina = disciplina;
	}
	
	public void setPeriodo(String periodo) throws ValidacaoException {
		validaPeriodo(periodo);
		this.periodo = new Periodo(periodo);
	}
	
	public void setRendimento(int rendimento) throws ValidacaoException {
		validaRendimento(rendimento);
		this.rendimento = rendimento;
	}

	@Override
	public double calculaColaboracao() {
		return 0;
	}

	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws ValidacaoException {
		if(montanteCusteio > 0 || montanteCapital > 0) throw new ValidacaoException("Erro na atualizacao de projeto: "
				+ "projeto do tipo monitoria nao permite despesas de custeio ou capital");
		super.getDespesas().atualizaDespesas(montanteBolsas, montanteCusteio, montanteCapital);
		
	}
}