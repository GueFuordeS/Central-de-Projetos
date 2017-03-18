package pessoa;

import java.util.HashSet;

import excecoes.ValidacaoException;

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
	
	public void removePath(String cpf) throws ValidacaoException {
		boolean bool = true;
		for(Pessoa p:pessoas) {
			if(p.getCpf().equals(cpf)) {
				pessoas.remove(p);
				bool = false;
			}
		}
		
		if(bool) {
			throw new Exception("Erro na consulta de pessoa: Pessoa nao encontrada");
		}
	}
	
	public String toString() {
		String retorno = "";
		for(Pessoa p:pessoas) {
			retorno += p;
		}
		return retorno;
	}
}
