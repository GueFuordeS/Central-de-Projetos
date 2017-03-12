package pessoa;

import java.util.HashSet;

import excecoes.*;

public class PessoaController {
	private HashSet<Pessoa> pessoas;
	
	public PessoaController() {
		this.pessoas = new HashSet<>();
	}

	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException {
			Pessoa p = new Pessoa(cpf,nome,email);
			this.pessoas.add(p);
			return p.getCpf();
	}
	
	public void removePessoa(String cpf) throws ValidacaoException, NaoEncontradaException {
		Pessoa p = this.recuperaPessoa(cpf);
		pessoas.remove(p);
	}
	
	public Pessoa recuperaPessoa(String cpf) throws NaoEncontradaException, ValidacaoException {
		Validacao.validaCpfUpdate(cpf);
		for(Pessoa p:pessoas) {
			if(p.getCpf().equals(cpf)) {
				return p;
			}
		}
		throw new NaoEncontradaException();
	}
	
	public void editaPessoa(String cpf, String opcao, String nova) throws ValidacaoException, NaoEncontradaException {
	
		
		Pessoa p = this.recuperaPessoa(cpf);
		if(opcao.toLowerCase().equals("nome")) {
			p.setNome(nova);
		}
		else if(opcao.toLowerCase().equals("email")) {
			p.setEmail(nova);
		}
		
		else if(opcao.toLowerCase().equals("cpf")) { 
			throw new ValidacaoException("Erro na atualizacao de pessoa: CPF nao pode ser alterado");
	}
		
		else throw new ValidacaoException("Opcao especificada nao existe");
	}
	
	public String toString() {
		String retorno = "";
		for(Pessoa p:pessoas) {
			retorno += p;
		}
		return retorno;
	}
}