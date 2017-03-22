package pessoa;

import java.util.HashSet;
import java.util.Set;

import excecoes.ValidacaoException;
import myUtils.Validacao;
import participacao.*;

/** Classe responsavel por moldar as caracteristicas de uma pessoa, tornando-a interativa em nosso
 * sistema.
 * 
 * @author Gabriel Fernandes
 * 
 */
public class Pessoa {
	private String cpf;
	private String nome;
	private String email;
	private Set<Participacao> participacoes;
	
	/**
	 * O Construtor abaixo ira validar o cpf, nome e email da pessoa cadastrada.
	 * @param cpf String - Cpf da pessoa.
	 * @param nome String - Nome da pessoa.
	 * @param email String - Email da pessoa.
	 * @throws ValidacaoException Ira validar os "param" cpf, nome e email.
	 */
	public Pessoa(String cpf, String nome, String email) throws ValidacaoException {
		Validacao.validaCpf(cpf);
		Validacao.validaString(nome);
		Validacao.validaEmail(email);
		
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		participacoes = new HashSet<>();
	}
	
	public void adicionaPartcicipacao(Participacao participacao){
		this.participacoes.add(participacao);
	}
	
	public Set<Participacao> getParticipacoes(){
		return this.participacoes;
	}
	
	public void removeParticipacao(int codigoProjeto) {
		for(Participacao p:participacoes) {
			if(p.getProjeto().getCodigo() == codigoProjeto) {
				participacoes.remove(p);
			}
		}
	}
	
	/** Metodo que ira retornar o nome da pessoa.
	 * 
	 * @return String - Nome da pessoa.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/** Metodo que ira retornar o cpf da pessoa.
	 * 
	 * @return String - Cpf da pessoa.
	 */
	
	public String getCpf() {
		return this.cpf;
	}
	
	/** Metodo que ira retornar o email da pessoa.
	 * 
	 * @return String - Email da pessoa.
	 */
	
	public String getEmail() {
		return this.email;
	}
	
	/** Metodo que mudara o nome da pessoa.
	 * 
	 * @param nome String - Nome da pessoa.
	 * @throws ValidacaoException - Validara a String nome.
	 */
	
	public void setNome(String nome) throws ValidacaoException {
		Validacao.validaNome(nome);
		this.nome = nome;
	}
	
	/** Metodo que mudara o email da pessoa.
	 * 
	 * @param email - Email da pessoa.
	 * @throws ValidacaoException - Validara a String email.
	 */
	
	public void setEmail(String email) throws ValidacaoException {
		Validacao.validaEmail(email);
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final String FIM_DE_LINHA = System.lineSeparator();
		return "Cpf: " + cpf + FIM_DE_LINHA + "Nome: " + nome + FIM_DE_LINHA + "Email: " + email
				+ FIM_DE_LINHA;
	}
}