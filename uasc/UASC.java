package uasc;

import projeto.ProjetoController;

import static myUtils.Validacao.*;

import java.io.Serializable;

import excecoes.ESException;
import excecoes.ValidacaoException;
import participacao.ParticipacaoController;
import pessoa.PessoaController;
import static myUtils.EntradaSaidaDados.*;

/**
 * Classe responsavel por controle de algumas areas
 * do sistema.
 * 
 * @author Gabriel Fernandes
 */
public class UASC implements Serializable {
	private static final long serialVersionUID = 1L;
	private double receita;
	private double totalGasto;
	private PessoaController pesController;
	private ProjetoController projController;
	private ParticipacaoController partController;
	
	/**
	 * Recebe outros controllers em sua inicializacao,
	 * pelo fato de por meio desta as outras partes do sistema poderem
	 * serem armazenadas em arquivo para conservacao de estado
	 * (um objeto do tipo UASC eh o unico salvo para conservar o estado do sistema, por isso ela possui os
	 * outros).
	 * 
	 * @param pesController			controller de pessoa
	 * @param projetoController		controller de projetos
	 * @param partController		controller de participacoes
	 */
	public UASC(PessoaController pesController, ProjetoController projetoController, ParticipacaoController partController) {
		this.receita = projetoController.calculaColaboracoesUASC();
		this.pesController = pesController; 
		this.projController = projetoController;
		this.partController = partController;
	}
	
	/**
	 * Permite pegar o objeto do tipo PessoaController
	 * a partir de outras partes do sistema
	 * (o pre requisito eh ter a uasc).
	 * 
	 * @return		controller de pessoa
	 */
	public PessoaController getPessoaController() {
		return this.pesController;
	}
	
	/**
	 * Permite pegar o objeto do tipo ProjetController
	 * a partir de outras partes do sistema
	 * (o pre requisito eh ter a uasc).
	 * 
	 * @return		controller de projeto
	 */
	public ProjetoController getProjetoController() {
		return this.projController;
	}
	
	/**
	 * Permite pegar o objeto do tipo ParticipacaoController
	 * a partir de outras partes do sistema
	 * (o pre requisito eh ter a uasc).
	 * 
	 * @return		controller de participacao
	 */
	public ParticipacaoController getParticipacaoController() {
		return this.partController;
	}
	
	/**
	 * Acessa quantidade dinheiro disponivel para uso da uasc.
	 * 
	 * @return		receita da uasc
	 */
	public double getReceita() {
		receita += projController.calculaColaboracoesUASC();
		return this.receita;
	}
	
	/**
	 * Armazena o total de recursos gastos pela uasc ate o dado
	 * momento.
	 * 
	 * @return  	total gasto
	 */
	public double getTotalGasto() {
		return this.totalGasto;
	}
	
	/**
	 * Responsavel por ao os recursos da uasc serem usados,
	 * serem por meio deste metodo subtraido da receita disponivel.
	 * 
	 * @param valor					valor gasto
	 * @throws ValidacaoException	em caso de valor invalido
	 */
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
	
	/**
	 * Calcula todo o valor recebido ate o dado por colaboracoes
	 * provindos de projetos.
	 * 
	 * @return		total de colaboracoes
	 */
	public double calculaColaboracaoTotalUASC() {
		return projController.calculaColaboracaoTotalUASC();
	}
	
	/**
	 * Metodo responsavel por gerar relatorios
	 * sobre os projetos cadastrados no sistema.
	 * Utiliza classe myUtils.EntradaSaidaDados para
	 * evitar excesso de responsabilidade a um metodo so.
	 * 
	 * @throws ESException			em caso de problemas ao acessar arquivo
	 * @throws ValidacaoException	em caso de problemas ao acessar projetos
	 */
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
	
	/**
	 * Metodo responsavel por gerar relatorios
	 * contendo os historicos de colaboracoes por parte
	 * dos projetos a uasc.
	 * Utiliza classe myUtils.EntradaSaidaDados para evitar excesso
	 * de acoes por um metodo so.
	 * 
	 * @throws ESException	em caso de problemas provindos de acoes com entrada e saida
	 */
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