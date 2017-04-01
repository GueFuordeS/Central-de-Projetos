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
		String formatado0 = String.format("%2s%5d", "",projController.getTotalProjetosConcluidos());
		String formatado1 = String.format("%5s%5d", "",projController.getNumParticipacaoGraduandos());
		String formatado2 = String.format("%1s%5d", "",projController.getNumParticipacaoPosGraduandos());
		String formatado3 = String.format("%1s%5d", "",projController.getNumParticipacaoProfissionais());
		
		String dados = "Cadastro de Projetos: " + projController.getTotalProjetosCadastrados() + " projetos registrados"
						+ FIM_DE_LINHA
						+ FIM_DE_LINHA
		
						+ projController.listaProjetos()
		
						+ "Total de projetos concluidos:" + formatado0
						+ FIM_DE_LINHA
						+  "Participacao da graduacao:" + formatado1
						+ " (" + projController.getPorcentagemParticipacaoGraduacao() + "%" + ")"
						+ FIM_DE_LINHA
						+  "Participacao da pos-graduacao:" + formatado2
						+ " (" + projController.getPorcentagemParticipacaoPosGraduacao() + "%" + ")"
						+ FIM_DE_LINHA
						+  "Participacao de profissionais:" + formatado3
						+ " (" + projController.getPorcentagemParticipacaoProfissional() + "%" + ")";

		geraRelatorio("arquivos_sistema/relatorios/cad_projetos.txt", dados);
	}
	
	public void exportaDadosColaboracoes() throws ESException {
		final String FIM_DE_LINHA = System.lineSeparator();
		String formatado0 = String.format("%22s%11.1f", "", this.totalGasto);
		String formatado1 = String.format("%19s%11.1f", "", this.receita);
		String formatado2 = String.format("%1s%11.1f", "", this.calculaColaboracaoTotalUASC());
		
		String dados = "Historico das colaboracoes:" + FIM_DE_LINHA
						+ projController.listaColaboracoes()
						+ FIM_DE_LINHA
						+ "Total gasto(R$):" + formatado0
						+ FIM_DE_LINHA
						+ "Total em caixa(R$):" + formatado1
						+ FIM_DE_LINHA
						+ "Total acumulado com colaboracoes(R$):" + formatado2;

		geraRelatorio("arquivos_sistema/relatorios/cad_colaboracoes.txt", dados);
	}
}