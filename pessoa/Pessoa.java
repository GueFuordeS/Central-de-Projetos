package pessoa;

import java.io.Serializable;
import java.util.ArrayList;

import excecoes.ValidacaoException;
import participacao.*;
import projeto.*;
import static myUtils.Validacao.*;

/** 
 * Classe responsavel por moldar as caracteristicas de uma pessoa, tornando-a interativa em nosso
 * sistema.
 * 
 * @author Gabriel Fernandes
 * 
 */
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cpf;
	private String nome;
	private String email;
	private ArrayList<Participacao> participacoes;
	
	/**
	 * Construtor de Pessoa.
	 *
	 * @param cpf String  			cpf da pessoa
	 * @param nome String 	 		nome da pessoa
	 * @param email String  		email da pessoa
	 * @throws ValidacaoException 	ira validar os "param" cpf, nome e email
	 */
	public Pessoa(String cpf, String nome, String email) throws ValidacaoException {
		validaCpf(cpf);
		validaString(nome);
		validaEmail(email);
		
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		participacoes = new ArrayList<>();
	}
	
	/**
	 * Responsavel por receber as participacoes que a pessoa em si esta sendo cadastrada
	 * e armazena uma copia destas nesta lista, que seria o historico de participacoes que cada pessoa
	 * possui.
	 * 
	 * @param participacao 			a participacao em questao
	 * @throws ValidacaoException 	caso a pessoa ja possua uma previa participacao no projeto em questao
	 */
	public void adicionaPartcicipacao(Participacao participacao) throws ValidacaoException {
		for(Participacao p:this.participacoes) {
			if(p.getProjeto().equals(participacao.getProjeto())) {
				throw new ValidacaoException("Erro na associacao de pessoa a projeto: Aluno ja esta cadastrado nesse projeto");
			}
		}
		this.participacoes.add(participacao);
	}
	
	/**
	 * Metodo get das participacoes da pessoa.
	 * 
	 * @return 		o arrayList contendo as participacoes
	 */
	public ArrayList<Participacao> getParticipacoes(){
		return this.participacoes;
	}
	
	public void removeParticipacao(int codigoProjeto) {
		boolean hasParticipacao = false;
		Participacao participacao = null;
		for(Participacao p:this.participacoes) {
			if(p.getProjeto().getCodigo() == codigoProjeto) {
				hasParticipacao = true;
				participacao = p;
			}
		}
		if(hasParticipacao==true && participacao!=null) {
			participacoes.remove(participacao);
		}
	}
	
	/** 
	 * Metodo que ira retornar o nome da pessoa.
	 * 
	 * @return String 	 nome da pessoa
	 */
	public String getNome() {
		return this.nome;
	}
	
	/** 
	 * Metodo que ira retornar o cpf da pessoa.
	 * 
	 * @return String 	 cpf da pessoa
	 */
	public String getCpf() {
		return this.cpf;
	}
	
	/** 
	 * Metodo que ira retornar o email da pessoa.
	 * 
	 * @return String 	 email da pessoa
	 */
	public String getEmail() {
		return this.email;
	}
	
	/** 
	 * Metodo que mudara o nome da pessoa.
	 * 
	 * @param nome 					string Nome da pessoa
	 * @throws ValidacaoException 	validara a String nome
	 */
	public void setNome(String nome) throws ValidacaoException {
		validaNome(nome);
		this.nome = nome;
	}
	
	/** 
	 * Metodo que mudara o email da pessoa.
	 * 
	 * @param email  				email da pessoa
	 * @throws ValidacaoException 	validara a String email
	 */
	public void setEmail(String email) throws ValidacaoException {
		validaEmail(email);
		this.email = email;
	}

	/**
	 * Responsavel por certificar se a pessoa ja participa do projeto.
	 * 
	 * @param nomeProjeto 	projeto em questao
	 * @return				true para se pessoa ja possui participacao naquele projeto ou false para nao
	 */
	public boolean hasParticipacao(String nomeProjeto) {
		for(Participacao p:this.participacoes) {
			if(p.getProjeto().getNome().equals(nomeProjeto)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Overloading do metodo hasParticipacao, neste usamos a propria participacao
	 * pra checar.
	 * 
	 * @param participacao 	objeto da classe Participacao
	 * @return				true para se ja tiver a participacao, false para nao havendo
	 */
	public boolean hasParticipacao(Participacao participacao) {
		for(Participacao p:this.participacoes) {
			if(participacao.equals(p)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sobrescrita do hashCode padrao.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	/**
	 * Sobrescrita do equals.
	 */
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

	/**
	 * Sobrescrita do toString padrao da Object.
	 */
	@Override
	public String toString() {
		final String FIM_DE_LINHA = System.lineSeparator();
		return "Cpf: " + cpf + FIM_DE_LINHA + "Nome: " + nome + FIM_DE_LINHA + "Email: " + email
				+ FIM_DE_LINHA;
	}

	/*
	 * Achei preferivel implementar a logica desse metodo localmente, em vez de usar polimorfismo por duas razoes:
	 * 
	 * >> primeira: nao eh todos os subtipos de participacao que geram pontos, pos-graduacao nao gera nenhum
	 * (apesar de nele a implementacao pudesse retornar somente um zero), alem de na especificacao citar o fato de que
	 * pessoa sabe calcular seus pontos, levei como um easter egg, poderia também implementar uma interface "Pontuavel", na qual
	 * apenas os com capacidade de gerar pontuacao implementasse, mas aqui volto para o ponto anterior, apesar de ser uma boa saida.
	 * 
	 * >> segunda: a necessidade de verificacao de que em projetos de certo tipo voce nao atingiu a meta, na qual
	 * há a necessidade de somente verificar isso apos ter todos os pontos provindos daquele tipo de projeto, com essa maneira(calculo
	 * local), eh facil uma saida para esse problema(eventualmente usando polimorfismo tem uma saida tambem, mas preferi simplificar).
	 */
	/**
	 * Gera a pontuacao total da pessoa, por meio dos projetos em que ela
	 * vinher a participar.
	 * 
	 * @return 	pontuacao total acumulada
	 */
	public double calculaPontuacaoPorParticipacao() {
		double pontuacao = 0;
		double pontosMonitoria = 0;
		double pontosExtras = 0;

		for(Participacao p:this.participacoes) {
			if(p instanceof ParticipacaoGraduando) {
				if(p.getProjeto() instanceof Monitoria) {
					pontosMonitoria += (p.getProjeto().getDuracao() / 6) * 1.5;
				}
				else {
					pontosExtras += (p.getProjeto().getDuracao() / 6) * 2;
				}
			}
			else if(p instanceof ParticipacaoProfessor) {
				if(p.getProjeto() instanceof Monitoria) {
					pontosMonitoria += (p.getProjeto().getDuracao() / 12) * 4;
				}
				else {
					pontuacao += (p.getProjeto().getDuracao() / 12) * 4;
					pontuacao += p.getProjeto().getNumGraduandos();
				}
			}
			else if(p instanceof ParticipacaoProfissional) {
				if(p.getProjeto() instanceof Extensao) {
					pontuacao += p.getProjeto().getDuracao() / 3.0;
				}
				else if(p.getProjeto() instanceof PED) {
					if(((ParticipacaoProfissional) p).getCargo().toLowerCase().equals("desenvolvedor")) {
						pontuacao += (p.getProjeto().getDuracao() / 12) * 5;
					}
					else if(((ParticipacaoProfissional) p).getCargo().toLowerCase().equals("pesquisador")) {
						pontuacao += (p.getProjeto().getDuracao() / 12) * 6;
					}
					else if(((ParticipacaoProfissional) p).getCargo().toLowerCase().equals("gerente")) {
						pontuacao += (p.getProjeto().getDuracao() / 12) * 9;
					}
				}
			}
		}
		
		if(pontosMonitoria > 6) pontosMonitoria = 6;
		if(pontosExtras > 8) pontosExtras = 8;	
		
		pontuacao += pontosMonitoria + pontosExtras;
		return pontuacao;
	}

	/**
	 * Metodo que utiliza de uma chamada polimorfica para determinar o valor total da bolsa
	 * da pessoa em questao, esse esse valor eh calculado dependendo de quais tipos
	 * de participacao essa pessoa tiver.
	 * 
	 * @return   valor do somatorio de todas as bolsas que essa pessoa recebe
	 */
	public double getInfoBolsa() {
		double bolsa = 0;
		for(Participacao p:this.participacoes) {
			bolsa += p.geraBolsa();
		}
		return bolsa;
	}
}