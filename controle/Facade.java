package controle;

import java.io.IOException;
import easyaccept.EasyAccept;
import excecoes.*;
import participacao.ParticipacaoController;
import pessoa.PessoaController;
import projeto.*;
import uasc.UASC;
import static myUtils.EntradaSaidaDados.*;

/**
 * Uso do padrao de projeto "facade" para convergir todo o sistema para esta classe,
 * a fim de ela ser uma porta de entrada para o controle do sistema como um todo
 * (interface com o usuario)
 * 
 * @author Gabriel Fernandes
 */
public class Facade {
	private PessoaController pessoaController;
	private ProjetoController projetoController;
	private ParticipacaoController participacaoController;
	private UASC uasc;

	public Facade() throws ValidacaoException {
		pessoaController = new PessoaController();
		projetoController = new ProjetoController();
		participacaoController = new ParticipacaoController(pessoaController, projetoController);
		uasc = new UASC(pessoaController, projetoController, participacaoController);
	}
	
	public void iniciaSistema() throws ClassNotFoundException, IOException {
		try {
		this.uasc = (UASC)leObjeto("arquivos_sistema/cpc_ufcg.dat");
		this.pessoaController = uasc.getPessoaController();
		this.projetoController = uasc.getProjetoController();
		this.participacaoController = uasc.getParticipacaoController();
		}
		catch(ClassNotFoundException | IOException e) {
			
		}
	}
	
// 	######### CONTROLE DE PESSOAS #########
	
	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException {
		return pessoaController.cadastraPessoa(cpf, nome, email);
	}
	
	public void editaPessoa(String cpf, String opcao, String nova) throws ValidacaoException, NaoEncontradaException {
		pessoaController.editaPessoa(cpf, opcao, nova);
	}
	
	public void removePessoa(String cpf) throws ValidacaoException, NaoEncontradaException {
		pessoaController.removePessoa(cpf);
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws NaoEncontradaException, ValidacaoException {
		return pessoaController.getInfoPessoa(cpf, atributo);
	}
	
	public double calculaPontuacaoPorParticipacao(String cpfPessoa) throws NaoEncontradaException, ValidacaoException {
		return pessoaController.calculaPontuacaoPorParticipacao(cpfPessoa);
	}
	
	public double getValorBolsa(String cpfPessoa) throws NaoEncontradaException, ValidacaoException {
		return pessoaController.getValorBolsa(cpfPessoa);
	}

// 	######### CONTROLE DE PROJETOS #########
	
	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo,
			 String periodo, String dataInicio, int duracao) throws ValidacaoException {
		
		return projetoController.adicionaMonitoria(nome, disciplina, rendimento, objetivo,
				 periodo, dataInicio, duracao);
	}
	
	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, 
			int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) 
					throws ValidacaoException {
		
		return projetoController.adicionaPET(nome, objetivo, impacto, rendimento, 
				 prodTecnica, prodAcademica, patentes, dataInicio, duracao);
	}
	
	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) 
			throws ValidacaoException {
		
		return projetoController.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}
	
	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, 
			String objetivo, String dataInicio, int duracao) throws ValidacaoException {
		
		return projetoController.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, 
				objetivo, dataInicio, duracao);
	}
	
	public String getInfoProjeto(int codigo, String atributo) throws NaoEncontradaException, ValidacaoException {
		return projetoController.getInfoProjeto(codigo, atributo);
	}
	
	public int getCodigoProjeto(String nome) throws NaoEncontradaException, ValidacaoException {
		return projetoController.getCodigoProjeto(nome);
	}
	
	public void removeProjeto(int codigo) throws NaoEncontradaException, ValidacaoException {
		projetoController.removeProjeto(codigo);
	}
	
	public void editaProjeto(int codigo, String atributo, String valor) throws NaoEncontradaException, 
	ValidacaoException {
		projetoController.editaProjeto(codigo, atributo, valor);
	}

	public void atualizaDespesasProjeto(String codigoProjeto, double montanteBolsas, double montanteCusteio, double montanteCapital) 
			throws NaoEncontradaException, ValidacaoException {
		if(codigoProjeto == null || codigoProjeto.trim().isEmpty()) throw new ValidacaoException("Erro na "
				+ "atualizacao de projeto: codigo nulo ou vazio");
		projetoController.atualizaDespesasProjeto(Integer.parseInt(codigoProjeto), montanteBolsas, montanteCusteio, montanteCapital);
	}

	public double calculaColaboracaoUASC(String codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		if(codigoProjeto == null || codigoProjeto.trim().isEmpty()) throw new ValidacaoException("Erro na "
				+ "consulta de projeto: codigo nulo ou vazio");
		return projetoController.calculaColaboracaoUASC(Integer.parseInt(codigoProjeto));
	}

// 	######### CONTROLE DE PARTICIPACOES #########
	
	public void associaProfessor(String cpfPessoa, int codigoProjeto, boolean coordenador, 
			double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaProfessor(cpfPessoa, codigoProjeto, coordenador, valorHora, qntHoras);
	}
	
	public void associaGraduando(String cpfPessoa, int codigoProjeto, double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaGraduando(cpfPessoa, codigoProjeto, valorHora, qntHoras);
	}
	
	public void associaProfissional(String cpfPessoa, int codigoProjeto,String cargo, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaProfissional(cpfPessoa, codigoProjeto, cargo, valorHora, qntHoras);
	}
	
	public void associaPosGraduando(String cpfPessoa, int codigoProjeto, String titulacao, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaPosGraduando(cpfPessoa, codigoProjeto, titulacao, valorHora, qntHoras);
	}

	public void removeParticipacao(String cpfPessoa, int codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		participacaoController.removeParticipacao(cpfPessoa, codigoProjeto);
	}
	
// 	######### UASC #########
	
	public void diminuiReceita(double valor) throws ValidacaoException {
		uasc.diminuiReceita(valor);
	}
	
	public double calculaColaboracaoTotalUASC() {
		return uasc.calculaColaboracaoTotalUASC();
	}

	public double calculaTotalEmCaixaUASC() {
		return uasc.getReceita();
	}
	
	public void exportaDadosProjetos() throws ESException, ValidacaoException {
		uasc.exportaDadosProjetos();
	}
	
	public void exportaDadosColaboracoes() throws ESException {
		uasc.exportaDadosColaboracoes();
	}
	
	public void fechaSistema() throws ValidacaoException, IOException  {
		uasc.exportaDadosProjetos();
		uasc.exportaDadosColaboracoes();
		escreveObjeto("arquivos_sistema/cpc_ufcg.dat", uasc);
	}

	public static void main(String[] args) {
	    args = new String[] {"controle.Facade", 
	    		"acceptance_test/us1_test.txt", 
	    		"acceptance_test/us1_test_exception.txt",
	    		"acceptance_test/us2_test.txt",
	    		"acceptance_test/us2_test_exception.txt", 
	    		"acceptance_test/us3_test.txt",
	    		"acceptance_test/us3_test_exception.txt",
	    		"acceptance_test/us4_test.txt",
	    		"acceptance_test/us5_test.txt",
	    		"acceptance_test/us6_test.txt",
	    		"acceptance_test/us6_test_exception.txt"};
	    EasyAccept.main(args);
	}
}