package controle;

import easyaccept.EasyAccept;
import excecoes.*;
import participacao.ParticipacaoController;
import pessoa.PessoaController;
import projeto.*;

/**
 * Uso do padrao de projeto "facade" para convergir todo o sistema para esta classe,
 * a fim de ela ser uma porta de entrada para o controle do sistema como um todo
 * (interface com o usuario)
 * 
 * @author Gabriel Fernandes
 */
public class Facade {
	private PessoaController pessoaController = new PessoaController();
	private ProjetoController projetoController = new ProjetoController();
	private ParticipacaoController participacaoController = new ParticipacaoController(pessoaController, projetoController);
	
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
	
	//Aqui comeca a parte de controle de projetos
	
	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo,
			 String periodo, String dataInicio, int duracao) throws ValidacaoException {
		
		return projetoController.adicionaMonitoria(nome, disciplina, rendimento, objetivo,
				 periodo, dataInicio, duracao);
	}
	
	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, 
			int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) throws ValidacaoException {
		
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
	
	public void editaProjeto(int codigo, String atributo, String valor) throws NaoEncontradaException, ValidacaoException {
		projetoController.editaProjeto(codigo, atributo, valor);
	}
	
	// Aqui comeca a parte de associacao de pessoa a projetos, ou seja, a criacao de participacoes
	
	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaProfessor(cpfPessoa, codigoProjeto, coordenador, valorHora, qntHoras);
		
	}
	
	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras)
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaGraduando(cpfPessoa, codigoProjeto, valorHora, qntHoras);
		
	}
	
	public void associaProfissional(String cpfPessoa, String codigoProjeto,String cargo, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaProfissional(cpfPessoa, codigoProjeto, cargo, valorHora, qntHoras);
	}
	
	public void associaPosGraduando(String cpfPessoa, String codigoProjeto, String titulacao, double valorHora, int qntHoras) 
			throws NaoEncontradaException, ValidacaoException{
		
		participacaoController.associaPosGraduando(cpfPessoa, codigoProjeto, titulacao, valorHora, qntHoras);
	}
	
	
	public static void main(String[] args) {
	    args = new String[] {"controle.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us1_test_exception.txt",
	    		"acceptance_test/us2_test.txt", "acceptance_test/us2_test_exception.txt", "acceptance_test/us3_test.txt"};
	    EasyAccept.main(args);
	}
	
	public void fechaSistema() {
		//por implementar
	}
}// fim da classe