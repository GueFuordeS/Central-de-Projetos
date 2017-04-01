package uasc;

import projeto.ProjetoController;

import static myUtils.Validacao.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import excecoes.ValidacaoException;

public class UASC {
	private double receita;
	private double totalGasto;
	private ProjetoController projController;
	
	public UASC(ProjetoController projetoController) {
		this.receita = projetoController.calculaColaboracoesUASC();
		this.projController = projetoController;
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
	
	public void exportaDadosProjetos() throws ValidacaoException, IOException {
		final String FIM_DE_LINHA = System.lineSeparator();

		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		
		try {
			fileWriter = new FileWriter("arquivos_sistema/relatorios/cad_projetos.txt");
			printWriter = new PrintWriter(fileWriter);
		}
		catch(IOException e) {
			throw new IOException("erro ao estabelecer conexao com pasta de saida");
		}
		
		
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
		
		if(printWriter != null) {
			printWriter.print(dados);
		}
		printWriter.close();
	}
	
	public void exportaDadosColaboracoes() throws IOException {
		final String FIM_DE_LINHA = System.lineSeparator();
		
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		
		try {
			fileWriter = new FileWriter("arquivos_sistema/relatorios/cad_colaboracoes.txt");
			printWriter = new PrintWriter(fileWriter);
		}
		catch(IOException e) {
			throw new IOException("erro ao estabelecer conexao com pasta de saida");
		}
		
		String dados = "Historico das colaboracoes:" + FIM_DE_LINHA
						+ projController.listaColaboracoes()
						+ FIM_DE_LINHA
						+ "Total acumulado com colaboracoes: " + this.calculaColaboracaoTotalUASC()
						+ FIM_DE_LINHA
						+ "Total gasto: " + this.totalGasto
						+ FIM_DE_LINHA
						+ "Total em caixa: " + this.receita
						+ FIM_DE_LINHA;
		
		if(printWriter != null) {
			printWriter.print(dados);
		}
		printWriter.close();
	}
}