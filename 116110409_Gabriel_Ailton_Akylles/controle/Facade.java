package controle;

import easyaccept.EasyAccept;
import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
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
	
	public int getCodigoProjeto(String nome) {
		return getCodigoProjeto(nome);
	}
	
	public void fechaSistema() {
		//por implementar
	}
	
	public static void main(String[] args) {
	    args = new String[] {"controle.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us1_test_exception.txt",
	    		"acceptance_test/us2_test.txt", "acceptance_test/us2_test_exception.txt"};
	    EasyAccept.main(args);
	}
}