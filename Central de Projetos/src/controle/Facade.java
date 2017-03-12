package controle;

import easyaccept.EasyAccept;
import excecoes.NaoEncontradaException;
import excecoes.ValidacaoException;
import pessoa.PessoaController;

public class Facade {
	private PessoaController pessoaController = new PessoaController();
	
	public void iniciaSistema() {
		
	}
	
	public void cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException {
		pessoaController.cadastraPessoa(cpf, nome, email);
	}
	
	public void editaPessoa(String cpf, String opcao, String nova) throws ValidacaoException, NaoEncontradaException {
		pessoaController.editaPessoa(cpf, opcao, nova);
	}
	
	public void removePessoa(String cpf) throws ValidacaoException, NaoEncontradaException {
		pessoaController.removePessoa(cpf);
	}
	
	public void fechaSistema() {
		
	}
	
	public static void main(String[] args) {
	    args = new String[] {"controle.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us1_test_exception.txt"};
	    EasyAccept.main(args);
	}
}
