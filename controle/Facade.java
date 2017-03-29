package controle;

import easyaccept.EasyAccept;
import excecoes.*;
import participacao.ParticipacaoController;
import pessoa.PessoaController;
import projeto.*;
import uasc.UASC;

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
		uasc = new UASC();
	}
	
	public void iniciaSistema() {
		//por implementar
	}
	
	//Aqui comeca a parte de controle de pessoas
	
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

	//Aqui comeca a parte de controle de projetos
	
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
	
	public void atualizaDespesasProjeto(int codigoProjeto, double montanteBolsas, double montanteCusteio, double montanteCapital) 
			throws NaoEncontradaException, ValidacaoException {
		projetoController.atualizaDespesasProjeto(codigoProjeto, montanteBolsas, montanteCusteio, montanteCapital);
	}
	
	public double calculaColaboracaoUASC(int codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		return projetoController.calculaColaboracaoUASC(codigoProjeto);
	}

	//Aqui comeca a parte de controle de participacoes
	
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
	
	public void diminuiReceita(double valor) {
		uasc.diminuiReceita(valor);
	}

	public void fechaSistema() throws NaoEncontradaException, ValidacaoException {
		//por implementar
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