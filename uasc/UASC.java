package uasc;

import projeto.ProjetoController;

import static myUtils.Validacao.*;

import java.io.Serializable;

import excecoes.ESException;
import excecoes.ValidacaoException;
import participacao.ParticipacaoController;
import pessoa.PessoaController;
import static myUtils.EntradaSaidaDados.*;

public class UASC implements Serializable {
	private static final long serialVersionUID = 1L;
	private double receita;
	private double totalGasto;
	private PessoaController pesController;
	private ProjetoController projController;
	private ParticipacaoController partController;
	
	public UASC(PessoaController pesController, ProjetoController projetoController, ParticipacaoController partController) {
		this.receita = projetoController.calculaColaboracoesUASC();
		this.pesController = pesController; 
		this.projController = projetoController;
		this.partController = partController;
	}
	
	public PessoaController getPessoaController() {
		return this.pesController;
	}
	
	public ProjetoController getProjetoController() {
		return this.projController;
	}
	
	public ParticipacaoController getParticipacaoController() {
		return this.partController;
	}
	
	public double getReceita() {
		receita += projController.calculaColaboracoesUASC();
		return this.receita;
	}
	
	public double getTotalGasto() {
		return this.totalGasto;
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
		totalGasto += valor;
		this.receita -= valor;
	}
	
	public double calculaColaboracaoTotalUASC() {
		return projController.calculaColaboracaoTotalUASC();
	}
	
	public void exportaDadosProjetos() throws ESException, ValidacaoException {
		final String FIM_DE_LINHA = System.lineSeparator();

		String dados = "Cadastro de Projetos: " + projController.getTotalProjetosCadastrados() + " projetos registrados"
						+ FIM_DE_LINHA
						+ FIM_DE_LINHA
		
						+ projController.listaProjetos()
		
						+ "Total de projetos concluidos: " + " " + projController.getTotalProjetosConcluidos()
						+ FIM_DE_LINHA
						+  "Participacao da graduacao: " + "    " + projController.getNumParticipacaoGraduandos()
						+ " (" + projController.getPorcentagemParticipacaoGraduacao() + "%" + ")"
						+ FIM_DE_LINHA
						+  "Participacao da pos-graduacao: " + projController.getNumParticipacaoPosGraduandos()
						+ " (" + projController.getPorcentagemParticipacaoPosGraduacao() + "%" + ")"
						+ FIM_DE_LINHA
						+  "Participacao de profissionais: " + projController.getNumParticipacaoProfissionais()
						+ " (" + projController.getPorcentagemParticipacaoProfissional() + "%" + ")"
						+ FIM_DE_LINHA;

		geraRelatorio("arquivos_sistema/relatorios/cad_projetos.txt", dados);
	}
	
	public void exportaDadosColaboracoes() throws ESException {
		final String FIM_DE_LINHA = System.lineSeparator();
		
		String dados = "Historico das colaboracoes:" + FIM_DE_LINHA
						+ projController.listaColaboracoes()
						+ FIM_DE_LINHA
						+ "Total acumulado com colaboracoes: " + this.calculaColaboracaoTotalUASC()
						+ FIM_DE_LINHA
						+ "Total gasto: " + this.totalGasto
						+ FIM_DE_LINHA
						+ "Total em caixa: " + this.receita
						+ FIM_DE_LINHA;

		geraRelatorio("arquivos_sistema/relatorios/cad_colaboracoes.txt", dados);
	}
}