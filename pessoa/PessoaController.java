package pessoa;

import java.io.Serializable;
import java.util.ArrayList;
import excecoes.*;
import participacao.Participacao;
import static myUtils.Validacao.*;

/**
 * 
 * Classe responsavel por controlar as pessoas representadas em nosso sistema.
 * 
 * @author Gabriel Fernandes
 *
 */
public class PessoaController implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Pessoa> pessoas;
	
	/**
	 * Inicializa classe, instanciando arrayList.
	 */
	public PessoaController() {
		this.pessoas = new ArrayList<>();
	}

	/**
	 * Realiza cadastro de pessoas, armazena pessoas cadastradas
	 * na unidade.
	 * 
	 * @param cpf 					cpf da nova pessoa
	 * @param nome					nome referente a pessoa
	 * @param email					email da pessoa
	 * @return						cpf do cadastrado
	 * @throws ValidacaoException	caso eventualmente algum dado seja invalido
	 */
	public String cadastraPessoa(String cpf, String nome, String email) throws ValidacaoException {
		validaCpf(cpf);
		validaNome(nome);
		validaEmail(email);	
		for(Pessoa p:pessoas) {
			if(p.getCpf().equals(cpf)) {
				throw new ValidacaoException("Erro no cadastro de pessoa: Pessoa com mesmo CPF ja cadastrada");
			}
		}
		this.pessoas.add(new Pessoa(cpf,nome,email));
		return cpf;
	}
	
	/**
	 * Remove do banco de dados interno de pessoas.
	 * 
	 * @param cpf						cpf da pessoa na qual deseja remover
	 * @throws ValidacaoException		em caso de cpf ser invalido
	 * @throws NaoEncontradaException	em caso de pessoa nao estar previamente cadastrada
	 */
	public void removePessoa(String cpf) throws ValidacaoException, NaoEncontradaException {
		Pessoa p = this.recuperaPessoa(cpf);
		pessoas.remove(p);
	}
	
	/**
	 * Recupera uma pessoa, a partir do cpf, que esteja presente entre os
	 * cadastrados no PessoaController.
	 * 
	 * @param cpf						cpf da pessoa	
	 * @return							objeto desejado do tipo Pessoa
	 * @throws NaoEncontradaException	em caso de nao haver essa pessoa nos cadastros
	 * @throws ValidacaoException		em caso de cpf ser invalido
	 */
	public Pessoa recuperaPessoa(String cpf) throws NaoEncontradaException, ValidacaoException {
		validaCpf(cpf);
		for(Pessoa p:this.pessoas) {
			if(p.getCpf().equals(cpf)) {
				return p;
			}
		}
		throw new NaoEncontradaException();
	}
	
	/**
	 * Atualiza informacao desejada(passada como parametro), do
	 * usuario na qual deseja atualizar.
	 * 
	 * @param cpf						cpf da pessoa na qual se deseja atualizar
	 * @param opcao						dado em que se deseja atualizar
	 * @param nova						novo dado para substituir o anterior
	 * @throws ValidacaoException		em caso de algum dado ser invalido
	 * @throws NaoEncontradaException	em caso de pessoa nao estiver previamente cadastrada
	 */
	public void editaPessoa(String cpf, String opcao, String nova) throws ValidacaoException, NaoEncontradaException {
		validaCpfUpdate(cpf);
		Pessoa p = this.recuperaPessoa(cpf);
		
		if(opcao.toLowerCase().equals("nome")) {
			validaNomeUpdate(nova);
			p.setNome(nova);
		}
		else if(opcao.toLowerCase().equals("email")) {
			validaEmailUpdate(nova);
			p.setEmail(nova);
		}
		
		else if(opcao.toLowerCase().equals("cpf")) { 
			throw new ValidacaoException("Erro na atualizacao de pessoa: CPF nao pode ser alterado");
		}
		
		else throw new ValidacaoException("Opcao especificada nao existe");
	}
	
	/**
	 * Recupera informacao especifica sobre uma pessoa
	 * cadastrada.
	 * 
	 * @param cpf						cpf da pessoa em especifico
	 * @param atributo					atributo no qual se deseja a informacao
	 * @return							o atributo desejado
	 * @throws NaoEncontradaException	no caso de pessoa nao estiver previamente cadastrada
	 * @throws ValidacaoException		no caso de atributo nao for um entre os disponiveis
	 */
	public String getInfoPessoa(String cpf, String atributo) throws NaoEncontradaException, ValidacaoException {
		Pessoa pessoa = this.recuperaPessoa(cpf);
		
		if(atributo.toLowerCase().equals("cpf")) {
			return pessoa.getCpf();
		}
		else if(atributo.toLowerCase().equals("nome")) {
			return pessoa.getNome();
		}
		else if(atributo.toLowerCase().equals("email")) {
			return pessoa.getEmail();
		}else if(atributo.toLowerCase().equals("participacoes")){
			ArrayList<Participacao> participacoes = pessoa.getParticipacoes();
			String participacoesRetorno = "";
			
			for (int i = 0; i < participacoes.size(); i++) {
				if(i<participacoes.size()-1)
					participacoesRetorno += participacoes.get(i).getProjeto().getNome() + ", ";
				else { 
					participacoesRetorno += participacoes.get(i).getProjeto().getNome();
				}
			}
			return participacoesRetorno;
		}
		return null;
	}
	
	/**
	 * Confere se pessoa passada por meio de cpf(para se buscar essa pessoa entre as ja cadastradas)
	 * possui participacao no projeto descrito pelo nome dele.
	 * 
	 * @param cpf			pessoa envolvida
	 * @param nomeProjeto	projeto desejado
	 * @return				true se participar, false para nao participa
	 */
	public boolean hasParticipacao(String cpf, String nomeProjeto) {
		for(Pessoa p:pessoas) {
			if(p.getCpf().equals(cpf)) {
				return p.hasParticipacao(nomeProjeto);
			}
		}
		return false;
	}
	
	/**
	 * Sobrescrita padrao.
	 */
	public String toString() {
		String retorno = "";
		for(Pessoa p:pessoas) {
			retorno += p;
		}
		return retorno;
	}
	
	/**
	 * Sobrescrita padrao.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pessoas == null) ? 0 : pessoas.hashCode());
		return result;
	}

	/**
	 * Sobrescrita padrao.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaController other = (PessoaController) obj;
		if (pessoas == null) {
			if (other.pessoas != null)
				return false;
		} else if (!pessoas.equals(other.pessoas))
			return false;
		return true;
	}
	/**
	 * Adciona participacao de projeto em uma pessoa especifica.
	 * 
	 * @param cpfPessoa					cpf da pessoa
	 * @param participacao				associacao entre pessoa e projeto
	 * @throws NaoEncontradaException	em caso de pessoa nao estiver cadastrada
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void addParticipacao(String cpfPessoa, Participacao participacao) throws NaoEncontradaException, ValidacaoException {
		Pessoa p = null;
		try {
			p = this.recuperaPessoa(cpfPessoa);
		}
		catch(NaoEncontradaException e) {
			throw new NaoEncontradaException("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		if(p != null) p.adicionaPartcicipacao(participacao);
	}

	/**
	 * Remove participacao de pessoa em um projeto.
	 * 
	 * @param cpfPessoa					identificacao da pessoa
	 * @param codigoProjeto				identificacao do projeto
	 * @throws NaoEncontradaException	em caso de pessoa nao estiver cadastrada
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public void removeParticipacao(String cpfPessoa, int codigoProjeto) throws NaoEncontradaException, ValidacaoException {
		Pessoa p = this.recuperaPessoa(cpfPessoa);
		p.removeParticipacao(codigoProjeto);
	}

	/**
	 * Metodo delegador do calculo de pontuacao das participacoes de uma pessoa
	 * em projetos.
	 * 
	 * @param cpf						cpf da pessoa
	 * @return							a pontuacao adquirida por essa pessoa
	 * @throws NaoEncontradaException	em caso de pessoa nao estiver cadastrada
	 * @throws ValidacaoException		em caso de dados invalidos
	 */
	public double calculaPontuacaoPorParticipacao(String cpf) throws NaoEncontradaException, ValidacaoException {
		Pessoa p = this.recuperaPessoa(cpf);
		return p.calculaPontuacaoPorParticipacao();
	}

	/**
	 * Metodo delegador do calculo do valor que pessoa recebe
	 * provenientes de bolsas em projetos.
	 * 
	 * @param cpf						identificacao da pessoa
	 * @return							valor total de bolsas que pessoa em especifico recebe
	 * @throws NaoEncontradaException	em caso de pessoa nao for cadastrada previamente
	 * @throws ValidacaoException		em caso de dados forem invalidos
	 */
	public double getValorBolsa(String cpf) throws NaoEncontradaException, ValidacaoException {
		Pessoa p = this.recuperaPessoa(cpf);
		return p.getInfoBolsa();
	}
}