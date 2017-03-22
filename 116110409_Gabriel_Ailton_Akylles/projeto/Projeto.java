package projeto;

import static myUtils.Validacao.*;

import java.util.ArrayList;

import excecoes.ValidacaoException;
import myUtils.Date;
import participacao.Participacao;

/**
 * 
 * Classe responsavel por criar uma forma geral para projetos, uma abstracao para suas subclasses.
 * 
 * @author Gabriel Fernandes
 *
 */

public abstract class Projeto {
	private int codigo;
	private String nome;
	private String objetivo;
	private Date dataInicio;
	private int duracao;
	private ArrayList<Participacao> participacoes;
	
	/** 
	 * Construtor de classe
	 * 
	 * @param nome - String nome do projeto.
	 * @param objetivo - String objetivo do projeto.
	 * @param dataInicio - String data de inicio do projeto.
	 * @param duracao - int duracao do projeto.
	 * @throws ValidacaoException - Ira validar os "param" nome, objetivo, dataInicio e duracao.
	 */
	public Projeto(int codigo, String nome, String objetivo, String dataInicio, int duracao) throws ValidacaoException {
		
		validaNomeProjeto(nome);
		validaObjetivo(objetivo);
		validaDuracao(duracao);
		
		this.codigo = codigo;
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = new Date(dataInicio);
		this.duracao = duracao;
		participacoes = new ArrayList<>();
	}
	
	/**
	 * Acessador de codigo
	 * 
	 * @return inteiro com o codigo do projeto
	 */
	public int getCodigo() {
		return this.codigo;
	}
	
	/** Metodo que ira retornar o nome do projeto.
	 * 
	 * @return String - nome do projeto.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/** Metodo que ira retornar o objetivo do projeto.
	 * 
	 * @return String - objetivo do projeto.
	 */
	public String getObjetivo() {
		return this.objetivo;
	}
	
	/** Metodo que ira retornar a data de inicio do projeto.
	 * 
	 * @return String - data de inicio do projeto.
	 */
	public String getDataInicio() {
		return this.dataInicio.toString();
	}
	
	/** Metodo que ira retornar a duracao do projeto.
	 * 
	 * @return int - duracao do projeto.
	 */
	public int getDuracao() {
		return this.duracao;
	}
	
	public void setNome(String nome) throws ValidacaoException {
		validaNomeProjeto(nome);
		this.nome = nome;
	}
	
	public void setObjetivo(String objetivo) throws ValidacaoException {
		validaObjetivoUpdate(objetivo);
		this.objetivo = objetivo;
	}
	
	public void setDataInicio(String data) throws ValidacaoException {
		try {
			this.dataInicio = new Date(data);
		}
		catch(ValidacaoException e) {
			throw new ValidacaoException("Erro na atualizacao de projeto: Formato de data invalido");
		}
		
	}

	public void setDuracao(int duracao) throws ValidacaoException {
		validaInt(duracao);
		this.duracao = duracao;
	}
	
	public void removeParticipacao(String cpf) {
		for(Participacao p:participacoes) {
			if(p.getPessoa().getCpf().equals(cpf)) {
				participacoes.remove(p);
			}
		}
	}
	
	public ArrayList<Participacao> getParticipacoes() {
		return this.participacoes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Projeto other = (Projeto) obj;
		if (codigo != other.codigo)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void addicionaParticipacao(Participacao participacao) {
		this.participacoes.add(participacao);
	}
}