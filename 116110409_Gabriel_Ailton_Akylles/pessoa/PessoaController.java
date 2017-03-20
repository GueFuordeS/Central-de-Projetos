package pessoa;

import java.util.HashSet;
import java.util.Set;

import excecoes.*;
import myUtils.Validacao;

/**
 * 
 * Classe responsavel por controlar as pessoas representadas em nosso sistema
 * 
 * @author Gabriel Fernandes
 *
 */
public class PessoaController {
	private Set<Pessoa> pessoas;
	
	public PessoaController() {
		this.pessoas = new HashSet<>();
	}

	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException {
		Validacao.validaCpf(cpf);
		Validacao.validaNome(nome);
		Validacao.validaEmail(email);	
		for(Pessoa p:pessoas) {
			if(p.getCpf().equals(cpf)) {
				throw new ValidacaoException("Erro no cadastro de pessoa: Pessoa com mesmo CPF ja cadastrada");
			}
		}
		this.pessoas.add(new Pessoa(cpf,nome,email));
		return cpf;
	}
	
	public void removePessoa(String cpf) throws ValidacaoException, NaoEncontradaException {
		Pessoa p = this.recuperaPessoa(cpf);
		pessoas.remove(p);
	}
	
	public Pessoa recuperaPessoa(String cpf) throws NaoEncontradaException, ValidacaoException {
		for(Pessoa p:this.pessoas) {
			if(p.getCpf().equals(cpf)) {
				return p;
			}
		}
		throw new NaoEncontradaException();
	}
	
	public void editaPessoa(String cpf, String opcao, String nova) throws ValidacaoException, NaoEncontradaException {
		Validacao.validaCpfUpdate(cpf);
		Pessoa p = this.recuperaPessoa(cpf);
		
		if(opcao.toLowerCase().equals("nome")) {
			Validacao.validaNomeUpdate(nova);
			p.setNome(nova);
		}
		else if(opcao.toLowerCase().equals("email")) {
			Validacao.validaEmailUpdate(nova);
			p.setEmail(nova);
		}
		
		else if(opcao.toLowerCase().equals("cpf")) { 
			throw new ValidacaoException("Erro na atualizacao de pessoa: CPF nao pode ser alterado");
		}
		
		else throw new ValidacaoException("Opcao especificada nao existe");
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws NaoEncontradaException, ValidacaoException {
		Pessoa p = this.recuperaPessoa(cpf);
		
		if(atributo.toLowerCase().equals("cpf")) {
			return p.getCpf();
		}
		else if(atributo.toLowerCase().equals("nome")) {
			return p.getNome();
		}
		else if(atributo.toLowerCase().equals("email")) {
			return p.getEmail();
		}
		return null;
	}
	
	public String toString() {
		String retorno = "";
		for(Pessoa p:pessoas) {
			retorno += p;
		}
		return retorno;
	}
}