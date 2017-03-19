package controle;

import easyaccept.EasyAccept;
import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import pessoa.PessoaController;

/**
 * Uso do padrao de projeto "facade" para convergir todo o sistema para esta classe,
 * a fim de ela ser uma porta de entrada para o controle do sistema como um todo
 * (interface com o usuario)
 * 
 * @author Gabriel Fernandes
 */
public class Facade {
	private PessoaController pessoaController = new PessoaController();
	
	public void iniciaSistema() {
		//por implementar
	}
	
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
	
	public void fechaSistema() {
		//por implementar
	}
	
	public static void main(String[] args) {
	    args = new String[] {"controle.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us1_test_exception.txt"};
	    EasyAccept.main(args);
	}
}